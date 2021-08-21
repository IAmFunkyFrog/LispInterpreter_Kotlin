package repl

import environment.Environment
import environment.primitiveProcedures.Car
import environment.primitiveProcedures.Cdr
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.Cons
import expressions.evaluators.specialForms.SelfEvaluating
import expressions.evaluators.specialForms.predicates.ConsPredicate

class REPL(
    val globalEnvironment: Environment
) {
    private val consPredicate = ConsPredicate()

    fun start() {
        while(true) {
            val expression = readLine()
            if (expression == "exit") {
                println("Exiting...")
                break
            }
            if (expression != null) {
                val evaluatedExpression = Expression(expression, globalEnvironment).evaluate()
                println(evaluateExpressionToString(evaluatedExpression))
            }
            else println("Empty input")
        }
    }

    fun evaluateExpressionToString(expression: Pair<List<String>, Environment>): String = when {
        SelfEvaluating.isInt(expression.first) || SelfEvaluating.isFloat(expression.first) || SelfEvaluating.isQuotedString(expression.first) -> expression.first[1]
        SelfEvaluating.isNil(expression.first) || SelfEvaluating.isFalse(expression.first) -> expression.first[0]
        consPredicate.check(expression.first, expression.second) -> {
            var currentCons: Pair<List<String>, Environment>? = expression
            val listToPrint = ArrayList<String>()
            while(currentCons != null) {
                //TODO технический долг и вообще кастыль
                val carValue = Expression("(car ${ currentCons.first.joinToString(separator = " ", prefix = "(", postfix = ")") })", currentCons.second).evaluate()
                val cdrValue = Expression("(cdr ${ currentCons.first.joinToString(separator = " ", prefix = "(", postfix = ")") })", currentCons.second).evaluate()

                listToPrint.add(evaluateExpressionToString(carValue))
                currentCons = if(consPredicate.check(cdrValue.first, cdrValue.second)) cdrValue
                else {
                    if(!SelfEvaluating.isNil(cdrValue.first)) listToPrint.add(evaluateExpressionToString(cdrValue))
                    null
                }
            }
            listToPrint.joinToString(separator = " ", prefix = "(", postfix = ")")
        }
        else -> expression.first.joinToString(separator = " ", prefix = "(", postfix = ")")
    }
}