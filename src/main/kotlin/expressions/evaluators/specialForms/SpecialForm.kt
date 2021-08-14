package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.Evaluator

abstract class SpecialForm(
    val expression: List<String>,
    val environment: Environment
) {
    abstract fun evaluate(): List<String>
}