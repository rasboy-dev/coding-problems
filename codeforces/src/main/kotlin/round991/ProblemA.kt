package dev.rasboy.round991

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    test(reader)

    reader.close()
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println(ans)
    }
}

private fun problem(reader: BufferedReader): Int {
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val words = mutableListOf<String>()
    for (i in 1..n) {
        words.add(reader.readLine())
    }

    val res = solve(words, m)

    return res
}

private fun solve(words: List<String>, m: Int): Int {
    var wordsCount = 0
    var charsLeft = m
    for (word in words) {
        if (charsLeft - word.length >= 0) {
            charsLeft -= word.length
            wordsCount++
        } else {
            break
        }
    }
    return wordsCount
}