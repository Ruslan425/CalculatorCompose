package ru.romazanov.calculatorcompose.keys

import ru.romazanov.calculatorcompose.MainViewModel


sealed class Key(val name: String, val onClick: (vm: MainViewModel) -> Unit) {
    object One : Key(name = "1", onClick = { it.onClickNumberKey("1") })
    object Two : Key(name = "2", onClick = { it.onClickNumberKey("2") })
    object Three : Key(name = "3", onClick = { it.onClickNumberKey("3") })
    object Four : Key(name = "4", onClick = { it.onClickNumberKey("4") })
    object Five : Key(name = "5", onClick = { it.onClickNumberKey("5") })
    object Six : Key(name = "6", onClick = { it.onClickNumberKey("6") })
    object Seven : Key(name = "7", onClick = { it.onClickNumberKey("7") })
    object Eight : Key(name = "8", onClick = { it.onClickNumberKey("8") })
    object Nine : Key(name = "9", onClick = { it.onClickNumberKey("9") })
    object Answer : Key(name = "=", onClick = {
      it.onClickAnswer("=")
        })

    object Delete : Key(name = "Del", onClick = { it.delete() })
    object Plus : Key(name = "+", onClick = {
        it.onClickOperatorKey(" + ")
    })

    object Minus : Key(name = "-", onClick = {
        it.onClickOperatorKey(" - ")
    })

    object Split : Key(name = "/", onClick = {
        it.onClickOperatorKey(" / ")
    })

    object Multiply : Key(name = "*", onClick = {
        it.onClickOperatorKey(" * ")
    })

    object Open : Key(name = "(", onClick = {
        it.onClickOperatorKey(" ( ")
    })

    object Close : Key(name = ")", onClick = {
        it.onClickOperatorKey(" ) ")
    })

    object Zero : Key(name = "0", onClick = { it.onClickNumberKey("0")})
    object Zero2 : Key(name = "+/-", onClick = { it.addMinusToText() })
    object A : Key(name = ".", onClick = { it.onClickNumberKey(".") })
}

