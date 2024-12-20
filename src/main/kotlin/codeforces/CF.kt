package codeforces

import java.io.BufferedReader

fun readIntList(reader: BufferedReader): List<Int> {
    return reader.readLine().split(" ").map {it.toInt()}
}

fun yesNo(b: Boolean) = if (b) "yes" else "no"