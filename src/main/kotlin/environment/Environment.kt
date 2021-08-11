package environment

interface Environment {
    fun getVariable(variable: String): String
    fun setVariable(variable: String, value: String): Boolean
    fun getProcedure(procedure: String): Pair<List<String>, Environment>
    fun setProcedure(procedure: String, environment: Environment): Boolean
}