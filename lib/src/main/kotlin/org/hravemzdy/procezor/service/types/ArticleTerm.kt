package org.hravemzdy.procezor.service.types

data class ArticleTerm(val code: ArticleCode, val seqs: ArticleSeqs) : Comparable<ArticleTerm> {
    companion object {
        fun zero(): ArticleTerm { return new() }
        fun new(): ArticleTerm { return ArticleTerm(ArticleCode.new(), ArticleSeqs.new()) }
        fun get(_code: Int, _seqs: Int): ArticleTerm { return ArticleTerm(ArticleCode.get(_code), ArticleSeqs.get(_seqs)) }
    }

    override fun compareTo(other: ArticleTerm): Int {
        if (this.seqs == other.seqs) {
            return this.code.compareTo(other.code)
        }
        return this.seqs.compareTo(other.seqs)
    }
}
