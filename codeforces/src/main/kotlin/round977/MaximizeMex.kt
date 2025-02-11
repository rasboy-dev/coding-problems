package dev.rasboy.round977

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

private fun problem(reader: BufferedReader, writer: BufferedWriter): Long {
    val (n, x) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toLong() }

    val res = solve(n, x.toLong(), arr.toMutableList())

    return res
}

private fun solve(n: Int, x: Long, arr: MutableList<Long>): Long {
    val uniq = mutableSetOf<Long>()
    for (i in 0..n - 1) {
        while (arr[i] in uniq) {
            arr[i] += x
        }
        uniq.add(arr[i])
    }

    arr.sort()

    if (arr[0] > 0)
        return 0

    var left = 0
    var right = n - 1
    var min = 0L
    while (left < right) {
        val mid = (left + right) / 2
        if (arr[mid] > mid) {
            right = mid
        } else {
            left = mid + 1
            min = arr[mid]
        }
    }
    return if (arr[left] == left.toLong()) left.toLong() + 1 else min + 1
}