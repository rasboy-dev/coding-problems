package codeforces.round994

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    test(reader)

    reader.close()
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println(ans.joinToString(" "))
    }
}

private fun problem(reader: BufferedReader): IntArray {
    val (n, x, y) = reader.readLine().split(" ").map { it.toInt() }
//    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n, x, y)

    return res
}

private fun solve(n: Int, x: Int, y: Int): IntArray {
    val res = IntArray(n) { -1 }
    if (abs(x - y) % 2 == 0) {
        res[x-1] = 0
        res[y-1] = 1
    } else {
        res[x-1] = 0
        res[y-1] = 2
    }
    for (i in 0 until n) {
        if (res[i] == -1) {
            res[i] = (res[(i - 1 + n) % n] + 1) % 3
        }
    }
    return res
}