package codeforces.round978

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections.max


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
    val (n, x) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }.toMutableList()

    val res = solve(n, x, arr)

    return res
}

private fun solve(n: Int, x: Int, arr: MutableList<Int>): Int {
    return maxOf(max(arr), n * x)
}