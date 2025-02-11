package dev.rasboy.round988

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    test(reader)

    reader.close()
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println(ans)
    }
}

private fun problem(reader: BufferedReader): Int {
    reader.readLine()
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(arr)

    return res
}

private fun solve(arr: List<Int>): Int {
    val counts = mutableMapOf<Int, Int>()
    for (a in arr) {
        counts[a] = counts.getOrDefault(a, 0) + 1
    }
    var max = 0
    for (c in counts.values) {
        max += c / 2
    }
    return max
}