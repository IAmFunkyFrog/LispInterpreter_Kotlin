package expressions.evaluators

import environment.Environment
import expressions.ExpressionParser

abstract class Evaluator(
    expression: String,
    val environment: Environment
) {
    private val parsedExpression = ExpressionParser(expression).parse()

    abstract fun evaluate(): List<String>
}