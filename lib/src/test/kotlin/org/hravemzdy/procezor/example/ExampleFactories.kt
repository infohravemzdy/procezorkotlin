package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.registry.factories.*
import org.hravemzdy.procezor.registry.providers.IArticleSpecProvider
import org.hravemzdy.procezor.registry.providers.IConceptSpecProvider

class ExampleArticleFactory() : ArticleSpecFactory() {
    private val providersConfig = arrayOf<ProviderRecord>(
        ProviderRecord(ExampleArticleConst.ARTICLE_TIMESHT_WORKING.code, ExampleConceptConst.CONCEPT_TIMESHT_WORKING.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_SALARY.code, ExampleConceptConst.CONCEPT_AMOUNT_BASIS.code,
            arrayOf<Int>(
                ExampleArticleConst.ARTICLE_INCOME_GROSS.code,
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
            ).toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_BONUS.code, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.code,
            arrayOf<Int>(
                ExampleArticleConst.ARTICLE_INCOME_GROSS.code,
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
            ).toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_PAYMENT_BARTER.code, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.code,
            arrayOf<Int>(
                ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
                ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
                ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
            ).toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_ALLOWCE_HOFFICE.code, ExampleConceptConst.CONCEPT_AMOUNT_FIXED.code,
            arrayOf<Int>(
                ExampleArticleConst.ARTICLE_INCOME_NETTO.code,
            ).toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code, ExampleConceptConst.CONCEPT_HEALTH_INSBASE.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code, ExampleConceptConst.CONCEPT_SOCIAL_INSBASE.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_HEALTH_INSPAYM.code, ExampleConceptConst.CONCEPT_HEALTH_INSPAYM.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_SOCIAL_INSPAYM.code, ExampleConceptConst.CONCEPT_SOCIAL_INSPAYM.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code, ExampleConceptConst.CONCEPT_TAXING_ADVBASE.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_TAXING_ADVPAYM.code, ExampleConceptConst.CONCEPT_TAXING_ADVPAYM.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_INCOME_GROSS.code, ExampleConceptConst.CONCEPT_INCOME_GROSS.code,
            arrayOf<Int>().toList()),
        ProviderRecord(ExampleArticleConst.ARTICLE_INCOME_NETTO.code, ExampleConceptConst.CONCEPT_INCOME_NETTO.code,
            arrayOf<Int>().toList()),
    )

    override val providers: Map<CODE, IArticleSpecProvider> =
        ArticleSpecFactory.buildProvidersFromRecords(providersConfig.toList())
}

class ExampleConceptFactory() : ConceptSpecFactory() {
    override val providers: Map<CODE, IConceptSpecProvider> =
        SpecFactory.buildProvidersFromAssembly("org.hravemzdy.procezor.example")
}