package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Assignment
import expressions.evaluators.specialForms.SpecialForm

class AssignmentPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0] == "set!" && expression.size == 3
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Assignment(expression, environment)
    }

}