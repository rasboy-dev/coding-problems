package dev.rasboy.round984

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    test(reader)

    reader.close()
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println(if (ans) "yes" else "no")
    }
}

private fun problem(reader: BufferedReader): Boolean {
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n, arr)

    return res
}

private fun solve(n: Int, arr: List<Int>): Boolean {
    val perfect = listOf(5, 7)
    for (i in 1 until n) {
        if (abs(arr[i] - arr[i-1]) !in perfect) {
            return false
        }
    }
    return true
}