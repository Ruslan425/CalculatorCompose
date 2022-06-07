package ru.romazanov.calculatorcompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.math.BigInteger

class MainViewModel : ViewModel() {

    var text = mutableStateOf("")

    fun getSum(text: String): String {
        var str = text
        str = strToNewStr(str)
        return sum(newList(str)).toString()
    }

    /*
       Функция разделяет строку на отдельные эелементы через пробел
        */
    private fun strToNewStr(str: String): String {

        return str.replace(Regex("\\s+"), "")
            .replace(Regex("--"), "+")
            .replace(Regex("\\+-"), " - ")
            .replace(Regex("\\++"), " + ")
            .replace(Regex("\\*"), " * ")
            .replace(Regex("/"), " / ")
            .replace(Regex("\\("), " ( ")
            .replace(Regex("\\)"), " ) ")
            .replace(Regex("-"), " - ")
            .replace(Regex("\\s+"), " ")
    }


    /*
       Функция работаят только со сторокой из чисел и операций записаных через пробел
       вид строки должен быть (1 + 2) + 3
        */
    private fun newList(str: String): MutableList<String> {

        /*
          Фунция для работы с очередью
           */
        fun popPush(queue: MutableList<String>, number: MutableList<String>) {

            if (queue.isEmpty()) return
            if (queue.last() == "(") {
                queue.removeAt(queue.lastIndex)
                return
            }
            number.add(queue.last())
            queue.removeAt(queue.lastIndex)
            popPush(queue, number)
        }

        val list = str.split(" ").toMutableList()
        val number = mutableListOf<String>() // ОПН
        val queue = mutableListOf<String>()  // очередь
        val one = Regex("[*/]")   // умножение и деление
        val two = Regex("[+-]")  // сложение и вычитание

        for (i in list) {
            when {
                i == " " -> continue
                i == "(" -> queue.add(i)
                i == ")" -> popPush(queue, number)
                i.matches("-*\\d+".toRegex()) -> number.add(i)
                else -> {
                    when {
                        i.matches(one) -> {
                            if (queue.isEmpty()) {
                                queue.add(i)
                                continue
                            }
                            if (queue.isNotEmpty()) {
                                if (queue.last() == "(") {
                                    queue.add(i)
                                    continue
                                }
                            }
                            if (queue.last().matches(two))
                                queue.add(i)
                            else {
                                number.add(queue.last())
                                queue.remove(queue.last())
                                queue.add(i)
                            }
                        }
                        i.matches(two) -> {
                            if (queue.isNotEmpty()) {
                                if (queue.last() == "(") {
                                    queue.add(i)
                                    continue
                                }
                                number.add(queue.last())
                                queue.remove(queue.last())
                                queue.add(i)
                            } else {
                                queue.add(i)
                            }

                        }
                    }
                }
            }
        }
        number += queue.reversed()
        return number
    }

    /*
     Функция принимает список с цифрами и операциями в ОПН
     возвращает сумму.
     */
    private fun sum(list: MutableList<String>): BigInteger {

        val queue = mutableListOf<BigInteger>()

        for (i in list) {
            when {
                i.matches(Regex("-*\\d+")) -> queue.add(i.toBigInteger())
                i == "+" -> {
                    queue[queue.lastIndex - 1] += queue[queue.lastIndex]
                    queue.removeAt(queue.lastIndex)
                }
                i == "-" -> {
                    queue[queue.lastIndex - 1] -= queue[queue.lastIndex]
                    queue.removeAt(queue.lastIndex)
                }
                i == "*" -> {
                    queue[queue.lastIndex - 1] *= queue[queue.lastIndex]
                    queue.removeAt(queue.lastIndex)
                }
                i == "/" -> {
                    queue[queue.lastIndex - 1] /= queue[queue.lastIndex]
                    queue.removeAt(queue.lastIndex)
                }
            }
        }
        return queue[0]
    }

    fun delete() {
        if (text.value.isNotEmpty()) {
            text.value = text.value.substring(0, text.value.length - 1)
        }
    }

}