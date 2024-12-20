package coderun.seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()

    val bitsetsWithCounts = mutableMapOf<Int, Long>()
    val offset = 48

    var d = 0
    var digitsBitset = 0
    while (d != -1) {
        d = reader.read()
        if (d - offset in 0..9) {
            val dBit = (1 shl (d - offset))
            digitsBitset = digitsBitset or dBit
        } else {
            if (digitsBitset > 0) {
                bitsetsWithCounts[digitsBitset] =
                    bitsetsWithCounts.getOrDefault(digitsBitset, 0L) + 1L
            }
            digitsBitset = 0
        }
    }

    var pairedCodes = 0L
    for (i in 0 until 1024) {
        val iCount = bitsetsWithCounts[i]
        if (iCount != null) {
            pairedCodes += (iCount * (iCount - 1)) / 2

            for (j in i + 1 until 1024) {
                val jCount = bitsetsWithCounts[j]
                if (jCount != null) {
                    if (i and j > 0) {
                        pairedCodes += iCount * jCount
                    }
                }
            }
        }
    }

    writer.write(pairedCodes.toString())

    reader.close()
    writer.close()
}