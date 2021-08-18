package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.If
import expressions.evaluators.specialForms.SelfEvaluating

class And(): PrimitiveProcedure {
    override val name: String = "and"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression.size > 3) throw Exception("Excepted 2 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to and evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate().first
        val parameter2 = Expression(expression[2], environment).evaluate().first

        return ArrayList<String>().apply {
            if(SelfEvaluating.isFalse(parameter1) || SelfEvaluating.isFalse(parameter2)) add("false")
            else add("true")
        }
    }
}