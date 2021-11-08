package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.ITermTarget

open class TermTarget : TermSymbol, ITermTarget {
    override var concept: ConceptCode = ConceptCode.new()
    override var targetBasis: Int = 0
    override var targetDescr: String = ""

    constructor(_month: MonthCode, _contract: ContractCode, _position: PositionCode, _variant: VariantCode, _article: ArticleCode,
                _concept: ConceptCode, _basis: Int, _descr: String) : super(_month, _contract, _position, _variant, _article) {
        this.concept = _concept
        this.targetBasis = _basis
        this.targetDescr = _descr
    }
    constructor(_month: MonthCode, _contract: ContractCode, _position: PositionCode, _variant: VariantCode, _article: ArticleCode, _concept: ConceptCode)
            : this(_month, _contract, _position, _variant, _article, _concept, 0, "")

    override val defs: IArticleDefine
        get() = ArticleDefine(this.article, this.concept)

    override fun conceptDescr(): String {
        return "ConceptCode for ${concept.value}"
    }
}