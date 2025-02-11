package codeforces.round997

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
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    var x = 0
    var y = 0
    reader.readLine()
    for (i in 1..<n) {
        val (xi, yi) = reader.readLine().split(" ").map { it.toInt() }
        x += xi
        y += yi
    }

    return 2 * (x + m + y + m)
}



