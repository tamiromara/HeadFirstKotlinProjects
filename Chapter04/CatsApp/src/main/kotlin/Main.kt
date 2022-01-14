class Dog(val name: String, weight_param: Int, breed_param: String) {
    init {
        println("Dog $name has been created!")
    }

    var activities = arrayOf("Walks")
    val breed = breed_param.uppercase()

    init {
        println("The breed is $breed")
    }

    var weight = weight_param
        set(value) {
            if (value > 0) field = value
        }

    val weightInKgs: Double
        get() = weight / 2.2

    fun bark() {
        println(if (weight < 20) "Yip!" else "Woof!")
    }

}

fun main() {
    val myDog = Dog("Bubbles", 70, "Mixed")
    myDog.bark()

    myDog.weight = 75
    println("Weight in kgs is ${myDog.weightInKgs}")

    myDog.weight = -2
    println("Weight is ${myDog.weight}")

    myDog.activities = arrayOf("Walks", "Fetching sticks", "Frisbee")
    for (item in myDog.activities) {
        println("My Dog ${myDog.name} enjoys $item")
    }

    val dogs = arrayOf(
        Dog("Candy", 30, "Westie"),
        Dog("Ripper", 10, "Poodle"))

    dogs[1].bark()
    dogs[1].weight = 15
    println("Weight for ${dogs[1].name} is ${dogs[1].weight}")

}