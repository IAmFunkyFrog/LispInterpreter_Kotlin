package environment

class MapEnvironment: Environment {
    override fun getVariable(variable: String): String {
        TODO("Not yet implemented")
    }

    override fun setVariable(variable: String, value: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getProcedure(procedure: String): Pair<List<String>, Environment> {
        TODO("Not yet implemented")
    }

    override fun setProcedure(procedure: String, environment: Environment): Boolean {
        TODO("Not yet implemented")
    }

}