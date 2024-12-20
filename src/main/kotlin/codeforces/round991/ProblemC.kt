package codeforces.round991

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
        println(if (ans) "yes" else "no")
    }
}

private fun problem(reader: BufferedReader): Boolean {
    val n = reader.readLine()

    val res = solve(n)

    return res
}

private fun solve(n: String): Boolean {
    var sum = 0
    val replaceable = mutableMapOf(2 to 0, 3 to 0)
    for (c in n) {
        val d = c.digitToInt()
        if (d in replaceable) {
            replaceable[d] = replaceable[d]!! + 1
        }
        sum += d
    }

    val rem = sum % 9

    for (a in 0..minOf(10, replaceable[2]!!)) {
        for (b in 0..minOf(10, replaceable[3]!!)) {
            if ((a * 2 + b * 6 + rem) % 9 == 0) {
                return true
            }
        }
    }


    return false
}