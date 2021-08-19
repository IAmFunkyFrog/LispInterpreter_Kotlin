package expressions.evaluators

import environment.Environment
import expressions.ExpressionParser
import expressions.evaluators.specialForms.predicates.*

class Expression(
    expression: String,
    environment: Environment
) : Evaluator(expression, environment) {
    private val parsedExpression = ExpressionParser(expression).parse()

    private val predicates = ArrayList<Predicate>().apply {
        add(VariablePredicate())
        add(ApplicationPredicate())
        add(DefinitionPredicate())
        add(IfPredicate())
        add(LambdaPredicate())
        add(SelfEvaluatingPredicate())
        add(BeginPredicate())
        add(ConditionPredicate())
        add(ImportPredicate())
        add(LispListPredicate())
        add(AssignmentPredicate())
        add(ConsPredicate())
    }

    override fun evaluate(): Pair<List<String>, Environment> {
        for(predicate in predicates) {
            if(predicate.check(parsedExpression, environment)) return predicate.getSpecialForm(parsedExpression, environment).evaluate()
        }
        throw Exception("Cant parse this type of list $parsedExpression")
    }
}