package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm
import java.lang.Exception

class SelfEvaluating(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        val list = ArrayList<String>()
        return when {
            isInt(expression) -> {
                list.add("int")
                list.add(expression[0])
                Pair(list, environment)
            }
            isFloat(expression) -> {
                list.add("float")
                list.add(expression[0])
                Pair(list, environment)
            }
            isNil(expression) -> {
                Pair(expression, environment)
            }
            isFalse(expression) -> {
                Pair(expression, environment)
            }
            isQuotedString(expression) -> {
                if(expression.size == 1) {
                    list.add("quote")
                    list.add(expression[0].substring(1))
                    Pair(list, environment)
                }
                else Pair(expression, environment)
            }
            else -> throw Exception("Given expression is not self evaluating form")
        }
    }

    companion object {
        val nil = List(1) { "nil" }

        fun isNil(expression: List<String>) = expression.size == 1 && expression[0] == nil[0]
        fun isInt(expression: List<String>) = expression.size == 1 && expression[0].toIntOrNull() != null
        fun isFloat(expression: List<String>) = expression.size == 1 && expression[0].toFloatOrNull() != null
        fun isQuotedString(expression: List<String>) = (expression.size == 2 && expression[0] == "quote") || (expression.size == 1 && expression[0][0] == '`')
        fun isFalse(expression: List<String>) = expression.size == 1 && expression[0] == "false"
    }
}