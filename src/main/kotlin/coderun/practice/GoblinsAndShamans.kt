package coderun.practice

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val n = reader.readLine().toInt()
    val queue = QueueList()
    for (i in 0 until n) {
        val (command, strId) = reader.readLine().split(" ")
        val id = strId.toInt()
        when (command) {
            "+" -> { queue.addLast(ListNode(id, null, null)) }
            "*" -> {}
            "-" -> {}
        }
    }
    reader.close()
}

private class QueueList() {
    private var n: Int = 0
    private var head: ListNode? = null
    private var tail: ListNode? = null
    private var nextMiddle: ListNode? = null
    private var prevMiddle: ListNode? = null
    
    fun addLast(node: ListNode) {
        if (n == 0) {
            head = node
            tail = node
            n++
            return
        }

        node.prev = tail!!
        tail!!.next = node
        tail = node
        n++

        if (n == 2) {
            prevMiddle = head
            nextMiddle = tail
        } else {
            if (n % 2 != 0) {
                prevMiddle = prevMiddle!!.next
                nextMiddle = nextMiddle!!.next
            }
        }
    }

    fun addMiddle(node: ListNode) {

    }

    fun removeFirst(): Int {
        return 0
    }

    fun size(): Int {
        return n
    }
}

private class ListNode(val id: Int, var prev: ListNode?, var next: ListNode?)