package org.hravemzdy.procezor.registry

import com.github.michaelbull.result.Err
import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.legalios.service.types.Period
import org.hravemzdy.procezor.interfaces.*
import org.hravemzdy.procezor.registry.factories.IArticleSpecFactory
import org.hravemzdy.procezor.registry.factories.IConceptSpecFactory
import org.hravemzdy.procezor.service.errors.TermResultError
import org.hravemzdy.procezor.service.types.*

class ResultBuilder : IResultBuilder {
    override var version: VersionCode = VersionCode.new()
        private set
    override var periodInit: IPeriod = Period.new()
        private set

    override var articleOrder: Iterable<ArticleTerm> = arrayListOf()
        private set
    override var articlePaths: Map<ArticleTerm, Iterable<IArticleDefine>> = mapOf()
        private set

    private var articleModel: Iterable<IArticleSpec> = listOf()
    private var conceptModel: Iterable<IConceptSpec> = listOf()

    override fun initWithPeriod(
        version: VersionCode,
        period: IPeriod,
        articleFactory: IArticleSpecFactory,
        conceptFactory: IConceptSpecFactory
    ): Boolean {
        this.version = version
        this.periodInit = period

        articleModel = articleFactory.getSpecList(period, version)

        conceptModel = conceptFactory.getSpecList(period, version)

        var dependencyGraph = DependencyGraph()

        val (order, paths) = dependencyGraph.initGraphModel(articleModel, conceptModel)

        articleOrder = order
        articlePaths = paths

        return true
    }

    override fun getResults(ruleset: IBundleProps,
                            contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                            targets: ITermTargetList, calcArticles: Iterable<ArticleCode>): BuilderResultList {
        val calculTargets = buildCalculsList(periodInit, ruleset,
        contractTerms, positionTerms, targets, calcArticles)

        val calculResults = buildResultsList(periodInit, ruleset, calculTargets)

        return calculResults
    }

    private fun buildCalculsList(
        period: IPeriod, ruleset: IBundleProps,
        contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
        targets: ITermTargetList,
        calcArticles: Iterable<ArticleCode>
    ): Iterable<ITermCalcul> {
        val specDefines: Iterable<IArticleSpec?> = calcArticles.map { a -> articleModel.firstOrNull { m -> m.code == a } }

        val calcDefines = specDefines.filter { s -> (s != null) }.map { t -> t!! }
            .map { x -> ArticleDefine(x.code.value, x.seqs.value, x.role.value) }

        val targetsSpec: ITermTargetList = addFinDefToTargets(period, ruleset,
            contractTerms, positionTerms, targets.toList(), calcDefines)

        val targetsStep: ITermTargetList = addExternToTargets(period, ruleset,
            contractTerms, positionTerms, targetsSpec)

        val calculsList: Iterable<ITermCalcul> = addTargetToCalculs(targetsStep)

        return calculsList
    }
    private fun buildResultsList(period: IPeriod, ruleset: IBundleProps, calculs: Iterable<ITermCalcul>): BuilderResultList {
        var resultsInit: BuilderResultList = listOf()

        return calculs.fold(resultsInit) {agr, x -> mergeResults(agr, *x.getResults(period, ruleset, agr).map { it }.toTypedArray()) }
    }
    private fun mergeResults(results: BuilderResultList, vararg resultValues: BuilderResult): BuilderResultList {
        return results.plus(resultValues)
    }
    private fun addFinDefToTargets(period: IPeriod, ruleset: IBundleProps,
                                   contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                                   targets: ITermTargetList, calcDefines: Iterable<IArticleDefine>): ITermTargetList {
        return mergeListPendings(period, ruleset, contractTerms, positionTerms, targets, calcDefines)
    }
    private fun addExternToTargets(period: IPeriod, ruleset: IBundleProps,
                                   contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                                   targets: ITermTargetList): ITermTargetList {
        var targetsInit: ITermTargetList = targets.toList()

        var targetList = targets.fold(targetsInit) {agr, item ->
            mergePendings(period, ruleset, contractTerms, positionTerms, agr, item)}.toList()

        var targetSort = targetList.sortedWith(TargetComparator(articleOrder.toList())).toList()

        return targetSort
    }

    private fun addDefinesToTargets(period: IPeriod, ruleset: IBundleProps,
                                    contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                                    targets: ITermTargetList, defines: Iterable<IArticleDefine>) : ITermTargetList {
        return defines.flatMap { x ->
                getTargetList(period, ruleset, conceptModel, contractTerms, positionTerms,
                    targets.filter {t -> t.article == x.code}, x.code, x.role).map { it }
        }
    }
    private fun addTargetToCalculs(targets: ITermTargetList): Iterable<ITermCalcul> {
        val targetsRets = targets.map {
            val articleSpec = articleModel.first { a -> (a.code == it.article) }
            TermCalcul(it, articleSpec, getCalculFunc(conceptModel, it.concept)) }
        return targetsRets.toList()
    }
    private fun mergePendings(period: IPeriod, ruleset: IBundleProps,
                              contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                              init: ITermTargetList, target: ITermTarget): ITermTargetList {
        var resultList: ITermTargetList = init.toList()

        val pendingsSpec = articlePaths.firstNotNullOfOrNull { x -> x.takeIf { p -> (p.key.code == target.article) } }
        val pendingsPath = pendingsSpec?.value

        if (pendingsPath != null)
        {
            resultList = pendingsPath.fold(resultList) {agr, def -> mergeItemPendings(
                period, ruleset, contractTerms, positionTerms, agr, def).toList()}
        }
        return resultList
    }
    private fun mergeItemPendings(period: IPeriod, ruleset: IBundleProps,
                                  contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                                  init: ITermTargetList, articleDefs: IArticleDefine): ITermTargetList {
        var resultList: ITermTargetList = init.toList()

        var initTargets = init.filter {x -> x.article == articleDefs.code}

        val targetList = getTargetList(period, ruleset, conceptModel,
            contractTerms, positionTerms, initTargets, articleDefs.code, articleDefs.role)

        resultList = resultList.plus(targetList).toList()

        return resultList
    }
    private fun mergeListPendings(period: IPeriod, ruleset: IBundleProps,
                                  contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                                  targets: ITermTargetList, calcDefines: Iterable<IArticleDefine>): ITermTargetList {
        var resultList: ITermTargetList = targets.toList()

        val defineList = calcDefines.filter {x -> targets.firstOrNull { t -> t.article == x.code} == null }

        val targetList = addDefinesToTargets(period, ruleset, contractTerms, positionTerms, targets, defineList)

        resultList = resultList.plus(targetList).toList()

        return resultList
    }
    private fun getCalculFunc(conceptsModel: Iterable<IConceptSpec>, concept: ConceptCode): ResultFunc? {
        var conceptSpec = conceptsModel.firstOrNull { a -> (a.code == concept) }

        if (conceptSpec == null) {
            return fun(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
                return notFoundCalculFunc(target, spec, period, ruleset, results)
            }
        }
        return conceptSpec.resultDelegate
    }
    private fun getTargetList(period: IPeriod, ruleset: IBundleProps, conceptsModel: Iterable<IConceptSpec>,
                              contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                              targets: ITermTargetList, article: ArticleCode, concept: ConceptCode) : ITermTargetList {
        val monthCode = MonthCode.get(period.code)
        val variant = VariantCode.get(1)

        val conceptSpec = conceptsModel.firstOrNull {a -> a.code.value == concept.value }
        if (conceptSpec == null) {
            val contract = ContractCode.new()
            val position = PositionCode.new()
            return listOf(TermTarget(monthCode, contract, position, variant, article, concept))
        }
        return conceptSpec.defaultTargetList(article, period, ruleset, monthCode,
            contractTerms, positionTerms, targets, variant)
    }

    private fun notFoundCalculFunc(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList
    {
        val resultError = TermResultError.NoResultFuncError(period, target)
        return listOf(Err<ITermResultError>(resultError))
    }
    private class TargetComparator(private val topoOrders: Iterable<ArticleTerm>) : Comparator<ITermTarget> {
        private val codeOrders: Iterable<ArticleCode> = topoOrders.map { t -> t.code }
        override fun compare(x: ITermTarget, y: ITermTarget): Int {
            var xIndex = codeOrders.indexOf(x.article)

            var yIndex = codeOrders.indexOf(y.article)

            if (xIndex == -1 && yIndex == -1)
            {
                return 0
            }

            if (xIndex == -1 && yIndex != -1)
            {
                return -1
            }

            if (xIndex != -1 && yIndex == -1)
            {
                return 1
            }

            return xIndex.compareTo(yIndex)
        }
    }
}