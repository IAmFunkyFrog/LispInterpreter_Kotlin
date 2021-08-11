package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm

interface Predicate {
    fun check(expression: List<String>, environment: Environment): Boolean
    fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm
}