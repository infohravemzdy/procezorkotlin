package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.*
import org.hravemzdy.procezor.registry.factories.IArticleSpecFactory
import org.hravemzdy.procezor.registry.factories.IConceptSpecFactory
import org.hravemzdy.procezor.service.types.*

interface IResultBuilder {
    val version: VersionCode
    val periodInit: IPeriod
    fun initWithPeriod(version: VersionCode, period: IPeriod, articleFactory: IArticleSpecFactory, conceptFactory: IConceptSpecFactory): Boolean
    fun getResults(ruleset: IBundleProps,
                   contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                   targets: ITermTargetList, calcArticles: Iterable<ArticleCode>): BuilderResultList
    val articleOrder: Iterable<ArticleTerm>
    val articlePaths: Map<ArticleTerm, Iterable<IArticleDefine>>
}