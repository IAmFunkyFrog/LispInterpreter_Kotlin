package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.LispList
import expressions.evaluators.specialForms.SpecialForm

class LispListPredicate : Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression.size > 1 && expression[0] == "list"
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return LispList(expression, environment)
    }
}