fun main() {
//    testBf(intArrayOf(2, 6, 8, 5), 3)
//    testBf(intArrayOf(1, 5, 5, 2, 6), 4)
//    testBf(intArrayOf(1, 1), 2)
//    testBf(intArrayOf(2, 1, 1, 2), 4)
//    testBf(intArrayOf(2, 2, 1, 1, 2, 3), 6)
//    testBf(intArrayOf(1, 1, 1, 2, 2, 1, 1), 5)
//    testBf(intArrayOf(1, 1, 1, 2, 2), 5)
//    testBf(intArrayOf(5, 4, 3, 2, 2, 2, 1, 1, 1), 9)

    testSolution(intArrayOf(2, 6, 8, 5))
    testSolution(intArrayOf(1, 5, 5, 2, 6))
    testSolution(intArrayOf(1, 1))
    testSolution(intArrayOf(2, 1, 1, 2))
    testSolution(intArrayOf(2, 2, 1, 1, 2, 3))
    testSolution(intArrayOf(1, 1, 1, 2, 2, 1, 1))
    testSolution(intArrayOf(1, 1, 1, 2, 2))
    testSolution(intArrayOf(5, 4, 3, 2, 2, 2, 1, 1, 1))
    testSolution(intArrayOf(5, 5, 5, 3, 2, 2, 3, 3, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4,4, 4, 5, 5, 6))
    testSolution(intArrayOf(5, 5, 5, 3, 2, 2, 3, 3, 5, 5, 5, 4, 4, 5, 5, 6))
    testSolution(intArrayOf(5, 5, 5, 3, 2, 2, 3, 3, 5, 5, 5, 4, 4, 5, 5, 6))
    testSolution(intArrayOf(1_000_000_000, 1_000_000_005, 1_000_000_005, 1_000_000_003, 1_000_000_002, 1_000_000_002, 1_000_000_003, 1_000_000_003, 1_000_000_005, 1_000_000_005, 1_000_000_005, 1_000_000_004, 1_000_000_004, 1_000_000_005, 1_000_000_005, 1_000_000_006))

//    for (i in 0..1000000) {
//        generatedTest()
//    }
}

private fun generatedTest() {
    val n = (2..100).random()
    val blocks = IntArray(n)
    for (i in 0 until n) {
        blocks[i] = (0..10).random()
    }
    testSolution(blocks)
}

private fun testBf(blocks: IntArray, expected: Int) {
    val ans = bf(blocks)
    assert(ans == expected)
    println("${blocks.joinToString(" ")} : $ans == $expected")
}

private fun testSolution(blocks: IntArray) {
    val ans = solution(blocks)
    val bf = bf(blocks)
    println("${blocks.joinToString(" ")} : $bf == $ans")
    assert(ans == bf)
}

private fun solution(blocks: IntArray): Int {
    var left = 0
    var right = 0
    var max = 0
    var nextLeft = 0
    while (right + 1 < blocks.size) {
        while (right + 1 < blocks.size && blocks[right] >= blocks[right + 1]) {
            right += 1
        }
        while (right + 1 < blocks.size && blocks[right] <= blocks[right + 1]) {
            if (blocks[right] < blocks[right + 1]) {
                nextLeft = right + 1
            } else if (blocks[nextLeft] != blocks[right]) {
                nextLeft = right
            }
            right += 1
        }
        max = maxOf(max, right - left + 1)
        left = nextLeft
    }
    return max
}

private fun bf(blocks: IntArray): Int {
    var max = Int.MIN_VALUE
    for (i in blocks.indices) {
        var j = i
        while (j - 1 >= 0 && blocks[j] <= blocks[j - 1]) {
            j--
        }
        var k = i
        while (k + 1 < blocks.size && blocks[k] <= blocks[k + 1]) {
            k++
        }
        max = maxOf(max, k - j + 1)
    }
    return max
}
