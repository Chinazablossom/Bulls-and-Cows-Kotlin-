package bullscows

import java.lang.NumberFormatException
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.system.exitProcess


fun main() {
    BullsAndCows()
}

class BullsAndCows {

    init {
        println("Input the length of the secret code:")
        val length = readIntInput()
        println("Input the number of possible symbols in the code:")
        val charterLength = readIntInput()
        try {

            validateInput(length, charterLength)

            val secretCode = generateSecreteCode(length, charterLength)

            println("Okay, let's start a game!")
            var turn = 1
            do {
                var cows = 0
                var bulls = 0

                println("Turn $turn:")

                val userInput = readln().trim()
                (0..secretCode.lastIndex).forEach {
                    val secretDigit = secretCode[it]
                    val userDigit = userInput[it]

                    if (secretCode.contains(userDigit)) {
                        cows++
                    }

                    if (secretDigit.equals(userDigit, true)) {
                        bulls++
                        cows--
                    }
                }
                turn++

                if (cows == 0 && bulls == 0) println("Grade: None.")

                if (cows >= 1 && bulls >= 1) println("Grade: ${if (bulls > 1) "$bulls bulls" else "$bulls bull"} and ${if (cows > 1) "$cows cows" else "$cows cow"}.")

                if (cows >= 1 && bulls < 1) println("Grade: ${if (cows > 1) "$cows cows" else "$cows cow"}.")

                if (cows < 1 && bulls >= 1) println("Grade: ${if (bulls > 1) "$bulls bulls" else "$bulls bull"}.${if (bulls == secretCode.length) "\nCongratulations! You guessed the secret code." else ""}")


            } while (bulls != secretCode.length)
        } catch (e: StringIndexOutOfBoundsException) {
            println("Error: Your input must be $length characters long ")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }

    }


    private fun generateSecreteCode(length: Int, characterLength: Int): String {
        val characters = ('0'..'9').toMutableList() + ('a'..'z').toMutableList()
        val lastChar = if (characterLength > 10) 'a' + (characterLength - 11) else 'a'
        val randomAlphabet = (characters[10]..lastChar).random()

        println("The secret is prepared: ${"*".repeat(length)} (0-9, a-${lastChar}).")
        val uniqueDigits = mutableSetOf<Char>()
        val randomNum = Random.nextUInt().toString() + randomAlphabet

            randomNum.forEach {
                if (uniqueDigits.size < length) {
                    uniqueDigits.add(it)
                    if ( uniqueDigits.size < length && !uniqueDigits.contains(randomAlphabet) ) {
                        uniqueDigits.add(randomAlphabet)
                    }
                }
            }

        return uniqueDigits.shuffled().joinToString("")
    }

    private fun readIntInput(): Int {
        val input = readlnOrNull()?.trim()
        try {
            return input?.toInt() ?: throw NumberFormatException("Input must be a number.")
        } catch (e: NumberFormatException) {
            println("Error: \"$input\" isn't a valid number.")
            exitProcess(0)
        }

    }

    private fun validateInput(length: Int, charterLength: Int) {

        if (length > 10) {
            throw IllegalArgumentException("can't generate a secret number with a length of $length because there aren't enough unique digits.")
        }

        if (charterLength !in 10..36) {
            if (charterLength > 36) {
                throw IllegalArgumentException("maximum number of possible symbols in the code is 36 (0-9, a-z).")
            }
            throw IllegalArgumentException("it's not possible to generate a code with a length of $length with $charterLength unique symbols.")
        }
    }


}





