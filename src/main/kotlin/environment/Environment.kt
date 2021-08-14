package environment

import environment.primitiveProcedures.PrimitiveProcedure

interface Environment {
    fun getVariable(variable: String): List<String>?
    fun setVariable(variable: String, value: List<String>): Boolean
    fun getProcedure(procedure: String): Pair<List<String>, Environment>?
    fun setProcedure(procedure: String, body: List<String>, environment: Environment): Boolean
    fun getPrimitiveProcedure(procedure: String): PrimitiveProcedure?
    fun extendEnvironment(): Environment
}