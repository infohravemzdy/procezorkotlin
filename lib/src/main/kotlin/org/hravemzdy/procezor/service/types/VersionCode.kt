package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ICodeValue

data class VersionCode(override val value: Int) : ICodeValue<Int>, Comparable<VersionCode>  {
    companion object {
        fun zero(): VersionCode {return new() }
        fun new(): VersionCode {return VersionCode(0) }
        fun get(value: Int): VersionCode {return VersionCode(value) }
    }

    override fun compareTo(other: VersionCode): Int {
        if (this.value == other.value) return 0
        return this.value.compareTo(other.value)
    }
}