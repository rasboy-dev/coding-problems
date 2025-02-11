fun main() {
    println(solution(""))
    println(solution("aaa"))
    println(solution("aaaa"))
    println(solution("aaabbb"))
    println(solution("aaabbbccc"))
    println(solution("aaabbbbcccc"))
    println(solution("aaabbabasbccaa"))
}

fun solution(s: String): Int {
    val odd = mutableSetOf<Char>()
    for (c in s) {
        if (c in odd) {
            odd.remove(c)
        } else {
            odd.add(c)
        }
    }
    return odd.size
}
