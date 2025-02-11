package dev.rasboy

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        val t = reader.readLine().toInt()
        for (q in 1..t) {
            val ans = solve(reader)
//            println(if (ans) "yes" else "no")
            println(ans)
        }
    }
}

private fun solve(reader: BufferedReader): Int {
    val (n, x) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }
    return 0
}