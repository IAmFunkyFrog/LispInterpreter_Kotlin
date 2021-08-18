package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression

class NumberEqual(): PrimitiveProcedure {
    override val name: String = "="

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression.size > 3) throw Exception("Excepted 2 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to multiplication evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate().first
        val parameter2 = Expression(expression[2], environment).evaluate().first

        when {
            parameter1[0] == "int" && parameter2[0] == "int" -> return ArrayList<String>().apply {
                if(parameter1[1].toInt() == parameter2[1].toInt()) add("true")
                else add("false")
            }
            parameter1[0] == "int" && parameter2[0] == "float" -> return ArrayList<String>().apply {
                if(parameter1[1].toInt().toFloat() == parameter2[1].toFloat()) add("true")
                else add("false")
            }
            parameter1[0] == "float" && parameter2[0] == "int" -> return ArrayList<String>().apply {
                if(parameter1[1].toFloat() == parameter2[1].toInt().toFloat()) add("true")
                else add("false")
            }
            parameter1[0] == "float" && parameter2[0] == "float" -> return ArrayList<String>().apply {
                if(parameter1[1].toFloat() == parameter2[1].toFloat()) add("true")
                else add("false")
            }
            else -> throw Exception("Unexpected error while evaluating multiplication procedure")
        }
    }
}