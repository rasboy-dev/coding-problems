package dev.rasboy

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        BufferedWriter(OutputStreamWriter(System.out)).use { writer ->

            val t = reader.readLine().toInt()
            for (q in 1..t) {
                val (n) = reader.readLine().split(" ").map { it.toLong() }
            }
        }
    }
}