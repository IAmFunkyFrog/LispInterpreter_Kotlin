package environment

import environment.primitiveProcedures.PrimitiveProcedure

interface Environment {
    val embracingEnvironment: Environment?
    val deep: Int

    fun getVariable(variable: String): List<String>?
    fun defineVariable(variable: String, value: List<String>)
    fun setVariable(variable: String, value: List<String>)
    fun deepOfVariable(variable: String): Int
    fun getProcedure(procedure: String): Pair<List<String>, Environment>?
    fun defineProcedure(procedure: String, body: List<String>, environment: Environment)
    fun setProcedure(procedure: String, body: List<String>, environment: Environment)
    fun deepOfProcedure(procedure: String): Int
    fun getPrimitiveProcedure(procedure: String): PrimitiveProcedure?
    fun extendEnvironment(): Environment
    fun debugLog()
}