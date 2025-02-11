package dev.rasboy.round984

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
        problem(reader)
    }
}

val wants = listOf(1, 1, 0, 0)

private fun check(s: IntArray, pos: Int): Boolean {
    var fav = true
    for (j in wants.indices) {
        fav = fav && s[pos+j] == wants[j]
    }
    return fav
}

private fun problem(reader: BufferedReader) {
    val s = reader.readLine().map { it.digitToInt()}.toIntArray()
    val favourites = mutableSetOf<List<Int>>()

    for (i in 0 .. s.size - wants.size) {
        if (check(s, i)) {
            favourites.add((i..i + wants.lastIndex).toList())
        }
    }

    val qn = reader.readLine().toInt()
    for (q in 1..qn) {
        val (i1, v) = reader.readLine().split(" ").map {it.toInt()}

        val i = i1-1
        s[i] = v

        val hasInt = solve(i, s, favourites)

        println(if (hasInt) "yes" else "no")
    }
}

private fun solve(
    i: Int,
    s: IntArray,
    favourites: MutableSet<List<Int>>
): Boolean {
    for (j in wants.indices) {
        val st = i - j
        if (st in (0..s.size - wants.size)) {
            val l = (st..st + wants.lastIndex).toList()
            if (check(s, st)) {
                favourites.add(l)
            } else {
                favourites.remove(l)
            }
        }
    }
    return favourites.isNotEmpty()
}

fun solveBf(s: IntArray): Boolean {
    for (i in 0 until s.size - 3) {
        if (s.slice(i..i+3) == listOf(1, 1, 0, 0)) {
            return true
        }
    }
    return false
}
