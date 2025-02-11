package dev.rasboy.round981

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
    val matrix = Array(n) { IntArray(n) }
    for (i in 0..n-1) {
        matrix[i] = reader.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val res = solve(matrix)

    return res
}

private fun solve(matrix: Array<IntArray>): Int {
    val n = matrix.size

    var total = 0
    for (j in 1..n-1) {
        var min = Int.MAX_VALUE
        for (k in 0..n-1-j) {
            min = minOf(min, matrix[k][j + k])
        }
        if (min < 0)
            total += -min
    }
    for (i in 0..n-1) {
        var min = Int.MAX_VALUE
        for (k in 0..n-1-i) {
            min = minOf(min, matrix[i + k][k])
        }
        if (min < 0)
            total += -min
    }

    return total
}
