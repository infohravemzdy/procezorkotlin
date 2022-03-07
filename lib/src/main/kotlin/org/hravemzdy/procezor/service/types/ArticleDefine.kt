package org.hravemzdy.procezor.service.types

import org.hravemzdy.procezor.interfaces.IArticleDefine

class ArticleDefine(override val code: ArticleCode, override val seqs: ArticleSeqs, override val role: ConceptCode) : IArticleDefine {
    constructor() : this(ArticleCode.new(), ArticleSeqs.new(), ConceptCode.new())
    constructor(code: ArticleCode) : this(code, ArticleSeqs.new(), ConceptCode.new())
    constructor(defs: IArticleDefine) : this(defs.code, defs.seqs, defs.role)
    constructor(code: Int, seqs: Int, role: Int) : this(ArticleCode.get(code), ArticleSeqs.get(seqs), ConceptCode.get(role))
    override fun term(): ArticleTerm {
        return ArticleTerm(code, seqs)
    }
}