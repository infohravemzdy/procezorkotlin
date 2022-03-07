package org.hravemzdy.procezor.registry

import org.hravemzdy.legalios.interfaces.IBundleProps
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ITermSymbol
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.interfaces.ResultFunc
import org.hravemzdy.procezor.service.types.BuilderResultList

interface ITermCalcul : ITermSymbol {
    val target: ITermTarget
    val spec: IArticleSpec?
    val resultDelegate: ResultFunc?
    fun getResults(period: IPeriod, ruleset: IBundleProps, results: BuilderResultList): BuilderResultList
}