package dev.rasboy.round987

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections.max

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
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n, arr)

    return res
}

private fun solve(n: Int, arr: List<Int>): Int {
    val counts = mutableMapOf<Int, Int>()
    for (i in 0 until n) {
        counts[arr[i]] = counts.getOrDefault(arr[i], 0) + 1
    }
    return n - max(counts.values)
}