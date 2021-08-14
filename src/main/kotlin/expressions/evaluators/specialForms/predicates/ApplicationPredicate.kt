package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Application
import expressions.evaluators.specialForms.SpecialForm

class ApplicationPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return environment.getProcedure(expression[0]) != null || environment.getPrimitiveProcedure(expression[0]) != null
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Application(expression, environment)
    }
}