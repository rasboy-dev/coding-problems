package dev.rasboy.round988

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val middle = listOf(2, 4, 5, 1, 3)

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
        val ans = problem(reader)
        writer.append(ans.joinToString(" "))
        writer.newLine()
    }
}

//private fun test1() {
//    for (n in 2 .. 1000) {
//        val res = solve(n)
//        for (i in 0 until res.lastIndex) {
//            if ((res[i] + res[i+1]) % 2 != 0 && res[i] + res[i+1] != 9) {
//                throw AssertionError("i = $i; ${res.joinToString(" ")}")
//            }
//        }
//    }
//}

private fun problem(reader: BufferedReader): List<Int> {
    val n = reader.readLine().toInt()

    val res = solve(n)

    return res
}

private fun solve(n: Int): List<Int> {
    if (n < 5) {
        return listOf(-1)
    }

    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    for (i in n downTo 6) {
        if (i % 2 == 0) {
            left.add(i)
        } else {
            right.add(i)
        }
    }

    left.addAll(middle)
    left.addAll(right)

    return left
}