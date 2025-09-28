package com.tinnova.desafio.question01

class Election(
    private val totalVoters: Int,
    private val validVotes: Int,
    private val blankVotes: Int,
    private val nullVotes: Int
) {

    fun validPercentage(): Double {
        return (validVotes.toDouble() / totalVoters) * 100
    }

    fun blankPercentage(): Double {
        return (blankVotes.toDouble() / totalVoters) * 100
    }

    fun nullPercentage(): Double {
        return (nullVotes.toDouble() / totalVoters) * 100
    }
}