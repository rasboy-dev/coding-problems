package coderun.practice

import java.io.BufferedReader
import java.io.InputStreamReader

private val priorities = mapOf('(' to 0, '!' to 10, '&' to 9, '|' to 8, '^' to 8)

fun main(args: Array<String>) {
    val reader: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val expression = reader.readLine()
    val ans = evaluate(expression)
    println(if (ans) 1 else 0)

    reader.close()
}

private fun evaluate(expression: String): Boolean {
    val valueStack = ArrayDeque<Boolean>()
    val operatorStack = ArrayDeque<Char>()

    var i = 0
    val n = expression.length

    while (i < n) {
        val symbol = expression[i]
        when (symbol) {
            '0' -> valueStack.addFirst(false)
            '1' -> valueStack.addFirst(true)
            '(' -> operatorStack.addFirst(symbol)
            ')' -> {
                while (operatorStack.first() != '(') {
                    resolveOperator(operatorStack.removeFirst(), valueStack)
                }
                operatorStack.removeFirst()
            }

            else -> {
                while (operatorStack.isNotEmpty() && priorities[operatorStack.first()]!! >= priorities[symbol]!!) {
                    resolveOperator(operatorStack.removeFirst(), valueStack)
                }
                operatorStack.addFirst(symbol)
            }
        }
        i++
    }

    while (operatorStack.isNotEmpty()) {
        resolveOperator(operatorStack.removeFirst(), valueStack)
    }

    return valueStack.first()
}

fun resolveOperator(operator: Char, valueStack: ArrayDeque<Boolean>) {
    when (operator) {
        '!' -> valueStack.addFirst(valueStack.removeFirst().not())
        '&' -> {
            val a = valueStack.removeFirst()
            val b = valueStack.removeFirst()
            valueStack.addFirst(a.and(b))
        }

        '|' -> {
            val a = valueStack.removeFirst()
            val b = valueStack.removeFirst()
            valueStack.addFirst(a.or(b))
        }

        '^' -> {
            val a = valueStack.removeFirst()
            val b = valueStack.removeFirst()
            valueStack.addFirst(a.xor(b))
        }

        else -> throw IllegalArgumentException("$operator is not a valid operator")
    }
}

