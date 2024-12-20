package codeforces.round986

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

private fun problem(reader: BufferedReader): Long {
    val (n, b, c) = reader.readLine().split(" ").map { it.toLong() }

    val res = solve(n, b, c)

    return res
}

private fun solve(n: Long, b: Long, c: Long): Long {
    var l = 0L
    var r = n
    var maxIn = 0L
    while (l <= r) {
        val mid = (l + r) / 2
        if (b * (mid - 1) + c > n) {
            r = mid - 1
        } else {
            l = mid
            maxIn = mid
        }
    }
    return n - maxIn
}