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
    val arr = reader.readLine().split(" ").map { it.toInt() }.toIntArray()

    val res = solve(n, arr)

    return res
}

private fun solve(n: Int, arr: IntArray): Int {
    var l = 1
    var r = n - 2
    while (l < r) {
        if (arr[l] == arr[l - 1] || arr[r] == arr[r + 1]) {
            swap(arr, l, r)
        }
        l++
        r--
    }
    var totalDist = 0
    for (i in 1..n-1) {
        if (arr[i-1] == arr[i])
            totalDist += 1
    }
    return totalDist
}

fun swap(arr: IntArray, l: Int, r: Int) {
    val tmp = arr[l]
    arr[l] = arr[r]
    arr[r] = tmp
}
