package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ISpecCode

data class ConceptCode(override val value: Int) : ISpecCode, Comparable<ConceptCode>  {
    companion object {
        fun new(): ConceptCode {return ConceptCode(0) }
        fun get(value: Int): ConceptCode {return ConceptCode(value) }
    }

    override fun compareTo(other: ConceptCode): Int {
        if (this.value == other.value) return 0
        return this.value.compareTo(other.value)
    }
}
