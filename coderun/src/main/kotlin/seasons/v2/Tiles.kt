package seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.sqrt


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

    val (b, w) = reader.readLine().split(" ").map{ it.toInt() }

    var x = 3
    val area = b + w
    val maxX = sqrt(area.toDouble()).toInt()

    while (x < maxX) {
        if (area % x == 0) {
            break
        }
        x++
    }

    println(listOf(x, area / x).sorted().reversed())

    reader.close()
    writer.close()
}