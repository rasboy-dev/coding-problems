package coderun.seasons.v2

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs


lateinit var adjacencyList: Array<MutableList<Int>>
lateinit var transposedAdjacencyList: Array<MutableList<Int>>
var fn: Int = 0
var tn: Int = 0
var wn: Int = 0

fun main(args: Array<String>) {

    readInput()

//    val componentMap = Kosaraju.run()
    val componentMap = Tarjan.run()

    val includedToppings = mutableListOf<Int>()
    for (v in 0 until tn) {
        if (componentMap[v] == componentMap[v + tn]) {
            println("-1")
            return
        }
        if (componentMap[v] < componentMap[v + tn]) {
            includedToppings.add(v + 1)
        }
    }

    println("${includedToppings.size}")
    println(includedToppings.joinToString(" "))
}

private fun readInput() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (_fn, _tn, _wn) = reader.readLine().split(" ").map { it.toInt() }
    fn = _fn
    tn = _tn
    wn = _wn

    adjacencyList = Array(2 * tn + 2 * wn) { mutableListOf() }
//    transposedAdjacencyList = Array(2 * tn + 2 * wn) { mutableListOf() }

    val friendToWishes = mutableMapOf<Int, MutableList<Pair<Int, Boolean>>>()

    for (i in 0 until wn) {
        val (friend, wish) = reader.readLine().split(" ").map { it.toInt() }

        friendToWishes[friend] = friendToWishes.getOrDefault(friend, mutableListOf())

        val newWishValue = abs(wish) - 1
        val newWishSign = wish > 0

        friendToWishes[friend]!!.add(Pair(newWishValue, newWishSign))
    }

    reader.close()

    var prefix = 2 * tn

    for (friendWishes in friendToWishes.values) {
        if (friendWishes.size < 2) continue
        for (i in 0..friendWishes.lastIndex) {
            val (topping, wanted) = friendWishes[i]

            addEdgeToPrefix(topping, !wanted, prefix, true)
            addEdgeFromPrefix(prefix, false, topping, wanted)

            if (i > 0) {
                addEdgeFromPrefix(prefix - 1, true, topping, wanted)
                addEdgeToPrefix(topping, !wanted, prefix - 1, false)

                addEdgeFromToPrefix(prefix - 1, true, prefix, true)
                addEdgeFromToPrefix(prefix, false, prefix - 1, false)
            }

            prefix++
        }
    }
}

fun addEdge(v: Int, nv: Boolean, u: Int, nu: Boolean) {
    adjacencyList[v + if (!nv) tn else 0].add(u + if (!nu) tn else 0)
//    transposedAdjacencyList[u + if (!nu) tn else 0].add(v + if (!nv) tn else 0)
}

fun addEdgeToPrefix(v: Int, nv: Boolean, u: Int, nu: Boolean) {
    adjacencyList[v + if (!nv) tn else 0].add(u + if (!nu) wn else 0)
//    transposedAdjacencyList[u + if (!nu) wn else 0].add(v + if (!nv) tn else 0)
}

fun addEdgeFromPrefix(v: Int, nv: Boolean, u: Int, nu: Boolean) {
    adjacencyList[v + if (!nv) wn else 0].add(u + if (!nu) tn else 0)
//    transposedAdjacencyList[u + if (!nu) tn else 0].add(v + if (!nv) wn else 0)
}

fun addEdgeFromToPrefix(v: Int, nv: Boolean, u: Int, nu: Boolean) {
    adjacencyList[v + if (!nv) wn else 0].add(u + if (!nu) wn else 0)
//    transposedAdjacencyList[u + if (!nu) wn else 0].add(v + if (!nv) wn else 0)
}

private class Kosaraju {
    companion object {
        fun run(): Array<Int> {
            val topologicalOrder = topologicalSort()
            return getComponents(topologicalOrder)
        }

        private fun topologicalSort(): List<Int> {
            val topologicalOrder = mutableListOf<Int>()
            val visited = mutableSetOf<Int>()

            for (v in adjacencyList.indices) {
                if (!visited.contains(v))
                    dfs(v, visited, topologicalOrder)
            }
            return topologicalOrder.reversed()
        }

        private fun dfs(
            v: Int,
            visited: MutableSet<Int>,
            topologicalOrder: MutableList<Int>
        ) {
            visited.add(v)

            for (neighbor in adjacencyList[v]) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, visited, topologicalOrder)
                }
            }
            topologicalOrder.add(v)
        }

        private fun getComponents(topologicalOrder: List<Int>): Array<Int> {
            val componentMap = Array(2 * tn + 2 * wn) { 0 }

            var componentId = 1
            for (v in topologicalOrder) {
                if (componentMap[v] == 0) {
                    scc(v, componentId, componentMap)
                    componentId++
                }
            }
            return componentMap
        }

        private fun scc(
            v: Int,
            componentId: Int,
            componentMap: Array<Int>
        ) {
            componentMap[v] = componentId
            for (u in transposedAdjacencyList[v]) {
                if (componentMap[u] == 0) {
                    scc(u, componentId, componentMap)
                }
            }
        }
    }
}

private class Tarjan {
    companion object {
        val visited = mutableSetOf<Int>()
        val processed = mutableSetOf<Int>()
        val nums = Array(2 * tn + 2 * wn) { 0 }
        val lowest = Array(2 * tn + 2 * wn) { 0 }
        val stack = ArrayDeque<Int>()
        val onStack = mutableSetOf<Int>()
        val componentMap = Array(2 * tn + 2 * wn) { 0 }
        var componentId = 1
        var i = 1

        fun run(): Array<Int> {
            for (v in adjacencyList.indices) {
                if (!visited.contains(v)) {
                    dfs(v)
                }
            }
            return componentMap
        }

        private fun dfs(v: Int) {
            nums[v] = i
            lowest[v] = nums[v]
            i++
            visited.add(v)
            stack.addFirst(v)
            onStack.add(v)

            for (u in adjacencyList[v]) {
                if (!visited.contains(u)) {
                    dfs(u)
                    lowest[v] = minOf(lowest[v], lowest[u])
                } else {
                    if (!processed.contains(u) && onStack.contains(u)) {
                        lowest[v] = minOf(lowest[v], lowest[u])
                    }
                }
            }

            processed.add(v)

            if (lowest[v] == nums[v]) {
                var sccLiteral = stack.removeFirst()
                while (sccLiteral != v) {
                    componentMap[sccLiteral] = componentId
                    sccLiteral = stack.removeFirst()
                }
                componentMap[sccLiteral] = componentId
                componentId++
            }
        }
    }
}
