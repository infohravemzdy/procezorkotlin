package org.hravemzdy.procezor.registry

import org.hravemzdy.procezor.interfaces.IArticleDefine
import org.hravemzdy.procezor.interfaces.IArticleSpec
import org.hravemzdy.procezor.interfaces.IConceptSpec
import org.hravemzdy.procezor.service.types.ArticleCode
import org.hravemzdy.procezor.service.types.ArticleDefine
import org.hravemzdy.procezor.service.types.ArticleTerm


data class ArticleEdge(val start: ArticleTerm, val stops:ArticleTerm)

class Queue<T> {
    constructor(items: Iterable<T>) {
        push(items)
    }
    val elements: MutableList<T> = mutableListOf()
    fun isEmpty() = elements.isEmpty()
    fun count() = elements.size
    fun enqueue(item: T) = elements.add(item)
    fun dequeue() = if (!isEmpty()) elements.removeAt(0) else null
    fun peek() = if (!isEmpty()) elements[0] else null

    override fun toString(): String = elements.toString()
}

fun <T> Queue<T>.push(items: Iterable<T>) = items.forEach { this.enqueue(it) }

class DependencyGraph {
    fun initGraphModel(articlesModel: Iterable<IArticleSpec>, conceptsModel: Iterable<IConceptSpec>): Pair<Iterable<ArticleTerm>, Map<ArticleTerm, Iterable<IArticleDefine>>> {
        val vertModel: Iterable<ArticleTerm> = createVertModel(articlesModel)
        val edgeModel: Iterable<ArticleEdge> = createEdgeModel(articlesModel, conceptsModel)
        val pendModel: Iterable<ArticleEdge> = createPendModel(articlesModel, conceptsModel)

        val order = createTopoModel(vertModel, edgeModel)
        val paths = createPathModel(articlesModel, vertModel, pendModel, order)

        return Pair(first = order, second = paths)
    }
    private fun createVertModel(articlesModel: Iterable<IArticleSpec>): Iterable<ArticleTerm> {
        return articlesModel.map {a -> a.term()}.sorted().toList()
    }
    private fun createEdgeModel(articlesModel: Iterable<IArticleSpec>, conceptsModel: Iterable<IConceptSpec>): Iterable<ArticleEdge> {
        var init: Iterable<ArticleEdge> = hashSetOf<ArticleEdge>()

        return articlesModel.fold(init) { agr, x -> mergeEdges(articlesModel, conceptsModel, agr, x) }
            .sortedWith(Comparator<ArticleEdge>{ x, y ->
                when (x.start) {
                    y.start -> x.stops.compareTo(y.stops)
                    else -> x.start.compareTo(y.start)
                }
        }).toList()
    }
    private fun createPendModel(articlesModel: Iterable<IArticleSpec>, conceptsModel: Iterable<IConceptSpec>): Iterable<ArticleEdge> {
        var init: Iterable<ArticleEdge> = hashSetOf<ArticleEdge>()

        return articlesModel.fold(init) { agr, x -> mergePends(articlesModel, conceptsModel, agr, x) }
            .sortedWith(Comparator<ArticleEdge>{ x, y ->
                when (x.start) {
                    y.start -> x.stops.compareTo(y.stops)
                    else -> x.start.compareTo(y.start)
                }
            }).toList()
    }
    private fun createPathModel(articlesModel: Iterable<IArticleSpec>, vertModel: Iterable<ArticleTerm>, edgeModel: Iterable<ArticleEdge>, vertOrder: Iterable<ArticleTerm>): Map<ArticleTerm, Iterable<IArticleDefine>> {
        return vertModel.associateWith { x -> mergePaths(articlesModel, edgeModel, vertOrder, x) }
    }
    private fun mergeEdges(articlesModel: Iterable<IArticleSpec>, conceptsModel: Iterable<IConceptSpec>, agr: Iterable<ArticleEdge>,  article: IArticleSpec): Iterable<ArticleEdge>
    {
        var result = agr.toHashSet()

        var concept = conceptsModel.firstOrNull { c -> (c.code == article.role) }

        if (concept != null) {
            result = article.sums.map { s -> ArticleEdge(article.term(), getArticleTerm(s, articlesModel)) }.plus(result).toHashSet()

            result = concept.path.map { p -> ArticleEdge(getArticleTerm(p, articlesModel), article.term()) }.plus(result).toHashSet()
        }

        return result
    }
    private fun mergePends(articlesModel: Iterable<IArticleSpec>, conceptsModel: Iterable<IConceptSpec>, agr: Iterable<ArticleEdge>, article: IArticleSpec): Iterable<ArticleEdge>
    {
        var result = agr.toHashSet()

        var concept = conceptsModel.firstOrNull { c -> (c.code == article.role) }

        if (concept != null) {
            result = concept.path.map { p -> ArticleEdge(getArticleTerm(p, articlesModel), article.term()) }.plus(result).toHashSet()
        }

        return result
    }
    private fun mergePaths(articlesModel: Iterable<IArticleSpec>, edgeModel: Iterable<ArticleEdge>, vertOrder: Iterable<ArticleTerm>, article: ArticleTerm): Iterable<IArticleDefine> {
        val articleInit: Iterable<IArticleDefine> = edgeModel.filter { e -> (e.stops == article) }.map { e -> getArticleDefs(e.start.code, articlesModel) }.toList()
        val articlePath = articleInit.fold(articleInit) { agr, x -> mergeVert(articlesModel, edgeModel, agr, x) }
        return articlePath.distinct().sortedWith(DefineComparator(vertOrder.toList()))
    }
    private fun mergeVert(articlesModel: Iterable<IArticleSpec>, edgeModel: Iterable<ArticleEdge>, resultVert: Iterable<IArticleDefine>, defs: IArticleDefine): Iterable<IArticleDefine> {
        val resultInit: Iterable<IArticleDefine> = edgeModel.
            filter { e -> (e.stops == defs.term()) }.
            map { e -> getArticleDefs(e.start.code, articlesModel) }.toList()
        val resultList = resultInit.fold(resultInit) { agr, x -> mergeVert(articlesModel, edgeModel, agr, x) }
        return resultVert.plus(resultList).plus(defs).toList()
    }
    private fun getArticleTerm(article: ArticleCode, articlesModel: Iterable<IArticleSpec>): ArticleTerm {
        val articleSpec = articlesModel.firstOrNull { m -> (m.code == article) } ?: return ArticleTerm.zero()
        return articleSpec.term()
    }
    private fun getArticleDefs(article: ArticleCode, articlesModel: Iterable<IArticleSpec>): IArticleDefine {
        val articleSpec = articlesModel.firstOrNull { m -> (m.code == article) } ?: return ArticleDefine()
        return articleSpec.defs()
    }
    private fun createTopoModel(vertModel: Iterable<ArticleTerm>, edgeModel: Iterable<ArticleEdge>): Iterable<ArticleTerm>
    {
        var articlesOrder = listOf<ArticleTerm>()

        val degrees = vertModel.associateWith { x -> edgeModel.count { e -> (e.stops == x) } }.toMutableMap()

        val queues = Queue<ArticleTerm>(degrees.filter { x -> (x.value == 0) }.map { x -> x.key }.sorted())

        var index = 0
        while (queues.count() != 0)
        {
            index++
            val article = queues.dequeue()
            articlesOrder = articlesOrder.plus(article!!)
            val paths = edgeModel.filter { x -> (x.start == article) }.map { x -> x.stops }.toList()
            paths.forEach { p ->
                degrees[p] = (degrees.getOrDefault(p, 0) - 1)
                if (degrees[p] == 0)
                {
                    queues.enqueue(p)
                }
            }
        }
        if (index != vertModel.count())
        {
            return listOf<ArticleTerm>()
        }
        return articlesOrder
    }
    private class DefineComparator(val topoOrders: Iterable<ArticleTerm>) : Comparator<IArticleDefine> {
        val codeOrders: Iterable<ArticleCode> = topoOrders.map {it -> it.code }
        override fun compare(x: IArticleDefine,  y: IArticleDefine): Int {
            var xIndex = codeOrders.indexOf(x.code)

            var yIndex = codeOrders.indexOf(y.code)

            if (xIndex == -1 && yIndex == -1) {
                return 0
            }

            if (xIndex == -1 && yIndex != -1) {
                return -1
            }

            if (xIndex != -1 && yIndex == -1) {
                return 1
            }

            return xIndex.compareTo(yIndex)
        }
    }
}