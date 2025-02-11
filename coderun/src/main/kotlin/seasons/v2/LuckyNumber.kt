package seasons.v2

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
//    test()

    val input = BufferedReader(InputStreamReader(System.`in`)).readLine()
    val strNum = input.trim()
    if (strNum == "0") {
        println("1")
    } else {
        println(nextLucky(strNum))
    }
}

fun nextLucky(strNum: String): String {
    val n = strNum.length / 2

    val left = strNum.substring(0, n)
    val right = strNum.substring(n)

    if (n == 1) {
        val l = left.toInt()
        val r = right.toInt()
        return when {
            l == 9 && r == 9 -> "11"
            l <= r -> "${l + 1}${l + 1}"
            else -> left + left
        }
    }

    val sl = dsum(left)
    val sr = dsum(right)

    return if (sl <= sr) {
        var newRight = nextForLowerSum(right, sl, n)
        if (newRight.length > n) {
            val newLeft = increment(left)
            if (newLeft.length > n) {
                val zeros = "0".repeat(n - 1)
                return "${zeros}1${zeros}1"
            }
            val zeros = "0".repeat(n)
            newRight = nextForHigherSum(zeros, dsum(newLeft), n)
            return addZeros(newLeft, n) + newRight
        }
        left + newRight
    } else {
        left + nextForHigherSum(right, sl, n)
    }
}

fun nextForLowerSum(strNum: String, sum: Int, n: Int): String {
    var i = 1
    while (sum > dsum(strNum.substring(0, i))) {
        i++
    }
    i--
    val zeros = "0".repeat(maxOf(strNum.length - i, 0))
    val front = "${if (i > 0) increment(strNum.substring(0, i)) else 1}$zeros"
    val rem = sum - dsum(front)
    val end = addZeros(minimize(rem), front.length)
    val res = StringBuilder()
    for (j in front.indices) {
        res.append((front[j].digitToInt() + end[j].digitToInt()).toString())
    }
    return res.toString()
}

fun nextForHigherSum(strNum: String, sum: Int, n: Int): String {
    val diff = sum - dsum(strNum)
    var i = 1
    while (diff > i * 9 - dsum(strNum.takeLast(i))) {
        i++
    }
    return if (i == 1) {
        addZeros(increment(strNum, diff), n)
    } else {
        val suffixSum = diff + dsum(strNum.takeLast(i))
        strNum.dropLast(i) + minimize(suffixSum)
    }
}

fun dsum(strNum: String): Int {
    return strNum.sumOf { it.digitToInt() }
}

fun minimize(rem: Int): String {
    val k = rem / 9
    val m = rem % 9
    val nines = "9".repeat(k)
    return if (m > 0) "$m$nines" else nines
}

fun addZeros(strNum: String, length: Int): String {
    return if (length - strNum.length <= 0) {
        strNum
    } else {
        "0".repeat(length - strNum.length) + strNum
    }
}

fun increment(strNum: String, times: Int = 1): String {
    val increase = strNum.last().digitToInt() + times
    if (increase < 10) {
        return strNum.dropLast(1) + increase.toString()
    }

    val res = StringBuilder()
    res.append((increase % 10).toString())
    var i = 2
    while (i <= strNum.length && strNum[strNum.length - i] == '9') {
        res.append('0')
        i++
    }

    if (i > strNum.length) {
        res.append('1')
    } else {
        res.append((strNum[strNum.length - i].digitToInt() + 1).toString())
        i++
        while (i <= strNum.length) {
            res.append(strNum[strNum.length - i])
            i++
        }
    }
    return res.reverse().toString()
}

fun bruteForce(strNum: String): String {
    var num = strNum.toInt() + 1
    val n = strNum.length
    var strNumStr = addZeros(num.toString(), n)
    val h = n / 2
    while (dsum(strNumStr.substring(0, h)) != dsum(strNumStr.substring(h))) {
        num++
        strNumStr = addZeros(num.toString(), n)
    }
    return if (strNumStr.length <= n) {
        addZeros(strNumStr, n)
    } else {
        "0".repeat(n / 2 - 1) + "1" + "0".repeat(n / 2 - 1) + "1"
    }
}

private fun test() {
    for (maxLength in 1..3) {
        for (l in 0 until Math.pow(10.0, maxLength.toDouble()).toInt()) {
            for (p in l.toString().length..maxLength) {
                val left = addZeros(l.toString(), maxLength)
                for (r in 0 until Math.pow(10.0, maxLength.toDouble()).toInt()) {
                    val right = addZeros(r.toString(), maxLength)
                    val strNum = left + right
                    if (bruteForce(strNum) != nextLucky(strNum)) {
                        println("$strNum ${bruteForce(strNum)} != ${nextLucky(strNum)}")
                    }
                }
            }
        }
    }
}
