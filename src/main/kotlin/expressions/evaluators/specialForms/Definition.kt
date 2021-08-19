package expressions.evaluators.specialForms

import environment.Environment
import expressions.ExpressionParser
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.predicates.ConsPredicate
import expressions.evaluators.specialForms.predicates.LambdaPredicate

class Definition(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
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
            val evaluatedExpression = Expression(expression[2], environment).evaluate()
            define(expression[1], evaluatedExpression, environment)
        }
    }

    private fun isList(string: String): Boolean {
        return string[0] == '(' && string[string.length - 1] == ')'
    }

    companion object {
        fun define(name: String, evaluatedExpression: Pair<List<String>, Environment>, environment: Environment): Pair<List<String>, Environment> {
            val lambdaPredicate = LambdaPredicate()
            val consPredicate = ConsPredicate()
            return if(lambdaPredicate.check(evaluatedExpression.first, evaluatedExpression.second)) {
                environment.defineProcedure(name, evaluatedExpression.first, evaluatedExpression.second)
                Pair(evaluatedExpression.first, environment)
            }
            else if(consPredicate.check(evaluatedExpression.first, evaluatedExpression.second)) {
                environment.defineProcedure(name, evaluatedExpression.first, evaluatedExpression.second)
                Pair(evaluatedExpression.first, environment)
            }
            else {
                environment.defineVariable(name, evaluatedExpression.first)
                Pair(evaluatedExpression.first, environment)
            }
        }
    }
}