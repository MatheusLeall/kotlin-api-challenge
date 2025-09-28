package com.tinnova.desafio.question02

import kotlinx.serialization.Serializable

@Serializable
data class BubbleSortResult(
    val originalArray: List<Int>,
    val sortedArray: List<Int>,
    val executionTimeMicroseconds: Long
)

fun bubbleSort(array: IntArray) {
    val n = array.size
    var swapped: Boolean

    for (i in 0 until n - 1) {
        swapped = false
        for (j in 0 until n - i - 1) {
            if (array[j] > array[j + 1]) {
                // swap elements
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
                swapped = true
            }
        }
        if (!swapped) break
    }
}

fun executeBubbleSort(v: IntArray): BubbleSortResult {
    val original = v.toList()

    val startTime = System.nanoTime()
    bubbleSort(v)
    val endTime = System.nanoTime()

    val durationMicroseconds = (endTime - startTime) / 1000

    return BubbleSortResult(
        originalArray = original,
        sortedArray = v.toList(),
        executionTimeMicroseconds = durationMicroseconds
    )
}
