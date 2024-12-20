package codeforces.round984

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

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
    val (n, k) = reader.readLine().split(" ").map { it.toInt() }
    val brandIncomes = mutableMapOf<Int, Int>()

    for (i in 1..k) {
        val (b, c) = reader.readLine().split(" ").map { it.toInt() }
        brandIncomes[b] = brandIncomes.getOrDefault(b, 0) + c
    }

    return solve(n, brandIncomes)
}

private fun solve(n: Int, brandIncomes: Map<Int, Int>): Int {
    return brandIncomes.values.sorted().reversed().slice(0 until minOf(n, brandIncomes.size)).sum()
}

