import environment.MapEnvironment
import expressions.evaluators.Expression

fun main() {
    val environment = MapEnvironment()
    while(true) {
        val expression = readLine()
        if (expression != null) {
            println(Expression(expression, environment).evaluate().first)
            println(environment.debugLog())
        }
        else println("Empty input")
    }
}