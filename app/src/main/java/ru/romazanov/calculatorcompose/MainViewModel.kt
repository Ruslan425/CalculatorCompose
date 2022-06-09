package ru.romazanov.calculatorcompose

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.romazanov.calculatorcompose.data.Calculation
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {


    var number = mutableStateOf("")
    var calculation = mutableStateOf("")
    var list = mutableStateListOf<String>()
    private val test = mutableStateOf(true)

    fun onClickNumberKey(key: String){
        if(test.value) {
            number.value += key
        } else {
            test.value = true
            number.value = key
        }
    }

    fun onClickOperatorKey(key: String) {
        addToList()
        calculation.value += number.value + key
        list.add(key)
        test.value = false
    }

    fun onClickAnswer(key: String) {
        addToList()
        calculation.value += number.value + key
        number.value = getSum(list.toList().joinToString(" "))
        deleteAll()
        test.value = false
    }

    val calculationList = mutableStateListOf<Calculation>()

    fun addMinusToText() {
        if (!number.value.matches("-\\d+.*\\d*".toRegex())) {
            number.value = "-${number.value}"
        } else {
            number.value = number.value.substringAfter("-")
        }
    }

    private fun addToList() {
        list.add(number.value)
    }

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
                i.matches("-*\\d+.*\\d*".toRegex()) -> number.add(i)
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

    private fun sum(list: MutableList<String>): Double {

        val queue = mutableListOf<Double>()

        for (i in list) {
            when {
                i.matches(Regex("-*\\d+.*\\d*")) -> queue.add(i.toDouble())
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
        return if(list.isEmpty()) 0.0 else queue[0]

    }

    private fun getSum(text: String): String {

        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate: String = sdf.format(Date())

        calculationList.add(
            Calculation(
                id = Random().nextInt(),
                calculation = list.toList()
                    .joinToString("") + " = " + sum(newList(text)).toString(),
                date = currentDate
            )
        )
        val answer = sum(newList(text)).toBigDecimal()

        return answer.setScale(3, RoundingMode.CEILING).toString()
    }

    fun delete() {

        if (number.value.isNotEmpty()) {
            number.value = number.value.substring(0, number.value.length - 1)
        } else if (number.value.isEmpty() && calculation.value.isNotEmpty()) {
            calculation.value = calculation.value.substring(0, calculation.value.length - 1)
        } else {
            list.clear()
        }
    }

    private fun deleteAll() {
        list.clear()
        calculation.value = ""
    }


}
