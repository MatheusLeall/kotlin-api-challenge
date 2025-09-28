package question02

import com.tinnova.desafio.question02.executeBubbleSort
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BubbleSortTest {

    @Test
    fun `test sorting an unsorted array`() {
        val input = intArrayOf(5, 3, 8, 1, 2)
        val result = executeBubbleSort(input)

        // Verifica se o array foi ordenado corretamente
        assertEquals(listOf(1, 2, 3, 5, 8), result.sortedArray)

        // Verifica se o array original permaneceu igual na propriedade originalArray
        assertEquals(listOf(5, 3, 8, 1, 2), result.originalArray)

        // Verifica se o tempo de execução é maior ou igual a zero
        assertTrue(result.executionTimeMicroseconds >= 0)
    }

    @Test
    fun `test already sorted array`() {
        val input = intArrayOf(1, 2, 3, 4, 5)
        val result = executeBubbleSort(input)

        assertEquals(listOf(1, 2, 3, 4, 5), result.sortedArray)
        assertEquals(listOf(1, 2, 3, 4, 5), result.originalArray)
    }

    @Test
    fun `test array with duplicate values`() {
        val input = intArrayOf(4, 2, 2, 1, 3)
        val result = executeBubbleSort(input)

        assertEquals(listOf(1, 2, 2, 3, 4), result.sortedArray)
        assertEquals(listOf(4, 2, 2, 1, 3), result.originalArray)
    }

    @Test
    fun `test empty array`() {
        val input = intArrayOf()
        val result = executeBubbleSort(input)

        assertEquals(emptyList<Int>(), result.sortedArray)
        assertEquals(emptyList<Int>(), result.originalArray)
    }

    @Test
    fun `test single element array`() {
        val input = intArrayOf(42)
        val result = executeBubbleSort(input)

        assertEquals(listOf(42), result.sortedArray)
        assertEquals(listOf(42), result.originalArray)
    }
}
