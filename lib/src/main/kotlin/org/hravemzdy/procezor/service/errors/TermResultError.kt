package org.hravemzdy.procezor.service.errors

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.ITermResultError
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.service.types.*

sealed class TermResultError : ITermResultError {
    private val period: IPeriod
    private val target: ITermTarget?
    private val message: String

    override val contract: ContractCode
    override val position: PositionCode
    override val article: ArticleCode
    override val concept: ConceptCode
    override val variant: VariantCode

    constructor(period: IPeriod, target: ITermTarget?, message: String) {
        this.period = period
        this.target = target
        this.message = message

        this.contract = target?.contract ?: ContractCode.new()
        this.position = target?.position ?: PositionCode.new()
        this.article = target?.article ?: ArticleCode.new()
        this.concept = target?.concept ?: ConceptCode.new()
        this.variant = target?.variant ?: VariantCode.new()
    }

    override fun description(): String {
        return message
    }

    override fun article(): ArticleCode {
        return target?.article ?: ArticleCode.new()
    }

    override fun concept(): ConceptCode {
        return target?.concept ?: ConceptCode.new()
    }

    override fun articleDescr(): String {
        return target?.articleDescr() ?: "ArticleCode for ${article.value}"
    }

    override fun conceptDescr(): String {
        return target?.conceptDescr() ?: "ConceptCode for ${concept.value}"
    }

    class EvalResultError(period: IPeriod, target: ITermTarget?) :
        TermResultError(period, target, "evaluation failed")
    class ExtractResultError(period: IPeriod, target: ITermTarget?) :
        TermResultError(period, target, "extract result failed")

    class NoImplementationError(period: IPeriod, target: ITermTarget?) :
        TermResultError(period, target, "failed with no-implementation")

    class NoResultFuncError(period: IPeriod, target: ITermTarget?) :
        TermResultError(period, target, "failed with no-result function")

    class InvalidResultError(period: IPeriod, target: ITermTarget?, typeDescr: String) :
        TermResultError(period, target, "invalid result type $typeDescr error!")

    class InvalidRulesetError(period: IPeriod, target: ITermTarget?, typeDescr: String) :
        TermResultError(period, target, "invalid $typeDescr Ruleset error!")

    class InvalidTargetError(period: IPeriod, target: ITermTarget?, typeDescr: String) :
        TermResultError(period, target, "invalid target type $typeDescr error!")

    class NoResultFoundError(
        period: IPeriod,
        target: ITermTarget?,
        articleDescr: String,
        contract: ContractCode? = null,
        position: PositionCode? = null
    ) : TermResultError(period, target, "result for ${articleDescr}${messageContractPosition(contract, position)} Not Found")

    class NullResultFoundError(
        period: IPeriod,
        target: ITermTarget?,
        articleDescr: String,
        contract: ContractCode? = null,
        position: PositionCode? = null
    ) : TermResultError(period, target, "result found for ${articleDescr}${messageContractPosition(contract, position)} but Instance is Null!")

    companion object {
        fun messageContractPosition(contract: ContractCode?, position: PositionCode?): String {
            return if (contract != null && position != null) {
                ", contract=${contract.value}, position=${position.value}"
            } else if (contract != null) {
                ", contract=${contract.value}"
            } else {
                ""
            }
        }
    }
}
