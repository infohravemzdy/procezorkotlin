package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.registry.factories.IArticleSpecFactory
import org.hravemzdy.procezor.registry.factories.IConceptSpecFactory
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.BuilderResultList
import org.hravemzdy.procezor.service.types.VersionCode

interface IResultBuilder {
    val version: VersionCode
    val periodInit: IPeriod
    fun initWithPeriod(version: VersionCode, period: IPeriod, articleFactory: IArticleSpecFactory, conceptFactory: IConceptSpecFactory): Boolean
    fun getResults(ruleset: IBundleProps, targets: Iterable<ITermTarget>, finDefs: IArticleDefine): BuilderResultList
    val articleOrder: Iterable<ArticleCode>
    val articlePaths: Map<ArticleCode, Iterable<IArticleDefine>>
}