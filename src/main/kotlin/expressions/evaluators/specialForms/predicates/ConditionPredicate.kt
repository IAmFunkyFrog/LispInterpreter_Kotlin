package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Condition
import expressions.evaluators.specialForms.SpecialForm

class ConditionPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0] == "cond"
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Condition(expression, environment)
    }
}