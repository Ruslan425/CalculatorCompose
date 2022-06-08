package ru.romazanov.calculatorcompose


fun main() {
   // val calculator = Calculator()
   // calculator.loop()

    var text = "55"

    fun addMinusToText() {
        if (!text.matches("-\\d+.*\\d*".toRegex())) {
            text = "-${text}"
        } else {
            text = text.substringAfter("-")
        }
    }
    addMinusToText()
    println(text)
}



