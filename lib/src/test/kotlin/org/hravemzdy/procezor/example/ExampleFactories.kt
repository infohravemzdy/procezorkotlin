package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.registry.factories.*
import org.hravemzdy.procezor.registry.providers.IArticleSpecProvider
import org.hravemzdy.procezor.registry.providers.IConceptSpecProvider

class ExampleArticleFactory() : ArticleSpecFactory() {
    private val ARTICLE_DEFAULT_SEQUENS: Int = 0

    private val providersConfig = arrayOf<ProviderRecord>(
        ProviderRecord(ExampleArticleConst.ARTICLE_TIMESHT_WORKING.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_TIMESHT_WORKING.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_SALARY.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_AMOUNT_BASIS.code,
            arrayOf<Int>(
                ExampleArticleConst.ARTICLE_INCOME_GROSS.code,
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
            ).toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_BONUS.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.code,
            arrayOf<Int>(
                ExampleArticleConst.ARTICLE_INCOME_GROSS.code,
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
            ).toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_BARTER.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.code,
            arrayOf<Int>(
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
            ).toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_ALLOWCE_HOFFICE.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.code,
            arrayOf<Int>(
                ExampleArticleConst.ARTICLE_INCOME_NETTO.code,
            ).toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_HEALTH_INSBASE.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_SOCIAL_INSBASE.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_HEALTH_INSPAYM.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_HEALTH_INSPAYM.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_SOCIAL_INSPAYM.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_SOCIAL_INSPAYM.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_TAXING_ADVBASE.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_TAXING_ADVPAYM.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_TAXING_ADVPAYM.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_INCOME_GROSS.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_INCOME_GROSS.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_INCOME_NETTO.code, ARTICLE_DEFAULT_SEQUENS, ExampleConceptConst.CONCEPT_INCOME_NETTO.code,
            arrayOf<Int>().toList()),
    )

    override val providers: Map<CODE, IArticleSpecProvider> =
        ArticleSpecFactory.buildProvidersFromRecords(providersConfig.toList())
}

class ExampleConceptFactory() : ConceptSpecFactory() {
    override val providers: Map<CODE, IConceptSpecProvider> =
        SpecFactory.buildProvidersFromAssembly("org.hravemzdy.procezor.example")
}