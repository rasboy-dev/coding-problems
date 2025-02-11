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
        val ans = problemB(reader)
        println(ans.joinToString(" "))
    }
}

private fun problemB(reader: BufferedReader): IntArray {
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }

    val hands = mutableListOf<IntArray>()
    for (i in 0..<n) {
        hands.add(reader.readLine().split(" ").map { it.toInt() }.sorted().toIntArray())
    }
    val p = IntArray(n)
    for (i in 0..<n) {
        val hand = hands[i]
        for (j in 0..<m - 1) {
            if (hand[j + 1] - hand[j] != n) {
                return intArrayOf(-1)
            }
        }
        if (hand.first() >= n) {
            return intArrayOf(-1)
        }
        p[hand.first()] = i + 1
    }
    return p
}
