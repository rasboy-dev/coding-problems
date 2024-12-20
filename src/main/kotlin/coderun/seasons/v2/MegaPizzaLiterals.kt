package coderun.seasons.v2

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (friendsNumber, toppingsNumber, wishesNumber) = reader.readLine().split(" ").map { it.toInt() }

    val wishes = mutableListOf<Triple<Int, Int, Boolean>>()
    for (i in 0 until wishesNumber) {
        val (friend, toppingWish) = reader.readLine().split(" ").map { it.toInt() }

        wishes.add(Triple(friend, abs(toppingWish), toppingWish > 0))
    }

    val solution = solve(
        friendsNumber,
        toppingsNumber,
        wishes
    )
    if (solution.isNotEmpty() && solution[0] == -1) {
        println(-1)
    } else {
        println(solution.size)
        println(solution.joinToString(" "))
    }

    reader.close()
}

private fun solve(friendsNumber: Int, toppingsNumber: Int, wishes: List<Triple<Int, Int, Boolean>>): List<Int> {
    val graph = constructGraph(friendsNumber, toppingsNumber, wishes)
//    val graph = constructGraphNaive(friendsNumber, toppingsNumber, wishes)

    val componentMap = TarjanLiterals(graph).run()
//    val componentMap = Kosaraju(graph).run()

    return findSolution(graph, toppingsNumber, componentMap)
}

private fun constructGraphNaive(
    friendsNumber: Int,
    toppingsNumber: Int,
    inputWishes: List<Triple<Int, Int, Boolean>>
): GraphLiterals {
    val graph = GraphLiterals()

    val friendToWishes = mutableMapOf<Int, MutableList<Literal>>()

    for (inputWish in inputWishes) {
        val (friend, topping, wanted) = inputWish
        friendToWishes[friend] = friendToWishes.getOrDefault(friend, mutableListOf())
        // add edges for each pair (toppingWish, w in wished[friend])

        val wishLiteral = Literal.of(topping, wanted)
        graph.addVerticeIfNotExist(wishLiteral)
        graph.addVerticeIfNotExist(wishLiteral.not())

        for (wish in friendToWishes[friend]!!) {
            graph.addEdgeIfNotExist(wishLiteral.not(), wish)
            graph.addEdgeIfNotExist(wish.not(), wishLiteral)
        }

        friendToWishes[friend]!!.add(wishLiteral)
    }

    return graph
}

private fun constructGraph(
    friendsNumber: Int,
    toppingsNumber: Int,
    wishes: List<Triple<Int, Int, Boolean>>
): GraphLiterals {
    val graph = GraphLiterals()

    val friendToWishes = mutableMapOf<Int, MutableList<Literal>>()

    for (wish in wishes) {
        val (friend, toppingWish, wanted) = wish
        friendToWishes[friend] = friendToWishes.getOrDefault(friend, mutableListOf())
        val wishLiteral = Literal.of(toppingWish, wanted)
        friendToWishes[friend]!!.add(wishLiteral)
    }

    var prefix = -1

    for (friendWishes in friendToWishes.values) {
        for (i in 0..friendWishes.lastIndex) {
            val wishLiteral = friendWishes[i]

            val prefixLiteral = Literal.of(prefix, true)

            graph.addEdgeIfNotExist(wishLiteral.not(), prefixLiteral)
            graph.addEdgeIfNotExist(prefixLiteral.not(), wishLiteral)

            if (i > 0) {
                val prevPrefixLiteral = Literal.of(prefix + 1, true)
                graph.addEdgeIfNotExist(prevPrefixLiteral, wishLiteral)
                graph.addEdgeIfNotExist(wishLiteral.not(), prevPrefixLiteral.not())

                graph.addEdgeIfNotExist(prevPrefixLiteral, prefixLiteral)
                graph.addEdgeIfNotExist(prefixLiteral.not(), prevPrefixLiteral.not())
            }

            prefix--
        }
    }

    return graph
}

private fun findSolution(
    graph: GraphLiterals,
    toppingsNumber: Int,
    componentMap: Map<Literal, Int>
): List<Int> {
    val includedToppings = mutableListOf<Int>()
    for (topping in 1 .. toppingsNumber) {
        val literal = Literal.of(topping, true)
        if (!componentMap.containsKey(literal)) continue
        if (componentMap[literal]!! == componentMap[literal.not()]!!) {
            return listOf(-1)
        }
        if (componentMap[literal]!! < componentMap[literal.not()]!!) {
            includedToppings.add(literal.value)
        }
    }
    return includedToppings
}

private class KosarajuLiteral(val graph: GraphLiterals) {
    fun run(): Map<Literal, Int> {
        val topologicalOrder = topologicalSort()
        return getComponents(topologicalOrder)
    }

    private fun topologicalSort(): List<Literal> {
        val topologicalOrder = mutableListOf<Literal>()
        val visited = mutableSetOf<Literal>()

        for (v in graph.vertices) {
            if (!visited.contains(v))
                dfs(v, topologicalOrder, visited)
        }
        return topologicalOrder.reversed()
    }

    private fun dfs(
        v: Literal,
        topologicalOrder: MutableCollection<Literal>,
        visited: MutableSet<Literal>
    ) {
        visited.add(v)
        for (neighbor in graph.getNeighbors(v)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, topologicalOrder, visited)
            }
        }
        topologicalOrder.add(v)
    }

    private fun getComponents(topologicalOrder: List<Literal>): Map<Literal, Int> {
        val componentMap = mutableMapOf<Literal, Int>()
        val visited = mutableSetOf<Literal>()

        var componentId = 1
        for (v in topologicalOrder) {
            if (!visited.contains(v)) {
                scc(v, visited, componentMap, componentId)
                componentId++
            }
        }
        return componentMap
    }

    private fun scc(
        v: Literal,
        visited: MutableSet<Literal>,
        componentMap: MutableMap<Literal, Int>,
        componentId: Int
    ) {
        visited.add(v)
        componentMap[v] = componentId

        for (u in graph.transposed().getNeighbors(v)) {
            if (!visited.contains(u)) {
                scc(u, visited, componentMap, componentId)
            }
        }
    }
}

private class TarjanLiterals(val graph: GraphLiterals) {
    val visited = mutableSetOf<Literal>()
    val processed = mutableSetOf<Literal>()
    val nums = mutableMapOf<Literal, Int>()
    val lowest = mutableMapOf<Literal, Int>()
    val stack = ArrayDeque<Literal>()
    val onStack = mutableSetOf<Literal>()
    val componentMap = mutableMapOf<Literal, Int>()
    var componentId = 1
    var i = 1

    fun run(): Map<Literal, Int> {
        for (v in graph.vertices) {
            if (!visited.contains(v)) {
                dfs(graph, v)
            }
        }
        return componentMap
    }

    private fun dfs(graph: GraphLiterals, v: Literal) {
        nums[v] = i
        lowest[v] = nums[v]!!
        i++
        visited.add(v)
        stack.addFirst(v)
        onStack.add(v)

        for (u in graph.getNeighbors(v)) {
            if (!visited.contains(u)) {
                dfs(graph, u)
                lowest[v] = minOf(lowest[v]!!, lowest[u]!!)
            } else {
                if (!processed.contains(u) && onStack.contains(u)) {
                    lowest[v] = minOf(lowest[v]!!, lowest[u]!!)
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

private class GraphLiterals(withTransposed: Boolean = true) {
    private val adjacencyList = mutableMapOf<Literal, MutableSet<Literal>>()
    private val transposed: GraphLiterals?
    val vertices = adjacencyList.keys

    init {
        transposed = if (withTransposed) {
            GraphLiterals(false)
        } else {
            null
        }
    }

    fun addVerticeIfNotExist(v: Literal) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList[v] = mutableSetOf()
            transposed?.addVerticeIfNotExist(v)
        }
    }

    fun addEdgeIfNotExist(v: Literal, u: Literal) {
        adjacencyList[v] = adjacencyList.getOrDefault(v, mutableSetOf())
        if (!adjacencyList[v]!!.contains(u)) {
            adjacencyList[v]!!.add(u)
            transposed?.addEdgeIfNotExist(u, v)
        }
    }

    fun transposed(): GraphLiterals {
        return transposed!!
    }

    fun getNeighbors(v: Literal): Set<Literal> {
        return adjacencyList.getOrDefault(v, mutableSetOf())
    }
}

private data class Literal(val value: Int, val sign: Boolean) {
    fun not(): Literal {
        return literals[value]!![!sign]!!
    }

    companion object {
        private val literals = mutableMapOf<Int, Map<Boolean, Literal>>()

        fun of(value: Int, sign: Boolean): Literal {
            if (!literals.containsKey(value)) {
                literals[value] = mutableMapOf(sign to Literal(value, sign), !sign to Literal(value, !sign))
            }
            return literals[value]!![sign]!!
        }
    }
}