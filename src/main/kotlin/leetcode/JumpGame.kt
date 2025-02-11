package leetcode

class JumpGame {
    fun canJump(nums: IntArray): Boolean {
        var maxDistance = 0
        var i = 0
        while (i in nums.indices && i <= maxDistance && maxDistance < nums.size) {
            maxDistance = maxOf(maxDistance, i + nums[i])
            i++
        }
        return maxDistance >= nums.size
    }
}