package codeforces.round993

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        val t = reader.readLine().toInt()
        for (q in 1..t) {
            val s = StringBuilder()
            val (m, a, b, c) = reader.readLine().split(" "). map { it.toLong() }

            val aSeated = minOf(m, a)
            val bSeated = minOf(m, b)
            val cSeated = minOf(2 * m - aSeated - bSeated, c)

            println(aSeated + bSeated + cSeated)
        }
    }
}