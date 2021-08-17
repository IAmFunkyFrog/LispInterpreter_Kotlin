package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.If

class Or(): PrimitiveProcedure {
    override val name: String = "or"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression.size > 3) throw Exception("Excepted 2 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to or evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate().first
        val parameter2 = Expression(expression[2], environment).evaluate().first

        return ArrayList<String>().apply {
            if(If.isFalse(parameter1) && If.isFalse(parameter2)) add("false")
            else add("true")
        }
    }
}