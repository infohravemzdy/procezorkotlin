package org.hravemzdy.procezor.example

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpec
import org.hravemzdy.procezor.registry.providers.ArticleSpecProvider
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.VersionCode

class TimeshtWorkingArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_TIMESHT_WORKING
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return TimeshtWorkingArtSpec(this.code)
    }
}

class TimeshtWorkingArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TIMESHT_WORKING
    }
}

class PaymentSalaryArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_PAYMENT_SALARY
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return PaymentSalaryArtSpec(this.code)
    }
}

class PaymentSalaryArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code),
        ArticleSpec.constToSumsArray(arrayOf<Int>(
            ExampleArticleConst.ARTICLE_INCOME_GROSS.code,
            ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
            ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
            ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
        ).toList()))

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_BASIS
    }
}

class PaymentBonusArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_PAYMENT_BONUS
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return PaymentBonusArtSpec(this.code)
    }
}

class PaymentBonusArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code),
        ArticleSpec.constToSumsArray(arrayOf<Int>(
            ExampleArticleConst.ARTICLE_INCOME_GROSS.code,
            ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
            ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
            ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
        ).toList()))

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
    }
}

class PaymentBarterArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_PAYMENT_BARTER
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return PaymentBarterArtSpec(this.code)
    }
}

class PaymentBarterArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code),
        ArticleSpec.constToSumsArray(arrayOf<Int>(
            ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
            ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
            ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
        ).toList()))

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
    }
}

class AllowceHofficeArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_ALLOWCE_HOFFICE
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return AllowceHofficeArtSpec(this.code)
    }
}

class AllowceHofficeArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code),
        ArticleSpec.constToSumsArray(arrayOf<Int>(
            ExampleArticleConst.ARTICLE_INCOME_NETTO.code,
        ).toList()))

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
    }
}

class HealthInsbaseArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_HEALTH_INSBASE
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return HealthInsbaseArtSpec(this.code)
    }
}

class HealthInsbaseArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSBASE
    }
}

class SocialInsbaseArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_SOCIAL_INSBASE
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return SocialInsbaseArtSpec(this.code)
    }
}

class SocialInsbaseArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSBASE
    }
}

class HealthInspaymArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_HEALTH_INSPAYM
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return HealthInspaymArtSpec(this.code)
    }
}

class HealthInspaymArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSPAYM
    }
}

class SocialInspaymArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_SOCIAL_INSPAYM
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return SocialInspaymArtSpec(this.code)
    }
}

class SocialInspaymArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSPAYM
    }
}

class TaxingAdvbaseArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_TAXING_ADVBASE
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return TaxingAdvbaseArtSpec(this.code)
    }
}

class TaxingAdvbaseArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVBASE
    }
}

class TaxingAdvpaymArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_TAXING_ADVPAYM
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return TaxingAdvpaymArtSpec(this.code)
    }
}

class TaxingAdvpaymArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVPAYM
    }
}

class IncomeGrossArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_INCOME_GROSS
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return IncomeGrossArtSpec(this.code)
    }
}

class IncomeGrossArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_GROSS
    }
}

class IncomeNettoArtProv : ArticleSpecProvider {
    constructor() : super(ArticleCode.get(ARTICLE_CODE.code)) {
    }

    companion object {
        val ARTICLE_CODE = ExampleArticleConst.ARTICLE_INCOME_NETTO
    }
    override fun getSpec(period: IPeriod, version: VersionCode): IArticleSpec {
        return IncomeNettoArtSpec(this.code)
    }
}

class IncomeNettoArtSpec : ArticleSpec {

    constructor(_code: ArticleCode) : super(_code, ConceptCode.get(CONCEPT_CODE.code), emptyList<ArticleCode>())

    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_NETTO
    }
}

