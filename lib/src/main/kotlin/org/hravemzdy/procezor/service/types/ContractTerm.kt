package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IContractTerm
import org.hravemzdy.procezor.registry.constants.TermConstants
import java.time.LocalDate

class ContractTerm(
    override val contract: ContractCode,
    override val dateFrom: LocalDate?,
    override val dateStop: LocalDate?,
    override val termDayFrom: Byte,
    override val termDayStop: Byte
) : IContractTerm {
    override fun isActive(): Boolean {
        return (termDayFrom < TermConstants.TERM_BEG_FINISHED
                && termDayStop > TermConstants.TERM_END_FINISHED)
    }
}