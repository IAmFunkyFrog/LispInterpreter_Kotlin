package expressions.evaluators.specialForms

import environment.Environment
import expressions.ExpressionParser
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm
import expressions.evaluators.specialForms.predicates.LambdaPredicate

class Application(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        val lambda = environment.getProcedure(expression[0])
        if (lambda == null) {
            val primitiveProcedure = environment.getPrimitiveProcedure(expression[0])

            return if(primitiveProcedure != null) Pair(primitiveProcedure.evaluate(expression, environment), environment)
            else {
                val lambdaPredicate = LambdaPredicate()
                val parsedFirstElement = Expression(expression[0], environment).evaluate()

                if(lambdaPredicate.check(parsedFirstElement.first, environment)) {
                    val givenParameters = expression.subList(1, expression.size)
                    applyLambda(parsedFirstElement.first, parsedFirstElement.second, givenParameters)
                } else throw Exception("Given expression is not application")
            }
        } else {
            val givenParameters = expression.subList(1, expression.size)

            val lambdaEnvironment = lambda.second
            val lambdaBody = lambda.first

            return applyLambda(lambdaBody, lambdaEnvironment, givenParameters)
        }
    }

    private fun applyLambda(lambdaBody: List<String>, lambdaEnvironment: Environment, givenParameters: List<String>): Pair<List<String>, Environment> {
        val lambdaParameters = lambdaBody[1].trim('(').trimEnd(')').split(' ')

        if (givenParameters.size > lambdaParameters.size) throw Exception("In application excepted ${lambdaParameters.size} parameter but given ${givenParameters.size}")
        else {
            val extendedEnvironment = lambdaEnvironment.extendEnvironment()
            for (i in givenParameters.indices) {
                val evaluatedParameter = Expression(givenParameters[i], environment).evaluate()
                Definition.define(lambdaParameters[i], evaluatedParameter, extendedEnvironment)
            }
            return if (givenParameters.size < lambdaParameters.size) Pair(ExpressionParser(Lambda.make(
                lambdaParameters.subList(
                    givenParameters.size,
                    lambdaParameters.size
                ), lambdaBody[2]
            )).parse(), extendedEnvironment)
            else Expression(lambdaBody[2], extendedEnvironment).evaluate()
        }
    }
}