package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Lambda
import expressions.evaluators.specialForms.SpecialForm

class LambdaPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0] == "lambda" && expression.size == 3
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Lambda(expression, environment)
    }
}