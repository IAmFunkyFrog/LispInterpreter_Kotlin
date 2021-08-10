package environment

import expressions.Expression

interface Environment {
    fun get(variable: String): String
    fun set(variable: String, value: String): Boolean
}