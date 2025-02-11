package dev.rasboy.round975

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val t = reader.readLine().toInt()

    for (i in 0 until t) {
        val n = reader.readLine().toInt()
        val arr = reader.readLine().split(" ").map { it.toInt() }

        println(solve(n, arr))
    }

    reader.close()
}

private fun solve(n: Int, arr: List<Int>): Int {
    var maxPrize = 0
    for (i in arr.indices) {
        val newPrize = arr[i] + i / 2 + 1 + (n - i + 1) / 2 - 1
        maxPrize = maxOf(newPrize, maxPrize)
    }
    return maxPrize
}
