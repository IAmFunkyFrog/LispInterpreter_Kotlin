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
            expression[0].toIntOrNull() != null -> {
                list.add("int")
                list.add(expression[0])
                Pair(list, environment)
            }
            expression[0].toFloatOrNull() != null -> {
                list.add("float")
                list.add(expression[0])
                Pair(list, environment)
            }
            else -> throw Exception("Given expression is not self evaluating form")
        }
    }
}