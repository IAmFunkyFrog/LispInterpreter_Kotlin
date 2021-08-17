package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm

class Begin(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        for(i in 1 until expression.size - 1) Expression(expression[i], environment).evaluate()
        return Expression(expression[expression.size - 1], environment).evaluate()
    }
}