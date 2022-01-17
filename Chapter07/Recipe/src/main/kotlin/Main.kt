/*
 *  Recipe data class declares default values to all its parameters
 */
data class Recipe(val title: String = "",
                  val mainIngredient: String = "",
                  val isVegetarian: Boolean = false,
                  val difficulty: String = "") {}

// Example of a function with a secondary constructor:
class Mushroom(val size: Int, val isMagic: Boolean) {
    constructor(isMagic_param: Boolean): this(0, isMagic_param) {
        // code that runs here when the secondary constructor is called
    }
}

/*
 *  findingRecipe function has found parameters declared with default values.
 *  It returns an Array of the type Array<Recipe>
 *
 *  We can also modify the return type in the function header to Recipe
 *  and then modifying the return statement to Recipe(...)
 */
fun findRecipe(title: String = "",
               ingredient: String = "",
               isVegetarian: Boolean = false,
               difficulty: String = ""): Array<Recipe> {
    // code to find the recipe
    return arrayOf(Recipe(title, ingredient, isVegetarian, difficulty))
}

/*
 *  Example of function overloading NOT overriding
 *  Function addNumbers has been overloaded to accommodate two types of inputs
 *  The compiler will decide which function to use based on the arguments passed
 */
fun addNumbers(a: Int, b: Int): Int {
    return a + b
}
fun addNumbers(a: Double, b: Double): Double {
    return a + b
}

fun main() {
    // Passing values to the Recipe constructor using order of decleration
    val r1 = Recipe("Thai Curry", "chicken")
    // Passing values to the Recipe constructor using named arguments
    val r2 = Recipe(mainIngredient = "chicken", title = "Thai Curry")
    // create a copy of r1 and alter the title property value using named arguments
    val r3 = r1.copy(title = "Chicken Bhuna")

    // compare hashCode returns of each object
    println("r1 hash code: ${r1.hashCode()}")
    println("r2 hash code: ${r2.hashCode()}")
    println("r3 hash code: ${r3.hashCode()}")

    //
    println("r1 toString: ${r1.toString()}")

    // compare == vs ===
    println("r1 == r2: ${r1 == r2}")
    println("r1 === r2: ${r1 === r2}")
    println("r1 == r3: ${r1 == r3}")

    /*
     *  Creating multiple variables at once using Destructuring. This is the same as:
     *  val name = r1.component1()
     *  val vegetarian = r1.component3()
     */
    val (title, mainIngredient, vegetarian, difficulty) = r1    // Destructuring declaration
    println("title is $title and vegetarian is $vegetarian")

    // Creating Mushroom by calling the primary constructor
    val m1 = Mushroom(6, false)
    println("m1 size is ${m1.size} and isMagic is ${m1.isMagic}")

    // Creating Mushroom by calling the secondary constructor
    val m2 = Mushroom(true)
    println("m2 size is ${m2.size} and isMagic is ${m2.isMagic}")

    // overloaded function
    println(addNumbers(2, 5))
    println(addNumbers(1.6, 7.3))
}