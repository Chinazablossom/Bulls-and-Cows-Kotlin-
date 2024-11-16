package bullscows



fun main() {

    val length = readln().toInt()
   println(generateSecreteCode(length))



    /*  //a correctly guessed digit is a cow, and if its position is also correct, then it is a bull.

      //val secretCode = (1000..9999).random().toString()
      val secretCode = "9305"

      val userInput = ""


      var cows = 0
      var bulls = 0

      (0..3).forEach {
          val secretDigit = secretCode[it]
          val userDigit = userInput[it]

          if (secretCode.contains(userDigit)) {
              cows++
          }

          if (secretDigit == userDigit ) {
              bulls++
              cows--
          }


      }


      if (cows == 0 && bulls == 0) println("Grade: None. The secret code is $secretCode.")

      if (cows >= 1 && bulls >= 1) println("Grade: $bulls bull(s) and $cows cow(s). The secret code is $secretCode.")

      if (cows >= 1 && bulls < 1) println("Grade: $cows cow(s). The secret code is $secretCode.")

      if (cows < 1 && bulls >= 1) println("Grade: $bulls bull(s). The secret code is $secretCode.")
  */


}


fun generateSecreteCode(length: Int): String {
    if (length > 10 ) {
        return "Error: can't generate a secret number with a length of $length because there aren't enough unique digits."
    }

    val pseudoRandomNumber: String = System.nanoTime().toString().reversed()
    val uniqueDigits = mutableSetOf<Int>()
    var firstDigitFound = false

    while (uniqueDigits.size < length) {
        pseudoRandomNumber.forEach {
            if (uniqueDigits.size < length) {
                if (!firstDigitFound && it != '0' && it.isDigit() ) {
                    uniqueDigits.add(it.digitToInt())
                    firstDigitFound = true
                } else if (firstDigitFound && it.isDigit() && !uniqueDigits.contains(it.digitToInt())) {
                    uniqueDigits.add(it.digitToInt())
                }
            }
        }
    }

    return "The random secret number is ${uniqueDigits.joinToString("")}."
}

