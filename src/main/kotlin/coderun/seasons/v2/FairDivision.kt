package coderun.seasons.v2

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val string = reader.readLine().trim()
//    val string = "ababab"

    println(findMaxDivision(string))

    reader.close()
}

private fun findMaxDivision(string: String): Int {
    if (string.isEmpty()) {
        return 0
    }

    val divisionCandidates = possibleDivisions(string)
    val counts = countChars(string)
    val maxPossibleDivision = counts.minWith(compareBy { it.value }).value

    var maxDivision = 1
    for (division in divisionCandidates) {
        if (division > maxPossibleDivision) break
        if (checkDivision(string, division, counts)) {
            maxDivision = maxOf(division, maxDivision)
        }
    }
    return maxDivision
}

fun checkDivision(string: String, m: Int, counts: Map<Char, Int>): Boolean {
    val substringLength = string.length / m

    val numberPerSubstring = mutableMapOf<Char, Int>()
    for ((c, v) in counts) {
        if (v % m != 0) return false
        numberPerSubstring[c] = v / m
    }

    val substringCounts = mutableMapOf<Char, Int>()
    for (i in 0 until m) {
        val start = i * substringLength
        for (j in 0 until substringLength) {
            substringCounts[string[start + j]] = substringCounts.getOrDefault(string[start + j], 0) + 1
        }
        if (substringCounts != numberPerSubstring) {
            return false
        }
        substringCounts.clear()
    }
    return true
}

private fun countChars(string: String): Map<Char, Int> {
    val countsTmp = mutableMapOf<Char, Int>()
    for (c in string) {
        countsTmp[c] = countsTmp.getOrDefault(c, 0) + 1
    }
    return countsTmp.toMap()
}

fun possibleDivisions(string: String): List<Int> {
    val lengths = mutableListOf<Int>()
    for (i in 1..string.length) {
        if (string.length % i == 0)
            lengths.add(i)
    }
    return lengths
}



