package dev.rasboy.round995
import java.io.BufferedReader
import java.io.InputStreamReader

class ProblemD {
    companion object {

        fun test(reader: BufferedReader) {

            val t = reader.readLine().toInt()

            for (q in 1..t) {
                val ans = problem(reader)
                println(ans)
            }
        }

        private fun problem(reader: BufferedReader): Long {
            val (n, x, y) = reader.readLine().split(" ").map { it.toLong() }
            val arr = reader.readLine().split(" ").map { it.toLong() }
            return solve(n.toInt(), x, y, arr)
        }

        private fun solve(n: Int, x: Long, y: Long, arr: List<Long>): Long {
            val aSorted = arr.sorted()
            val sum = arr.sum()
            val minS = sum - y
            val maxS = sum - x
            var c = 0L
            for (i in 1 until n) {
                val min = minS - aSorted[i]
                val max = maxS - aSorted[i]

                val minI = findMin(aSorted, min, i - 1)
                val maxI = findMax(aSorted, max, i - 1)
                if (minI < 0 || maxI < 0) continue
                c += if (maxI >= minI) maxI - minI + 1 else 0
            }
            return c
        }
    }
}

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        ProblemD.test(reader)
    }
}

private fun findMin(arr: List<Long>, min: Long, rMax: Int): Int {
    var l = 0
    var r = rMax
    while (l < r) {
        val mid = (l + r) / 2
        if (arr[mid] >= min) {
            r = mid
        } else {
            l = mid + 1
        }
    }
    val minI = if (l == rMax && arr[l] < min) -1 else l
    return minI
}

private fun findMax(arr: List<Long>, max: Long, rMax: Int): Int {
    var l = 0
    var r = rMax
    while (l < r) {
        val mid = (l + r) / 2
        if (arr[mid] > max) {
            r = mid
        } else {
            l = mid + 1
        }
    }
    var maxI: Int = if (arr[l] > max) l-1 else l
    maxI = if (maxI == 0 && arr[maxI] > max) -1 else maxI

    return maxI
}
