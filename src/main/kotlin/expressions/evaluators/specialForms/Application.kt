package expressions.evaluators.specialForms

import environment.Environment
import expressions.ExpressionParser
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm

//TODO: страница 341, там написано как интерпретировать применение процедуры
class Application(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        val lambda = environment.getProcedure(expression[0])
        if (lambda == null) {
            val primitiveProcedure = environment.getPrimitiveProcedure(expression[0])
                ?: throw Exception("Given expression is not application")

            return Pair(primitiveProcedure.evaluate(expression, environment), environment)
        } else {
            val givenParameters = expression.subList(1, expression.size)

            val lambdaEnvironment = lambda.second
            val lambdaBody = lambda.first

            val lambdaParameters = lambdaBody[1].trim('(').trimEnd(')').split(' ')

            if (givenParameters.size > lambdaParameters.size) throw Exception("In application excepted ${lambdaParameters.size} parameter but given ${givenParameters.size}")
            else {
                var extendedEnvironment = lambdaEnvironment
                for (i in givenParameters.indices) {
                    extendedEnvironment = extendedEnvironment.extendEnvironment()
                    //TODO решить проблему с возвратом лямбд из define
                    Definition(ArrayList<String>().apply {
                        add("define")
                        add(lambdaParameters[i])
                        add(givenParameters[i])
                    }, extendedEnvironment).evaluate()
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
}