package codeforces.round975

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
//    val t = reader.readLine().toInt()
    val t  = 10000

    for (i in 0 until t) {
//        val k = reader.readLine().toLong()
        val k = (1L..1000000000L).random()
        val ans = solve2(k)
        println(ans)
    }

    reader.close()
}

private fun solve(k: Long): Int {
    var n = 0
    while (true) {
        n++
        val bulbs = BooleanArray(n) { true }
        for (i in 1..n) {
            for (idx in 0..n - 1) {
                if ((idx + 1) % i == 0) {
                    bulbs[idx] = bulbs[idx].not()
                }
            }
        }
        var counter = 0L
        for (b in bulbs) {
            if (b) {
                counter++
            }
        }
        if (counter == k) {
            return n
        }
    }
}

private fun solve2(k: Long): Int {
    var n = 1
    var counter = 0L
    while (true) {
        n++
        var factors = 1
        trialDivision2(n.toLong()).forEach({ factors *= it.value })
        counter += factors % 2
        if (counter == k) {
            return n
        }
    }
}

fun trialDivision2(n: Long): MutableMap<Long, Int> {
    val factorization = mutableMapOf<Long, Int>()
    var num = n

    // Divide by 2 until n is odd
    while (num % 2 == 0L) {
        factorization[2L] = factorization.getOrDefault(2L, 0) + 1
        num /= 2
    }

    // Check odd numbers from 3 upwards
    var d = 3L
    while (d * d <= num) {
        while (num % d == 0L) {
            factorization[d] = factorization.getOrDefault(d, 0) + 1
            num /= d
        }
        d += 2
    }

    // If num is still greater than 1, it must be prime
    if (num > 1) {
        factorization[num] = factorization.getOrDefault(num, 0) + 1
    }

    return factorization
}