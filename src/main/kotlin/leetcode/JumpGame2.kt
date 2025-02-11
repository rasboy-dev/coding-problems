package leetcode

class JumpGame2 {
    fun jump(nums: IntArray): Int {
        var jumps = 0
        var near = 0
        var far = 0

        while (far < nums.lastIndex) {
            var furthest = far
            for (i in near..far) {
                furthest = maxOf(furthest, i + nums[i])
            }
            near = far + 1
            far = furthest
            jumps++

        }

        return jumps
    }
}