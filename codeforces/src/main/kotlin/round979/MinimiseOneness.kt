package dev.rasboy.round979

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
        println(ans)
    }
}

private fun problem(reader: BufferedReader): String {
    val (n) = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n)

    return res
}

private fun solve(n: Int): String {
    return '1' + "0".repeat(n-1)
}

private fun bf(n: Int): Int {
    println()
    println("n = $n")

    val total = (2 shl (n - 1)) - 1

    println("total = $total")

    var min: Pair<Int, Int> = Pair(Int.MAX_VALUE, 0)
    for (i in 0..n) {
        println("$i zeros")
        val ft = if (i == 0) 0 else (2 shl (i - 1)) - 1
        println("f(t) = $ft")
        val gt = total - ft
        println("g(t) = $gt")
        val oneness = abs(gt - ft)
        println("|f(t) - g(t)| = $oneness")
        min = minOf(Pair(oneness, i), min) { a: Pair<Int, Int>, b: Pair<Int, Int> -> a.first.compareTo(b.first) }
    }
    println("min oneness = ${min.first}, zero count = ${min.second}")
    return 1
}