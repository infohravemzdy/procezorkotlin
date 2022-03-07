package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ConceptCode

interface ITermTarget : ITermSymbol {
    val concept: ConceptCode
    fun conceptDescr(): String
}

typealias ITermTargetList = Iterable<ITermTarget>

