package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SelfEvaluating

class Error(): PrimitiveProcedure {
    override val name: String = "error"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression.size > 2) throw Exception("Excepted 1 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to error evaluator")

        throw Exception(expression[1])
    }
}