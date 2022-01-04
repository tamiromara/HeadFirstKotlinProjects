fun main() {

    //  creates an array of Strings and stores a reference to it in variable Options
    val options = arrayOf("Rock", "paper", "Scissors")

    /*
     *  getGameChoice takes an array of Strings, options, as an argument and returns a random String.
     *  The reference to the random String is stored in gameChoice.
     *
     *  userChoice takes an array of Strings, options, as an argument and returns the user selected Answer.
     *  Reference to the user's choice is stored in userChoice.
     */
    val gameChoice = getGameChoice(options)
    val userChoice = getUserChoice(options)
}

/*
 *  getGameChoice chooses one of the parameter array, optionsParam, items at random.
 *  getGameChoice has one parameter of the type Array<Sting> and return a String as inferred by the returned result.
 *
 *  getGameChoice function has been simplified into a single line expression as it only has
 *  a single expression and a single return type.
 *
 *  a random number is generated by multiplying a number between 0 and almost 1, Math.random(),
 *  by the size of the array, optionsParam.size .
 *  The result is then rounded into an integer value, .toInt(), which is then used as an index for accessing array.
 */
fun getGameChoice(optionsParam: Array<String>) = optionsParam[(Math.random() * optionsParam.size).toInt()]

fun getUserChoice(optionsParam: Array<String>): String {
    var isValidChoice = false
    var userChoice = ""

    while (!isValidChoice) {
        /*
         *  Asking the user for his answer:
         *
         *  Instead of hard coding the choices, we iterate through the available options.
         *  This is helpful in case new values were added/removed from our Array<String>.
         *  for loop will iterate over the optionsParam values and print them on the screen.
         */
        print("Please choose of the following,")
        for (item in optionsParam) print(" $item")
        print(": ")

        //  reads the user input from output window and stores it in a variable
        val userInput = readLine()

        /*
         *  Validating the user input:
         *
         *  readLines() returns null value if it's reading a line from a file, and it reaches the end of the file.
         *  Despite this not being the case in our program, as we're reading from the output window,
         *  it's a good practice to always validate the user input.
         *
         *  if Statement return true if both the userInput isn't null and its value matches an item within
         *  the passed Array<String> optionsParam.
         *  The expressions within if body are then executed:
         *  isValidChoice is updated to true, so the if statement stops looping.
         *  userInput is verified and a reference to it is stored in variable userChoice
         */
        if (userInput != null && userInput in optionsParam) {
            isValidChoice = true
            userChoice = userInput
        }
        //  if user input didn't pass the above condition, the user will be asked to re-enter his choice
        if (!isValidChoice) println("You must enter a valid choice.")
    }
    // userChoice reference value is updated and returned
    return userChoice
}













