package codeforces.round992

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
        if (ans >= 0) {
            println("yes")
            println(ans+1)
        } else {
            println("no")
        }
    }
}

private fun problem(reader: BufferedReader): Int {
    val (_, k) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(k, arr)

    return res
}

private fun solve(k: Int, arr: List<Int>): Int {
    val remCounts = mutableMapOf<Int, Int>()
    for (i in arr.indices) {
        val a = arr[i]
        remCounts[a % k] = remCounts.getOrDefault(a % k, 0) + 1
    }
    val rem = remCounts.firstNotNullOfOrNull { if (it.value == 1) it.key else null}
    if (rem != null) {
        return arr.indices.find { arr[it] % k == rem }!!
    }
    return -1
}