package expressions

import environment.Environment

class Expression(
    expression: String,
    private val environment: Environment
) {
    private val parsedExpression = ExpressionParser(expression).parse()

    fun evaluate() {
        println(parsedExpression)
    }
}