package org.hravemzdy.procezor.service

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.*
import org.hravemzdy.procezor.registry.IResultBuilder
import org.hravemzdy.procezor.registry.ResultBuilder
import org.hravemzdy.procezor.registry.factories.IArticleSpecFactory
import org.hravemzdy.procezor.registry.factories.IConceptSpecFactory
import org.hravemzdy.procezor.service.types.*

abstract class ServiceProcezor : IServiceProcezor {
    final override val version: VersionCode
    final override val calcArticles: Iterable<ArticleCode>
    private val builder: IResultBuilder = ResultBuilder()
    protected var articleFactory: IArticleSpecFactory? = null
    protected var conceptFactory: IConceptSpecFactory? = null

    constructor(_version: VersionCode,  _calcArticles: Iterable<ArticleCode>) {
        this.version = _version
        this.calcArticles = _calcArticles.toList()
    }
    override fun builderOrder() : Iterable<ArticleTerm> {
        return builder.articleOrder
    }
    override fun builderPaths() : Map<ArticleTerm, Iterable<IArticleDefine>> {
        return builder.articlePaths
    }

    override fun getContractTerms(period: IPeriod, targets: ITermTargetList) : Iterable<ContractTerm> {
        return emptyList()
    }

    override fun getPositionTerms(period: IPeriod, contracts: Iterable<ContractTerm>, targets: ITermTargetList) : Iterable<PositionTerm> {
        return emptyList()
    }

    override fun getResults(period: IPeriod, ruleset: IBundleProps, targets: ITermTargetList): BuilderResultList {
        var results: BuilderResultList = emptyList<BuilderResult>()

        val success: Boolean = initWithPeriod(period)

        if (!success) {
            return results
        }
        val contractTerms = getContractTerms(period, targets)
        val positionTerms = getPositionTerms(period, contractTerms, targets)

        if (builder != null) {
            results = builder.getResults(ruleset,
                contractTerms, positionTerms, targets, calcArticles)
        }
        return results
    }
    override fun initWithPeriod(period: IPeriod): Boolean {
        var initResult: Boolean = false

        if (builder != null) {
            initResult = true
        }

        var initBuilder: Boolean = false

        if (builder != null) {
            initBuilder = builder.periodInit != period
        }

        if (initBuilder && articleFactory != null && conceptFactory != null) {
            initResult = builder.initWithPeriod(version, period, articleFactory!!, conceptFactory!!)
        }

        if (initResult == false) {
            println("Period: ${period.code}, init with period failed")
        }
        return initResult
    }
    override fun buildFactories(): Boolean {
        val articleFactorySuccess: Boolean = buildArticleFactory()

        val conceptFactorySuccess: Boolean = buildConceptFactory()

        if (!(articleFactorySuccess && conceptFactorySuccess)) {
            println("ServiceProcezor::BuildFactories(): Version: ${version}, build factories failed")
        }
        return articleFactorySuccess && conceptFactorySuccess
    }
    override fun getArticleSpec(code: ArticleCode, period: IPeriod, version: VersionCode): IArticleSpec? {
        if  (articleFactory == null) {
            return null
        }
        return articleFactory!!.getSpec(code, period, version)
    }
    override fun getConceptSpec(code: ConceptCode, period: IPeriod, version: VersionCode): IConceptSpec? {
        if  (conceptFactory == null) {
            return null
        }
        return conceptFactory!!.getSpec(code, period, version)
    }

    abstract fun buildArticleFactory(): Boolean

    abstract fun buildConceptFactory(): Boolean
}