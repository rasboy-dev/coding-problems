package codeforces.round995

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        test(reader)
    }
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
    val a = reader.readLine().split(" ").map { it.toInt() }
    val b = reader.readLine().split(" ").map { it.toInt() }

    return solve(n, a, b)
}

private fun solve(n: Int, a: List<Int>, b: List<Int>): Int {
    val diffs = IntArray(n)
    for (i in 0 until n-1) {
        diffs[i] = a[i] - b[i+1]
    }
    diffs[n-1] = a[n-1]
    return diffs.filter { it > 0 }.sum()
}
