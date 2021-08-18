package environment

import environment.primitiveProcedures.*
import java.util.*

class MapEnvironment(
    override val embracingEnvironment: Environment? = null
): Environment {
    override val deep: Int
        get() = if(embracingEnvironment == null) 0 else embracingEnvironment.deep + 1
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
        Multiplication().let {
            this[it.name] = it
        }
        Division().let {
            this[it.name] = it
        }
        And().let {
            this[it.name] = it
        }
        Error().let {
            this[it.name] = it
        }
        NumberEqual().let {
            this[it.name] = it
        }
        IsNil().let {
            this[it.name] = it
        }
    }

    private val variables = TreeMap<String, List<String>?>()
    private val procedures = TreeMap<String, Pair<List<String>, Environment>?>()

    override fun getVariable(variable: String): List<String>? {
        return if(embracingEnvironment == null) variables[variable]
        else variables[variable] ?: embracingEnvironment.getVariable(variable)
    }

    override fun defineVariable(variable: String, value: List<String>) {
        variables[variable] = value
    }

    override fun setVariable(variable: String, value: List<String>) {
        val v = variables[variable]
        if(v == null) {
            if(embracingEnvironment != null) embracingEnvironment.setVariable(variable, value)
            else throw Exception("Assignment to not defined variable not supported")
        }
        else variables[variable] = value
    }

    override fun deepOfVariable(variable: String): Int {
        return if(variables[variable] != null) deep
        else embracingEnvironment?.deepOfVariable(variable) ?: -1
    }

    override fun getProcedure(procedure: String): Pair<List<String>, Environment>? {
        return if(embracingEnvironment == null) procedures[procedure]
        else procedures[procedure] ?: embracingEnvironment.getProcedure(procedure)
    }

    override fun defineProcedure(procedure: String, body: List<String>, environment: Environment) {
        procedures[procedure] = Pair(body, environment)
    }

    override fun setProcedure(procedure: String, body: List<String>, environment: Environment) {
        val p = procedures[procedure]
        if(p == null) {
            if(embracingEnvironment != null) embracingEnvironment.setProcedure(procedure, body, environment)
            else throw Exception("Assignment to not defined procedures not supported")
        }
        else procedures[procedure] = Pair(body, environment)
    }

    override fun deepOfProcedure(procedure: String): Int {
        return if(procedures[procedure] != null) deep
        else {
            embracingEnvironment?.deepOfProcedure(procedure)
                ?: if(getPrimitiveProcedure(procedure) != null) 0
                else -1
        }
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