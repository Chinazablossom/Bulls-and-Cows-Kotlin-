package bullscows

import kotlin.random.Random
import kotlin.random.nextUInt


fun main() {
    //a correctly guessed digit is a cow, and if its position is also correct, then it is a bull.
    println("Please, enter the secret code's length:")
    val length = readln().toInt()
    println("Input the number of possible symbols in the code:")
    val charterLength =  readln().toInt()

    if (length !in 1..36 ) {
        println("Error: can't generate a secret number with a length of $length because there aren't enough unique digits.")
        return
    }

    val secretCode = generateSecreteCode(length,charterLength)
    println("Okay, let's start a game!")
    var turn = 1

    do {
        var cows = 0
        var bulls = 0

        println("Turn $turn:")
        try {
            val userInput = readln().trim()
            (0..secretCode.lastIndex).forEach {
                val secretDigit = secretCode[it]
                val userDigit = userInput[it]

                if (secretCode.contains(userDigit)) {
                    cows++
                }

                if (secretDigit.equals(userDigit,true) ) {
                    bulls++
                    cows--
                }
            }
            turn++

        } catch (e: Exception) {
            println("Invalid input Please enter a valid amount of numbers\n ${e.message}\n${e.cause}")
        }


        if (cows == 0 && bulls == 0) println("Grade: None.")

        if (cows >= 1 && bulls >= 1) println("Grade: ${if (bulls > 1) "$bulls bulls" else "$bulls bull"} and ${if (cows > 1) "$cows cows" else "$cows cow"}.")

        if (cows >= 1 && bulls < 1) println("Grade: ${if (cows > 1) "$cows cows" else "$cows cow"}.")

        if (cows < 1 && bulls >= 1) println("Grade: ${if (bulls > 1) "$bulls bulls" else "$bulls bull"}.${if (bulls == secretCode.length) "\nCongratulations! You guessed the secret code." else ""}")

    } while (bulls != secretCode.length)

}



fun generateSecreteCode(length: Int,characterLength: Int): String {
    val characters = ('0'..'9').toMutableList() + ('a'..'z').toMutableList()
    val lastChar = if (characterLength > 10) 'a' + (characterLength - 11) else 'a'
    val randomAlphabet = (characters[10]..lastChar).random()

    println("The secret is prepared: ${"*".repeat(length)} (0-9, a-${lastChar}).")
    val uniqueDigits = mutableSetOf<Char>()
    val randomNum = Random.nextUInt().toString() + randomAlphabet

    while (uniqueDigits.size < length) {
        randomNum.forEach {
            if (uniqueDigits.size < length) {
                    uniqueDigits.add(it)
                 if (!uniqueDigits.contains(randomAlphabet) && uniqueDigits.size < length) {
                    uniqueDigits.add(randomAlphabet)
                }
            }
        }
    }

    return uniqueDigits.shuffled().joinToString("")
}

