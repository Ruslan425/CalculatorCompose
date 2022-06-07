package ru.romazanov.calculatorcompose


import java.math.BigInteger

class Calculator {

    private val map: MutableMap<String, BigInteger> = mutableMapOf() // Мап для сохранения переменных

    lateinit var string: String

    lateinit var list: MutableList<String>

    private val letters = Regex("[a-zA-Z]*")

    private fun newList(str: String): MutableList<String> {
        /*
        Функция работаят только со сторокой из чисел и операций записаных через пробел
        вид строки должен быть (1 + 2) + 3
         */
        fun popPush(queue: MutableList<String>, number: MutableList<String>) {
            /*
            Фунция для работы с очередью
             */
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
        val one = Regex("[*/]")   // цмножение и деление
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

    private fun sum(list: MutableList<String>): BigInteger {
        /*
        Функция принимает список с цифрами и операциями в ОПН
        возвращает сумму.
        */
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

    private fun strToNewStr(str: String): String {

        /*
        Функция разделяет строку на отдельные эелементы через пробел
         */

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

    private fun fromMapToString(map: Map<String, BigInteger>, str: String): String {

        /*
        Добавляем элементы из Мап в строку для подчета
         */

        var answer = ""

        for (i in str) {
            when {
                i.toString().matches(letters) -> {
                    answer += map[i.toString()]
                }
                else -> answer += i.toString()
            }
        }
        return answer
    }

    private fun addToMap(str: String) {
        /*
        Добавляем в хешМап для сохранение переменых
         */
        val key = str.substringBefore("=").replace(Regex("\\s+"), "")
        if (key.matches(Regex(".*\\d.*")) || key.matches(Regex("[^a-zA-Z]*"))) {
            return println("Invalid identifier")
        }

        val value = str.substringAfter("=").replace(Regex("\\s+"), "")
        if (map.containsKey(value)) {
            map[key] = map.getOrElse(value) { 0 } as BigInteger
        } else {
            try {
                map[key] = value.toBigInteger()
            } catch (e: Exception) {
                println("Invalid assignment")
            }
        }
    }




    fun loop() {
        while (true) {
            val str = readLine()!!
            when {
                str.isEmpty() -> {
                    continue
                }
                str == "/exit" -> {
                    println("Bye!")
                    return
                }
                str == "/help" -> {
                    println("Калькулятор умеет складывать и вычитать, -- дает +" +
                            "\n Так же теперь умеет делить и умножать" +
                            "\n Работатает со скобками, но пока без степеней ^")
                    continue
                }
                str.matches(Regex("/.+")) -> {
                    println("Unknown command")
                    continue
                }
                str.matches(Regex(".+=.+")) -> {
                    addToMap(str)
                    continue
                }
                str.matches(Regex(".*\\*\\*+.*")) || str.matches(Regex(".*//+.*")) -> {
                    println("Invalid expression")
                    continue
                }
                str.matches(letters) -> {
                    if (map[str] == null) {
                        println("Unknown variable")
                    } else {
                        println(map[str])
                    }
                    continue
                }
                else -> {
                    var num = 0
                    for (i in str) {
                        if (i == '(') ++num
                        if (i == ')') --num
                    }
                    if (num == 0) {
                        string = strToNewStr(str)
                        string = fromMapToString(map, string)
                        list = newList(string)
                        println(sum(list))
                        continue
                    } else {
                        println("Invalid expression")
                        continue
                    }
                }
            }
        }
    }
}


fun main() {
   // val calculator = Calculator()
   // calculator.loop()

    var text = "1234567"

    fun delete() {
        text = text.substring(0, text.length - 1)
    }

    delete()

    println(text)
    delete()
    println(text)
}



val list = listOf(
    "Del",
    "(",
    ")",
    "/",
    "7",
    "8",
    "9",
    "*",
    "4",
    "5",
    "6",
    "-",
    "1",
    "2",
    "3",
    "+",
    "0",
    "00",
    ".",
    "="
)
