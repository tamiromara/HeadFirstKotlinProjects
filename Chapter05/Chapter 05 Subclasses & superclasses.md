# Superclasses & Subclasses



**<u>Inheritance</u>**: putting common code in one class (**superclass**) and the allowing other more specific classes (**subclass**) to inherit this code.



#### An inheritance example: 

Car class:

- includes properties and functions to create a *generic* car:

  - *make and model properties* 
  - *accelerate* and *applyBreaks* and *changeTempreture* functions

ConvertibleCar class:

- is a subclass of *Car*
- It inherits all the properties and functions of *Car*
- it can also add its own properties and functions (*openTop* / *closeTop*)
- it can *override* the things it inherits from its superclass.



#### Design an animal class inheritance structure

Start by thinking about the **common** characteristics between all the animals.

###### (1) look for attributes and behavior that these objects have in common

###### (2) Design a superclass that represents the common state and behavior.

​		Class: Animal

​		Properties: image, food, habitat, hunger

​		Functions: makeNoise(), eat(), roam(), sleep()



###### (3) Decide if a sublass needs default property values or function implementations that are specific to that class.

#### What shoud the subclasses override?

Each animal looks different, lives in a different habitats and have different dietary requirements.

Therefore, each animal subclass will have to override the image, food and habitat properties so that they're ***initialized*** in a different way for each animal.

All Animal sublasses will include *makeNoise()* function, but the ***implementation*** of this funciton will vary depending on the animal.

###### (4) Look for more opportunities to abstract out properties and functions by finding two or more subclasses with comon behavior.



#### We'll create some kotlin animals

Prefixing the name of class with ***open*** tells the compiler that this **class is a superclass**.

Prefixing properties and funcitons with ***open*** tells the compiler that you're happy for them to be overriden.

```kotlin
open class Animal {}
```



```kotlin
open class Animal { // declaring the class as open to be used as a superclass
    open val image = ""
    open val food = ""
    open val habitat = ""
    var hunger = 10

  // declaring makeNoise, eat and roam as open because we'll override them in our subclass.  
  open fun makeNoise() {
        println("the Animal is making a noise")
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
```



#### How a subclass inherits from a superclass

Adding `:` to the class header followed by the superclass name:

- turns the class into a subclass

- inherits all the properties and functions of the superclass.

  

```kotlin
class Hippo : Animal() {
  // Hippo code goes here
}
```

- `Animal()` is the constructor.

- Calling the superclass constructor is ***mandatory***.

- Even if you don't explicity add a constructor to the superclass, the *compiler automatically creates empty constructor* at compile time.

- **NOTE**: if the superclass has a *<u>primary constructor</u>*, we **must** call it in our subclass header. Else code won't compile!

- If superclass constructor includes parameters, when ***must pass values for these parameters*** when calling the constructor.

- Example:

  To define a subclass *convertableCar* of the superclass *Car*:

  1. we add the *Car* constructor in the *convertableCar* class header
  2. passing in value for the *make* and *model* parameters.

  ```kotlin
  open class Car(val make: String, val model: String) {
    // Car class code goes in here
  }
  ```

  ```kotlin
  // convertableCar has two parameters: make_param and model_param.
  // It passes the values of these parameters to the Car constructor, which initializes the make and model properties in the Car superclass.
  class convertableCar(make_param: String, model_param: String) : Car(make_param, model_param) {
    // covertableCar class code goes in here
  }
  ```

  

  #### How to override properties

  By adding the property to the subclass and prefixing it with the keyword *override*

  

  Hippo class: we override the *image, food* and *habitat* properties that the *Hippo* class inhereted from the *Animal* superclass so that they're initialized with the values that are specific to the *Hippo*.

  **Remember**: when a class inherit from a superclass, we must call the superclass constructor (so it can run its initialization code, including creating its properties and initializing them)

  If you define a property in a superclass using *val*, we ***must*** override it in the subclass if we want to assign it a different value.

  ```kotlin
  class Hippo : Animal() { //
    override val image = "hippo.jpg"
    override val food = "grass"
    override val habitat = "water"
  }
  ```

  If the property was define using *var* however, it doesn't have to be overriden in order to assign a new value to it. We can just assign it a new value in the subclass's initializer block:

  ```kotlin
  open class Animal {
    ...
  }
  ```

  ```kotlin
  class Hippo : Animal() {
    init {
      image = "hippo.jpg"
    }
  }
  ```

  

  #### Overriding properties lets you do more than assigning default values:

  //TODO look more into this

  

  #### How to override functions

  by adding the function to the subclass prefixed with the keyword *override*

  

  We will override the *makeNoise* and *eat* functions in the Hippo subclass so that the actions they perform are specific to the Hippo:

  ```kotlin
  // Overriding the makeNoise and eat functions so that their implementations are Hippo-specific
  
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
  ```

  

  #### The rules for overriding functions:

  (1) **The function parameters in the subclass must match those in the superclass**: So if the function in a superclass takes in three *Int* , then the overriding function in the subclass must also take three *Int* arguments. Else, code won't compile.

  (2) **The function return types must be compatible:** Whatever the superclass function defines as a return type, the overriding function must either return the same type or a subclass type. 



#### An overridden function or property stays open

**NOTE**: when a function or a property is defined as *open*, they **stay open** in each of its subclasses, until we declare it as ***final***.

Example:

```kotlin
// Vehicle class defines lowerTempreture as open function
open class Vehicle {
  open fun lowerTempreture() {
    println("Turn down tempreture")
  }
}
```

```kotlin
// the lowerTempreture function remains open despite overriding it 
open class Car : Vehicle() { // this would still be valid without the open keyword
  override fun lowerTempreture() {
    println("Turn on AC")
  }
}
```

```kotlin
// which means we can override it again 
class ConvertableCar : Car() { // open wasn't used here since subclasses inherit the openness of their superclass
  override fun lowerTempreture() {
    print("Open roof")
  }
}
```

To stop a function or a property from being overriden further down the class hierarchy, prefix it with ***final***:

```kotlin
open class Car {
  // declares the function as final, which prevents it from being overriden by any of the Car subclasses.
  final override fun lowerTempreture() {}
    println("Turn on AC")
  }
}
```



#### Adding the Hippo class to the animal project

```kotlin
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
```



#### Adding the canine and wolf classes:

```kotlin
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
        println("The wolf is eating $food")
    }
}
```



#### Using the classes (which function is called)

Above, the *Wolf* class has four functions:

- `sleep()` inherited from *Animal*
- `roam()` inherited from *Canine*, which is an overriden version of a function in *Animal*
- `makeNoise()` & `eat()` overridden functions in the *Wolf* class.

When create a *Wolf* object and assign it to a variable, we can use the dot operator on that variable to envoke each of the four function.



#### Inheritance guarantees that all subclasses have the functions and properties defined in the superclass

When you define a set of properties and functions in a superclass, you’re guaranteeing that all its subclasses also have these properties and functions



**<u>IMPORTANT</u>**:

When you declare a variable, any object that's a subclass of the object's type can be assigned to it:

- The following code defines an *Animal* variable and assigns it a reference of a *Wolf* object. 

- The compiler knows that *Wolf* is a type of *Animal*. That's why it compiles.

  ```kotlin
  val animal: Animal = Wolf()
  ```

  



#### When you call a function on a variable, its the object's version that responds

We can assign an object to a variable and that variable to access the object's functions.

The same is true if the variable is a supertype of the object.

```kotlin
val animal: Animal = Wolf()
animal.eat()
```

When the *eat()* function gets called, the version that's in the *wolf* class responds. 

The system knows that the underlying object is a *Wolf* object and therefore responds in a ***Wolf-like*** way.

**Another Example**: 

We can create an array of different types of animals and have each one respond in its own way:

```kotlin
// The compiler spots that the array items are all types of Animal, so it creates an array of type Array<Animal>
val animals = arrayOf(Hippo(),Wolf(),Lion(),Cheetha(),Fox())

// This loops through the animals and calls roam and eat functions on each one. 
// Each animal responds in a way that is appropriate to it's type.
for (item in animals) {
  item.roam()
  item.eat()
}
```



**<u>Conclusion</u>**:

designing with *inheritance* means that we can write flexible code in the safe knowledge that each object will do the right thing when its functions are called.





#### You can use a supertype as function's parameter and return type

```kotlin
class Vet {
  // the animal parameter can take any Animal type as an argument
  fun giveShot(animal: Animal) {
    animal.makeNoise()
  }
}
```

```kotlin
val vet = Vet()
val wolf = Wolf()
val hippo = Hippo()
// wolf and hippo are both type of Animal, thats why we can pass them as a function argument.
vet.getShot(wolf)
vet.getShot(hippo)
```

Being able to use one type of object in a place that explicitly expects a different type is called: **Polymorphism**

The ability to provide different implementations for a function that has been inherited from somewhere else.



#### The full Animal Code

```kotlin
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
```

