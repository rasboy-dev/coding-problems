package codeforces.round997

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
                writer.appendLine(ans.joinToString(" "))
            }
        }

        private fun problem(reader: BufferedReader): IntArray {
            val (n) = reader.readLine().split(" ").map { it.toInt() }
            val res = IntArray(n)
            res[0] = 1
            val max = n / 2
            for (i in 1..max) {
                res[i] = i
            }
            var i = 1
            while (i + max < n) {
                res[i + max] = i
                i++
            }
            return res
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
