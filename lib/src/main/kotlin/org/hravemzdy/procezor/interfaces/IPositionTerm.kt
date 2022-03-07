package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ContractCode
import org.hravemzdy.procezor.service.types.PositionCode
import java.time.LocalDate

interface IPositionTerm {
    val position: PositionCode
    val contract: ContractCode
    val baseTerm: IContractTerm?
    val dateFrom: LocalDate?
    val dateStop: LocalDate?
    val termDayFrom: Byte
    val termDayStop: Byte
    fun isActive(): Boolean
}