package org.hravemzdy.procezor.registry.providers

import com.google.common.base.Equivalence
import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ArticleDefine
import org.hravemzdy.procezor.service.types.ConceptCode

open class ArticleSpec(override val code: ArticleCode, override val role: ConceptCode, override var sums: Iterable<ArticleCode>) : IArticleSpec, Comparable<ArticleSpec> {
    override fun defs(): IArticleDefine {
        return ArticleDefine(code.value, role.value)
    }

    constructor(_code: Int,  _role: Int, _sums: Iterable<Int>):
            this(ArticleCode.get(_code), ConceptCode.get(_role), constToSumsArray(_sums))

    override fun compareTo(other: ArticleSpec): Int {
        if (code == other.code) return 0
        return code.compareTo(other.code);
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
