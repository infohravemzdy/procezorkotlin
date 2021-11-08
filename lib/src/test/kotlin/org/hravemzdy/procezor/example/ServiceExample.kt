package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.service.ServiceProcezor
import org.hravemzdy.procezor.service.types.ArticleDefine
import org.hravemzdy.procezor.service.types.VersionCode

class ServiceExample : ServiceProcezor {
    companion object {
        private val TEST_VERSION: VersionCode = VersionCode.get(100)
        private val TEST_FINAL_ARTICLE = ExampleArticleConst.ARTICLE_INCOME_NETTO
        private val TEST_FINAL_CONCEPT = ExampleConceptConst.CONCEPT_INCOME_NETTO

        private val TEST_FINAL_DEFS: IArticleDefine = ArticleDefine(TEST_FINAL_ARTICLE.code, TEST_FINAL_CONCEPT.code)
    }
    constructor() : super(TEST_VERSION, TEST_FINAL_DEFS) {

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