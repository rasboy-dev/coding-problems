import java.io.BufferedReader
import java.io.InputStreamReader

class ProblemETL {
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

            val graphF = Array(n) { mutableSetOf<Int>() }
            for (i in 1..m1) {
                val (a, b) = reader.readLine().split(" ").map { it.toInt() }
                graphF[a - 1].add(b - 1)
                graphF[b - 1].add(a - 1)
            }

            val graphG = Array(n) { mutableSetOf<Int>() }
            for (i in 1..m2) {
                val (a, b) = reader.readLine().split(" ").map { it.toInt() }
                graphG[a - 1].add(b - 1)
                graphG[b - 1].add(a - 1)
            }

            return this.solve(n, graphF, graphG)
        }

        private fun solve(n: Int, graphF: Array<MutableSet<Int>>, graphG: Array<MutableSet<Int>>): Int {
            val connectivityG = findConnectivityComponents(graphG)
            println(connectivityG)
            var opCounter = 0

            for (gComp in connectivityG.values) {
                for (v in gComp) {
                    val remove = graphF[v].filter{it !in gComp}.toList()
                    for (u in remove) {
                        graphF[v].remove(u)
                        graphF[u].remove(v)
                        opCounter++
                    }
                }
            }


            val connectivityF = findConnectivityComponents(graphF)

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

        private fun findConnectivityComponents(graph: Array<MutableSet<Int>>): MutableMap<Int, MutableSet<Int>> {
            val n = graph.size
            val conn = IntArray(n) { i -> i }
            val sizes = IntArray(n) { 1 }
            for (v in 0..<n) {
                for (u in graph[v]) {
                    val vRoot = findRoot(conn, v)
                    val uRoot = findRoot(conn, u)
                    if (sizes[vRoot] < sizes[uRoot]) {
                        conn[vRoot] = uRoot
                        sizes[uRoot] += sizes[vRoot]
                    } else {
                        conn[uRoot] = vRoot
                        sizes[vRoot] += sizes[uRoot]
                    }
                }
            }

            val connComps = mutableMapOf<Int, MutableSet<Int>>()
            for (v in 0..<n) {
                val root = findRoot(conn, v)
                if (root !in connComps) {
                    connComps[root] = mutableSetOf(root)
                }
                connComps[root]!!.add(v)
            }

            return connComps
        }

        private fun findRoot(connectivity: IntArray, u: Int): Int {
            var root = u
            while (root != connectivity[root]) {
                root = connectivity[root]
            }
            var v = u
            while (v != root) {
                val nextV = connectivity[v]
                connectivity[v] = root
                v = nextV
            }
            return root
        }
    }
}

fun main(args: Array<String>) {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        ProblemETL.test(reader)
    }
}
