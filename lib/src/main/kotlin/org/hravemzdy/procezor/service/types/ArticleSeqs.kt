package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.interfaces.ISpecSeqs

data class ArticleSeqs(override val value: Int) : ISpecSeqs, Comparable<ArticleSeqs> {
    companion object {
        fun zero(): ArticleSeqs {return new() }
        fun new(): ArticleSeqs {return ArticleSeqs(0) }
        fun get(value: Int): ArticleSeqs {return ArticleSeqs(value) }
    }

    override fun compareTo(other: ArticleSeqs): Int {
        if (this.value == other.value) return 0
        return this.value.compareTo(other.value)
    }
}