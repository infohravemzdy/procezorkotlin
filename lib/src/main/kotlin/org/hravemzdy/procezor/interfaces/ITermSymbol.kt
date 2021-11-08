package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.*

interface ITermSymbol {
    val monthCode: MonthCode
    val contract: ContractCode
    val position: PositionCode
    val variant: VariantCode
    val article: ArticleCode
    fun articleDescr():String
}