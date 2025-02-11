package dev.rasboy.round986

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    test(reader)

    reader.close()
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println(if (ans) "Yes" else "no")
    }
}

private fun problem(reader: BufferedReader): Boolean {
//    val (n, xq, yq) = reader.readLine().split(" ").map { it.toInt() }
//    val str = reader.readLine()

    val n = (1..10).random()
    val xq = (1..10).random()
    val yq = (1..10).random()
    val string = StringBuilder()
    for (i in 0 until n) {
        string.append(listOf('N', 'W', 'S', 'E').random())
    }

    val str = string.toString()
    val res = solve(n, str, xq, yq)
    val res1 = solve1(str, xq, yq)
    if (res != res1) {
        println("$n $xq $yq")
        println(str)
        throw AssertionError()
    }

    return res
}

private fun solve(n: Int, str: String, xq: Int, yq: Int): Boolean {
    var x = 0
    var y = 0
    for (i in 0..4000) {
        when (str[i % n]) {
            'N' -> {
                y++
            }

            'E' -> {
                x++
            }

            'S' -> {
                y--
            }

            'W' -> {
                x--
            }
        }
        if (x == xq && y == yq) {
            return true
        }
    }
    return false
}

private fun solve1(str: String, xq: Int, yq: Int): Boolean {
    if (xq == 0 && yq == 0) return true

    var x = 0
    var y = 0

    val visited = mutableSetOf(Pair(0, 0))

    for (d in str) {
        when (d) {
            'N' -> {
                y++
            }

            'E' -> {
                x++
            }

            'S' -> {
                y--
            }

            'W' -> {
                x--
            }
        }
        if (xq == x && yq == y) return true
        visited.add(Pair(x, y))
    }

    if (x == 0 && y == 0) return false

    val fx = x
    val fy = y
    while (abs(x) <= 20 && abs(y) <= 20) {
        for (p in visited) {
            if (x + p.first == xq && y + p.second == yq)
                return true
        }
        x += fx
        y += fy
    }

    return false
}