package leetcode

class ProductExceptSelf {
    fun productExceptSelf(nums: IntArray): IntArray {

        val n = nums.size
        val res = IntArray(n) {1}
        for (i in 1 until n) {
            res[i] *= res[i-1] * nums[i-1]
        }
        var right = 1
        for (i in n-2 downTo 0) {
            right *= nums[i+1]
            res[i] *= right
        }
        return res
    }
}