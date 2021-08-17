package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm

class Lambda(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        if(expression.size != 3) throw Exception("Wrong lambda definition")

        return Pair(expression, environment)
    }

    companion object {
        fun make(parameters: List<String>, body: String): String {
            val parametersString = parameters.joinToString(separator = " ", prefix = "(", postfix = ")")
            val lambda = ArrayList<String>().apply {
                add("lambda")
                add(parametersString)
                add(body)
            }
            return lambda.joinToString(separator = " ", prefix = "(", postfix = ")")
        }
    }
}