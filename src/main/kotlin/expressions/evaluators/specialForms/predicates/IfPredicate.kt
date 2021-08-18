package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.If
import expressions.evaluators.specialForms.SpecialForm

class IfPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0] == "if" && expression.size >= 3
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return If(expression, environment)
    }
}