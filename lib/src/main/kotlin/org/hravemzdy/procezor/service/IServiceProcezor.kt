package org.hravemzdy.procezor.service

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.*
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.BuilderResultList
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.VersionCode

interface IServiceProcezor {
    val version: VersionCode
    val finDefs: IArticleDefine

    fun getResults(period: IPeriod, ruleset: IBundleProps, targets: ITermTargetList): BuilderResultList
    fun initWithPeriod(period: IPeriod): Boolean
    fun buildFactories(): Boolean
    fun getArticleSpec(code: ArticleCode, period: IPeriod, version: VersionCode): IArticleSpec?
    fun getConceptSpec(code: ConceptCode, period: IPeriod, version: VersionCode): IConceptSpec?
}
