package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ICodeValue

data class PositionCode(override val value: Int) : ICodeValue<Int>, Comparable<PositionCode>  {
    companion object {
        fun new(): PositionCode {return PositionCode(0) }
        fun get(value: Int): PositionCode {return PositionCode(value) }
    }

    override fun compareTo(other: PositionCode): Int {
        if (this.value == other.value) return 0
        return this.value.compareTo(other.value)
    }
}
