package leetcode

class RemoveElement {
    fun removeElement(nums: IntArray, v: Int): Int {
        var k = 0
        for (n in nums) {
            if (n != v) {
                nums[k] = n
                k++
            }
        }
        return k
    }
}