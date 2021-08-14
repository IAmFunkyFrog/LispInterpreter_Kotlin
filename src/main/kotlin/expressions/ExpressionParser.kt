package expressions

import java.lang.StringBuilder

class ExpressionParser(
    private val expression: String
) {
    fun parse(): List<String> {
        val list = ArrayList<String>()

        var bracket = 0
        val builder = StringBuilder()
        for (symbol in expression) {
            when (symbol) {
                '(' -> {
                    bracket++
                    if (bracket > 1) builder.append(symbol)
                }
                ')' -> {
                    bracket--
                    if (bracket >= 1) builder.append(symbol)
                    if (bracket == 0) break
                }
                ' ' -> {
                    list.add(builder.toString())
                    builder.clear()
                }
                else -> {
                    builder.append(symbol)
                }
            }
        }
        list.add(builder.toString())

        return list
    }
}