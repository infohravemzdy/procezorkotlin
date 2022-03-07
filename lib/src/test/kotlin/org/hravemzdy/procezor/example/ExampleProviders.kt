package org.hravemzdy.procezor.example

import com.github.michaelbull.result.Ok
import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.interfaces.ITermResult
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.registry.providers.ConceptSpecProvider
import org.hravemzdy.procezor.service.types.*

class TimeshtWorkingConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return TimeshtWorkingConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TIMESHT_WORKING
    }
}

class TimeshtWorkingConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, emptyList<ArticleCode>(), ::conceptEval) {
    companion object {
        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = TimeshtWorkingResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class AmountBasisConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return AmountBasisConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_BASIS
    }
}

class AmountBasisConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, constToPathArray(AmountBasisConSpec.specPath), ::conceptEval) {
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_TIMESHT_WORKING.code,
        ).toList()

        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = AmountBasisResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class AmountFixedConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return AmountFixedConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
    }
}

class AmountFixedConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, emptyList<ArticleCode>(), ::conceptEval) {
    companion object {
        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = AmountFixedResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class HealthInsbaseConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return HealthInsbaseConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSBASE
    }
}

class HealthInsbaseConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, emptyList<ArticleCode>(), ::conceptEval) {
    companion object {
        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = HealthInsbaseResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class SocialInsbaseConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return SocialInsbaseConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSBASE
    }
}

class SocialInsbaseConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, emptyList<ArticleCode>(), ::conceptEval) {
    companion object {
        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = SocialInsbaseResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class HealthInspaymConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return HealthInspaymConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSPAYM
    }
}

class HealthInspaymConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, constToPathArray(HealthInspaymConSpec.specPath), ::conceptEval) {
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
        ).toList()

        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = HealthInspaymResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class SocialInspaymConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return SocialInspaymConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSPAYM
    }
}

class SocialInspaymConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, constToPathArray(SocialInspaymConSpec.specPath), ::conceptEval) {
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
        ).toList()

        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = SocialInspaymResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class TaxingAdvbaseConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return TaxingAdvbaseConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVBASE
    }
}

class TaxingAdvbaseConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, emptyList<ArticleCode>(), ::conceptEval) {
    companion object {
        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = TaxingAdvbaseResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class TaxingAdvpaymConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return TaxingAdvpaymConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVPAYM
    }
}

class TaxingAdvpaymConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, constToPathArray(TaxingAdvpaymConSpec.specPath), ::conceptEval) {
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
        ).toList()

        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = TaxingAdvpaymResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class IncomeGrossConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return IncomeGrossConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_GROSS
    }
}

class IncomeGrossConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, emptyList<ArticleCode>(), ::conceptEval) {
    companion object {
        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = IncomeGrossResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class IncomeNettoConProv() : ConceptSpecProvider(ConceptCode.get(CONCEPT_CODE.code)) {
    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return IncomeNettoConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_NETTO
    }
}

class IncomeNettoConSpec(_code: ConceptCode) : ExampleConceptSpec(_code, constToPathArray(IncomeNettoConSpec.specPath), ::conceptEval) {
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_INCOME_GROSS.code,
            ExampleArticleConst.ARTICLE_HEALTH_INSPAYM.code,
            ExampleArticleConst.ARTICLE_SOCIAL_INSPAYM.code,
            ExampleArticleConst.ARTICLE_TAXING_ADVPAYM.code,
        ).toList()

        fun conceptEval(target: ITermTarget, spec: IArticleSpec?, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = IncomeNettoResult(target, spec)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

