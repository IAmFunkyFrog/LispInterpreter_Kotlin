import environment.MapEnvironment
import expressions.evaluators.Expression
import repl.REPL

fun main() {
    REPL(MapEnvironment()).start()
}