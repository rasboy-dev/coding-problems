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
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(arr, n)

    return res
}

private fun solve(arr: List<Int>, n: Int): Boolean {
    var odd = 0L
    var even = 0L
    for (i in 0 until n step 2) {
        even += arr[i]
    }
    for (i in 1 until n step 2) {
        odd += arr[i]
    }
    return even / ((n + 1) / 2) == odd / (n / 2) && even % ((n + 1) / 2) == 0L && odd % (n / 2) == 0L
}