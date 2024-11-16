package bullscows

import kotlin.random.Random


fun main() {
    //a correctly guessed digit is a cow, and if its position is also correct, then it is a bull.

    println("Please, enter the secret code's length:")
    val length = readln().toInt()
    if (length > 10) {
         println("Error: can't generate a secret number with a length of $length because there aren't enough unique digits.")
        return
    }

    val secretCode = generateSecreteCode(length)
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

                if (secretDigit == userDigit) {
                    bulls++
                    cows--
                }
            }
            turn++

        } catch (e: Exception) {
            println("Invalid input Please enter a valid amount of numbers\n")
        }


        if (cows == 0 && bulls == 0) println("Grade: None.")

        if (cows >= 1 && bulls >= 1) println("Grade: ${if (bulls > 1) "$bulls bulls" else "$bulls bull"} and ${if (cows > 1) "$cows cows" else "$cows cow"}.")

        if (cows >= 1 && bulls < 1) println("Grade: ${if (cows > 1) "$cows cows" else "$cows cow"}.")

        if (cows < 1 && bulls >= 1) println("Grade: ${if (bulls > 1) "$bulls bulls" else "$bulls bull"}.${if (bulls == secretCode.length) "\nCongratulations! You guessed the secret code." else ""}")


    } while (bulls != secretCode.length)

}


fun generateSecreteCode(length: Int): String {
    val randomNum = Random.nextInt().toString()
    val uniqueDigits = mutableSetOf<Int>()
    var firstDigitFound = false

    while (uniqueDigits.size < length) {
        randomNum.forEach {
            if (uniqueDigits.size < length) {
                if (!firstDigitFound && it != '0' && it.isDigit()) {
                    uniqueDigits.add(it.digitToInt())
                    firstDigitFound = true
                } else if (firstDigitFound && it.isDigit() && !uniqueDigits.contains(it.digitToInt())) {
                    uniqueDigits.add(it.digitToInt())
                }
            }
        }
    }

    return uniqueDigits.joinToString("")
}

