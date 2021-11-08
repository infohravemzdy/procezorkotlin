package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ConceptCode

interface ITermTarget : ITermSymbol {
    val concept: ConceptCode
    val targetBasis: Int
    val targetDescr: String
    val defs: IArticleDefine
    fun conceptDescr(): String
}

typealias ITermTargetList = Iterable<ITermTarget>

