package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.*
import org.hravemzdy.procezor.service.types.*

abstract class ConceptSpec(override val code: ConceptCode, override val path: Iterable<ArticleCode>,
                           override val resultDelegate: ResultFunc?) : IConceptSpec, Comparable<ConceptSpec> {
    constructor(_code: Int) : this(ConceptCode.get(_code), emptyList<ArticleCode>(), null) {
    }
    fun getMonthCode(period: IPeriod) : MonthCode {
        return MonthCode.get(period.code)
    }

    override fun compareTo(other: ConceptSpec): Int {
        if (this.code == other.code) return 0
        return this.code.compareTo(other.code)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConceptSpec

        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        val result = code.hashCode()
        return result
    }
    override fun defaultTargetList(article: ArticleCode, period: IPeriod, ruleset: IBundleProps, month: MonthCode,
                                   contractTerms: Iterable<IContractTerm>, positionTerms: Iterable<IPositionTerm>,
                                   targets: ITermTargetList, vars: VariantCode) : ITermTargetList {
        val con = ContractCode.zero()
        val pos = PositionCode.zero()

        if (targets.count()!=0) {
            return listOf<ITermTarget>()
        }
        return listOf<ITermTarget>(TermTarget(month, con, pos, vars, article, code))
    }

    companion object {
        fun constToPathArray(path: Iterable<Int>): Iterable<ArticleCode> {
            return path.map { x -> ArticleCode.get(x) }
        }
    }
}

