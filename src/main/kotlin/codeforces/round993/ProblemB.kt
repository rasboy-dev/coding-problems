package codeforces.round993

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        val t = reader.readLine().toInt()
        for (q in 1..t) {
            val s = StringBuilder()
            val a = reader.readLine()
            for (c in a.reversed()) {
                s.append(when (c) {
                    'q' -> 'p'
                    'p' -> 'q'
                    else -> c
                })
            }
            println(s.toString())
        }
    }
}