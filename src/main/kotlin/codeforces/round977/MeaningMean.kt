package codeforces.round977

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue

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
    val n = reader.readLine().toInt()
    val list = reader.readLine().split(" ").map {it.toInt()}
    val ll = LinkedList(list)
    val res = solve(n, ll)

    return res
}

private fun solve(n: Int, arr: List<Int>): Int {
    val odd = PriorityQueue<Int>()
    val even = PriorityQueue<Int>()

    for (a in arr) {
        if (a % 2 == 0) {
            even.add(a)
        } else {
            odd.add(a)
        }
    }

    while (odd.size + even.size > 1) {
        if (odd.size >= 2) {
            val a = odd.remove()
            val b = odd.remove()
            val mean = (a + b) / 2
            if (mean % 2 == 0) {
                even.add(mean)
            } else {
                odd.add(mean)
            }
        } else if (even.size >= 2) {
            val a = even.remove()
            val b = even.remove()
            val mean = (a + b) / 2
            if (mean % 2 == 0) {
                even.add(mean)
            } else {
                odd.add(mean)
            }
        } else {
            val a = even.remove()
            val b = odd.remove()
            val mean = (a + b) / 2
            if (mean % 2 == 0) {
                even.add(mean)
            } else {
                odd.add(mean)
            }
        }
    }

    val res = if (odd.isEmpty()) even.remove() else odd.remove()

    return res
}