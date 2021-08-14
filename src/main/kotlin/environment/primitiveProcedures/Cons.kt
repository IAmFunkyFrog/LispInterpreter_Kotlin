package environment.primitiveProcedures

import environment.Environment
import kotlin.math.exp

class Cons() : PrimitiveProcedure {
    override val name: String = "cons"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression[0] == name) throw Exception("Not cons form given to cons evaluator")
        if(expression.size != 3) throw Exception("Cons excepted 2 parameters, but given " + (expression.size - 1))

        return ArrayList<String>().apply {
            add("cons")
            add(expression[1])
            add(expression[2])
        }
    }
}