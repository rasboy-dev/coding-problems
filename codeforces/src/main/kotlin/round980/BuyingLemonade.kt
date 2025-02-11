package dev.rasboy.round980

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
    val (n, k) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }.toIntArray()

    val res = solve(n, k, arr)

    return res
}

private fun solve(n: Int, k: Int, a: IntArray): Long {
    a.sort()

    var total = 0L
    for (i in 0..n-1) {
        val guaranteed = a[i] * (n - i)
        if (k - total <= guaranteed) {
            return (k + i).toLong()
        }
        total += guaranteed
    }

    return -1
}