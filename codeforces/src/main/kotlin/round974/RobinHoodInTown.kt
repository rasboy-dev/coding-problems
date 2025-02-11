package dev.rasboy.round974

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val t = reader.readLine().toInt()

    for (i in 0 until t) {
        val n = reader.readLine().toInt()
        val wealths = reader.readLine().split(" ").map { it.toInt() }

        println(solve(n, wealths))
    }

    reader.close()
}

private fun solve(n: Int, wealth: List<Int>): Int {
    if (n == 1) {
        return -1
    }
    var aw = 0.0
    var maxIdx = 0
    for (i in 0..n -1) {
        if (wealth[i] > wealth[maxIdx]) {
            maxIdx = i
        }
        aw += wealth[i].toDouble() / n
    }
    val x = DoubleArray(n)
    for (i in 0 .. n-1) {

        val ai = wealth[i]
        val m = 2 * ai - aw
        val xin = if (m < 0) 0.0 else m + 1.0 / n
        x[i] = xin
    }
    x.sort()
    return (x[(n + 1) / 2] * n).toInt()
}