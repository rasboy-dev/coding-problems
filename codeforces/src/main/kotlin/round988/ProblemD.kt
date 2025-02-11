package dev.rasboy.round988

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayDeque

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
    val (n, m, _) = reader.readLine().split(" ").map { it.toInt() }
    val boosts = ArrayDeque<Pair<Int, Int>>()
    val obstacles = ArrayDeque<Pair<Int, Int>>()
    for (i in 1 .. n) {
        val obs = reader.readLine().split(" ").map {it.toInt()}
        obstacles.addLast(Pair(obs[0], obs[1]))
    }
    for (i in 1..m) {
        val boost = reader.readLine().split(" ").map {it.toInt()}
        boosts.addLast(Pair(boost[0], boost[1]))
    }

    val res = solve(boosts, obstacles)

    return res
}

private fun solve(boosts: ArrayDeque<Pair<Int, Int>>, obstacles: ArrayDeque<Pair<Int, Int>>): Int {
    var boostCounter = 0
    var jump = 1
    val boostQueue = PriorityQueue<Int> { a, b -> b.compareTo(a)}
    while (obstacles.isNotEmpty()) {
        val os = obstacles.removeFirst()
        val (l, r) = os

        if (l - 1 + jump > r) {
            continue
        }

        while (boosts.isNotEmpty() && boosts.first().first < l) {
            val boost = boosts.removeFirst()
            boostQueue.add(boost.second)
        }

        while (boostQueue.isNotEmpty() && jump + l - 1 <= r) {
            jump += boostQueue.remove()
            boostCounter++
        }

        if (boostQueue.isEmpty() && jump + l - 1 <= r) {
            return -1
        }
    }
    return boostCounter
}