package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.registry.providers.ArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpecProvider
import org.hravemzdy.procezor.service.types.VersionCode

class ArticleSpecConfig : ArticleSpec {
     constructor(code: Int, role: Int,  sums: Iterable<Int>) : super(code, role, sums)

    companion object {
        fun specsToIntSums(_codes: Iterable<ISpecCode>) : Iterable<Int> {
            return _codes.map { x -> x.value };
        }
    }
}

class ArticleProviderConfig : ArticleSpecProvider {
    private var articleSpec: ArticleSpecConfig
    constructor(article: ISpecCode, concept: ISpecCode, sums: Iterable<ISpecCode>) : super(article.value) {
        articleSpec = ArticleSpecConfig(article.value, concept.value, ArticleSpecConfig.specsToIntSums(sums))
    }
    constructor(article: Int, concept: Int, sums: Iterable<Int>) : super(article) {
        articleSpec = ArticleSpecConfig(article, concept, sums.toList())
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return articleSpec
    }
}
