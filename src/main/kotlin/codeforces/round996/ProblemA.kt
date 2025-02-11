import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

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
    val (n,a, b) = reader.readLine().split(" ").map { it.toInt() }
//    val arr = reader.readLine().split(" ").map { it.toLong() }

    val res = solveA(n, a, b)
    return res
}

private fun solveA(n: Int, a: Int, b: Int): Boolean {
    return abs(b - a) % 2 == 0
}


