package seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val players = mutableMapOf<String, Int>()
    for (i in 0 until n) {
        players[reader.readLine()] = 0
    }
    val m = reader.readLine().toInt()
    var maxPlayer = players.keys.first()
    var totalScore = 0
    for (i in 0 until m) {
        val (newScore, player) = reader.readLine().split(" ")
        val (scoreA, scoreB) = newScore.split(":").map { it.toInt() }
        val newTotalScore = scoreA + scoreB
        val diff = newTotalScore - totalScore
        totalScore = newTotalScore
        players[player] = players[player]!! + diff
        if (players[player]!! > players[maxPlayer]!!) {
            maxPlayer = player
        } else if (players[player]!! == players[maxPlayer]!!) {
            maxPlayer = maxOf(player, maxPlayer)
        }
    }

    println("$maxPlayer ${players[maxPlayer]!!}")

    reader.close()
    writer.close()
}