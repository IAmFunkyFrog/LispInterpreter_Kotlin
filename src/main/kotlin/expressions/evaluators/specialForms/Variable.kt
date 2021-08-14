package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm

class Variable(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): List<String> {
        val variable = environment.getVariable(expression[0])
        if(variable != null) return variable
        else throw Exception("Variable is undefined in environment")
    }
}