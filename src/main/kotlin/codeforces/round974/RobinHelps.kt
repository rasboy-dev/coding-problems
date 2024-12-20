package codeforces.round974

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val t = reader.readLine().toInt()

    for (i in 0 until t) {
        val (_, k) = reader.readLine().split(" ").map { it.toInt() }

        val golds = reader.readLine().split(" ").map { it.toInt() }

        println(solve(k, golds))
    }

    reader.close()
}

private fun solve(k: Int, golds: List<Int>): Int {
    var robin = 0
    var given = 0
    for (gold in golds) {
        if (gold >= k) {
             robin += gold
        } else if (gold == 0 && robin > 0) {
            robin--
            given++
        }
    }
    return given
}
