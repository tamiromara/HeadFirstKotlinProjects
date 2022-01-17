### To Learn:

- Using *abstract classes* to control which classes can or can't be *instantiated* in the hierarchy.

- How *abstract classes* can force *concrete classes* to provide *their own implementation*.

- How to use *interfaces* to share *behavior* between *independent classes*.

- Get into ***is**, **as*** and ***when***



#### Some Classes shouldn't be instantiated:

- We need the Animal class for *inheritance & polymorphism*.

- Should not be able instantiate a *generic* Animal class.

- Prefix a class with `abstract` keyword to make it *an abstract class*.

- *Abstract* classes can't be instantiated even if they have a *constructor*.

  ```kotlin
  abstract class Animal { ... }
  ```

- *Abstract* classes can, however, be used as a *declared variable type*

  ```kotlin
  var animal: Animal	// using abstract class Animal as a declared variable type	
  ```

  ```kotlin
  animal = Wolf()
  animal = Animal()		// This won't compile
  ```

- A class that is not *abstract* is known as a *concrete class*.



#### Abstract properties:

- *Abstract* class can have *abstract* properties and functions. Useful if:

  - The class has *behaviors* that don't make sense unless they're *implemented by a subclass*.
  - You can't think of a *generic implementation* useful enough for subclasses to inherit.

  ```kotlin
  abstract class Animal {
      abstract val image: String
      abstract val food: String
      abstract val habitat: String
      var hunger = 10 }
  ```

- If you try to initialize an abstract property or define a custom getter or setter for it, it won't compile.

- By marking a property as abstract, it means:

  - there's no useful initial value it can have, and
  - no useful implementation for a custom getter or setter.



#### Abstract Functions:

- We can abstract `makeNoise()` and `eat()` since they're being overridden in every *conceret* subclass and there's no useful implementation that we can provide that would help the subclasses.

  ```kotlin
  abstract fun makeNoise()
  abstract fun eat()
  open fun roam() {
          println("The Animal is roaming")
      }
      fun sleep() {
          println("The Animal is sleeping")
      }
  ```

- Abstract functions are without bodies. Because abstract means there's no useful code for it.

- if a body's added to an abstract function, the program won't compile.

- `abstract fun makeNoise() {}`: This won't compile!



- Abstract properties and functions ***define a common protocol*** so that we can use *Polymorphism*.

- **Polymorphism**: when we define a *supertype* for a group of classes, we can use any *subclass in place of the superclass it inherits from*.

- Polymorphism allows us to use a *superclass type* as a *variable type, function argument, return type or array type*:

  ```kotlin
  val animals = arrayOf(Hippo(), Wolf())
  for (item in animals) {
    item.roam()
    item.roar()}
  ```

- `val animals`: using *superclass type* as a *declared variable type*.

- `arrayOf(Hippo(), Wolf())`: Creates an array of *different Animal objects*

- `item.roam()` & `item.roar()`: each *Animal* in the array responds in its own way.

- This means we can add new *subclasses (subtypes)* without having to change how the functions deal with those new types.



#### Implementing abstract classes:

- Implementing abstract class:

  ```kotlin
  class Hippo : Animal() { ... }
  ```

- Implementing properties and functions: by overriding each one, and providing implementation (this is the same as if the superclass was concrete):

  - *initialize* any <u>abstract property</u>, and
  - *provide a body* for any <u>abstract function</u>.

  ```kotlin
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

- The rules for implementing abstract properties and functions are the same as those used for overriding normal properties and function:

  - When implementing an *abstract property*: it must have the *same name* and its *type* must be compatible (same type or one of its subclasses) with the type defined in the *abstract superclass*.
  - When implementing an *abstract function*: it must have the same * function signature* as the function defined in the *abstract superclass*, and its *return type* must be compatible with the declared return type.

- The *first* ***concrete class*** in the inheritance tree below the ***abstract superclass*** *must implement all abstract properties and functions.* 

- Example: the *Hippo* class is a direct concrete subclass of *Animal*, so it must implement all *abstract* properties and functions.

- With ***abstract subclasses*** , however, we have a choice: either implement the abstract properties and functions or pass the buck to its subclasses.

- Example: If both *Animal* and *Canine* are *abstract*, then *Canine* can either implement the abstract properties and functions or leave the implementation to its subclasses. If *Canine* doesn't implement them, then its first *concrete subclass* *Wolf* must implement them. and if *Canine* class were to define any new abstract properties and functions then *Canine* subclasses would have to implement these too!



### Interfaces:

- What if you wanted to include classes in your application that shares *some* of the behavior defined in the inheritance hierarchy, *but not all*?

- Example: Adding *Vehicle* class to our animal simulation app, it has one function `roam`. This would allow us to create a *vehicle* object that *roams* around the animals environment. 

- It would therefore be useful if somehow *Vehicle* could implement *Animal's roam* function, which would allow us to use <u>*polymorphism* to create an array of objects that can roam</u>, and then call the function on each object.

- When we *independent* classes that exhibit common behavior, we can model this behavior into *an interface.*
- **Interface**: lets you define a common behavior protocol outside a superclass hierarchy.
- *Interfaces* are similar to *abstract classes*:
  - They **can't** be *instantiated*.
  - They **can** define *abstract or concrete* properties and functions
- **Key difference:** class can implement *multiple* *interfaces*, but can only *inherit from a single direct superclass*.



### Defining an interface:

- Create `Roamable` interface will provide a *common protocol for roaming behavior*.
- Define an *abstract* function named `roam` that *Animal & Vehicle* will have to implement.

#### Interface functions:

- interface function can be *abstract or concrete*.

  - `fun roam()`: defines an *abstract* function. No need to prefix with `abstract.`
  - with interfaces, the compiler automatically *infers* that a function with no body must be *abstract*.

  ```kotlin
  interface Roamable { 
    fun roam()
  }
  ```

  - to define a *concrete* function, just add body to the function:

  ```kotlin
  interface Roamable {
    fun roam() {
      println("Roamable is roaming!")
    }
  }
  ```

#### Interface properties:

**Defining interface properties:**

- Unlike *abstract* classes, **Interfaces can't have constructors**.

- Just like in *abstract functions*, there's no need to prefix an abstract property with `abstract` keyword.

  ```kotlin
  interface Roamable {
    val velocity: Int
  }
  ```

-  Unlike *properties in abstract classes*, properties defined in an *interface*  ***can't store state*** and therefore ***can't be initialized***.

- We can, however, return a value for a property by defining a custom *getter*:

  ```kotlin
  interface Roamable {
    val velocity: Int
    // this returns value 20 whenever the property is accessed. 
    // we can still override the property in any class that implements the interface.
    		get() = 20
  }
  ```

- Interface properties don't have ***backing fields***. (check chapter 4)



**Declaring that a class implements an interface:**

- by adding `:` to the class header followed by the name of the interface.

- then, *overriding* its properties and functions.

  ```kotlin
  class Vehicle: Roamable {		// no parantheses
    override fun roam() {			// overriding the interface function
      println("The Vehicle is roaming!")
    }
  }
  ```

- we don't add parantheses after the interface name, becuase parantheses are only needed to call the *superclass constructor*, and interfaces **don't** have constructors.

- any *concrete classes* that *implements* the interface ***must*** have *concrete implementations* for any *abstract properties and functions*. (same as with *abstract superclasses*)



**Implementing multiple interfaces:**

- by adding them to the class header separated with `,`: `class X: A, B {...}`

- Inheriting from a superclass plus one or more interfaces:`class X: C(), A`: class `X` inherits from *superclass* `C` and *interface* `A`.

- if a class inherits from a multiple implementations of the same function or property then:

  - the class must provide it's own implementation, **or**
  - specify which version of the function or property it should use.

  ```kotlin
  interface A {
    fun myFunction() {println("From A")}
  }
  interface B {
    fun myFunction() {println("From B")}
  }
  
  class X: A, B {
    override fun myFunction() {
      // super<A> refers to the superclass/interface named A.
      super<A>.myFunction()	// calls the version of myFunction that's defined in A
      super<B>.myFunction()	// calls the version of myFunction that's defined in B
      
      // extra code specific to class X
    }
  }
  ```



**Remember**:

- Kotlin classes can have one (*superclass*) parent, and that superclass defines who you are.
- You can implement *multiple* interfaces, and those interfaces define the *role* that you can play.



#### How do you know whether to make *Class*, *subclass*, *abstract class* or *interfae*?

**Class**: make a class with no *superclass* when the new class doesn't pass the ***IS-T*** test for any other type.

**Subclass**: when you need more specific versions of a class, and need to override or add new behaviors.

**Abstract Class**: when you need to define a template for a group of subclasses, and want to guarantee that nobody can create an object out of that type.

**Interface**: when you want to define a *common behavior or role*, that other classes can play, regadless of where these classes are in the inheritance tree.



#### Interfaces let us use polymorphism:

- we can create an array of *Roamable* objects and call each object's *roam* function:

  ```kotlin
  val roamables = arrayOf(Hippo(), Wolf(), Vehicle())		// creats an array of Roamable objects
  for (item in roamables) {		// item variable is of the type Roamable
    item.roam()
  }
  ```

- What if you wanted to access more than the properties and functions defined in the *Roamable* interface?

- Calling `item.makeNoise()` won't work: because `item` is a variable of type *Roamable*. So it doesn't recognize the `makeNoise` function.

- We can access a *behavior* that is not defined by a *variable's type* by using the `is` operator to check the *type* of the *underlying object*.

- If the underlying object is of the appropriate type, the compiler lets you access the *behaviors* that are appropriate for that type.

- So, if we want to call the `eat` function for each *Animal* object in an *array of Roamables*:

  ```kotlin
  val roamables = arrayOf(Hippo(), Wolf(), Vehicle())
  for (item in roamables) {
    item.roam()
    if (item is Animal) {
      item.eat()
    }
  }
  ```



#### Where to use the `is` operator:

- As a condition for an `if`:

- This assigns a *String* of `wolf` if `animal` variable *holds reference* to *Wolf* object, and `Not Wolf` if it doesn't

  ```kotlin
  val str = if (animal is Wolf) "Wolf!" else "Not Wolf!"
  ```

-  In conditions using `&&` and `||`: 

  ```kotlin
  if (roamable is Animal && roamable.hunger < 5) {
  		//Code to deal with a hungry Animal 
  }
  ```

- In a `while` loop:

- The code continues to loop while the `animal` variable holds reference to a *Wolf* object:

  ```kotlin
  while (animal is Wolf) {
    // code runs here while animal is Wolf
  }
  ```



#### When to use `when`:

- Use `when` to compare variables against a bunch of different options.

- same as using a chain of *if-else* statements, but more compact and readable:

  ```kotlin
  
  when (x) {		// checks the value of x
    0 -> println("x is Zero")				// if x is 0, execute this code
    1, 2 -> println("x is 1 or 2")	// if x is 1 or 2, execute this code
    else -> {												// while loops can have an else clause
      println("x is neither 0, 1 nor 2")
      println("x is some other value")
    }
    
  }
  ```

- If you want to run different code depending on the underlying type of an object, we can use `is` operator inside `when` statement:

  ```kotlin
  when (roamable) {		//checks the underlying type of object roamable
    is Wolf -> { Wolf-specific code }
    is Hippo -> { Hippo-specific code }
    // This only runs if roamabl is typf of Animal that is not Wolf or Hippo
    is Animal -> { Code that runs if roamable is some other Animal }	
  }
  ```



#### Casting:

- ***Casting***: is when the compiler treats a variable as though its type is different to the one that its declared as.

- ***Smart Casting***: when the compiler *automatically* performs *casting* on our behalf.

  ```kotlin
  if (item is Wolf) {
    item.eat()
    item.makeNoise()
    // other Wolf specific code
  }
  ```

- the above code uses the `is` operator to *smart cast* the variable named `item` to a *Wolf*.

- so, inside the `if` body, the compiler can treat `item` as of type `Wolf`

- `is` operator will only perform *smart casting* if the compiler can *guarantee* that the *variable* can't change between checking the object's type and when it's used.

- `is` operator *won't smart cast* a `var` property in a class: because the compiler can't guarantee that some other code won't sneak in and update the property.

  ```kotlin
  class Roamable {
    var r: Roamable = Wolf()
    fun myFunction() {
     	// the compiler can't smart cast the Roamable property r to a Wolf.
      if (r is Wolf) {	// Won't compiler
        r.eat()
      }
    }
  }
  ```

- to solve this, use `as` to perform ***explicit casting***:

**Using `as` to perform explicit casting:**

- When we want to access the behavior or an underlying object but the compiler can't perform smart casting, 
- We can explicitly cast the object into an appropriate type.
- Look more into as and casting



#### Updating the Animal Project:

```kotlin
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
```



![chapter_06_summary](/Users/Elomara/Documents/Android Journey/HeadFirstKotlin/Chapter06/chapter_06_summary.png)





















