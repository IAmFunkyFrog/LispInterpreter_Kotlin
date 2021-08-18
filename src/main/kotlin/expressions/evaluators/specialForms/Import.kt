package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Expression
import java.io.File

class Import(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        val bufferedReader = File(expression[1]).bufferedReader()
        bufferedReader.forEachLine {
            Expression(it, environment).evaluate()
        }
        return Pair(SelfEvaluating.nil, environment)
    }
}