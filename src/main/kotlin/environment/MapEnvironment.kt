package environment

import environment.primitiveProcedures.*
import java.util.*

class MapEnvironment(
    override val embracingEnvironment: Environment? = null
): Environment {
    //TODO засунуть инициализацию в интерфейс
    private val primitiveProcedures = TreeMap<String, PrimitiveProcedure>().apply {
        Plus().let {
            this[it.name] = it
        }
        Comparison().let {
            this[it.name] = it
        }
        Not().let {
            this[it.name] = it
        }
        Or().let {
            this[it.name] = it
        }
        Minus().let {
            this[it.name] = it
        }
    }

    private val variables = TreeMap<String, List<String>>()
    private val procedures = TreeMap<String, Pair<List<String>, Environment>>()

    override fun getVariable(variable: String): List<String>? {
        return if(embracingEnvironment == null) variables[variable]
        else variables[variable] ?: embracingEnvironment.getVariable(variable)
    }

    override fun setVariable(variable: String, value: List<String>) {
        variables[variable] = value
    }

    override fun getProcedure(procedure: String): Pair<List<String>, Environment>? {
        return if(embracingEnvironment == null) procedures[procedure]
        else procedures[procedure] ?: embracingEnvironment.getProcedure(procedure)
    }

    override fun setProcedure(procedure: String, body: List<String>, environment: Environment) {
        procedures[procedure] = Pair(body, environment)
    }

    override fun getPrimitiveProcedure(procedure: String): PrimitiveProcedure? {
        return primitiveProcedures[procedure]
    }

    override fun extendEnvironment(): Environment = MapEnvironment(this)

    override fun debugLog() {
        println(variables)
        println(procedures)
    }

}