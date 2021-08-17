package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm

class If(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        val conditionResult = Expression(expression[1], environment).evaluate().first

        return if(isFalse(conditionResult)) Expression(expression[3], environment).evaluate()
        else Expression(expression[2], environment).evaluate()
    }

    companion object {
        fun isFalse(expression: List<String>) =expression[0] == "false" && expression.size == 1
    }
}