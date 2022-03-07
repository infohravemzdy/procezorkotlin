package org.hravemzdy.procezor.service

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.*
import org.hravemzdy.procezor.service.types.*

interface IServiceProcezor {
    val version: VersionCode
    val calcArticles: Iterable<ArticleCode>

    fun builderOrder() : Iterable<ArticleTerm>
    fun builderPaths() : Map<ArticleTerm, Iterable<IArticleDefine>>

    fun getContractTerms(period: IPeriod, targets: ITermTargetList) : Iterable<ContractTerm>
    fun getPositionTerms(period: IPeriod, contracts: Iterable<ContractTerm>, targets: ITermTargetList) : Iterable<PositionTerm>

    fun getResults(period: IPeriod, ruleset: IBundleProps, targets: ITermTargetList): BuilderResultList
    fun initWithPeriod(period: IPeriod): Boolean
    fun buildFactories(): Boolean
    fun getArticleSpec(code: ArticleCode, period: IPeriod, version: VersionCode): IArticleSpec?
    fun getConceptSpec(code: ConceptCode, period: IPeriod, version: VersionCode): IConceptSpec?
}
