package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ISpecCode

data class ArticleCode(override val value: Int) : ISpecCode, Comparable<ArticleCode> {
    companion object {
        fun zero(): ArticleCode {return new() }
        fun new(): ArticleCode {return ArticleCode(0) }
        fun get(value: Int): ArticleCode {return ArticleCode(value) }
    }

    override fun compareTo(other: ArticleCode): Int {
        if (this.value == other.value) return 0
        return this.value.compareTo(other.value)
    }
}
