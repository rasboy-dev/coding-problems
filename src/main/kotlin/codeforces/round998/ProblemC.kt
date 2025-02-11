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
        val ans = problemC(reader)
        println(ans)
    }
}

private fun problemC(reader: BufferedReader): Int {
    val (n, k) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    val arrCounts = mutableMapOf<Int, Int>()
    for (a in arr) {
        val coa = k - a
        if (coa in arrCounts) {
            arrCounts[coa] = arrCounts[coa]!! - 1
            if (arrCounts[coa] == 0) {
                arrCounts.remove(coa)
            }
        } else {
            arrCounts[a] = arrCounts.getOrDefault(a, 0) + 1
        }
    }

    var pairs = n
    arrCounts.forEach { pairs -= it.value}
    return (pairs) / 2
}
