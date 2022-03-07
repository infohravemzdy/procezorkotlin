package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.service.types.*

open class ArticleSpec(override val code: ArticleCode, override val seqs: ArticleSeqs, override val role: ConceptCode, override var sums: Iterable<ArticleCode>) : IArticleSpec, Comparable<ArticleSpec> {
    override fun term(): ArticleTerm {
        return ArticleTerm(code, seqs)
    }
    override fun defs(): ArticleDefine {
        return ArticleDefine(code.value, seqs.value, role.value)
    }

    constructor(_code: Int, _seqs: Int, _role: Int, _sums: Iterable<Int>):
            this(ArticleCode.get(_code), ArticleSeqs.get(_seqs), ConceptCode.get(_role), constToSumsArray(_sums))

    override fun compareTo(other: ArticleSpec): Int {
        if (seqs == other.seqs) return code.compareTo(other.code)
        return seqs.compareTo(other.seqs)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleSpec

        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        return result
    }
    companion object {
        fun constToSumsArray(sums: Iterable<Int>): Iterable<ArticleCode> {
            return sums.map { x -> ArticleCode.get(x) }
        }

        fun specsToSumsArray(sums: Iterable<ISpecCode>): Iterable<ArticleCode> {
            return sums.map { x -> ArticleCode.get(x.value) }
        }
    }
}
