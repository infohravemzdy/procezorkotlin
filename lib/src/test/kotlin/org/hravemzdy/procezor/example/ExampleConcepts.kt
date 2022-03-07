package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.interfaces.ResultFunc
import org.hravemzdy.procezor.registry.providers.ConceptSpec
import org.hravemzdy.procezor.service.types.*


open class ExampleConceptSpec(concept: ConceptCode, path: Iterable<ArticleCode>, result: ResultFunc?) : ConceptSpec(concept, path, result) {
    constructor(code: Int) : this(ConceptCode.get(code), emptyList<ArticleCode>(), null) {
    }
}


open class ExampleTermTarget(_month: MonthCode, _contract: ContractCode, _position: PositionCode,
    _variant: VariantCode, _article: ArticleCode, _concept: ConceptCode)
    : TermTarget(_month, _contract, _position, _variant, _article, _concept) {

    override fun articleDescr(): String {
        return getArticleSymbol(article.value)
    }
    override fun conceptDescr(): String {
        return getConceptSymbol(concept.value)
    }
}

open class ExampleTermResult(target: ITermTarget, spec: IArticleSpec?) : TermResult(target, spec) {
    override fun articleDescr(): String {
        return getArticleSymbol(article.value)
    }
    override fun conceptDescr(): String {
        return getConceptSymbol(concept.value)
    }
}

