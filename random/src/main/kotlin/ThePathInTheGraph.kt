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
	val previousLevelMap = IntArray(n + 1)
	var pathExists = false
	var lastInLevel = from

	queue@ while (queue.isNotEmpty()) {
		val i = queue.removeLast()
		neighbors@ for (j in 1..n) {
			if (matrix[i][j] > 0 && !checked.contains(j)) {
				previousLevelMap[j] = i
				if (j == to) {
					pathExists = true
					break@queue
				}
				checked.add(j)
				queue.add(j)
			}
		}
		if (lastInLevel == i && queue.isNotEmpty()) {
			lastInLevel = queue.last()
		}
	}

	if (pathExists) {
		var i = to
		val pathReversed = mutableListOf(to)
		while (i != from) {
			i = previousLevelMap[i]
			pathReversed.add(i)
		}
		println(pathReversed.size - 1)
		println(pathReversed.reversed().joinToString(" "))
		return
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