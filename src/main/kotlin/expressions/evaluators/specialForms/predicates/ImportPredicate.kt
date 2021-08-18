package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Import
import expressions.evaluators.specialForms.SpecialForm

class ImportPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return expression[0] == "import"
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Import(expression, environment)
    }
}