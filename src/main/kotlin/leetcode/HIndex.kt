package leetcode

class HIndex {
    fun hIndex(citations: IntArray): Int {
        val n = citations.size
        val counts = IntArray(n+1)
        for (cs in citations) {
            counts[maxOf(cs, n)] += 1
        }
        var hIndex = 0
        var i = n
        while (i >= 0 && i >= hIndex) {
            hIndex += counts[i]
            i--
        }
        return hIndex
    }
}