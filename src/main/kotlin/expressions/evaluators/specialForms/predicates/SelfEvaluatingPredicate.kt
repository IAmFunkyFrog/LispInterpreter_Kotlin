package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.SelfEvaluating
import expressions.evaluators.specialForms.SpecialForm

class SelfEvaluatingPredicate : Predicate {
    //TODO: добавить поддержку строк
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return SelfEvaluating.isInt(expression) || SelfEvaluating.isFloat(expression) || SelfEvaluating.isNil(expression)
                || SelfEvaluating.isFalse(expression) || SelfEvaluating.isQuotedString(expression)
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return SelfEvaluating(expression, environment)
    }
}