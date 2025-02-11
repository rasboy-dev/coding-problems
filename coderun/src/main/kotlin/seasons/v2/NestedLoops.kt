package seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val boundaries = mutableListOf<Pair<Any, Any>>()
    val varValues = mutableListOf<List<Int>>()
    val numBoundaries = mutableListOf<Pair<Int, Int>>()

    val (l1, r1) = reader.readLine().split(" ").map { it.toInt() }
    boundaries.add(Pair(l1, r1))
    varValues.add((l1..r1+1).toList())
    numBoundaries.add(Pair(l1, r1))

    for (i in 1 until n) {
        val lri = reader.readLine().split(" ")
        val li = if (isNumeric(lri[0])) lri[0].first() else lri[0].toInt()
        val ri = if (isNumeric(lri[1])) lri[1].first() else lri[1].toInt()

        boundaries.add(Pair(li, ri))

        val l = if (li is Char) {
            varValues[li.code - 'a'.code][0]
        } else {
            li as Int
        }
        val r = if (ri is Char) {
            varValues[ri.code - 'a'.code][0]
        } else {
            ri as Int
        }
    }



    reader.close()
    writer.close()
}

private fun isNumeric(v: String): Boolean {
    return v.toIntOrNull() != null
}