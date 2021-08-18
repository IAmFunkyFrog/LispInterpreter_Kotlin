package expressions.evaluators.specialForms

import environment.Environment
import expressions.ExpressionParser
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm

class Condition(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        for (i in 1 until expression.size) {
            val parsedExpression = ExpressionParser(expression[i]).parse()
            if (parsedExpression.size != 2) throw Exception("Bad cond declaration at $i condition")
            if (parsedExpression[0] == "else") return Expression(parsedExpression[1], environment).evaluate()
            else {
                val evaluatedCondition = Expression(parsedExpression[0], environment).evaluate()
                if (!SelfEvaluating.isFalse(evaluatedCondition.first)) return Expression(
                    parsedExpression[1],
                    environment
                ).evaluate()
            }
        }
        return Pair(SelfEvaluating.nil, environment)
    }
}