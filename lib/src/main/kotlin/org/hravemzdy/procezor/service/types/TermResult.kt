package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ITermResult
import org.hravemzdy.procezor.interfaces.ITermTarget

open class TermResult : TermSymbol, ITermResult {
    override var target: ITermTarget? = null
        protected set
    override var concept: ConceptCode = ConceptCode.new()
        protected set
    override var spec: IArticleSpec? = null
        protected set


    constructor(_target: ITermTarget?, _spec: IArticleSpec?) : super() {
        this.target = _target
        this.spec = _spec

        if (this.target != null) {
            this.monthCode = target!!.monthCode
            this.contract = target!!.contract
            this.position = target!!.position
            this.variant = target!!.variant
            this.article = target!!.article
            this.concept = target!!.concept
        }
    }

    override fun conceptDescr(): String {
        return "ConceptCode for ${concept.value}"
    }
}