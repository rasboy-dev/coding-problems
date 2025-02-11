package leetcode

class BestTime2BuyAndSell {
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) {
            return 0
        }

        var maxProfit = 0

        var buy = prices[0]
        for (sell in prices.indices) {
            if (buy > sell) {
                buy = sell
            } else {
                maxProfit = maxOf(sell - buy, maxProfit)
            }
        }

        return maxProfit
    }
}