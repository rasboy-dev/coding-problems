package practice

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val input = reader.readLine()
    val n = input.length

    val tags = input.substring(1 until n - 1).split("><")
    solve(tags)

    reader.close()
}

private fun solve(tags: List<String>): String {
    if (tags.size % 2 != 0) {
        return fixBrackets(tags)
    }

    return fixTags(tags)
}

private fun fixTags(tags: List<String>): String {
    val stack = ArrayDeque<String>()
    for (i in tags.indices) {
        // missing slash
        // wrong slash
        // wrong letter
        // wrong symbol

        if (stack.first() == tags[i].substring(1)) {
            if (tags[i].first() == '/') {
                // check if wrong slash, continue otherwise
            } else {
                // check if missing slash, continue otherwise
                val fixed = tags.toMutableList()
                fixed[i] = "/${tags[i].substring(1)}"
                if (isValidXml(fixed)) {
                    return joinToXml(fixed)
                }
            }
        }

//        if (tags[i].any { it !in 'a'..'z' }) {
//            if (tags[i].first() == '/') {
//                if (stack.first() == tags[i].substring(1)) {
//                    // or try replacing slash
//
//                    // continue
//                } else {
//
//                }
//            } else {
//                // problem found
//            }
//        } else {
//        }
    }
    return ""
}

fun isValidXml(tags: MutableList<String>): Boolean {
    val stack = ArrayDeque<String>()
    for (tag in tags) {
        if (stack.isEmpty()) {
            stack.addFirst(tag)
            continue
        }
        if (tag.first() == '/') {
            if (tag.substring(1) != stack.first()) {
                return false
            }
            stack.removeFirst()
        } else {
            stack.addFirst(tag)
        }
    }
    return stack.isEmpty()
}

private fun fixBrackets(tags: List<String>): String {
    for (i in tags.indices) {
        if (tags[i].contains('>')) {
            val pos = tags[i].indexOf('>')
            val fixed = tags.toMutableList()
            fixed[i] = tags[i].replaceRange(pos..pos + 1, "><")
            return joinToXml(fixed)
        } else if (tags[i].contains('<')) {
            val pos = tags[i].indexOf('<')
            val fixed = tags.toMutableList()
            fixed[i] = tags[i].replaceRange(pos - 1..pos, "><")
            return joinToXml(fixed)
        }
    }
    return ""
}

private fun joinToXml(tags: List<String>): String {
    return "<${tags.joinToString { "><" }}>"
}