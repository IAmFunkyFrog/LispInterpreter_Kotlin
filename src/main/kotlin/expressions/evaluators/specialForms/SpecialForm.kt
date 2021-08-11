package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Evaluator

abstract class SpecialForm(
    expression: List<String>,
    environment: Environment
) {
    abstract fun evaluate(): List<String>
}