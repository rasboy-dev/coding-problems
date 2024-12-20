package codeforces.round991

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    test(reader)

    reader.close()
}

private fun test(reader: BufferedReader) {

    val t = reader.readLine().toInt()

    for (q in 1..t) {
        val ans = problem(reader)
        println(ans)
    }
}

private fun problem(reader: BufferedReader): String {
    val n = reader.readLine()

    val res = solve(n)

    return res
}

private fun solve(n: String): String {
    val head = LinkedNode(Int.MAX_VALUE)
    var prevNode: LinkedNode = head
    val nodes = mutableListOf<LinkedNode>()
    for (i in n) {
        val node = LinkedNode(i.digitToInt())
        prevNode.next = node
        node.prev = prevNode
        prevNode = node
        nodes.add(node)
    }

    for (node in nodes.slice(1..nodes.lastIndex)) {
        var shift = 0
        var newPrev = node.prev
        while (newPrev!!.value < node.value - shift - 1) {
            newPrev = newPrev.prev
            shift++
        }

        if (shift > 0) {
            val oldPrev = node.prev
            val oldNext = node.next
            oldPrev!!.next = oldNext
            if (oldNext != null)
                oldNext.prev = oldPrev

            val newNext = newPrev.next
            val newNode = LinkedNode(node.value - shift)
            newNode.prev = newPrev
            newPrev.next = newNode
            newNode.next = newNext
            if (newNext != null)
                newNext.prev = newNode
        }
    }

    val res = StringBuilder()
    var node = head.next
    while (node != null) {
        res.append(node.value)
        node = node.next
    }

    return res.toString()
}

class LinkedNode(val value: Int) {
    var prev: LinkedNode? = null
    var next: LinkedNode? = null
}