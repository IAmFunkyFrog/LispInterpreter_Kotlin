package expressions.evaluators.specialForms.predicates

import environment.Environment
import expressions.evaluators.specialForms.Application
import expressions.evaluators.specialForms.SpecialForm

class ApplicationPredicate: Predicate {
    override fun check(expression: List<String>, environment: Environment): Boolean {
        return checkIfProcedure(expression, environment) || checkIfPrimitiveProcedure(expression, environment)
    }

    override fun getSpecialForm(expression: List<String>, environment: Environment): SpecialForm {
        return Application(expression, environment)
    }

    private fun isList(string: String): Boolean {
        return string[0] == '(' && string[string.length - 1] == ')'
    }

    private fun checkIfPrimitiveProcedure(expression: List<String>, environment: Environment) = ((environment.getPrimitiveProcedure(expression[0]) != null) && environment.deepOfProcedure(expression[0]) > environment.deepOfVariable(expression[0]))
    private fun checkIfProcedure(expression: List<String>, environment: Environment): Boolean {
        val p = environment.getProcedure(expression[0])
        val lambdaPredicate = LambdaPredicate()
        return ((p != null) && lambdaPredicate.check(p.first, environment) && environment.deepOfProcedure(expression[0]) > environment.deepOfVariable(expression[0])) || isList(expression[0])
    }
}