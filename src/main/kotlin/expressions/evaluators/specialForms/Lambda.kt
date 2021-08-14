package expressions.evaluators.specialForms

import environment.Environment
import expressions.evaluators.specialForms.SpecialForm

class Lambda(
    expression: List<String>,
    environment: Environment
) : SpecialForm(expression, environment) {
    override fun evaluate(): List<String> {
        if(expression.size != 3) throw Exception("Wrong lambda definition")

        val list = ArrayList<String>()

        list.add("procedure")
        list.add(expression[1])
        list.add(expression[2])

        return list
    }
}