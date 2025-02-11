package dev.rasboy.round995

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
    val (n, a, b, c) = reader.readLine().split(" ").map { it.toInt() }

    return solve(n, a, b, c)
}

private fun solve(n: Int, a: Int, b: Int, c: Int): Int {
    val sum3 = a + b + c
    val daysAll3 = n / sum3
    var rem = n % sum3
    val days = listOf(a, b, c)
    var i = 0
    while (i < 3 && rem > 0) {
        rem -= days[i]
        i++
    }
    return daysAll3 * 3 + i
}
