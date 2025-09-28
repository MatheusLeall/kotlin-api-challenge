package question03

import com.tinnova.desafio.question03.factorialCalculus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FactorialCalculusTest {

    @Test
    fun `factorial of zero should be 1`() {
        val response = factorialCalculus(0)
        assertEquals(1, response.factorial)
    }

    @Test
    fun `factorial of one should be 1`() {
        val response = factorialCalculus(1)
        assertEquals(1, response.factorial)
    }

    @Test
    fun `factorial of small numbers`() {
        val response2 = factorialCalculus(2)
        assertEquals(2, response2.factorial)

        val response3 = factorialCalculus(3)
        assertEquals(6, response3.factorial)

        val response5 = factorialCalculus(5)
        assertEquals(120, response5.factorial)
    }

    @Test
    fun `factorial of larger numbers`() {
        val response10 = factorialCalculus(10)
        assertEquals(3628800, response10.factorial)
    }

    @Test
    fun `negative number should throw exception`() {
        assertFailsWith<IllegalArgumentException> {
            factorialCalculus(-1)
        }
    }
}
