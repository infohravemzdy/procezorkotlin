package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.registry.constants.ArticleConst
import org.hravemzdy.procezor.registry.constants.ConceptConst
import org.hravemzdy.procezor.registry.providers.ArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpecProvider
import org.hravemzdy.procezor.registry.providers.IArticleSpecProvider
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ArticleSeqs
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.VersionCode


interface IArticleSpecFactory : ISpecFactory<IArticleSpecProvider, IArticleSpec, ArticleCode> {
}

class ProviderRecord(val article: Int, val sequens: Int, val concept: Int, val sums: Iterable<Int>)

class NotFoundArticleSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ArticleSeqs.zero(), ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ConceptConst.CONCEPT_NOTFOUND
    }
}

class NotFoundArticleProvider : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ArticleConst.ARTICLE_NOTFOUND
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return NotFoundArticleSpec(this.code)
    }
}

abstract class ArticleSpecFactory() : SpecFactory<IArticleSpecProvider, IArticleSpec, ArticleCode>(), IArticleSpecFactory {
    override val notFoundProvider = NotFoundArticleProvider()
    override val notFoundSpec = NotFoundArticleSpec(ArticleCode.get(NotFoundArticleProvider.ARTICLE_CODE.code));

    companion object {
        fun buildProvidersFromRecords(records: Iterable<ProviderRecord>): Map<CODE, IArticleSpecProvider> {
            var providers: Map<CODE, IArticleSpecProvider> = records.map { x -> ArticleProviderConfig(x.article, x.sequens, x.concept, x.sums) }
                .associateBy { k -> k.code.value }

            return providers
        }
    }
}
