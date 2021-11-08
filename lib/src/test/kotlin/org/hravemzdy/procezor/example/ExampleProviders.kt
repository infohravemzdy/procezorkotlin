package org.hravemzdy.procezor.example

import com.github.michaelbull.result.Ok
import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.interfaces.ITermResult
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.registry.providers.ConceptSpec
import org.hravemzdy.procezor.registry.providers.ConceptSpecProvider
import org.hravemzdy.procezor.service.types.*

open class ExampleTermTarget : TermTarget {
    constructor(_month: MonthCode, _contract: ContractCode, _position: PositionCode, _variant: VariantCode, _article: ArticleCode,
                _concept: ConceptCode, _basis: Int, _descr: String)
            : super(_month, _contract, _position, _variant, _article, _concept, _basis, _descr) {
    }
    constructor(_month: MonthCode, _contract: ContractCode, _position: PositionCode, _variant: VariantCode, _article: ArticleCode,
                _concept: ConceptCode)
            : this(_month, _contract, _position, _variant, _article, _concept, 0, "")


    override fun articleDescr(): String {
        return getArticleSymbol(article.value)
    }
    override fun conceptDescr(): String {
        return getConceptSymbol(concept.value)
    }
}

open class ExampleTermResult : TermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, value, basis, descr)

    override fun articleDescr(): String {
        return getArticleSymbol(article.value)
    }
    override fun conceptDescr(): String {
        return getConceptSymbol(concept.value)
    }
}

class TimeshtWorkingConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return TimeshtWorkingConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TIMESHT_WORKING
    }
}

class TimeshtWorkingConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, emptyList<ArticleCode>(), ::conceptEval)
    companion object {
        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = TimeshtWorkingResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class AmountBasisConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return AmountBasisConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_BASIS
    }
}

class AmountBasisConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, constToPathArray(AmountBasisConSpec.specPath), ::conceptEval)
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_TIMESHT_WORKING.code,
        ).toList()

        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = AmountBasisResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class AmountFixedConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return AmountFixedConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_AMOUNT_FIXED
    }
}

class AmountFixedConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, emptyList<ArticleCode>(), ::conceptEval)
    companion object {
        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = AmountFixedResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class HealthInsbaseConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return HealthInsbaseConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSBASE
    }
}

class HealthInsbaseConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, emptyList<ArticleCode>(), ::conceptEval)
    companion object {
        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = HealthInsbaseResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class SocialInsbaseConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return SocialInsbaseConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSBASE
    }
}

class SocialInsbaseConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, emptyList<ArticleCode>(), ::conceptEval)
    companion object {
        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = SocialInsbaseResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class HealthInspaymConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return HealthInspaymConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_HEALTH_INSPAYM
    }
}

class HealthInspaymConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, constToPathArray(HealthInspaymConSpec.specPath), ::conceptEval)
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_HEALTH_INSBASE.code,
        ).toList()

        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = HealthInspaymResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class SocialInspaymConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return SocialInspaymConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_SOCIAL_INSPAYM
    }
}

class SocialInspaymConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, constToPathArray(SocialInspaymConSpec.specPath), ::conceptEval)
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_SOCIAL_INSBASE.code,
        ).toList()

        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = SocialInspaymResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class TaxingAdvbaseConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return TaxingAdvbaseConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVBASE
    }
}

class TaxingAdvbaseConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, emptyList<ArticleCode>(), ::conceptEval)
    companion object {
        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = TaxingAdvbaseResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class TaxingAdvpaymConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return TaxingAdvpaymConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_TAXING_ADVPAYM
    }
}

class TaxingAdvpaymConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, constToPathArray(TaxingAdvpaymConSpec.specPath), ::conceptEval)
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_TAXING_ADVBASE.code,
        ).toList()

        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = TaxingAdvpaymResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class IncomeGrossConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return IncomeGrossConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_GROSS
    }
}

class IncomeGrossConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, emptyList<ArticleCode>(), ::conceptEval)
    companion object {
        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = IncomeGrossResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

class IncomeNettoConProv : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return IncomeNettoConSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ExampleConceptConst.CONCEPT_INCOME_NETTO
    }
}

class IncomeNettoConSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, constToPathArray(IncomeNettoConSpec.specPath), ::conceptEval)
    companion object {
        val specPath: Iterable<Int> = arrayOf<Int>(
            ExampleArticleConst.ARTICLE_INCOME_GROSS.code,
            ExampleArticleConst.ARTICLE_HEALTH_INSPAYM.code,
            ExampleArticleConst.ARTICLE_SOCIAL_INSPAYM.code,
            ExampleArticleConst.ARTICLE_TAXING_ADVPAYM.code,
        ).toList()

        fun conceptEval(target: ITermTarget, period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
            val resultsValues: ITermResult = IncomeNettoResult(target, 0, 0, ExampleResultConst.DESCRIPTION_EMPTY)

            return listOf(Ok<ITermResult>(resultsValues))
        }
    }
}

