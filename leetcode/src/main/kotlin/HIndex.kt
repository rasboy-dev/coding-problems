package leetcode

class Solution {
    fun hIndex(citations: IntArray): Int {
        val n = citations.size
        val counts = IntArray(n+1)
        for (cs in citations) {
            counts[minOf(cs, n)] += 1
        }
        var hIndex = 0
        var i = n
        while (i > hIndex) {
            hIndex = minOf(hIndex + counts[i], i)
            i--
        }
        return hIndex
    }
}