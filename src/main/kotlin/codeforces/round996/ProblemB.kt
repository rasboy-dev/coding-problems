import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        test(reader)
    }
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println(if (ans) "yes" else "no")
    }
}

private fun problem(reader: BufferedReader): Boolean {
    val (n) = reader.readLine().split(" ").map { it.toInt() }
    val a = reader.readLine().split(" ").map { it.toInt() }
    val b = reader.readLine().split(" ").map { it.toInt() }

    return solveB(n, a, b)
}

private fun solveB(n: Int, a: List<Int>, b: List<Int>): Boolean {

    if (n == 1) {
        return a[0] >= b[0]
    }

    var demand = 0
    var minExcess = Int.MAX_VALUE

    for (i in 0..<n) {
        if (a[i] < b[i]) {
            if (demand > 0) {
                return false
            }
            demand = b[i] - a[i]
        } else {
            minExcess = minOf(minExcess, a[i]-b[i])
        }
    }

    return minExcess >= demand
}
