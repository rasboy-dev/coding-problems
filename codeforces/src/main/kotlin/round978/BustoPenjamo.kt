package dev.rasboy.round978

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    test(reader, writer)

    reader.close()
    writer.close()
}

private fun test(reader: BufferedReader, writer: BufferedWriter) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader, writer)
        println(ans)
    }
}

private fun problem(reader: BufferedReader, writer: BufferedWriter): Int {
    val (n, r) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val res = solve(n, r, arr)

    return res
}

private fun solve(n: Int, r: Int, arr: List<Int>): Int {
    var hp = 0
    var singles = 0
    for (i in 0..n-1) {
        hp += arr[i] / 2
        singles += arr[i] % 2
    }

    return 2 * hp + minOf(singles, 2 * (r - hp) - singles)
}