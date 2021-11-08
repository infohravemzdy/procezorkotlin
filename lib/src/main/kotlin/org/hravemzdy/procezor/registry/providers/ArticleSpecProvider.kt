package org.hravemzdy.procezor.registry.providers

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.VersionCode

interface IArticleSpecProvider : ISpecProvider<IArticleSpec, ArticleCode> {
}

abstract class ArticleSpecProvider(override val code: ArticleCode) : IArticleSpecProvider {
    constructor(_code: Int) : this(ArticleCode(_code)) {
    }
}
