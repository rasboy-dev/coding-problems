package seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 1000000007

class SegmentTree(array: LongArray) {
    private val tree: LongArray
    private val arr: LongArray
    private val size: Int

    init {
        size = array.size
        tree = LongArray(size * 4)
        arr = array

        buildTree(array, 0, 0, size - 1)
    }

    private fun buildTree(array: LongArray, treeIndex: Int, left: Int, right: Int) {
        if (left == right) {
            tree[treeIndex] = array[left] % MOD
            return
        }
        val mid = (left + right) / 2
        buildTree(array, 2 * treeIndex + 1, left, mid)
        buildTree(array, 2 * treeIndex + 2, mid + 1, right)
        tree[treeIndex] = modProd(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2])
    }

    fun getProduct(left: Int, right: Int): Long {
        return getProduct(0, 0, size - 1, left, right)
    }

    fun updateRange(diff: Int, left: Int, right: Int) {
        updateRange(0, 0, size - 1, left, right, diff)
    }

    private fun getProduct(treeIndex: Int, left: Int, right: Int, queryLeft: Int, queryRight: Int): Long {
        if (queryLeft <= left && right <= queryRight) {
            return tree[treeIndex] % MOD
        }
        if (right < queryLeft || queryRight < left) {
            return 1
        }
        val mid = (left + right) / 2
        val leftProduct = getProduct(2 * treeIndex + 1, left, mid, queryLeft, queryRight)
        val rightProduct = getProduct(2 * treeIndex + 2, mid + 1, right, queryLeft, queryRight)
        return modProd(leftProduct, rightProduct)
    }

    private fun updateRange(treeIndex: Int, left: Int, right: Int, updateLeft: Int, updateRight: Int, diff: Int) {
        if (right < updateLeft || updateRight < left) {
            return
        }
        if (left == right) {
            tree[treeIndex] = (tree[treeIndex] + diff) % MOD
//            arr[left] += diff
            return
        }
        val mid = (left + right) / 2
        updateRange(2 * treeIndex + 1, left, mid, updateLeft, updateRight, diff)
        updateRange(2 * treeIndex + 2, mid + 1, right, updateLeft, updateRight, diff)
        tree[treeIndex] = modProd(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2])
    }

    override fun toString(): String {
        return "size = $size, arr = ${arr.contentToString()}, tree = ${tree.contentToString()}"
    }
}

fun modProd(a: Long, b: Long): Long {
    return ((a % MOD) * (b % MOD)) % MOD
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()

    val inputArray = reader.readLine().split(" ").map { it.toLong() }
    val array = LongArray(inputArray.size) { i -> inputArray[i] }
    val segmentTree = SegmentTree(array)

    val q = reader.readLine().toInt()

    for (i in 0 until q) {
        val query = reader.readLine().split(" ").map { it.toInt() }
        when (query[0]) {
            0 -> segmentTree.updateRange(1, query[1] - 1, query[2] - 1)
            1 -> writer.write(segmentTree.getProduct(query[1] - 1, query[2] - 1).toString() + System.lineSeparator())
        }
    }

    reader.close()
    writer.close()
}