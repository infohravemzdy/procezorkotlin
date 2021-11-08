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

    override var articleOrder: Iterable<ArticleCode> = arrayListOf()
        private set
    override var articlePaths: Map<ArticleCode, Iterable<IArticleDefine>> = mapOf()
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

    override fun getResults(ruleset: IBundleProps, targets: Iterable<ITermTarget>, finDefs: IArticleDefine): BuilderResultList {
        val calculTargets = buildCalculsList(periodInit, targets, finDefs)

        val calculResults = buildResultsList(periodInit, ruleset, calculTargets)

        return calculResults
    }

    private fun buildCalculsList(
        period: IPeriod,
        targets: Iterable<ITermTarget>,
        finDefs: IArticleDefine
    ): Iterable<ITermCalcul> {
        val finDefine: IArticleDefine = ArticleDefine(finDefs)

        val targetsSpec: Iterable<ITermTarget> = addFinDefToTargets(period, targets.toList(), finDefine)

        val targetsStep: Iterable<ITermTarget> = addExternToTargets(period, targetsSpec)

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
    private fun addFinDefToTargets(period: IPeriod, targets: Iterable<ITermTarget>, finDefs: IArticleDefine): Iterable<ITermTarget> {
        return mergeItemPendings(period, targets, finDefs)
    }
    private fun addExternToTargets(period: IPeriod, targets: Iterable<ITermTarget>): Iterable<ITermTarget> {
        var targetsInit: Iterable<ITermTarget> = targets.toList()

        var targetList = targets.fold(targetsInit) {agr, item -> mergePendings(period, agr, item)}.toList()

        var targetSort = targetList.sortedWith(TargetComparator(articleOrder.toList())).toList()

        return targetSort
    }

    private fun addTargetToCalculs(targets: Iterable<ITermTarget>): Iterable<ITermCalcul> {
        val targetsRets = targets.map {TermCalcul(it, getCalculFunc(conceptModel, it.concept)) }
        return targetsRets.toList()
    }
    private fun mergePendings(period: IPeriod, init: Iterable<ITermTarget>, target: ITermTarget): Iterable<ITermTarget> {
        var resultList: Iterable<ITermTarget> = init.toList()

        val pendingsSpec = articlePaths.firstNotNullOfOrNull { x -> x.takeIf { p -> (p.key == target.article) } }
        val pendingsPath = pendingsSpec?.value

        if (pendingsPath != null)
        {
            resultList = pendingsPath.fold(resultList) {agr, def -> mergeItemPendings(period, agr, def).toList()}
        }
        return resultList
    }
    private fun mergeItemPendings(period: IPeriod, init: Iterable<ITermTarget>, articleDefs: IArticleDefine): Iterable<ITermTarget> {
        val monthCode = MonthCode.get(period.code)

        val contract = ContractCode.new()
        val position = PositionCode.new()

        var resultList: Iterable<ITermTarget> = init.toList()

        var initTarget = init.firstOrNull {x -> x.article == articleDefs.code}

        if (initTarget == null)
        {
            val variant = VariantCode.get(1)

            val resultItem = TermTarget(monthCode, contract, position, variant, articleDefs.code, articleDefs.role)

            resultList = resultList.plus(resultItem).toList()
        }

        return resultList
    }
    private fun getCalculFunc(conceptsModel: Iterable<IConceptSpec>, concept: ConceptCode): ResultFunc? {
        var conceptSpec = conceptsModel.firstOrNull { a -> (a.code == concept) }

        if (conceptSpec == null)
        {
            return fun(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
                return notFoundCalculFunc(target, period, ruleset, results)
            }
        }
        return conceptSpec.resultDelegate
    }
    private fun notFoundCalculFunc(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList
    {
        val resultError = TermResultError.NoResultFuncError(period, target)
        return listOf(Err<ITermResultError>(resultError))
    }
    private class TargetComparator(private val topoOrders: Iterable<ArticleCode>) : Comparator<ITermTarget> {
        override fun compare(x: ITermTarget, y: ITermTarget): Int {
            var xIndex = topoOrders.indexOf(x.article)

            var yIndex = topoOrders.indexOf(y.article)

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