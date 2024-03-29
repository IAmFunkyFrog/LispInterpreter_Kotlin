package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Application
import expressions.evaluators.specialForms.SpecialForm

class ApplicationPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return ((environment.getProcedure(expression[0]) != null || environment.getPrimitiveProcedure(expression[0]) != null) && environment.deepOfProcedure(expression[0]) > environment.deepOfVariable(expression[0])) || isList(expression[0])
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Application(expression, environment)
    }

    private fun isList(string: String): Boolean {
        return string[0] == '(' && string[string.length - 1] == ')'
    }
}