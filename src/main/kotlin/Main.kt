package com.tinnova.desafio

import com.tinnova.desafio.question01.calculateElectionResult
import com.tinnova.desafio.question02.executeBubbleSort
import com.tinnova.desafio.question03.factorialCalculus
import com.tinnova.desafio.question04.sumMultiplesOf3Or5
import com.tinnova.desafio.question05.vehicleRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.swagger.swaggerUI

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/") {
                call.respond(mapOf("message" to "Hello, Kotlin API! 🚀"))
            }
            get("/elections") {
                val totalVoters = call.request.queryParameters["totalVoters"]?.toIntOrNull()
                val validVotes = call.request.queryParameters["validVotes"]?.toIntOrNull()
                val blankVotes = call.request.queryParameters["blankVotes"]?.toIntOrNull()
                val nullVotes = call.request.queryParameters["nullVotes"]?.toIntOrNull()

                if (totalVoters == null || validVotes == null || blankVotes == null || nullVotes == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        mapOf("error" to "Parameters totalVoters, validVotes, blankVotes, and nullVotes are required and must be integers.")
                    )
                    return@get
                }

                val response = calculateElectionResult(totalVoters, validVotes, blankVotes, nullVotes)
                call.respond(response)
            }
            get(path = "/calculate-factorial/{number}"){
                val n = call.parameters["number"]?.toIntOrNull() ?: 0
                val response = factorialCalculus(n)
                call.respond(response)
            }
            get(path = "/multiples/{limit}"){
                val limit = call.parameters["limit"]?.toIntOrNull() ?: 0
                val sumResponse = sumMultiplesOf3Or5(limit=limit)
                call.respond(sumResponse)
            }
            get("/bubble-sort/{values}") {
                val valuesParam = call.parameters["values"]

                if (valuesParam.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Parâmetro 'values' é obrigatório."))
                    return@get
                }

                val valuesArray = valuesParam.split(",")
                    .mapNotNull { it.toIntOrNull() }
                    .toIntArray()

                if (valuesArray.isEmpty()) {
                    call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Nenhum valor válido foi fornecido."))
                    return@get
                }

                val response = executeBubbleSort(valuesArray)
                call.respond(response)
            }
            vehicleRoutes()
            //swaggerUI(path = "swagger")
        }
    }.start(wait = true)
}
