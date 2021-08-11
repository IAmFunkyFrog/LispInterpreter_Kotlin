package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm

//TODO: страница 341, там написано как интерпретировать применение процедуры
class Application(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): List<String> {
        TODO("Not yet implemented")
    }
}