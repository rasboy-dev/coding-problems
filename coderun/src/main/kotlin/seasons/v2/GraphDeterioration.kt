package seasons.v2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    fromConsole()

// 0 -1 -1 3 4
//-1 0 1 5 -1
//-1 1 0 2 1
//3 5 2 0 1
//4 -1 1 1 0
//1 != 2


//    test(
//        "0 -1 6 6 2",
//        "-1 0 1 2 3",
//        "6 1 0 3 1",
//        "6 2 3 0 4",
//        "2 3 1 4 0"
//    )
//
//    test(
//        "0 5 5 1 5",
//        "5 0 3 -1 4",
//        "5 3 0 1 2",
//        "1 -1 1 0 5",
//        "5 4 2 5 0"
//    )
//
//    test(
//        "0 4 4 1",
//        "4 0 1 4",
//        "4 1 0 1",
//        "1 4 1 0",
//    )
//
//    runTests()
//
//    test(
//        "0 -1 4 2 3",
//        "-1 0 3 -1 -1",
//        "4 3 0 4 1",
//        "2 -1 4 0 1",
//        "3 -1 1 1 0",
//    )
//
//    test(
//        "0 1 -1 -1 4",
//        "1 0 2 1 -1",
//        "-1 2 0 1 1",
//        "-1 1 1 0 3",
//        "4 -1 1 3 0"
//    )
//
//    runGeneratedTests()
}

private fun fromConsole() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    n = reader.readLine().toInt()
    val adjacencyMatrix = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        var j = 0
        reader.readLine().split(" ").map { adjacencyMatrix[i][j] = it.toInt(); j++ }
    }
    reader.close()

    val maxDeterioration = findMaxDeterioration(n, adjacencyMatrix)

    println(maxDeterioration)
}

private fun runTests() {
    test(
        "0 1 -1",
        "1 0 1",
        "-1 1 0"
    )

    test(
        "0 1 1",
        "1 0 -1",
        "1 -1 0"
    )

    test(
        "0 1",
        "1 0"
    )

    test(
        "0 -1 -1",
        "-1 0 1",
        "-1 1 0"
    )

    test(
        "0 16 12 1 12",
        "16 0 12 13 -1",
        "12 12 0 5 2",
        "1 13 5 0 2",
        "12 -1 2 2 0"
    )

    test(
        "0 1 -1 -1",
        "1 0 1 2",
        "-1 1 0 -1",
        "-1 2 -1 0"
    )

    test(
        "0 -1",
        "-1 0"
    )

    test(
        "0",
    )

    test(
        "0 1 2",
        "1 0 1",
        "2 1 0"
    )

    test(
        "0 1 2 -1",
        "1 0 1 -1",
        "2 1 0 -1",
        "-1 -1 -1 0"
    )

    test(
        "0 1 2 -1",
        "1 0 -1 2",
        "2 -1 0 1",
        "-1 2 1 0"
    )

    test(
        "0 1 2 -1 -1",
        "1 0 -1 2 -1",
        "2 -1 0 1 -1",
        "-1 2 1 0 -1",
        "-1 -1 -1 -1 0"
    )

    test(
        "0 4 2",
        "4 0 2",
        "2 2 0"
    )

    test(
        "0 -1 -1",
        "-1 0 -1",
        "-1 -1 0"
    )

    test(
        "0 1 2 -1 -1",
        "1 0 -1 2 -1",
        "2 -1 0 1 -1",
        "-1 2 1 0 1",
        "-1 -1 -1 1 0"
    )

    test(
        "0 1 -1 -1 -1",
        "1 0 2 1 -1",
        "-1 2 0 -1 1",
        "-1 1 -1 0 2",
        "-1 -1 1 2 0"
    )

    test(
        "0 1 -1 -1 -1",
        "1 0 2 1 -1",
        "-1 2 0 3 1",
        "-1 1 3 0 2",
        "-1 -1 1 2 0"
    )

    test(
        "0 1 2 -1 -1",
        "1 0 1 2 3",
        "2 1 0 1 4",
        "-1 2 1 0 1",
        "-1 3 4 1 0"
    )

    test(
        "0 1 3 2 3 -1 -1",
        "1 0 -1 1 2 2 3",
        "3 -1 0 1 -1 2 3",
        "2 1 1 0 3 1 4",
        "3 2 -1 3 0 3 2",
        "-1 2 2 1 3 0 1",
        "-1 3 3 4 2 1 0"
    )

    test(
        "0 1 -1 -1 4",
        "1 0 2 1 -1",
        "-1 2 0 1 1",
        "-1 1 1 0 3",
        "4 -1 1 3 0"
    )

    test(
        "0 4 -1 -1 -1 -1 3",
        "4 0 2 -1 2 1 4",
        "-1 2 0 1 3 -1 1",
        "-1 -1 1 0 1 2 2",
        "-1 2 3 1 0 4 4",
        "-1 1 -1 2 4 0 1",
        "3 4 1 2 4 1 0"
    )

    test(
        "0 1 -1 -1 4",
        "1 0 2 1 -1",
        "-1 2 0 1 1",
        "-1 1 1 0 3",
        "4 -1 1 3 0"
    )

    test(
        "0 2 1 4 -1",
        "2 0 1 1 3",
        "1 1 0 -1 -1",
        "4 1 -1 0 1",
        "-1 3 -1 1 0"
    )


}

private fun runGeneratedTests() {
//    for (x in 0..100000) {
    while (true) {
        val n = (1..5).random()
        val mx = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val d = (0..6).random()
                mx[i][j] = if (d > 0) d else -1
                mx[j][i] = if (d > 0) d else -1
            }
        }
        val mxStr = mx.map { it.joinToString(" ") }.toTypedArray()
        test(*mxStr)
    }
}

private fun test(vararg rows: String) {
    val matrix: Array<IntArray> = parseMatrix(rows)
    println("Starting new test, n = ${matrix.size}")
    val start = System.currentTimeMillis()
    val expected = findMaxDeteriorationNaive(matrix.size, matrix)
    val naiveFinished = System.currentTimeMillis()
    val res = findMaxDeterioration(matrix.size, matrix)
    val optimizedFinished = System.currentTimeMillis()
    println("naive took ${naiveFinished - start}, optimized took ${optimizedFinished - naiveFinished}")
    if (expected != res) {
        for (row in matrix) {
            println(row.joinToString(" "))
        }
        println("$expected != $res")
        throw AssertionError()
    }
}

private fun parseMatrix(rows: Array<out String>): Array<IntArray> {
    n = rows.size
    val adjacencyMatrix = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        var j = 0
        rows[i].split(" ").map { adjacencyMatrix[i][j] = it.toInt(); j++ }
    }
    return adjacencyMatrix
}

private fun findMaxDeteriorationNaive(n: Int, adjacencyMatrix: Array<IntArray>): Int {
    var maxDiff = 0
    var maxEdge = Pair(-1, -1)
    val (dists, reachable) = dijkstra(n, adjacencyMatrix)
    for (i in 0 until n) {
        if (i !in reachable)
            continue
        for (j in i + 1 until n) {
            if (j !in reachable)
                continue
            val edge = adjacencyMatrix[i][j]
            adjacencyMatrix[i][j] = -1
            adjacencyMatrix[j][i] = -1
            val (distsNew) = dijkstra(n, adjacencyMatrix)
            adjacencyMatrix[i][j] = edge
            adjacencyMatrix[j][i] = edge

            var diff = 0
            for (k in 0 until n) {
                if (dists[k] != distsNew[k])
                    diff++
            }
            if (diff > maxDiff) {
                maxEdge = Pair(i, j)
            }
            maxDiff = maxOf(diff, maxDiff)
        }
    }
    println(maxEdge)
    return maxDiff
}

private fun dijkstra(n: Int, adjacencyMatrix: Array<IntArray>): Pair<IntArray, Set<Int>> {
    val dists = IntArray(n) { Int.MAX_VALUE }
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    val visited = mutableSetOf<Int>()

    dists[0] = 0
    queue.add(Pair(0, 0))

    while (queue.isNotEmpty()) {
        val v = queue.poll()
        if (v.first in visited) continue
        visited.add(v.first)
        for (u in 1 until n) {
            if (u !in visited && adjacencyMatrix[v.first][u] > 0) {
                dists[u] = minOf(dists[u], dists[v.first] + adjacencyMatrix[v.first][u])
                queue.add(Pair(u, dists[u]))
            }
        }
    }
    return Pair(dists, visited)
}

private fun findMaxDeterioration(n: Int, adjacencyMatrix: Array<IntArray>): Int {
    val dists = IntArray(n) { Int.MAX_VALUE }
    val pathGraph = Array(n) { mutableSetOf<Int>() }
    val parents = Array(n) { mutableSetOf<Int>() }
    val parentsCounter = IntArray(n)
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    val visited = mutableSetOf<Int>()
    val multiPath = mutableSetOf<Int>()

    dists[0] = 0
    queue.add(Pair(0, 0))

    while (queue.isNotEmpty()) {
        val v = queue.poll()
        if (v.first in visited) {
            continue
        }

        visited.add(v.first)

        for (i in 1 until n) {
            if (adjacencyMatrix[v.first][i] >= 0 && v.first != i) {
                val distToNeighbor = dists[v.first] + adjacencyMatrix[v.first][i]
                if (dists[i] == distToNeighbor) {
                    parents[i].add(v.first)
                    parentsCounter[i]++
                    multiPath.add(i)
                } else if (i !in visited && dists[i] > distToNeighbor) {
                    dists[i] = distToNeighbor
                    multiPath.remove(i)
                    parents[i] = mutableSetOf(v.first)
                    parentsCounter[i] = 1
                    queue.add(Pair(i, dists[i]))
                }
            }
        }
    }

    for (i in 1 until n) {
        parents[i].forEach { pathGraph[it].add(i) }
    }

    val paths = IntArray(n)
    val unresolvedParents = Array(n) { mutableSetOf<Int>() }
    val unresolvedCounters = Array(n) { mutableMapOf<Int, Int>() }
    val vertices = Array(n) { Vertice(it, parentsCounter[it]) }
    for (i in 0 until n) {
        pathGraph[i].forEach { vertices[i].children.add(vertices[it]) }
    }
//    visitedV = mutableSetOf()
//    visited.clear()
//    dfsCountPaths(0, pathGraph, visited, paths, parentsCounter, unresolvedParents, unresolvedCounters)
    dfsCountPathsVertices(vertices[0], null)
    var maxPaths = 0
    for (i in 1 until n) {
        if (maxPaths < vertices[i].pathsThrough && i !in multiPath && i in visited) {
            maxPaths = vertices[i].pathsThrough
        }
    }
    return maxPaths
}

var n: Int = 0

//val tree: Array<MutableSet<Int>> = Array(n) { mutableSetOf() }
//lateinit var tree: Array<MutableSet<Int>>
//lateinit var paths: IntArray
//lateinit var parents: Array<MutableSet<Int>>
//lateinit var parentsCounter: IntArray
//lateinit var multiPath: MutableSet<Int>
//val pathsInProgress: IntArray = IntArray(n)
//lateinit var visitedV: MutableSet<Int>
//val ignored: MutableSet<Int> = mutableSetOf()

/*

unresolved parent =
    if has unresolved parents
        first unresolved parent
    else if !resolvable
        itself
    else
        null

for i in children
    dfsCountPaths(unresolved parent)
    check resolvability
    mark as resolvable if resolvable

for i in resolvable children
    resolve i






 */
private fun dfsCountPathsVertices(
    vertice: Vertice,
    unresolvedParent: Vertice?
) {
    if (unresolvedParent != null) {
//        unresolvedParent.unresolvedVertices.add(vertice)
        vertice.unresolvedParents.add(unresolvedParent)
    }

    if (vertice.visited)
        return

    vertice.visited = true

    if (vertice.children.isEmpty())
        return

    val firstUnresolved =
        if (unresolvedParent == null && vertice.unresolvedWaysTo > 1)
            vertice
        else
            unresolvedParent

    val unresolvedVertices = mutableMapOf<Vertice, MutableSet<Pair<Vertice, Vertice>>>()
    for (child in vertice.children) {
        dfsCountPathsVertices(child, firstUnresolved)
        unresolvedVertices[child] = unresolvedVertices.getOrDefault(child, mutableSetOf())
        unresolvedVertices[child]!!.add(Pair(vertice, child))
        for (subChild in child.unresolvedVertices) {
//            unresolvedVertices[subChild] = unresolvedVertices.getOrDefault(subChild, 0) + 1
            unresolvedVertices[subChild.second] = unresolvedVertices.getOrDefault(subChild.second, mutableSetOf())
            unresolvedVertices[subChild.second]!!.add(subChild)
        }
    }

    for ((v, waysTo) in unresolvedVertices) {
//        if (waysTo.size > 1) {
//            v.unresolvedWaysTo -= waysTo.size - 1
//        }
        if (waysTo.size > 1 && v.unresolvedWaysTo == waysTo.size) {
            v.unresolvedWaysTo -= waysTo.size - 1
            for (child in v.unresolvedVertices) {
                child.second.unresolvedParents.remove(v)
            }
        }
    }

    val resolved = mutableSetOf<Vertice>()
    for (v in unresolvedVertices.keys) {
        if (v.unresolvedWaysTo <= 1 && (v.unresolvedParents.isEmpty() || v.unresolvedParents == vertice.unresolvedParents)) {
            resolved.add(v)
        }
    }

    for (v in resolved) {
        unresolvedVertices.remove(v)
        vertice.pathsThrough += v.pathsThrough
    }

    vertice.unresolvedVertices.addAll(unresolvedVertices.values.flatten())
}

private fun dfsCountPaths(
    v: Int,
    tree: Array<MutableSet<Int>>,
    visited: MutableSet<Int>,
    paths: IntArray,
    parentsCounter: IntArray,
    unresolveParents: Array<MutableSet<Int>>,
    unresolvedCounters: Array<MutableMap<Int, Int>>,
): MutableSet<Int> {
    if (v !in visited) {
        val unresolvedCounter = mutableMapOf<Int, Int>()
        if (tree[v].isNotEmpty()) {
            for (i in tree[v]) {
                val unresolvedVertices =
                    dfsCountPaths(i, tree, visited, paths, parentsCounter, unresolveParents, unresolvedCounters)
                if (i !in unresolvedVertices) {
                    paths[v] += paths[i]
                }
                for (j in unresolvedVertices) {
                    unresolvedCounter[j] = unresolvedCounter.getOrDefault(j, 0) + 1
                }
            }

            val resolved = mutableSetOf<Int>()
            for ((i, unresolvedWaysThrough) in unresolvedCounter) {
                if (unresolvedWaysThrough > 1) {
                    parentsCounter[i] -= unresolvedWaysThrough - 1
                }
                if (parentsCounter[i] <= 1) {
                    resolved.add(i)
                    for (key in unresolvedCounter.keys) {
                        if (i in unresolveParents[key]) {
                            unresolveParents[key].remove(i)
                        }
                    }
                }
            }
            for (i in resolved) {
                if (unresolveParents[i].isEmpty()) {
                    unresolvedCounter.remove(i)
                    paths[v] += paths[i]
                }
            }
        }
        unresolvedCounters[v] = unresolvedCounter
        paths[v] += 1
        visited.add(v)

        val unresolved = unresolvedCounters[v].keys.toMutableSet()
        if (parentsCounter[v] > 1) {
            for (u in unresolved) {
                unresolveParents[u].add(v)
            }
        }
    }

    val unresolved = unresolvedCounters[v].keys.toMutableSet()
    if (parentsCounter[v] > 1) {
        for (u in unresolved) {
            unresolveParents[u].add(v)
        }
        unresolved.add(v)
    }

    return unresolved
}

private data class Vertice(
    val id: Int,
    var unresolvedWaysTo: Int
) {
    val unresolvedVertices = mutableSetOf<Pair<Vertice, Vertice>>()
    val unresolvedParents = mutableSetOf<Vertice>()
    var parents: Int = 0
    var visited: Boolean = false
    var canResolve = false
    var pathsThrough = 1
    val children = mutableSetOf<Vertice>()

    override fun hashCode() = Objects.hash(id)
}
