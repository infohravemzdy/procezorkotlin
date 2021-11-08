package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ICodeValue

data class VariantCode(override val value: Int) : ICodeValue<Int>, Comparable<VariantCode>  {
    companion object {
        fun new(): VariantCode {return VariantCode(0) }
        fun get(value: Int): VariantCode {return VariantCode(value) }
    }

    override fun compareTo(other: VariantCode): Int {
        if (this.value == other.value) return 0
        return this.value.compareTo(other.value)
    }
}
