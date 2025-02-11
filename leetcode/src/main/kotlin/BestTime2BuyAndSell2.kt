package leetcode

class BestTime2BuyAndSell2 {
    fun maxProfit(prices: IntArray): Int {
        if (prices.size < 2) {
            return 0
        }

        var profit = 0

        for (i in 0 until prices.lastIndex) {
            val diff = prices[i+1] - prices[i]
            if (diff > 0) {
                profit += diff
            }
        }

        return profit
    }
}