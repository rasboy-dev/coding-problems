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
        println(if (ans) "yes" else "no")
    }
}

private fun problem(reader: BufferedReader): Boolean {
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine()

    val res = solve(n, arr)

    return res
}

private fun solve(n: Int, arr: String): Boolean {
    val sPos = arr.indices.filter { arr[it] == 's' }
    val pPos = arr.indices.filter { arr[it] == 'p' }

    if (pPos.isNotEmpty() && sPos.isNotEmpty()) {
        if (pPos.first() < sPos.last()) {
            return false
        }

        if (pPos.first() < n - 1 && sPos.last() > 0) {
            return false
        }
    }
    return true
}