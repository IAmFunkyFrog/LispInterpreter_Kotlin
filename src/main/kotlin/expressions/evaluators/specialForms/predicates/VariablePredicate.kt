package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm
import expressions.evaluators.specialForms.Variable

class VariablePredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return environment.getVariable(expression[0]) != null
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Variable(expression, environment)
    }
}