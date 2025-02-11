package dev.rasboy.round992

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

    val res = solve(n)

    return res
}

private fun solve(n: Int): Int {
    if (n == 1) return 1
    if (n == 2 || n == 3) return 2

    var i = 2
    var painted = 4
    while (painted < n) {
        painted = (painted + 1) * 2
        i++
    }
    return i
}