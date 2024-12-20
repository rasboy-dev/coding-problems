import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val (n, matrix) = readGraphMatrix()

    val checked = mutableSetOf<Int>()

    for (i in 1..n) {
        val visited = mutableSetOf<Int>()
        if (!checked.contains(i)) {
            val cycle: List<Int> = findCyclesDfs(i, 0, matrix, checked, visited)
            if (cycle.isNotEmpty()) {
                println("YES")
                println(cycle.size)
                println(cycle.joinToString(" "))
                return
            }
        }
    }
    println("NO")
}

fun findCyclesDfs(
    i: Int,
    prev: Int,
    matrix: Array<IntArray>,
    checked: MutableSet<Int>,
    visited: MutableSet<Int>
): MutableList<Int> {

    if (visited.contains(i))
        return mutableListOf(i)

    visited.add(i)

    val n = matrix.lastIndex
    for (j in 1..n) {
        if (j != prev && matrix[i][j] > 0 && !checked.contains(j)) {
            val cycle: MutableList<Int> = findCyclesDfs(j, i, matrix, checked, visited)
            if (cycle.isNotEmpty()) {
                visited.remove(i)
                if (visited.contains(cycle.first()))
                    cycle.add(i)
                return cycle
            }
        }
    }

    checked.add(i)

    return mutableListOf()
}

private fun readGraphMatrix(): Pair<Int, Array<IntArray>> {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().toInt()
    val matrix = Array(n + 1) { IntArray(n + 1) }
    for (i in 1..n) {
        val edges = reader.readLine().split(" ")
        for (j in 1..n) {
            matrix[i][j] = edges[j - 1].toInt()
        }
    }

    reader.close()

    return Pair(n, matrix)
}