package practice

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


/*
	Для чтения входных данных необходимо получить их
	из стандартного потока ввода (System.in).
	Данные во входном потоке соответствуют описанному
	в условии формату. Обычно входные данные состоят
	из нескольких строк. Можно использовать более производительные
	и удобные классы BufferedReader, BufferedWriter, Scanner, PrintWriter.

	С помощью BufferedReader можно прочитать из стандартного потока:
	* строку -- reader.readLine()
	* число -- val n = reader.readLine().toInt();
	* массив чисел известной длины (во входном потоке каждое число на новой строке) --
	val nums = arrayOfNulls<Int>(len)
	for (i in 0 until len) {
		nums[i] = reader.readLine().toInt()
	}

	* последовательность слов в строке --
	val parts = reader.readLine().split(" ")

	Чтобы вывести результат в стандартный поток вывода (System.out),
	Через BufferedWriter можно использовать методы
	writer.write("Строка"), writer.write('A') и writer.newLine().

	Возможное решение задачи "Вычислите сумму чисел в строке":
	var sum = 0
	val parts = reader.readLine().split(" ")
	for (i in 0 until parts.length) {
		val num = parts[i].toInt()
		sum += num
	}
	writer.write(sum.toString())
*/
fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()

    val cave = Array<Array<BooleanArray>>(n) { Array(n) { BooleanArray(n) { false } } }
    var start: Triple<Int, Int, Int> = Triple(0, 0, 0)

    for (i in 0  .. n-1) {
        reader.readLine()
        for (j in 0 .. n-1) {
            val row = reader.readLine()
            for (k in row.indices) {
                if (row[k] == '#') {
                    cave[i][j][k] = true
                } else if (row[k] == 'S') {
                    start = Triple(i, j, k)
                }
            }
        }
    }

    val steps = solveNaive(start, cave, n)
    println(steps)

    reader.close()
    writer.close()
}

private fun solveNaive(start: Triple<Int, Int, Int>, cave: Array<Array<BooleanArray>>, n: Int): Int {
    var curStepQueue: ArrayDeque<Triple<Int, Int, Int>> = ArrayDeque()
    var nextStepQueue: ArrayDeque<Triple<Int, Int, Int>> = ArrayDeque()
    val visited = mutableSetOf(start)
    curStepQueue.add(start)
    var steps = 0
    while (true) {
        val cur = curStepQueue.removeFirst()
        if (cur.first == 0) {
            return steps
        }
        val (i, j, k) = cur
        val nexts = listOf(
            Triple(i + 1, j, k),
            Triple(i - 1, j, k),
            Triple(i, j + 1, k),
            Triple(i, j - 1, k),
            Triple(i, j, k + 1),
            Triple(i, j, k - 1),
        )
        for (next in nexts) {
            if (next.first in 0..n-1 && next.second in 0..n-1 && next.third in 0..n-1) {
                if (next !in visited && !cave[next.first][next.second][next.third]) {
                    visited.add(next)
                    nextStepQueue.add(next)
                }
            }
        }

        if (curStepQueue.isEmpty()) {
            steps++
            curStepQueue = nextStepQueue.also { nextStepQueue = curStepQueue }
        }
    }
}