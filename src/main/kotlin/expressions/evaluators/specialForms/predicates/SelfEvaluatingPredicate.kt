package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.SelfEvaluating
import expressions.evaluators.specialForms.SpecialForm

class SelfEvaluatingPredicate: Predicate {
    //TODO: добавить поддержку строк
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0].toIntOrNull() != null || expression[0].toFloatOrNull() != null
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return SelfEvaluating(expression, environment)
    }
}