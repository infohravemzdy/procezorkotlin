package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ICodeValue

data class ContractCode(override val value: Int) : ICodeValue<Int>, Comparable<ContractCode> {
    companion object {
        fun new(): ContractCode {return ContractCode(0) }
        fun get(value: Int): ContractCode {return ContractCode(value) }
    }

    override fun compareTo(other: ContractCode): Int {
        if (this.value == other.value) return 0
        return this.value.compareTo(other.value)
    }
}
