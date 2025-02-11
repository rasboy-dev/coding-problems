package dev.rasboy.round995

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class ProblemC {
    companion object {

        fun test(reader: BufferedReader, writer: BufferedWriter) {

            val t = reader.readLine().toInt()

            for (q in 1..t) {
                val ans = problem(reader)
                writer.appendLine(ans)
            }
        }

        private fun problem(reader: BufferedReader): String {
            val (n, m, k) = reader.readLine().split(" ").map { it.toInt() }
            val a = reader.readLine().split(" ").map { it.toInt() }
            val q = reader.readLine().split(" ").map { it.toInt() }
            return solve(n, m, k, a, q)
        }

        private fun solve(n: Int, m: Int, k: Int, a: List<Int>, q: List<Int>): String {
            if (k < n - 1) {
                return "0".repeat(m)
            }
            if (k == n) {
                return "1".repeat(m)
            }

            var l = 0
            var r = k-1
            while (l < r) {
                val mid = (l + r) / 2
                if (q[mid] > mid+1) {
                    r = mid
                } else {
                    l = mid + 1
                }
            }

            val missing = if (l == k - 1 && q[l] == l + 1) l + 2 else l + 1

            val res = StringBuilder()
            for (ai in a) {
                if (ai == missing) {
                    res.append("1")
                } else {
                    res.append("0")
                }
            }

            return res.toString()
        }
    }
}

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        BufferedWriter(OutputStreamWriter(System.out)).use { writer ->
            ProblemC.test(reader, writer)
        }
    }
}
