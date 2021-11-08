package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.service.types.ConceptCode

interface IConceptSpecProvider : ISpecProvider<IConceptSpec, ConceptCode> {
}

abstract class ConceptSpecProvider(override val code: ConceptCode) : IConceptSpecProvider {
    constructor(_code: Int) : this(ConceptCode(_code)) {
    }
}
