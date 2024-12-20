package codeforces.round975

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val t = reader.readLine().toInt()

    for (i in 0 until t) {
        val (n, k) = reader.readLine().split(" ").map{ it.toInt() }
        val ans = solve(n, k)
        println(ans)
    }

    reader.close()
}

private fun solve(n: Int, k: Int): Int {
    if (k == 1 || n < k) {
        return n
    }
    if (n == k) {
        return 1;
    }

    var sum = n - n % k
    var steps = n % k

    while (sum > 0) {
        while (sum > 0 && sum % k == 0) {
            sum /= k
        }
        steps++
        sum--
    }

    return steps
}
