package seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (m, n) = reader.readLine().split(" ").map { it.toInt() }
    val dp = IntArray(n)
    var prevDp = IntArray(n)
    var maxEdge = 0
    var x = 0
    var y = 0
    for (i in 0 until m) {
        prevDp = dp.copyOf()

        val row = reader.readLine().split(" ").map { it.toInt() }
        dp[0] = row[0]
        if (dp[0] > maxEdge) {
            maxEdge = dp[0]
            x = 0
            y = i
        }
        for (j in 1 until n) {
            if (row[j] == 1) {
                dp[j] = minOf(dp[j] + 1, dp[j - 1] + 1, prevDp[j - 1] + 1)
                if (dp[j] > maxEdge) {
                    maxEdge = dp[j]
                    x = j
                    y = i
                }
            } else {
                dp[j] = 0
            }
        }
    }

    println(maxEdge)
    println("${y + 2 - maxEdge} ${x + 2 - maxEdge}")

    reader.close()
    writer.close()
}