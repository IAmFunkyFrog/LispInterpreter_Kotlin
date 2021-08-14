package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Begin
import expressions.evaluators.specialForms.SpecialForm

class BeginPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0] == "begin"
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Begin(expression, environment)
    }
}