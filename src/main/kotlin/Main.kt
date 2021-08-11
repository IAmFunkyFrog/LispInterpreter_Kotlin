import environment.MapEnvironment
import expressions.evaluators.ListOfExpressions

fun main() {
    val expression = readLine()
    if (expression != null) ListOfExpressions(expression, MapEnvironment()).evaluate()
    else println("Empty input")
}