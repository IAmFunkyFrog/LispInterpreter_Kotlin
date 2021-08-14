package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression

class Car(): PrimitiveProcedure {
    override val name: String = "car"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression[0] == name) throw Exception("Not car form given to car evaluator")
        if(expression.size != 2) throw Exception("Car excepted 1 parameters, but given " + (expression.size - 1))

        val value = Expression(expression[1], environment).evaluate()
        if(value[0] != "cons" || expression.size != 3) throw Exception("Not cons`ed value given to car")

        return ArrayList<String>().apply {
            add(expression[1])
        }
    }
}