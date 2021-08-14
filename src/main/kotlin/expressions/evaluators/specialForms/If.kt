package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm

class If(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): List<String> {
        val conditionResult = Expression(expression[1], environment).evaluate()

        return if(conditionResult[0] == "false" && conditionResult.size == 1) Expression(expression[2], environment).evaluate()
        else Expression(expression[3], environment).evaluate()
    }
}