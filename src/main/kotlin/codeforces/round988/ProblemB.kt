package codeforces.round988

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    test(reader)

    reader.close()
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println("${ans.first} ${ans.second}")
    }
}

private fun problem(reader: BufferedReader): Pair<Int, Int> {
    val n = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n, arr)

    return res
}

private fun solve(k: Int, arr: List<Int>): Pair<Int, Int> {
    val n = k - 2
    val numbers = arr.toSet()
    for (i in 1..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0 && i in numbers && n / i in numbers) {
            return Pair(i, n / i)
        }
    }
    return Pair(-1, -1)
}