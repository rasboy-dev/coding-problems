package codeforces.round997

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        test(reader)
    }
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println(ans.joinToString(" "))
    }
}

private fun problem(reader: BufferedReader): IntArray {
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val p = IntArray(n)
    for (i in 0..<n) {
        val gi = reader.readLine()
        val left = (0..<i).count { j -> gi[j] == '0' }
        val right = (i+1..<n).count { j -> gi[j] == '0' }
        val diff = right - left
        p[i + diff] = i+1
    }
    return p
}
