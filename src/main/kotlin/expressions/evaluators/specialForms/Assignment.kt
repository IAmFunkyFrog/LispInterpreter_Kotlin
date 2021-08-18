package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm
import expressions.evaluators.specialForms.predicates.LambdaPredicate
import kotlin.math.exp

class Assignment(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        val lambdaPredicate = LambdaPredicate()
        val evaluatedValue = Expression(expression[2], environment).evaluate()

        if(lambdaPredicate.check(evaluatedValue.first, environment)) environment.setProcedure(expression[1], evaluatedValue.first, evaluatedValue.second)
        else environment.setVariable(expression[1], evaluatedValue.first)

        return Pair(evaluatedValue.first, environment)
    }
}