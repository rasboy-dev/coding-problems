package leetcode

class MajorityElement {
    fun majorityElement(nums: IntArray): Int {
        var majorityElement = nums[0]
        var count = 1
        for (num in nums) {
            if (majorityElement == num) {
                count++
            } else {
                count--
                if (count == 0) {
                    majorityElement = num
                }
            }
        }
        return majorityElement
    }
}