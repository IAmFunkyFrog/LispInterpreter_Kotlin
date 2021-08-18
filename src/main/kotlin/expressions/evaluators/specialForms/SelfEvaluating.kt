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
                if(expression.size == 1) {
                    list.add("int")
                    list.add(expression[0])
                    Pair(list, environment)
                }
                else Pair(expression, environment)
            }
            isFloat(expression) -> {
                if(expression.size == 1) {
                    list.add("float")
                    list.add(expression[0])
                    Pair(list, environment)
                }
                else Pair(expression, environment)
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
        fun isInt(expression: List<String>) = (expression.size == 2 && expression[0] == "int" && expression[1].toIntOrNull() != null) || (expression.size == 1 && expression[0].toIntOrNull() != null)
        fun isFloat(expression: List<String>) = (expression.size == 2 && expression[0] == "float" && expression[1].toFloatOrNull() != null) || (expression.size == 1 && expression[0].toFloatOrNull() != null)
        fun isQuotedString(expression: List<String>) = (expression.size == 2 && expression[0] == "quote") || (expression.size == 1 && expression[0][0] == '`')
        fun isFalse(expression: List<String>) = expression.size == 1 && expression[0] == "false"
    }
}