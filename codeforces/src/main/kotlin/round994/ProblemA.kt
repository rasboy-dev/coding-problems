package dev.rasboy.round994

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
    val arrSet = arr.toSet()
    if (!arrSet.contains(0)) {
        return 1
    } else if (arrSet.size == 1) {
        return 0
    }

    var i = 0
    var j = n - 1

    while (arr[i] == 0) {
        i++
    }
    while (arr[j] == 0) {
        j--
    }

    val subArr = arr.subList(i, j + 1)

    if (!subArr.contains(0)) {
        return 1
    }
    return 2
}