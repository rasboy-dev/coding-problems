package dev.rasboy.round974

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val t = reader.readLine().toInt()

    for (i in 0 until t) {
        val (n, k) = reader.readLine().split(" ").map { it.toInt() }

        println(if (solve(n, k)) "YES" else "NO")
    }

    reader.close()
}

private fun solve(n: Int, k: Int): Boolean {
//    for (i in 1 .. k) {
//        odd += (n - i + 1) % 2
//    }
//    return odd % 2 == 0

    val s1 = if (n % 2 == 1) 0 else k % 2
    val s2 = (k + 1) / 2 % 2

    return (s1 + s2) % 2 == 0
}
