package expressions.evaluators.specialForms

import environment.Environment
import expressions.ExpressionParser
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.SpecialForm
import expressions.evaluators.specialForms.predicates.LambdaPredicate

class Definition(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): List<String> {
        //TODO добавить определение процедур
        return if(isList(expression[1])) {
            val parsedParameters = ExpressionParser(expression[1]).parse()
            val parameters = parsedParameters.subList(1, parsedParameters.size)
            val lambda = makeLambda(parameters, expression[2])
            Definition(ArrayList<String>().apply {
                add("define")
                add(parsedParameters[0])
                add(lambda)
            }, environment).evaluate()
        } else {
            val lambdaPredicate = LambdaPredicate()
            val parsedValue = ExpressionParser(expression[2]).parse()
            if(lambdaPredicate.check(parsedValue, environment)) {
                val value = lambdaPredicate.getSpecialForm(parsedValue, environment).evaluate()
                environment.setProcedure(expression[1], value, environment.extendEnvironment())
                value
            }
            else {
                val value = Expression(expression[2], environment).evaluate()
                environment.setVariable(expression[1], value)
                value
            }
        }
    }

    private fun isList(string: String): Boolean {
        return string[0] == '(' && string[string.length - 1] == ')'
    }

    private fun makeLambda(parameters: List<String>, body: String): String {
        val parametersString = parameters.joinToString(separator = " ", prefix = "(", postfix = ")")
        val lambda = emptyList<String>() as MutableList<String>
        lambda.add("lambda")
        lambda.add(parametersString)
        lambda.add(body)
        return lambda.joinToString(separator = " ", prefix = "(", postfix = ")")
    }
}