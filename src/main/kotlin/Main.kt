import environment.ListEnvironment
import expressions.evaluators.ListOfExpressions

fun main() {
    val expression = readLine()
    if (expression != null) ListOfExpressions(expression, ListEnvironment()).evaluate()
    else println("Empty input")
}