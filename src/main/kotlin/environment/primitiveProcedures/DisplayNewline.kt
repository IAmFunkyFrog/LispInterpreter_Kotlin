package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SelfEvaluating

class DisplayNewline(): PrimitiveProcedure {
    override val name: String = "display"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression.size > 2) throw Exception("Excepted 1 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to $name evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate().first

        return when {
            SelfEvaluating.isFloat(parameter1) || SelfEvaluating.isInt(parameter1) ||
                    SelfEvaluating.isQuotedString(parameter1) -> {
                println(parameter1[1])
                parameter1
            }
            SelfEvaluating.isNil(parameter1) || SelfEvaluating.isFalse(parameter1) -> {
                println(parameter1[0])
                parameter1
            }
            else -> {
                println(expression[1])
                return List(1) { expression[1] }
            }
        }
    }

}