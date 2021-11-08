package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ITermSymbol

open class TermSymbol : ITermSymbol {
    override var monthCode: MonthCode
        protected set
    override var contract: ContractCode
        protected set
    override var position: PositionCode
        protected set
    override var variant: VariantCode
        protected set
    override var article: ArticleCode
        protected set

    constructor(_month: MonthCode, _contract: ContractCode, _position: PositionCode, _variant: VariantCode, _article: ArticleCode) {
        this.monthCode = _month
        this.contract = _contract
        this.position = _position
        this.variant = _variant
        this.article = _article
    }
    constructor() : this(MonthCode.new(), ContractCode.new(), PositionCode.new(), VariantCode.new(), ArticleCode.new())

    override fun articleDescr(): String {
        return "ArticleCode for ${article.value}"
    }
}