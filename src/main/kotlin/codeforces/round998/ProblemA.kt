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
        println(ans)
    }
}

private fun problem(reader: BufferedReader): Int {
    val (a1, a2, a4, a5) = reader.readLine().split(" ").map { it.toInt() }
    val a31 = a1 + a2
    val a32 = a4 - a2
    val a33 = a5 - a4
    val fib = 4 - setOf(a31, a32, a33).size
    return fib
}



