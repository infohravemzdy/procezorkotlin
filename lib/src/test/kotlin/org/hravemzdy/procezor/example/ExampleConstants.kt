package org.hravemzdy.procezor.example

enum class ExampleArticleConst(val code: Int) {
    ARTICLE_TIMESHT_WORKING (80001),
    ARTICLE_PAYMENT_SALARY  (80002),
    ARTICLE_PAYMENT_BONUS   (80003),
    ARTICLE_PAYMENT_BARTER  (80004),
    ARTICLE_ALLOWCE_HOFFICE (80005),
    ARTICLE_HEALTH_INSBASE  (80006),
    ARTICLE_SOCIAL_INSBASE  (80007),
    ARTICLE_HEALTH_INSPAYM  (80008),
    ARTICLE_SOCIAL_INSPAYM  (80009),
    ARTICLE_TAXING_ADVBASE  (80010),
    ARTICLE_TAXING_ADVPAYM  (80011),
    ARTICLE_INCOME_GROSS    (80012),
    ARTICLE_INCOME_NETTO    (80013),
}

fun getArticleSymbol(item: Int): String {
    val exist =  ExampleArticleConst.values().find { x -> x.code == item }
    return exist?.name ?: ""
}

enum class ExampleConceptConst(val code: Int) {
    CONCEPT_TIMESHT_WORKING (80001),
    CONCEPT_AMOUNT_BASIS    (80002),
    CONCEPT_AMOUNT_FIXED    (80003),
    CONCEPT_HEALTH_INSBASE  (80006),
    CONCEPT_SOCIAL_INSBASE  (80007),
    CONCEPT_HEALTH_INSPAYM  (80008),
    CONCEPT_SOCIAL_INSPAYM  (80009),
    CONCEPT_TAXING_ADVBASE  (80010),
    CONCEPT_TAXING_ADVPAYM  (80011),
    CONCEPT_INCOME_GROSS    (80012),
    CONCEPT_INCOME_NETTO    (80013),
}

fun getConceptSymbol(item: Int): String {
    val exist =  ExampleConceptConst.values().find { x -> x.code == item }
    return exist?.name ?: ""
}
