package coderun.practice

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

    println(problem(reader, writer))
//    test(reader, writer)

    reader.close()
    writer.close()
}

fun test(reader: BufferedReader, writer: BufferedWriter) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        reader.readLine()
        val expected = reader.readLine().toInt()
        val ans = problem(reader, writer)
        println("$q: expected = $expected, ans = $ans")
    }
}

private fun problem(reader: BufferedReader, writer: BufferedWriter): Int {
    val n = reader.readLine().toInt()
    val m = reader.readLine().toInt()

    val adjacencyList = Array<MutableSet<Int>>(n + m + 1) { mutableSetOf() }
    for (line in 1..m) {
        val lineStations = reader.readLine().split(" ").map { it.toInt() }
        for (i in 1..lineStations.lastIndex) {
            adjacencyList[lineStations[i]].add(n + line)
            adjacencyList[n + line].add(lineStations[i])
        }
    }
    val (from, to) = reader.readLine().split(" ").map { it.toInt() }

    val changes = solve(adjacencyList, n, m, from, to)

    return changes;
}

fun solve(adjacencyList: Array<MutableSet<Int>>, n: Int, m: Int, from: Int, to: Int): Int {
    var curNodeQueue = ArrayDeque<Int>()
    var nextNodeQueue = ArrayDeque<Int>()
    val visited = mutableSetOf<Int>()

    var distance = 0
    curNodeQueue.add(from)
    visited.add(from)

    while (curNodeQueue.isNotEmpty()) {
        val curNode = curNodeQueue.removeFirst()
        if (curNode == to) {
            return calculateDistance(distance)
        }
        for (node in adjacencyList[curNode]) {

            if (node !in visited) {
                nextNodeQueue.add(node)
                visited.add(node)
            }
        }
        if (curNodeQueue.isEmpty()) {
            distance++
            curNodeQueue = nextNodeQueue.also { nextNodeQueue = curNodeQueue }
        }
    }

    return -1
}

private fun calculateDistance(distance: Int): Int {
    return distance / 2 - 1
}

// private fun problemNaive(reader: BufferedReader, writer: BufferedWriter): Int {
//     val n = reader.readLine().toInt()
//     val m = reader.readLine().toInt()
//     val stationToLines = mutableMapOf<Int, MutableSet<Int>>()
//     val linesToStations = mutableMapOf<Int, MutableSet<Int>>()
//     for (i in 0..n - 1) {
//         stationToLines[i] = mutableSetOf()
//     }
//     for (j in 0..m - 1) {
//         linesToStations[j] = mutableSetOf()
//     }
//     for (line in 0..m - 1) {
//         val lineStations = reader.readLine().split(" ").map { it.toInt() - 1 }
//         for (i in lineStations.slice(1..lineStations.lastIndex)) {
//             linesToStations[line]!!.add(i)
//             stationToLines[i]!!.add(line)
//         }
//     }

//     val (from, to) = reader.readLine().split(" ").map { it.toInt() - 1 }

//     return solveNaive(from, stationToLines, linesToStations, to)
// }

// private fun solveNaive(
//     from: Int,
//     stationToLines: MutableMap<Int, MutableSet<Int>>,
//     linesToStations: MutableMap<Int, MutableSet<Int>>,
//     to: Int
// ): Int {
//     var stationQueueCur = ArrayDeque<Int>()
//     var stationQueueNext = ArrayDeque<Int>()
//     val visitedStations = mutableSetOf<Int>()
//     val visitedLines = mutableSetOf<Int>()

//     visitedStations.add(from)
//     for (line in stationToLines[from]!!) {
//         for (i in linesToStations[line]!!) {
//             if (i == to) {
//                 return 0
//             }
//             if (!visitedStations.contains(i)) {
//                 stationQueueCur.add(i)
//                 visitedStations.add(i)
//             }
//         }
//         visitedLines.add(line)
//     }

//     var changes = 0

//     while (stationQueueCur.isNotEmpty()) {
//         val station = stationQueueCur.removeFirst()
//         if (station == to) {
//             return changes
//         }
//         val lines = stationToLines[station]!!
//         for (line in lines) {
//             if (line !in visitedLines) {
//                 for (st in linesToStations[line]!!) {
//                     if (st !in visitedStations) {
//                         stationQueueNext.add(st)
//                         visitedStations.add(st)
//                     }
//                 }
//                 visitedLines.add(line)
//             }
//         }
//         if (stationQueueCur.isEmpty()) {
//             stationQueueCur = stationQueueNext.also { stationQueueNext = stationQueueCur }
//             changes++
//         }
//     }

//     return -1
// }

