package codeforces.round993

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        BufferedWriter(OutputStreamWriter(System.out)).use { writer ->
            val t = reader.readLine().toInt()
            for (q in 1..t) {
                val (n) = reader.readLine().split(" ").map { it.toInt() }
                val arr = reader.readLine().split(" ").map { it.toInt() }
                val set = arr.toSet()

                val res = mutableListOf<Int>()
                for (a in set) {
                    res.add(a)
                }

                var d = 1
                for (i in 1..n - set.size) {
                    while (d in set) {
                        d++
                    }
                    res.add(d)
                    d++
                }
                writer.appendLine(res.joinToString(" "))
            }
        }
    }
}