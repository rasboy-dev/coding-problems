package coderun.seasons.v2

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()

    val tasks = mutableListOf<Task>()
    for (i in 0 until n) {
        val (deadline, penalty) = reader.readLine().split(" ")
        tasks.add(Task(i, deadline.toInt(), penalty.toInt()))
    }
    tasks.sortWith(compareBy({ it.deadline }, { - it.penalty }))

    val leastPenalizedTasks = PriorityQueue(compareBy<Task> { it.penalty })
    val taskToExecDay = mutableMapOf<Task, Int>()

    var totalPenalty = 0L
    var executionDay = 1
    for (task in tasks) {
        if (task.deadline >= executionDay) {
            leastPenalizedTasks.add(task)
            taskToExecDay[task] = executionDay
            executionDay++
        } else {
            val leastPenalizedTask = leastPenalizedTasks.peek()
            if (leastPenalizedTask.penalty < task.penalty) {
                taskToExecDay[task] = taskToExecDay[leastPenalizedTask]!!
                taskToExecDay.remove(leastPenalizedTask)
                leastPenalizedTasks.remove()
                leastPenalizedTasks.add(task)
                totalPenalty += leastPenalizedTask.penalty
            } else {
                totalPenalty += task.penalty
            }
        }
    }

    writer.write(totalPenalty.toString())

    reader.close()
    writer.close()
}

data class Task(val id: Int, val deadline: Int, val penalty: Int)