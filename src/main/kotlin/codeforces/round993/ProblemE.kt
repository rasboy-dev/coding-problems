package codeforces.round993

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        BufferedWriter(OutputStreamWriter(System.out)).use { _ ->

            val t = reader.readLine().toInt()
            for (q in 1..t) {
                val (k, l1, r1, l2, r2) = reader.readLine().split(" ").map { it.toLong() }

                val kns = mutableListOf(Pair(0, 1L))
                var knt = 1L
                var nt = 0
                while (knt * k <= 1_000_000_000) {
                    knt *= k
                    nt += 1
                    kns.add(Pair(nt, knt))
                }
                var count = 0L
                for ((_, kn) in kns) {
                    val a = if (l2 % kn == 0L) l2 / kn else l2 / kn + 1
                    val b = r2 / kn

                    if (a <= b) {
                        val minx = maxOf(a, l1)
                        val maxx = minOf(b, r1)

                        if (minx <= maxx) {
                            count += maxx - minx + 1
                        }
                    }

                }
                println(count)
            }
        }
    }
}