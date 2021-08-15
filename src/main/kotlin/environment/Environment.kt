package environment

import environment.primitiveProcedures.PrimitiveProcedure

interface Environment {
    fun getVariable(variable: String): List<String>?
    fun setVariable(variable: String, value: List<String>)
    fun getProcedure(procedure: String): Pair<List<String>, Environment>?
    fun setProcedure(procedure: String, body: List<String>, environment: Environment)
    fun getPrimitiveProcedure(procedure: String): PrimitiveProcedure?
    fun extendEnvironment(): Environment
}