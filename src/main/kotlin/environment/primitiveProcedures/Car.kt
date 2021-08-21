package environment.primitiveProcedures

import environment.Environment
import expressions.evaluators.Expression
import expressions.evaluators.specialForms.Cons
import expressions.evaluators.specialForms.predicates.ConsPredicate

class Car(): PrimitiveProcedure {
    override val name: String = "car"

    override fun evaluate(expression: List<String>, environment: Environment): Pair<List<String>, Environment> {
        if(expression.size > 2) throw Exception("Excepted 1 parameters but given ${expression.size - 1}")
        if(expression[0] != name) throw Exception("Bad procedure name given to $name evaluator")

        val parameter1 = Expression(expression[1], environment).evaluate()

        val consPredicate = ConsPredicate()

        return if(!consPredicate.check(parameter1.first, environment)) throw Exception("Not cons form given to $name procedure")
        else {
            val v = parameter1.second.getVariable(Cons.carEnvironmentName)
            val p = parameter1.second.getProcedure(Cons.carEnvironmentName)
            if(v != null) {
                if(p == null) Pair(v, environment)
                else if(environment.deepOfProcedure(Cons.carEnvironmentName) > environment.deepOfVariable(Cons.carEnvironmentName)) p
                else Pair(v, environment)
            }
            else {
                parameter1.second.getProcedure(Cons.carEnvironmentName)
                    ?: throw Exception("Unexpected error while parsing car procedure")
            }
        }
    }

}