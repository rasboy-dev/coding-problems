package leetcode

class RandomizedSet {

    val map = mutableMapOf<Int, Int>()
    val list = mutableListOf<Int>()

    fun insert(v: Int): Boolean {
        if (v in map) return false
        list.add(v)
        map[v] = list.lastIndex
        return true
    }

    fun remove(v: Int): Boolean {
        if (v !in map) return false
        val id = map.remove(v)!!
        val last = list.removeLast()
        if (v != last) {
            list[id] = last
            map[last] = id
        }
        return true
    }

    fun getRandom(): Int {
        return list[(0..list.lastIndex).random()]
    }
}