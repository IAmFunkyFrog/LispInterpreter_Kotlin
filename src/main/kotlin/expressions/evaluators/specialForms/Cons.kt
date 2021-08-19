package expressions.evaluators.specialForms

import environment.Environment
import expressions.ExpressionParser
import expressions.evaluators.Expression

class Cons(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    private val cdrEnvironmentName = "_cdr"
    private val carEnvironmentName = "_car"

    override fun evaluate(): Pair<List<String>, Environment> {
        val extendedEnvironment = environment.extendEnvironment()
        val car = Expression(expression[1], environment).evaluate()
        val cdr = Expression(expression[2], environment).evaluate()
        Definition.define(carEnvironmentName, car, extendedEnvironment)
        Definition.define(cdrEnvironmentName, cdr, extendedEnvironment)

        return Pair(expression, extendedEnvironment)
    }
}