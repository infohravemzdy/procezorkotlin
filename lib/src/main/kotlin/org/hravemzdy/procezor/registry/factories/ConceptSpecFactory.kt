package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.registry.constants.ConceptConst
import org.hravemzdy.procezor.registry.providers.ConceptSpec
import org.hravemzdy.procezor.registry.providers.ConceptSpecProvider
import org.hravemzdy.procezor.registry.providers.IConceptSpecProvider
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ConceptCode
import org.hravemzdy.procezor.service.types.VersionCode

interface IConceptSpecFactory : ISpecFactory<IConceptSpecProvider, IConceptSpec, ConceptCode> {
}

class NotFoundConceptSpec : ConceptSpec {
    constructor(_code: ConceptCode) : super(_code, emptyList<ArticleCode>(), null)
}

class NotFoundConceptProvider : ConceptSpecProvider {
    constructor() : super(ConceptCode.get(CONCEPT_CODE.code))

    override fun getSpec(period: IPeriod, version: VersionCode): IConceptSpec {
        return NotFoundConceptSpec(this.code)
    }
    companion object {
        val CONCEPT_CODE = ConceptConst.CONCEPT_NOTFOUND
    }
}


abstract class ConceptSpecFactory() : SpecFactory<IConceptSpecProvider, IConceptSpec, ConceptCode>(), IConceptSpecFactory {
    override val notFoundProvider = NotFoundConceptProvider()
    override val notFoundSpec = NotFoundConceptSpec(ConceptCode.get(NotFoundConceptProvider.CONCEPT_CODE.code));
}
