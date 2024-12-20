package codeforces.round987

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


//val squares = mutableSetOf<Int>()

fun main(args: Array<String>) {
//    var d = 1
//    while (d * d <= 2 * 100_000) {
//        squares.add(d * d)
//        d++
//    }

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    test(reader, writer)

    reader.close()
    writer.close()
}

private fun test(reader: BufferedReader, writer: BufferedWriter) {

    val t = reader.readLine().toInt()
//    val t = 100

    for (q in 1..t) {
        val ans = problem(reader)
        writer.write(ans)
        writer.newLine()
    }
}

private fun problem(reader: BufferedReader): String {
    val (n) = reader.readLine().split(" ").map { it.toInt() }
//    val n = t

    val res = solve(n)
//    validate(res)

    return res.joinToString(" ")
}

//private fun validate(arr: IntArray) {
//    if (arr[0] == -1) return
//    val pos = mutableMapOf<Int, MutableList<Int>>()
//    for (i in arr.indices) {
//        if (pos.contains(arr[i])) {
//            for (j in pos[arr[i]]!!) {
//                if (abs(i - j) !in squares) {
//                    throw AssertionError("distance $i $j in ${arr.joinToString(" ")}")
//                }
//            }
//            pos[arr[i]]!!.add(i)
//        } else {
//            pos[arr[i]] = mutableListOf(i)
//        }
//    }
//    for (ps in pos) {
//        if (ps.value.size == 1) {
//            throw AssertionError("only one ${ps.key} in ${arr.joinToString(" ")}")
//        }
//    }
//}

private fun solve(n: Int): IntArray {
    if (n % 2 == 0) {
        val res = IntArray(n)
        for (i in 1 .. n / 2) {
            res[2 * i - 1] = i
            res[2 * i - 2] = i
        }
        return res
    }
    if (n < 27) {
        return intArrayOf(-1)
    }
    val res = IntArray(n)
    val max = 1_000_000
    for (i in 1 .. 4) {
        res[2 * i] = i
        res[2 * i - 1] = i
    }
    for (i in 5 .. 11) {
        res[2 * i] = i
        res[2 * i + 1] = i
    }
    res[0] = max
    res[9] = max
    res[25] = max
    res[23] = max - 1
    res[24] = max - 1
    res[22] = max - 2
    res[26] = max - 2
    for (i in 14 .. n/2) {
        res[2 * i] = i
        res[2 * i - 1] = i
    }
    return res
}