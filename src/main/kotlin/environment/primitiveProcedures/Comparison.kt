package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression
import java.lang.reflect.Array

class Comparison(): PrimitiveProcedure {
    override val name: String = ">"

    override fun evaluate(expression: List<String>, environment: Environment): Pair<List<String>, Environment> {
        if(expression.size > 3) throw Exception("Excepted 2 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to comparison evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate().first
        val parameter2 = Expression(expression[2], environment).evaluate().first

        return Pair(if(isNumber(parameter1) && isNumber(parameter2)) ArrayList<String>().apply {
            if(parameter1[1].toFloat() > parameter2[1].toFloat()) add("true")
            else add("false")
        }
        else throw Exception("Bad parameters given to comparison procedure"), environment)
    }

    private fun isNumber(expression: List<String>) = expression[0] == "int" || expression[0] == "float"
}