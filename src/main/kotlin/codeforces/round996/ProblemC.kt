import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class ProblemC {
    companion object {

        fun test(reader: BufferedReader, writer: BufferedWriter) {

            val t = reader.readLine().toInt()

            for (q in 1..t) {
                println("---")
                val ans = problem(reader)
                for (row in ans) {
//                    writer.appendLine(row.joinToString(" "))
                    println(row.joinToString(" "))
                }
            }
        }

        private fun problem(reader: BufferedReader): List<IntArray> {
            val (n, m) = reader.readLine().split(" ").map { it.toInt() }
            val path = reader.readLine()
            val mx = mutableListOf<IntArray>()
            for (i in 0..<n) {
                val row = reader.readLine().split(" ").map { it.toInt() }.toIntArray()
                mx.add(row)
                println(row.joinToString(" "))
            }

            return this.solve(n, m, path, mx)
        }

        private fun solve(n: Int, m: Int, path: String, mx: List<IntArray>): List<IntArray> {
            val (rowSums, colSums) = findColRowSums(n, m, mx)

            var x = 0

            var step = 0
            var ip = 0
            var jp = 0

            while (step < n + m - 2) {
                while (step < n + m - 2 && path[step] == 'D') {
                    val pij = x - rowSums[ip]
                    mx[ip][jp] = pij
                    colSums[jp] += pij
                    step++
                    ip++
                }
                while (step < n + m - 2 && path[step] == 'R') {
                    val pij = x - colSums[jp]
                    mx[ip][jp] = pij
                    rowSums[ip] += pij
                    step++
                    jp++
                }
            }
            mx[n-1][m-1] = x - colSums[m-1]
            val (rs, cs) = findColRowSums(n, m, mx)
            println("row sums ${rs.joinToString(" ")}")
            println("col sums ${cs.joinToString(" ")}")

            return mx
        }

        private fun findColRowSums(
            n: Int,
            m: Int,
            mx: List<IntArray>
        ): Pair<IntArray, IntArray> {
            val rowSums = IntArray(n)
            val colSums = IntArray(m)
            for (i in 0..<n) {
                for (j in 0..<m) {
                    rowSums[i] += mx[i][j]
                    colSums[j] += mx[i][j]
                }
            }
            return Pair(rowSums, colSums)
        }
    }
}

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        BufferedWriter(OutputStreamWriter(System.out)).use { writer ->
            ProblemC.test(reader, writer)
        }
    }
}
