import java.io.BufferedReader
import java.io.InputStreamReader

class ProblemE {
    companion object {

        fun test(reader: BufferedReader) {

            val t = reader.readLine().toInt()

            for (q in 1..t) {
                val ans = problem(reader)
//                println(if (ans) "yes" else "no")
                println(ans)
            }
        }

        private fun problem(reader: BufferedReader): Int {
            val (n, m1, m2) = reader.readLine().split(" ").map { it.toInt() }

            val graphF = mutableMapOf<Int, MutableSet<Int>>()
            for (i in 1..m1) {
                val (v, u) = reader.readLine().split(" ").map { it.toInt() }
                graphF[v - 1] = graphF.getOrDefault(v - 1, mutableSetOf())
                graphF[u - 1] = graphF.getOrDefault(u - 1, mutableSetOf())
                graphF[v - 1]!!.add(u - 1)
                graphF[u - 1]!!.add(v - 1)
            }

            val graphG = mutableMapOf<Int, MutableSet<Int>>()
            for (i in 1..m2) {
                val (u, v) = reader.readLine().split(" ").map { it.toInt() }
                graphG[v - 1] = graphG.getOrDefault(v - 1, mutableSetOf())
                graphG[u - 1] = graphG.getOrDefault(u - 1, mutableSetOf())
                graphG[v - 1]!!.add(u - 1)
                graphG[u - 1]!!.add(v - 1)
            }

            return this.solve(n, graphF, graphG)
        }

        private fun solve(
            n: Int,
            graphF: MutableMap<Int, MutableSet<Int>>,
            graphG: MutableMap<Int, MutableSet<Int>>
        ): Int {
            val connectivityG = findConnectivityComponents(n, graphG)
            println(connectivityG)
            var opCounter = 0

            for (gComp in connectivityG.values) {
                for (v in gComp) {
                    if (v in graphF) {
                        val remove = graphF[v]!!.filter { it !in gComp }.toList()
                        for (u in remove) {
                            graphF[v]!!.remove(u)
                            graphF[u]!!.remove(v)
                            opCounter++
                        }
                    }
                }
            }

            val connectivityF = findConnectivityComponents(n, graphF)

            val rerootG = mutableMapOf<Int, MutableSet<Int>>()
            for (gComp in connectivityG.values) {
                rerootG[gComp.min()] = gComp
            }
            val rerootF = mutableMapOf<Int, MutableSet<Int>>()
            for (fComp in connectivityF.values) {
                rerootF[fComp.min()] = fComp
            }

            for (root in rerootF.keys) {
                if (root !in rerootG) {
                    opCounter++
                }
            }
            return opCounter

        }

        private fun findConnectivityComponents(n: Int, graph: MutableMap<Int, MutableSet<Int>>): MutableMap<Int, MutableSet<Int>> {
            val conn = mutableMapOf<Int,Int>()
            val sizes = mutableMapOf<Int,Int>()
            graph.keys.forEach {conn[it] = it; sizes[it] = 1}

            for (v in graph.keys) {
                for (u in graph[v]!!) {
                    val vRoot = findRoot(conn, v)
                    val uRoot = findRoot(conn, u)
                    if (sizes[vRoot]!! < sizes[uRoot]!!) {
                        conn[vRoot] = uRoot
                        sizes[uRoot] = sizes[vRoot]!! + sizes[uRoot]!!
                    } else {
                        conn[uRoot] = vRoot
                        sizes[vRoot] = sizes[vRoot]!! + sizes[uRoot]!!
                    }
                }
            }

            val connComps = mutableMapOf<Int, MutableSet<Int>>()
            for (v in graph.keys) {
                val root = findRoot(conn, v)
                if (root !in connComps) {
                    connComps[root] = mutableSetOf(root)
                }
                connComps[root]!!.add(v)
            }

            return connComps
        }

        private fun findRoot(connectivity: MutableMap<Int, Int>, u: Int): Int {
            var root = u
            while (root != connectivity[root]) {
                root = connectivity[root]!!
            }
            var v = u
            while (v != root) {
                val nextV = connectivity[v]!!
                connectivity[v] = root
                v = nextV
            }
            return root
        }
    }
}

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        ProblemE.test(reader)
    }
}
