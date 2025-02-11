import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().toInt()
    val matrix = readGraphMatrix(reader, n)
    val (from, to) = reader.readLine().split(" ").map { it.toInt() }
    reader.close()

    if (from == to) {
        println(0)
        return
    }

    val queue = ArrayDeque(listOf(from))
    val checked = mutableSetOf(from)
    var length = 1
    var lastForLength = from
    while (queue.isNotEmpty()) {
        val i = queue.removeFirst()
        for (j in 1..n) {
            if (matrix[i][j] > 0 && !checked.contains(j)) {
                if (j == to) {
                    println(length)
                    return
                }
                checked.add(j)
                queue.add(j)
            }
        }
        if (lastForLength == i) {
            length++
            lastForLength = if (queue.isNotEmpty()) queue.last() else lastForLength
        }
    }
    println(-1)
}

private fun readGraphMatrix(reader: BufferedReader, n: Int): Array<IntArray> {
    val matrix = Array(n + 1) { IntArray(n + 1) }
    for (i in 1..n) {
        val edges = reader.readLine().split(" ")
        for (j in 1..n) {
            matrix[i][j] = edges[j - 1].toInt()
        }
    }
    return matrix
}