package org.hravemzdy.procezor.service.types

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.ICodeValue

data class MonthCode(override val value: Int) : ICodeValue<Int>, Comparable<MonthCode>  {
    companion object {
        fun new(): MonthCode {return MonthCode(0) }
        fun get(value: Int): MonthCode {return MonthCode(value) }
        fun getWithPeriod(period: IPeriod): MonthCode {return MonthCode(period.code) }
    }

    override fun compareTo(other: MonthCode): Int {
        if (this.value == other.value) return 0
        return this.value.compareTo(other.value)
    }
}
