package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm
import expressions.evaluators.specialForms.Variable

class VariablePredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return checkIfVariable(expression, environment) || checkIfCons(expression, environment)
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Variable(expression, environment)
    }

    private fun checkIfVariable(expression: List<String>, environment: Environment) = environment.getVariable(expression[0]) != null && environment.deepOfVariable(expression[0]) > environment.deepOfProcedure(expression[0])
    private fun checkIfCons(expression: List<String>, environment: Environment): Boolean {
        val consPredicate = ConsPredicate()
        val c = environment.getProcedure(expression[0])
        return c != null && consPredicate.check(c.first, environment) && environment.deepOfProcedure(expression[0]) > environment.deepOfVariable(expression[0])
    }
}