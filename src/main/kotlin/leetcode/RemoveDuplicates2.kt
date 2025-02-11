package leetcode

class RemoveDuplicates2 {

    // 1 1 1 2 2 3 4 4 4 5 5 5 5

    fun removeDuplicates(nums: IntArray): Int {
        var i = 2
        for (j in 2..nums.lastIndex) {
            if (nums[i-2] != nums[j]) {
                nums[i] = nums[j]
                i++
            }
        }
        return i
    }
}