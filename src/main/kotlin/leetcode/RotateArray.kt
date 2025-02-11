package leetcode

class RotateArray {
    fun rotate(nums: IntArray, k: Int): Unit {
        nums.reverse()
        nums.reverse(0, k)
        nums.reverse(k, nums.size)
    }
}

// k = 3
// 0 1 2 3 4 5 6 7
// a b c d e f g h
// 0 1 2 0 1 2 0 1