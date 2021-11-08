package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.ITermResult
import org.hravemzdy.procezor.interfaces.ITermTarget

open class TermResult : TermSymbol, ITermResult {
    override var target: ITermTarget? = null
        protected set
    override var concept: ConceptCode = ConceptCode.new()
        protected set
    override var resultValue: Int = 0
        protected set
    override var resultBasis: Int = 0
        protected set
    override var resultDescr: String = ""
        protected set


    constructor(_target: ITermTarget?, _value: Int, _basis: Int, _descr: String) : super() {
        this.target = _target

        if (this.target != null) {
            this.monthCode = target!!.monthCode
            this.contract = target!!.contract
            this.position = target!!.position
            this.variant = target!!.variant
            this.article = target!!.article
            this.concept = target!!.concept
        }

        this.resultValue = _value
        this.resultBasis = _basis
        this.resultDescr = _descr
    }

    override fun conceptDescr(): String {
        return "ConceptCode for ${concept.value}"
    }
}