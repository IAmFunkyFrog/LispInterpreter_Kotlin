package environment

import environment.primitiveProcedures.PrimitiveProcedure

class MapEnvironment: Environment {
    override fun getVariable(variable: String): List<String>? {
        TODO("Not yet implemented")
    }

    override fun setVariable(variable: String, value: List<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun getProcedure(procedure: String): Pair<List<String>, Environment>? {
        TODO("Not yet implemented")
    }

    override fun setProcedure(procedure: String, body: List<String>, environment: Environment): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPrimitiveProcedure(procedure: String): PrimitiveProcedure? {
        TODO("Not yet implemented")
    }

    override fun extendEnvironment(): Environment {
        TODO("Not yet implemented")
    }

}