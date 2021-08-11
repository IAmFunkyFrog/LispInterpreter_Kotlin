package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm

class SelfEvaluating(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): List<String> {
        TODO("Not yet implemented")
    }
}