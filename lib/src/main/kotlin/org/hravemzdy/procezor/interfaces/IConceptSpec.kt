package org.hravemzdy.procezor.interfaces

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.BuilderResultList

typealias ResultFunc = (ITermTarget, IPeriod, IBundleProps, BuilderResultList) -> BuilderResultList

interface IConceptSpec : IConceptDefine {
    val path : Iterable<ArticleCode>
    val resultDelegate : ResultFunc?
}

