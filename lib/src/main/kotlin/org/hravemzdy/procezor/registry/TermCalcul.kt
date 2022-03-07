package org.hravemzdy.procezor.registry

import com.github.michaelbull.result.Err
import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ITermResultError
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.interfaces.ResultFunc
import org.hravemzdy.procezor.service.errors.TermResultError
import org.hravemzdy.procezor.service.types.BuilderResultList
import org.hravemzdy.procezor.service.types.TermSymbol

class TermCalcul(override val target: ITermTarget, override val  spec: IArticleSpec?, override val resultDelegate: ResultFunc?)
    : TermSymbol(target.monthCode, target.contract, target.position, target.variant, target.article), ITermCalcul {

    override fun getResults(period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList {
        if (resultDelegate == null)
        {
            val resultError = TermResultError.NoResultFuncError(period, target)
            return listOf(Err<ITermResultError>(resultError))
        }
        var resultTarget = resultDelegate!!(target, spec, period, ruleset, results)

        return resultTarget.toList()
    }
}