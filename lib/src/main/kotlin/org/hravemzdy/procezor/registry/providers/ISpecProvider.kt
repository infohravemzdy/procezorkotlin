package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.service.types.VersionCode

interface ISpecProvider<S, C : ISpecCode> {
    val code: C
    fun getSpec(period: IPeriod, version: VersionCode): S
}