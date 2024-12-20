package codeforces.round980

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
    val (a, b) = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(a, b)

    return res
}

private fun solve(a: Int, b: Int): Int {
    if (b - 2 * a > 0) {
        return 0
    }
    if (a - b >= 0) {
        return a
    }
    return 2 * a - b
}