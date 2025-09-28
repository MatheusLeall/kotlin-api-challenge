package com.tinnova.desafio.question04

import kotlin.test.Test
import kotlin.test.assertEquals

class Sum3Or5MultiplesTest {

    @Test
    fun testSumMultiplesOf3Or5() {
        val limit = 10
        val result = sumMultiplesOf3Or5(limit)

        // Validando input
        assertEquals(limit, result.input)

        // Validando múltiplos
        val expectedMultiples = listOf(3, 5, 6, 9)
        assertEquals(expectedMultiples, result.multiples)

        // Validando soma
        val expectedSum = expectedMultiples.sum()
        assertEquals(expectedSum, result.sumMultiples)
    }
}
