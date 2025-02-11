package leetcode

class MergeSortedArray {
    companion object {
        fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
            var i = m + n - 1
            var i1 = m - 1
            var i2 = n - 1
            while (i1 >= 0 || i2 >= 0) {
                nums1[i] = if (i1 < 0) {
                    nums2[i2--]
                } else if (i2 < 0) {
                    nums1[i1--]
                } else if (nums1[i1] > nums2[i2]) {
                    nums1[i1--]
                } else {
                    nums2[i2--]
                }
                i--
            }
        }
    }
}

fun main() {
    MergeSortedArray.merge(intArrayOf(1, 2, 4, 0, 0, 0), 3, intArrayOf(3, 4, 5),  3)
}