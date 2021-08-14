import environment.MapEnvironment
import expressions.evaluators.Expression

fun main() {
    val expression = readLine()
    if (expression != null) Expression(expression, MapEnvironment()).evaluate()
    else println("Empty input")
}