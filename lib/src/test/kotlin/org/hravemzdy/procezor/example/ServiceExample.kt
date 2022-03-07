package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.service.ServiceProcezor
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.VersionCode

class ServiceExample : ServiceProcezor {
    companion object {
        private val TEST_VERSION: VersionCode = VersionCode.get(100)
        private val TEST_FINAL_ARTICLE = ExampleArticleConst.ARTICLE_INCOME_NETTO

        private val TEST_CALCS_ARTICLE: Iterable<ArticleCode> = listOf(ArticleCode.get(TEST_FINAL_ARTICLE.code))
    }
    constructor() : super(TEST_VERSION, TEST_CALCS_ARTICLE) {

    }

    override fun buildArticleFactory(): Boolean {
        this.articleFactory = ExampleArticleFactory()

        return true
    }

    override fun buildConceptFactory(): Boolean {
        this.conceptFactory = ExampleConceptFactory()

        return true
    }
}