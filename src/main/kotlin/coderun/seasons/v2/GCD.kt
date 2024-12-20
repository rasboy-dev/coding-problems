package coderun.seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun gcd(p: Int, q: Int): Int {
    return if (q == 0) {
        p
    } else gcd(q, p % q)
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val a = reader.readLine().split(" ").map { it.toInt() }.toMutableList()
    val m = reader.readLine().toInt()
    val b = reader.readLine().split(" ").map { it.toInt() }.toMutableList()

    var resGCD = 1L
    var over1b = false

    for (i in 0 until n) {
        var j = 0
        while (j < m && a[i] > 1) {
            val gcd = gcd(a[i], b[j])
            if (gcd > 1) {
                a[i] = a[i] / gcd
                b[j] = b[j] / gcd
                resGCD *= gcd
                if (resGCD >= 1_000_000_000) {
                    resGCD %= 1_000_000_000
                    over1b = true
                }
            }
            j++
        }
    }

    val prefix = if (over1b) "0".repeat(9 - resGCD.toString().length) else ""
    writer.write("$prefix$resGCD")

    reader.close()
    writer.close()
}