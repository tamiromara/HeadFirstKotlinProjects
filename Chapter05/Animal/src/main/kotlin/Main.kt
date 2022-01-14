open class Animal {
    open val image = ""
    open val food = ""
    open val habitat = ""
    var hunger = 10

    open fun makeNoise() {
        println("Make noise")
    }

    open fun eat() {
        println("The Animal is eating")
    }

    open fun roam() {
        println("The Animal is roaming")
    }

    fun sleep() {
        println("The Animal is sleeping")
    }
}

// The Hippo class is a subclass to Animal
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

// The Canine class is a subclass of Animal
open class Canine : Animal() { // double check about the open prefix if it's still rquired here
    override fun roam() {
        println("The Canine is roaming")
    }
}

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
    vet.giveShot(wolf)
    vet.giveShot(hippo)
}