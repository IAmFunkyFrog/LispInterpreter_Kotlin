package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression

class Cdr(): PrimitiveProcedure {
    override val name: String = "cdr"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression[0] == name) throw Exception("Not cdr form given to cdr evaluator")
        if(expression.size != 2) throw Exception("Cdr excepted 1 parameters, but given " + (expression.size - 1))

        val value = Expression(expression[1], environment).evaluate()
        if(value[0] != "cons" || expression.size != 3) throw Exception("Not cons`ed value given to cdr")

        return ArrayList<String>().apply {
            add(expression[2])
        }
    }
}