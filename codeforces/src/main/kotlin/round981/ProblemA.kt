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

private fun problem(reader: BufferedReader): String {
    val (n) = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n)

    return res
}

private fun solve(n: Int): String {
    var value = 0
    var i = 1
    while (value in -n..n) {
        if (i % 2 == 0) {
            value += 2*i - 1
        } else {
            value -= 2*i - 1
        }
        i++
    }

    return if (i % 2 == 0) "Sakurako" else "Kosuke"
}