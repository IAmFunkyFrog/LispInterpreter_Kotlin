package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.If
import expressions.evaluators.specialForms.SelfEvaluating

class Not(): PrimitiveProcedure {
    override val name: String = "not"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression.size > 2) throw Exception("Excepted 1 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to not evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate().first

        return ArrayList<String>().apply {
            if(SelfEvaluating.isFalse(parameter1)) add("true")
            else add("false")
        }
    }

}