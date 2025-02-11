package dev.rasboy.round979

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
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n, arr)

    return res
}

private fun solve(n: Int, arr: List<Int>): Int {
    var max = arr[0]
    var min = arr[0]
    for (ai in arr) {
        max = maxOf(max, ai)
        min = minOf(min, ai)
    }
    return (max - min) * (n - 1)
}