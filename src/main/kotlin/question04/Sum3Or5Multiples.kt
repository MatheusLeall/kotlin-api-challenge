package com.tinnova.desafio.question04

import kotlinx.serialization.Serializable

@Serializable
data class MultiplesResult(
    val input: Int,
    val multiples: List<Int>,
    val sumMultiples: Int
)

fun sumMultiplesOf3Or5(limit: Int): MultiplesResult {
    val multiples = (1 until limit).filter { it % 3 == 0 || it % 5 == 0 }
    val sumMultiples = multiples.sum()

    return MultiplesResult(input=limit, multiples=multiples, sumMultiples=sumMultiples)
}
