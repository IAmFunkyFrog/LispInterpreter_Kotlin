package expressions.evaluators.specialForms

import environment.Environment
import expressions.ExpressionParser
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.predicates.ApplicationPredicate
import expressions.evaluators.specialForms.predicates.LambdaPredicate

class Definition(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        //TODO добавить определение процедур
        return if(isList(expression[1])) {
            val parsedParameters = ExpressionParser(expression[1]).parse()
            val parameters = parsedParameters.subList(1, parsedParameters.size)
            val lambda = Lambda.make(parameters, expression[2])
            Definition(ArrayList<String>().apply {
                add("define")
                add(parsedParameters[0])
                add(lambda)
            }, environment).evaluate()
        } else {
            val lambdaPredicate = LambdaPredicate()
            val parsedExpression = Expression(expression[2], environment).evaluate()
            if(lambdaPredicate.check(parsedExpression.first, parsedExpression.second)) {
                val value = lambdaPredicate.getSpecialForm(parsedExpression.first, parsedExpression.second).evaluate()
                environment.setProcedure(expression[1], value.first, parsedExpression.second)
                Pair(value.first, environment)
            }
            else {
                val value = Expression(expression[2], environment).evaluate()
                environment.setVariable(expression[1], value.first)
                Pair(value.first, environment)
            }
        }
    }

    private fun isList(string: String): Boolean {
        return string[0] == '(' && string[string.length - 1] == ')'
    }
}