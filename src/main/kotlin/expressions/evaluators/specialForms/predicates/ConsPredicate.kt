package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Cons
import expressions.evaluators.specialForms.SpecialForm

class ConsPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0] == "cons" && expression.size == 3
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Cons(expression, environment)
    }
}