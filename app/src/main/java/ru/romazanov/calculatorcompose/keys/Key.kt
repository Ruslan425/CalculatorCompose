package ru.romazanov.calculatorcompose.keys

import ru.romazanov.calculatorcompose.MainViewModel


sealed class Key(val name: String, val onClick: (vm: MainViewModel) -> Unit) {
    object One : Key(name = "1", onClick = { it.text.value += "1" })
    object Two : Key(name = "2", onClick = { it.text.value += "2" })
    object Three : Key(name = "3", onClick = { it.text.value += "3" })
    object Four : Key(name = "4", onClick = { it.text.value += "4" })
    object Five : Key(name = "5", onClick = { it.text.value += "5" })
    object Six : Key(name = "6", onClick = { it.text.value += "6" })
    object Seven : Key(name = "7", onClick = { it.text.value += "7" })
    object Eight : Key(name = "8", onClick = { it.text.value += "8" })
    object Nine : Key(name = "9", onClick = { it.text.value += "9" })
    object Answer : Key(
        name = "=",
        onClick = {
            it.addToList()
            it.answer.value = it.getSum(it.list.toList().joinToString(" "))
        })

    object Delete : Key(name = "Del", onClick = { it.delete() })
    object Plus : Key(name = "+", onClick = {
        it.addToList()
        it.text.value += " + "
        it.addToList()
    })

    object Minus : Key(name = "-", onClick = {
        it.addToList()
        it.text.value += " - "
        it.addToList()
    })

    object Split : Key(name = "/", onClick = {
        it.addToList()
        it.text.value += " / "
        it.addToList()
    })

    object Multiply : Key(name = "*", onClick = {
        it.addToList()
        it.text.value += " * "
        it.addToList()
    })

    object Open : Key(name = "(", onClick = {
        it.addToList()
        it.text.value += " ( "
        it.addToList()
    })

    object Close : Key(name = ")", onClick = {
        it.addToList()
        it.text.value += " ) "
        it.addToList()
    })

    object Zero : Key(name = "0", onClick = { it.text.value += " 0 " })
    object Zero2 : Key(name = "+/-", onClick = { it.addMinusToText() })
    object A : Key(name = ".", onClick = { it.text.value += "." })

}