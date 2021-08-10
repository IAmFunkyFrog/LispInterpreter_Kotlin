import environment.ListEnvironment
import expressions.Expression

fun main() {
    val expression = readLine()
    if (expression != null) Expression(expression, ListEnvironment()).evaluate()
    else println("Empty input")
}