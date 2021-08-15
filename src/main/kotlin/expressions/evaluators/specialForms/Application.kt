package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm

//TODO: страница 341, там написано как интерпретировать применение процедуры
class Application(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): List<String> {
        val lambda = environment.getProcedure(expression[0])
        if (lambda == null) {
            val primitiveProcedure = environment.getPrimitiveProcedure(expression[0])
                ?: throw Exception("Given expression is not application")

            return primitiveProcedure.evaluate(expression, environment)
        } else {
            val evaluatedParameters = expression.subList(1, expression.size).map {
                Expression(it, environment).evaluate()
            }

            val lambdaEnvironment = lambda.second
            val lambdaBody = lambda.first

            val lambdaParameters = lambdaBody[1].trim('(').trimEnd(')').split(' ')

            if (evaluatedParameters.size > lambdaParameters.size) throw Exception("In application excepted ${lambdaParameters.size} but given ${evaluatedParameters.size}")
            else {
                for (i in evaluatedParameters.indices) {
                    Definition(ArrayList<String>().apply {
                        add("define")
                        add(lambdaParameters[i])
                        add(evaluatedParameters[i].joinToString(separator = " ", prefix = "(", postfix = ")"))
                    }, lambdaEnvironment).evaluate()
                }
                return if (evaluatedParameters.size < lambdaParameters.size) Lambda.make(
                    lambdaParameters.subList(
                        evaluatedParameters.size,
                        lambdaParameters.size
                    ), lambdaBody[2]
                ).trim('(').trimEnd(')').split(" ")
                else Expression(lambdaBody[2], lambdaEnvironment).evaluate()
            }
        }
    }
}