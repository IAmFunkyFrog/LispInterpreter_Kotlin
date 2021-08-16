package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression

class Plus(): PrimitiveProcedure {
    override val name: String = "+"

    override fun evaluate(expression: List<String>, environment: Environment): List<String> {
        if(expression.size > 3) throw Exception("Excepted 2 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to plus evaluator")

        println(expression)

        val parameter1 = Expression(expression[1], environment).evaluate().first
        val parameter2 = Expression(expression[2], environment).evaluate().first

        when {
            parameter1[0] == "int" && parameter2[0] == "int" -> return ArrayList<String>().apply {
                add("int")
                add((parameter1[1].toInt() + parameter2[1].toInt()).toString())
            }
            parameter1[0] == "int" && parameter2[0] == "float" -> return ArrayList<String>().apply {
                add("float")
                add((parameter1[1].toInt() + parameter2[1].toFloat()).toString())
            }
            parameter1[0] == "float" && parameter2[0] == "int" -> return ArrayList<String>().apply {
                add("float")
                add((parameter1[1].toFloat() + parameter2[1].toInt()).toString())
            }
            parameter1[0] == "float" && parameter2[0] == "float" -> return ArrayList<String>().apply {
                add("float")
                add((parameter1[1].toFloat() + parameter2[1].toFloat()).toString())
            }
            else -> throw Exception("Unexpected error while evaluating plus procedure")
        }
    }
}