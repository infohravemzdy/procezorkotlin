package org.hravemzdy.procezor.interfaces

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.BuilderResultList
import org.hravemzdy.procezor.service.types.MonthCode
import org.hravemzdy.procezor.service.types.VariantCode

typealias ResultFunc = (ITermTarget, IArticleSpec?, IPeriod, IBundleProps, BuilderResultList) -> BuilderResultList

interface IConceptSpec : IConceptDefine {
    val path : Iterable<ArticleCode>
    val resultDelegate : ResultFunc?
    fun defaultTargetList(article: ArticleCode, period: IPeriod, ruleset: IBundleProps, month: MonthCode,
                          contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                          targets: ITermTargetList, vars: VariantCode) : ITermTargetList
}

