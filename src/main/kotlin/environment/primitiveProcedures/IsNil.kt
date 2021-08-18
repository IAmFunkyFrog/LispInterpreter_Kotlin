package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SelfEvaluating

class IsNil(): PrimitiveProcedure {
    override val name: String = "nil?"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression.size > 2) throw Exception("Excepted 1 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to $name evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate().first

        return ArrayList<String>().apply {
            if(SelfEvaluating.isNil(parameter1)) add("true")
            else add("false")
        }
    }
}