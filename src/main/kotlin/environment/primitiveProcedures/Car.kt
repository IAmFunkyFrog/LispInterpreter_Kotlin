package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression

class Car(): PrimitiveProcedure {
    override val name: String = "car"

    override fun evaluate(expression: List<String>, environment: Environment): Pair<List<String>, Environment> {
        if(expression.size > 2) throw Exception("Excepted 1 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to $name evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate()


    }
}