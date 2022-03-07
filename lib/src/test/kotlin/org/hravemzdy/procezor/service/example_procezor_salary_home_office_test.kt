package org.hravemzdy.procezor.service

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.getAll
import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.legalios.service.types.Period
import org.hravemzdy.legalios.service.types.BundleProps
import org.hravemzdy.procezor.example.ExampleArticleConst
import org.hravemzdy.procezor.example.ExampleConceptConst
import org.hravemzdy.procezor.example.ExampleTermTarget
import org.hravemzdy.procezor.example.ServiceExample
import org.hravemzdy.procezor.interfaces.ITermTarget
import org.hravemzdy.procezor.interfaces.ITermTargetList
import org.hravemzdy.procezor.service.types.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.*

class ServiceProcezorExampleWithSalaryHomeOfficeTest : Spek({
    fun getTargetsFromDb(period: IPeriod): ITermTargetList {
        val  CONTRACT_CODE = 0
        val  POSITION_CODE = 0


        val montCode = MonthCode.getWithPeriod(period)
        val contract = ContractCode.get(CONTRACT_CODE)
        val position = PositionCode.get(POSITION_CODE)
        val variant1 = VariantCode.get(1)
        val targets  = arrayListOf<ITermTarget>(
            ExampleTermTarget(montCode, contract, position, variant1,
                ArticleCode.get(ExampleArticleConst.ARTICLE_TIMESHT_WORKING.code),
                ConceptCode.get(ExampleConceptConst.CONCEPT_TIMESHT_WORKING.code)),
            ExampleTermTarget(montCode, contract, position, variant1,
                ArticleCode.get(ExampleArticleConst.ARTICLE_PAYMENT_SALARY.code),
                ConceptCode.get(ExampleConceptConst.CONCEPT_AMOUNT_BASIS.code)),
            ExampleTermTarget(montCode, contract, position, variant1,
                ArticleCode.get(ExampleArticleConst.ARTICLE_ALLOWCE_HOFFICE.code),
                ConceptCode.get(ExampleConceptConst.CONCEPT_AMOUNT_FIXED.code)),
        )
        return targets
    }

    describe("Procezor Example - Salary-HomeOffice") {
        val testService = ServiceExample()

        val testVersion = testService.version
        it("service version should return value = 100") {
            assertEquals(100, testVersion.value)
        }
        val testPeriod = Period.getWithYearMonth(2021, 1)
        it("service period should return value = 202101") {
            assertEquals(202101, testPeriod.code)
        }
        val testLegal = BundleProps.empty(testPeriod)

        val factoryArticleCode = ArticleCode.get(ExampleArticleConst.ARTICLE_TIMESHT_WORKING.code)

        val factoryArticle = testService.getArticleSpec(factoryArticleCode, testPeriod, testVersion)
        it("article should have value = ${ExampleArticleConst.ARTICLE_TIMESHT_WORKING.code}") {
            assertNotNull(factoryArticle, "Error getting article - article is not valid")
            assertNotEquals(0, factoryArticle.code.value, "Error getting article - article is not valid")
        }
        val factoryConceptCode = ConceptCode.get(ExampleConceptConst.CONCEPT_TIMESHT_WORKING.code)

        val factoryConcept = testService.getConceptSpec(factoryConceptCode, testPeriod, testVersion)
        it("concept should have value = ${ExampleConceptConst.CONCEPT_TIMESHT_WORKING.code}") {
            assertNotNull(factoryConcept, "Error getting concept - concept is not valid")
            assertNotEquals(0, factoryConcept.code.value, "Error getting concept - concept is not valid")
        }
        val initService = testService.initWithPeriod(testPeriod)
        it("initiating service should return true") {
            assertTrue(initService, "Error initiating service - init was failed")
        }
        val restService = testService.getResults(testPeriod, testLegal, getTargetsFromDb(testPeriod))

        restService.forEachIndexed { index, result ->
            when (result) {
                is Ok -> {
                    val articleSymbol = result.value.articleDescr()
                    val conceptSymbol = result.value.conceptDescr()
                    println("Index: $index, ART: $articleSymbol, CON: $conceptSymbol")
                }
                is Err -> {
                    val articleSymbol = result.error.articleDescr()
                    val conceptSymbol = result.error.conceptDescr()
                    val errorValue = result.error.description()
                    println("Index: $index, ART: $articleSymbol, CON: $conceptSymbol, Error: $errorValue")
                }
            }
        }

        val testArticles = intArrayOf(80001, 80005, 80002, 80006, 80007, 80010, 80012, 80008, 80009, 80011, 80013);
        it("get result should return array of length ${testArticles.count()}") {
            assertEquals(testArticles.count(), restService.count(), "Error getting results - get result was failed")
        }
        val restArticles = restService.getAll().map { x -> (x.article.value) }.toIntArray()
        it("get result should return array of articles") {
            assertContentEquals(
                testArticles,
                restArticles,
                "Error getting results same order array - get result was failed"
            )
        }
    }
})
