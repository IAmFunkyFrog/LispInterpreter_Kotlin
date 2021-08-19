package expressions.evaluators.specialForms

import environment.Environment

class Variable(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        val variable = environment.getVariable(expression[0])
        return if (variable != null) Pair(variable, environment)
        else environment.getProcedure(expression[0]) ?: throw Exception("Variable is undefined in environment")
    }
}