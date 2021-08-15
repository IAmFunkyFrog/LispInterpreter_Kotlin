package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm

class Lambda(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): List<String> {
        if(expression.size != 3) throw Exception("Wrong lambda definition")

        return expression
    }

    companion object {
        fun make(parameters: List<String>, body: String): String {
            val parametersString = parameters.joinToString(separator = " ", prefix = "(", postfix = ")")
            val lambda = emptyList<String>() as MutableList<String>
            lambda.add("lambda")
            lambda.add(parametersString)
            lambda.add(body)
            return lambda.joinToString(separator = " ", prefix = "(", postfix = ")")
        }
    }
}