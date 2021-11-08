package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.*

interface ITermResultError {
    val contract: ContractCode
    val position: PositionCode
    val article: ArticleCode
    val concept: ConceptCode
    val variant: VariantCode

    fun description(): String
    fun article(): ArticleCode
    fun concept(): ConceptCode
    fun articleDescr(): String
    fun conceptDescr(): String
}