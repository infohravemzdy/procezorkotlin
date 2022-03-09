package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ConceptCode

interface ITermResult : ITermSymbol {
    val target: ITermTarget?
    val concept: ConceptCode
    val spec : IArticleSpec?
    fun conceptDescr():String
}