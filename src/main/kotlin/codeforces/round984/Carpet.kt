package codeforces.round984

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

    val carpet = Array(n) { intArrayOf() }
    for (i in 0 until n) {
        carpet[i] = reader.readLine().map{ it.digitToInt() }.toIntArray()
    }

    val res = solve(n, m, carpet)

    return res
}

private fun solve(n: Int, m: Int, carpet: Array<IntArray>): Int {
    val target = listOf(1, 5, 4, 3)
    val layerCount = minOf(n, m)/2
    for (layer in 0 until layerCount) {
        var k = 0
        var (i, j) = Pair(layer, layer)
        while (j < m - 1 - layer) {
            if (carpet[i][j] == target[k]) {
                k++
            } else {
                k = 0
            }
            if (k > 3) {
                k = 0
                // target found
            }
            j++
        }

//        for (i in layer until n-layer) {
//
//        }
//        for (i in layer until n-layer) {
//
//        }
//        for (i in layer until n-layer) {
//
//        }
    }
    return 0
}

//private fun check(layer: Int, i: Int, j: Int, carpet: Array<IntArray>) {
//    if (i > )
//}