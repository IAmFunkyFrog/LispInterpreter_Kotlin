package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Definition
import expressions.evaluators.specialForms.SpecialForm

class DefinitionPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0] == "define" && expression.size == 3
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Definition(expression, environment)
    }
}