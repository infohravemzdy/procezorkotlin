package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ContractCode
import java.time.LocalDate

interface IContractTerm {
    val contract: ContractCode
    val dateFrom: LocalDate?
    val dateStop: LocalDate?
    val termDayFrom: Byte
    val termDayStop: Byte
    fun isActive(): Boolean
}