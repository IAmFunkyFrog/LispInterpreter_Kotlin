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
    fun start() {
        val consPredicate = ConsPredicate()
        while(true) {
            val expression = readLine()
            if (expression != null) {
                val evaluatedExpression = Expression(expression, globalEnvironment).evaluate()
                val listToPrint = ArrayList<String>()
                if(consPredicate.check(evaluatedExpression.first, evaluatedExpression.second)) {
                    var currentCons: Pair<List<String>, Environment>? = evaluatedExpression
                    while(currentCons != null) {
                        //TODO технический долг и вообще кастыль
                        val carValue = Expression("(car ${ currentCons.first.joinToString(separator = " ", prefix = "(", postfix = ")") })", currentCons.second).evaluate()
                        val cdrValue = Expression("(cdr ${ currentCons.first.joinToString(separator = " ", prefix = "(", postfix = ")") })", currentCons.second).evaluate()

                        listToPrint.add(evaluateListToString(carValue.first))
                        currentCons = if(consPredicate.check(cdrValue.first, cdrValue.second)) cdrValue
                        else {
                            if(!SelfEvaluating.isNil(cdrValue.first)) listToPrint.add(evaluateListToString(cdrValue.first))
                            null
                        }
                    }
                    println(listToPrint.joinToString(separator = ", ", prefix = "(", postfix = ")"))
                }
                else println(evaluateListToString(evaluatedExpression.first))
            }
            else println("Empty input")
        }
    }

    fun evaluateListToString(list: List<String>): String = when {
        SelfEvaluating.isInt(list) || SelfEvaluating.isFloat(list) || SelfEvaluating.isQuotedString(list) -> list[1]
        SelfEvaluating.isNil(list) || SelfEvaluating.isFalse(list) -> list[0]
        else -> list.joinToString(separator = " ", prefix = "(", postfix = ")")
    }
}