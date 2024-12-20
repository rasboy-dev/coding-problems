package codeforces.round979

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
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine()

    val res = solve(n, arr)

    return res
}

private fun solve(n: Int, str: String): Boolean {
    if (str[0] == '1' || str[n - 1] == '1')
        return true
    for (i in 1..n-1)
        if (str[i] == '1' && str[i - 1] == '1')
        return true
    return false
}