package dev.rasboy.round975

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val t = reader.readLine().toInt()

    for (i in 0 until t) {
        val (n, q) = reader.readLine().split(" ").map { it.toInt() }
        val points = reader.readLine().split(" ").map { it.toInt() }
        val queries = reader.readLine().split(" ").map { it.toLong() }

        println(solve(n, q, points, queries))
    }

    reader.close()
}

private fun solve(n: Int, q: Int, points: List<Int>, queries: List<Long>): String {
    val counts = mutableMapOf<Long, Long>()
    for (i in 0 until n-1) {
        val count: Long = (i + 1).toLong() * (n - i - 1).toLong()
        counts[count] = counts.getOrDefault(count, 0) + points[i + 1] - points[i] - 1
        counts[count + i] = counts.getOrDefault(count + i, 0) + 1
    }
    counts[(n - 1).toLong()] = counts.getOrDefault((n - 1).toLong(), 0) + 1

    val ans = StringBuilder()
    for (q in queries) {
        ans.append(counts.getOrDefault(q, 0)).append(" ")
    }
    ans.deleteAt(ans.lastIndex)
    return ans.toString()
}
