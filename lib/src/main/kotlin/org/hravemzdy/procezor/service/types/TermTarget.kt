package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ITermTarget

open class TermTarget : TermSymbol, ITermTarget {
    override var concept: ConceptCode = ConceptCode.new()

    constructor(_month: MonthCode, _contract: ContractCode, _position: PositionCode, _variant: VariantCode,
                _article: ArticleCode, _concept: ConceptCode) : super(_month, _contract, _position, _variant, _article) {
        this.concept = _concept
    }
    override fun conceptDescr(): String {
        return "ConceptCode for ${concept.value}"
    }
}