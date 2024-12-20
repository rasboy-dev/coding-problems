package coderun.seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val (left, right) = reader.readLine().split(" ").map { it.toInt() }

    val blues = mutableListOf<Pair<Int, Int>>()
    val reds = mutableListOf<Pair<Int, Int>>()

    for (i in 0 until n) {
        val (l, r) = reader.readLine().split(" ").map { it.toInt() }
        blues.add(Pair(l, r))
    }
    for (i in 0 until m) {
        val (l, r) = reader.readLine().split(" ").map { it.toInt() }
        reds.add(Pair(l, r))
    }

    blues.sortBy { it.first }
    reds.sortBy { it.first }

    val leftShift = reds[0].first - left
    val rightShift = right - reds[reds.lastIndex].second

    val blueStarts = blues.map { it.first }
    val blueEnds = blues.map { it.second }

    if (reds[reds.lastIndex].second - blues[0].first <= leftShift ||
        blues[blues.lastIndex].second - reds[0].first <= rightShift
    ) {
        println(0)
        return
    }

    var minOverlap = Int.MAX_VALUE
    for (shift in leftShift downTo 0) {
        minOverlap = minOf(findOverlap(reds, blues, -shift, blueStarts, blueEnds), minOverlap)
    }
    for (shift in 1..rightShift) {
        minOverlap = minOf(findOverlap(reds, blues, shift, blueStarts, blueEnds), minOverlap)
    }

    println(minOverlap)

    reader.close()
    writer.close()
}

fun findOverlap(reds: List<Pair<Int, Int>>, blues: List<Pair<Int, Int>>, shift: Int, blueStarts: List<Int>, blueEnds: List<Int>): Int {
    var overlap = 0
    var i = 0
    for (red in reds) {
        val redShiftedLeft = red.first + shift
        val redShiftedRight = red.second + shift

        if (i >= blues.size)
            return overlap

        // while b is not on the right and not overlap
        var left = i
        var right = blues.size - 1
        while (left <= right) {
            var mid = (left + right) / 2
            if (redShiftedLeft in blues[mid].first until blues[mid].second) {
                i = mid
                break
            } else if (redShiftedLeft < blues[mid].first) {
                right = mid - 1
            } else {
                left = mid + 1
                i = left
            }
        }




        while (i < blues.size && (redShiftedLeft > blues[i].first || redShiftedRight > blues[i].first)) {
            val blue = blues[i]
            if (redShiftedLeft in blue.first..blue.second) {
                overlap += blue.second - redShiftedLeft
                if (redShiftedRight < blue.second) {
                    overlap -= blue.second - redShiftedRight
                    break
                }
                i++
            }
            if (redShiftedLeft <= blue.first && blue.second <= redShiftedRight) {
                overlap += blue.second - blue.first
                i++
            }
            if (redShiftedRight in blue.first..blue.second) {
                overlap += redShiftedRight - blue.first
                break
            }
        }
        if (i >= blues.size)
            break
    }
    return overlap
}