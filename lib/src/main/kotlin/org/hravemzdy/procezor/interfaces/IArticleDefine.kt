package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ArticleSeqs
import org.hravemzdy.procezor.service.types.ArticleTerm
import org.hravemzdy.procezor.service.types.ConceptCode

interface IArticleDefine : ISpecDefine<ArticleCode> {
    val role: ConceptCode
    val seqs: ArticleSeqs
    fun term() : ArticleTerm
}