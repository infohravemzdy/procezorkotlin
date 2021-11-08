package org.hravemzdy.procezor.interfaces

import org.hravemzdy.procezor.service.types.ArticleCode

interface IArticleSpec : IArticleDefine {
    val sums: Iterable<ArticleCode>
    fun defs() : IArticleDefine
}