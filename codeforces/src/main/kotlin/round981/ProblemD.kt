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

private fun problem(reader: BufferedReader): Int {
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n, arr)

    return res
}

private fun solve(n: Int, arr: List<Int>): Int {
    var l = 0
    var r = 0
    var count = 0
    var sum = 0

    val sums = mutableSetOf<Int>()

    while (l < n) {
        do {
            sums.add(sum)
            sum += arr[r]
            r++
        } while (r < n && sum !in sums)

        while (l < r && sum != 0) {
            sum -= arr[l]
            l++
        }

        if (sum == 0 && l != r) {
            count++
            l = r
            sums.clear()
            sum = 0
        }
    }
    return count
}