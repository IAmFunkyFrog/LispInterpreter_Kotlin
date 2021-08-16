package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm

class Variable(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): Pair<List<String>, Environment> {
        val variable = environment.getVariable(expression[0])
        if(variable != null) return Pair(variable, environment)
        else throw Exception("Variable is undefined in environment")
    }
}