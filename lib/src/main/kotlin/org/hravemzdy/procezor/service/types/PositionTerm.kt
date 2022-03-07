package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IContractTerm
import org.hravemzdy.procezor.interfaces.IPositionTerm
import org.hravemzdy.procezor.registry.constants.TermConstants
import java.time.LocalDate

class PositionTerm(
    override val position: PositionCode,
    override val contract: ContractCode,
    override val baseTerm: IContractTerm?,
    override val dateFrom: LocalDate?,
    override val dateStop: LocalDate?,
    override val termDayFrom: Byte,
    override val termDayStop: Byte
) : IPositionTerm {
    private fun isPositionActive(): Boolean {
        return (termDayFrom < TermConstants.TERM_BEG_FINISHED
                && termDayStop > TermConstants.TERM_END_FINISHED)
    }
    override fun isActive(): Boolean {
        if (baseTerm != null) {
            return (baseTerm.isActive() && isPositionActive())
        }
        return isPositionActive()
    }
}