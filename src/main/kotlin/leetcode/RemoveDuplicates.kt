package leetcode

class RemoveDuplicates {
    fun removeDuplicates(nums: IntArray): Int {
        var i = 1
        var cur = nums[0]
        for (num in nums) {
            if (cur != num) {
                cur = num
                nums[i] = cur
                i++
            }
        }
        return i
    }
}