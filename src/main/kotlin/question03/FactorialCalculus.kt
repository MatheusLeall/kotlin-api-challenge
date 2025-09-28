package com.tinnova.desafio.question03

import kotlinx.serialization.Serializable

@Serializable
data class FactorialCalculusResponse(
    val factorial: Long
)

fun factorialCalculus(n: Int): FactorialCalculusResponse {
    require(n >= 0) { "n deve ser maior ou igual a zero" }
    var result: Long = 1
    for (i in 1..n) {
        result *= i
    }

    return FactorialCalculusResponse(factorial = result)
}

