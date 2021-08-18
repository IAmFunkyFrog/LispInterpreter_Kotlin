package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Expression
import kotlin.collections.List

class LispList(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        var evalConsForm = ""
        for(i in 1 until expression.size) {
            evalConsForm = if(i == 1) "(cons ${expression[expression.size - i]} nil)"
            else "(cons ${expression[expression.size - i]} $evalConsForm)"
        }
        return Expression(evalConsForm, environment).evaluate()
    }
}