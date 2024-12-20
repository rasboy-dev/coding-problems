package coderun.seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
//    fromConsole()
    runTests()
//    runGeneratedTest()
}

fun runGeneratedTest() {
    for (i in 0..100000) {
        val n = (1..2000).random()
        val m = (1..n).random()

        val pattern = getRandomString(m)
        val str = getRandomString(n)
        println(str)
        println(pattern)
        val resNaive = findPossibleSubstitutions(str, pattern)
        val resRoll = rollingHash(str, pattern)
        assert(resRoll.first == resNaive.first)
        assert(resRoll.second == resNaive.second)
        println("----")

    }
}

fun testHashes() {
//    println(getHashes(2, "ab"))
//    println(getHashes(3, "abc"))
//    println(getHashes(3, "abb"))
//    println(getHashes(3, "aab"))
//    println(getHashes(3, "aba"))
//    println(getHashes(3, "bbb"))

    println(getHashes(2, "abc"))
    println(getHashes(2, "abb"))
    println(getHashes(2, "aab"))
    println(getHashes(2, "aba"))
    println(getHashes(2, "bbb"))
}

fun runRollingHash() {
    rollingHash("abc", "abc")
    rollingHash("abcz", "abxc")
    rollingHash("aabc", "abbc")
    rollingHash("aabc", "abcc")
    rollingHash("aabb", "aabc")
    rollingHash("aabca", "aabac")
    rollingHash("waabcaw", "zaabacz")
    rollingHash("waabcammw", "zaabxaccz")
}

private fun runTests() {
//    runTest(
//        1,
//        "bwca",
//        "love",
//        1,
//        listOf(1)
//    )
//
//    runTest(
//        2,
//        "abab",
//        "tat",
//        2,
//        listOf(1, 2)
//    )
//
//    runTest(
//        3,
//        "abab",
//        "baba",
//        1,
//        listOf(1)
//    )
//
//    runTest(
//        4,
//        "abab",
//        "q",
//        4,
//        listOf(1, 2, 3, 4)
//    )
//
//    runTest(
//        5,
//        "abab",
//        "qqq",
//        0,
//        listOf()
//    )
//
//    runTest(
//        6,
//        "w",
//        "q",
//        1,
//        listOf(1)
//    )
//
//    runTest(
//        7,
//        "abbcabcc",
//        "bca",
//        3,
//        listOf(3, 4, 5)
//    )
//
//    runTest(
//        8,
//        "atatad",
//        "atatd",
//        1,
//        listOf(2)
//    )

    runTest(
        9,
        "qqqqqqqaccccccc",
        "tttttt",
        4,
        listOf(1, 2, 9, 10)
    )

    runTest(
        10,
        "tttttt",
        "tttttt",
        1,
        listOf(1)
    )

//    wJWfFU35SJRYCAtbK3lJqO0TXrxLjr8Af1afFkPAOqBwmS2DYaZlSvJke2oybKvrru6QY6GzbiFKyYnkQqnLNPBceS37lAZaAn0WnRaJM5KotYdFtMvd11GSkaYVSowKwb272Xo73p1GTQGTCQwNNRpVQ1MwTNC7T8Cg2JLpQMsDcuRYxkcoDVbSSULghYSb0C3zoadzr7JBH8sqtJ76EY0eaxNNJpplmWt9xr8Z3Q8qr0zNO4uJU53MS91WnNWZ4EAAuU7R5eu13NlEHktNoeG1EJpvChHaHnQr6U7FLkCo6K9Ac5aeYF9xwBNwGOUqlrVu1DaOWQ3LnHx99ZTvoNCzX23QnCz0KsRaPKVGMAM3OaEe39zP
//    HuxC1C

    val resNaive = findPossibleSubstitutions("IaQBwOCecvdu", "Tt0g1pfS")
    val resRoll = rollingHash("IaQBwOCecvdu", "Tt0g1pfS")
    println(resNaive)
    println(resRoll)
//    IaQBwOCecvdu
//    Tt0g1pfS
}

private fun runTest(
    testId: Int,
    codedString: String,
    string: String,
    expectedCount: Int,
    expectedPositions: List<Int>
) {
    val res = rollingHash(codedString, string)
    println("Test $testId:")
    println(res.first)
    println(res.second.joinToString(" "))

    assert(expectedCount == res.first)
    assert(expectedPositions.containsAll(res.second) && res.second.containsAll(expectedPositions))
    println("passed")
    println()
}

private fun fromConsole() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val codedString = reader.readLine()
    val string = reader.readLine()

    val res = rollingHash(codedString, string)

    println(res.first)
    println(res.second.joinToString(" "))

    reader.close()
    writer.close()
}

private fun rollingHash(string: String, pattern: String): Pair<Int, List<Int>> {
    val m: Int = pattern.length

    val patternHash = getHashes(m, pattern)[0]
    val stringHashes = getHashes(m, string)

    var posCount = 0
    val positions = mutableListOf<Int>()

    for (i in stringHashes.indices) {
        if (stringHashes[i] == patternHash) {
            posCount++
            positions.add(i)
        }
    }

    return Pair(posCount, positions.map { it + 1 })
}

fun getHashes(m: Int, string: String): List<Int> {
    val base = 499L
    val mod = 1_000_000_007

    val pows = mutableListOf<Int>()
    pows.add(1)
    for (i in 1 until m) {
        pows.add((((pows[i - 1].toLong()) % mod * base) % mod).toInt())
    }

    val n: Int = string.length

    var stringHash = 0 // hash value for txt

    val stringPositions = mutableMapOf<Char, LinkedList<Int>>()

    val hashes = mutableListOf<Int>()

    for (i in 0 until m) {
        if (!stringPositions.containsKey(string[i])) {
            stringPositions[string[i]] = LinkedList()
        }
        val y = i - if (stringPositions[string[i]]!!.isNotEmpty()) stringPositions[string[i]]!!.last() else -1
        stringPositions[string[i]]!!.add(i)
        stringHash = (((base * stringHash.toLong()) % mod + y.toLong()) % mod).toInt()
    }

    hashes.add(stringHash)

    for (i in m until n) {
        for (char in stringPositions.keys) {
            if (stringPositions[char]!!.isNotEmpty()) {
                val pow = i - stringPositions[char]!!.first() - 1
                stringHash = ((stringHash.toLong() - pows[pow].toLong()) % mod).toInt()
            }
        }

        stringPositions[string[i - m]]!!.removeFirst()

        if (!stringPositions.containsKey(string[i])) {
            stringPositions[string[i]] = LinkedList()
        }
        val y = i - if (stringPositions[string[i]]!!.isNotEmpty()) stringPositions[string[i]]!!.last() else i - m
        stringPositions[string[i]]!!.add(i)
        stringHash = ((base * stringHash.toLong()) % mod).toInt()
        stringHash = ((stringHash.toLong() + y.toLong()) % mod).toInt()

        if (stringHash < 0 ) {
            stringHash += mod
        }

        hashes.add(stringHash)
    }

    return hashes.toList()
}

private fun findPossibleSubstitutions(codedString: String, string: String): Pair<Int, List<Int>> {
    var posCount = 0
    val positions = mutableListOf<Int>()

    val map = mutableMapOf<Char, Char>()
    val mapped = mutableSetOf<Char>()
    for (i in 0..codedString.lastIndex - string.length + 1) {
        map.clear()
        mapped.clear()
        var j = i
        for (char in string) {
            if (map.containsKey(char)) {
                if (map[char] != codedString[j]) {
                    break
                }
            } else {
                if (mapped.contains(codedString[j]))
                    break
                map[char] = codedString[j]
                mapped.add(codedString[j])
            }
            j++
        }
        if (i + string.length == j) {
            posCount++
            positions.add(i)
        }
    }
    return Pair(posCount, positions.map { it + 1 })
}

private fun isCoded(
    i: Int,
    string: String,
    codedString: String,
): Boolean {
    val map = mutableMapOf<Char, Char>()
    val mapped = mutableSetOf<Char>()
    var j = i
    for (char in string) {
        if (map.containsKey(char)) {
            if (map[char] != codedString[j]) {
                return false
            }
        } else {
            if (mapped.contains(codedString[j]))
                return false
            map[char] = codedString[j]
            mapped.add(codedString[j])
        }
        j++
    }
    return true
}

fun getRandomString(length: Int) : String {
    val allowedChars = ('a'..'z')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}