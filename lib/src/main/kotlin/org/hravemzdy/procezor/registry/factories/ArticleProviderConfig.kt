package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.registry.providers.ArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpecProvider
import org.hravemzdy.procezor.service.types.ArticleSeqs
import org.hravemzdy.procezor.service.types.VersionCode

class ArticleSpecConfig(code: Int, seqs: Int, role: Int, sums: Iterable<Int>) : ArticleSpec(code, seqs, role, sums) {
    companion object {
        fun specsToIntSums(_codes: Iterable<ISpecCode>) : Iterable<Int> {
            return _codes.map { x -> x.value };
        }
    }
}

class ArticleProviderConfig(private var articleSpec: ArticleSpecConfig) : ArticleSpecProvider(articleSpec.code) {
    constructor(article: ISpecCode, sequens: ArticleSeqs, concept: ISpecCode, sums: Iterable<ISpecCode>) : this(
        ArticleSpecConfig(article.value, sequens.value, concept.value, ArticleSpecConfig.specsToIntSums(sums))) {
    }
    constructor(article: Int, sequens: Int, concept: Int, sums: Iterable<Int>) : this(
        ArticleSpecConfig(article, sequens, concept, sums.toList())) {
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return articleSpec
    }
}
