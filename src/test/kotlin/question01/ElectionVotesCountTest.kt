package question01

import com.tinnova.desafio.question01.calculateElectionResult
import kotlin.test.Test
import kotlin.test.assertEquals

class ElectionVotesCountTest {

    @Test
    fun `calculateElectionResult retorna porcentagens corretas`() {
        val totalVoters = 1000
        val validVotes = 600
        val blankVotes = 250
        val nullVotes = 150

        val result = calculateElectionResult(totalVoters, validVotes, blankVotes, nullVotes)

        assertEquals(totalVoters, result.totalVoters)
        assertEquals(60.0, result.validPercentage, 0.001)
        assertEquals(25.0, result.blankPercentage, 0.001)
        assertEquals(15.0, result.nullPercentage, 0.001)
    }

    @Test
    fun `calculateElectionResult lida com votos maiores que total de eleitores`() {
        val result = calculateElectionResult(100, 80, 50, 30) // totalVotes = 160

        assertEquals(100, result.totalVoters)
        assertEquals(80.0, result.validPercentage, 0.001)
        assertEquals(50.0, result.blankPercentage, 0.001)
        assertEquals(30.0, result.nullPercentage, 0.001)
    }
}
