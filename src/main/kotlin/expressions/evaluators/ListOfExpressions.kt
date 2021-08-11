package expressions.evaluators

import environment.Environment
import expressions.ExpressionParser

class ListOfExpressions(
    expression: String,
    environment: Environment
) : Evaluator(expression, environment) {
    private val parsedExpression = ExpressionParser(expression).parse()

    override fun evaluate(): List<String> {
        return parsedExpression
    }
}