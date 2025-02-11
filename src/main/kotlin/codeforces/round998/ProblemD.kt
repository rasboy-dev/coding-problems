import java.io.BufferedReader
import java.io.InputStreamReader

class ProblemD {
    companion object {

        fun test(reader: BufferedReader) {

            val t = reader.readLine().toInt()

            for (q in 1..t) {
                val ans = problem(reader)
                println(if (ans) "yes" else "no")
            }
        }

        private fun problem(reader: BufferedReader): Boolean {
            val (n) = reader.readLine().split(" ").map { it.toInt() }
            val arr = reader.readLine().split(" ").map { it.toInt() }.toIntArray()
            return this.solve(n, arr)
        }

        private fun solve(n: Int, arr: IntArray): Boolean {
            for (i in 0..<n-1) {
                if (arr[i] > arr[i+1]) {
                    return false
                } else {
                    arr[i+1] -= arr[i]
                    arr[i] = 0
                }
            }
            return true
        }
    }
}

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        ProblemD.test(reader)
    }
}
