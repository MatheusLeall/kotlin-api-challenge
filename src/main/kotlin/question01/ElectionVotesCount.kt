package com.tinnova.desafio.question01

import kotlinx.serialization.Serializable

@Serializable
data class ElectionResult(
    val totalVoters: Int,
    val validPercentage: Double,
    val blankPercentage: Double,
    val nullPercentage: Double
)

fun calculateElectionResult(
    totalVoters: Int,
    validVotes: Int,
    blankVotes: Int,
    nullVotes: Int
): ElectionResult {
    val election = Election(totalVoters, validVotes, blankVotes, nullVotes)
    return ElectionResult(
        totalVoters = totalVoters,
        validPercentage = election.validPercentage(),
        blankPercentage = election.blankPercentage(),
        nullPercentage = election.nullPercentage()
    )
}

