package environment.primitiveProcedures

import environment.Environment

interface PrimitiveProcedure {
    val name: String

    fun evaluate(expression: List<String>, environment: Environment): List<String>
}