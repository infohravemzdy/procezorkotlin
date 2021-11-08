package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleDefine

class ArticleDefine(override val code: ArticleCode, override val role: ConceptCode) : IArticleDefine {
    constructor() : this(ArticleCode.new(), ConceptCode.new())
    constructor(code: ArticleCode) : this(code, ConceptCode.new())
    constructor(defs: IArticleDefine) : this(defs.code, defs.role)
    constructor(code: Int, role: Int) : this(ArticleCode.get(code), ConceptCode.get(role))
}