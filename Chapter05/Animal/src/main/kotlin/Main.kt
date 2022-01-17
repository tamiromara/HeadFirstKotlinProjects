/*
 * Interface Roamable defines a roaming behavior.
 * There's no need prefix abstract properties/functions with keyword abstract,
 * because interface properties & functions are abstract
 */
interface Roamable {
    fun roam()
}

abstract class Animal : Roamable {
    abstract val image: String
    abstract val food: String
    abstract val habitat: String
    var hunger = 10

    abstract fun makeNoise()
    abstract fun eat()

    // overrides the roam() function from the Roamable interface
    override fun roam() {
        println("The Animal is Roaming!")
    }

    fun sleep() {
        println("The Animal is sleeping")
    }
}

/*
 * Hippo is a direct concrete subclass of abstract class Animal.
 * Therefore, all Animal abstract properties and functions MUST be implemented.
 */
class Hippo : Animal() {
    override val image = "hippo.jpg"
    override val food = "grass"
    override val habitat = "water"

    override fun makeNoise() {
        println("Grunt! Grunt!")
    }

    override fun eat() {
        println("The Hippo is eating $food")
    }
}

/*
 *  The Canine class is a subclass of Animal
 *  Canine declared as an abstract class.
 *  We chose to override and implement the roam() function from Animal
 *  The implementation of the Animal concrete properties and functions are left for the next
 *  Canine concrete subclass Wolf().
 */
abstract class Canine : Animal() {
    override fun roam() {
        println("The Canine is roaming")
    }
}

/*
 * Wolf is a concrete subclass that implements Canine, therefore,
 * it overrides and implements all Animal concrete properties and functions,
 * that were not implemented by Canine.
 */
class Wolf : Canine() {
    override val image = "wolf.jpg"
    override val food = "meat"
    override val habitat = "forests"

    override fun makeNoise() {
        println("Hooooooowl")
    }

    override fun eat() {
        println("The Wolf is eating $food")
    }

}

class Vehicle : Roamable {
    override fun roam() {
        println("the Vehicle is roaming!")
    }
}

class Vet {
    fun giveShot(animal: Animal) {
        animal.makeNoise()
    }
}

fun main() {
    // creates an array of the type Array<Animal> and assigns it reference to variable animals
    val animals = arrayOf(Hippo(), Wolf())

    // looping through the array items
    for (animal in animals) {
        animal.roam()
        animal.eat()
    }

    val vet = Vet()
    val wolf = Wolf()
    val hippo = Hippo()

    /*
     * Wolf, Hippo and Vehicle all implement Roamable.
     * if we add a function that doesn't, Vet for example, it won't compile
     */
    val roamables = arrayOf(Wolf(), Hippo(), Vehicle())
    for (item in roamables) {
        item.roam()
        if (item is Animal) {
            item.eat()
        }
    }

    vet.giveShot(wolf)
    vet.giveShot(hippo)
    println("${wolf.image}")
}