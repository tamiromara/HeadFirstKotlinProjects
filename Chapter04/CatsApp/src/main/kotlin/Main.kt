class Cat(val name: String, weight_param: Int, breed_param: String) {
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
    val myCat = Cat("Bubbles", 70, "Mixed")
    myCat.bark()

    myCat.weight = 75
    println("Weight in kgs is ${myCat.weightInKgs}")

    myCat.weight = -2
    println("Weight is ${myCat.weight}")

    myCat.activities = arrayOf("Walks", "Fetching sticks", "Frisbee")
    for (item in myCat.activities) {
        println("My Dog ${myCat.name} enjoys $item")
    }

    val cats = arrayOf(
        Cat("Candy", 30, "Westie"),
        Cat("Ripper", 10, "Poodle"))

    cats[1].bark()
    cats[1].weight = 15
    println("Weight for ${cats[1].name} is ${cats[1].weight}")

}