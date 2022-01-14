#### Object types are defined using Classes:

```kotlin
var x = 6
```

Creates an `Int` object with value `6`, and a reference to that object is assigned to a new variable named `x`.

A Class is: a template that defines what *properties* and *functions* are associated with objects of that type.



#### How to design your own Classes:

Things to consider: 

- The things each object knows about itself:
  - **Properties**
  - Represents the **state** of the object.
- The things each object can do:
  - **Functions**
  - Determine the object's  **behavior**.
  - May use the object properties.



#### Let's define a Dog Class:

The Dog class will be used to create other dog objects.

Class Properties: each dog will have a name, weight and breed.

Class function(s): each dog bark will be determined based on its weight.

```kotlin
class Dog(val name: String, var weight: Int, val breed: String) {
  
  fun bark(){
    println(if (weight < 20) "Yip!" else "Woof!")
  }
}
```



#### Creating Dog Objects: 

A class tells the compiler what properties each object will have.

Each object created from that class can have its own values.

For example: each Dog object will share the name, weight and breed properties with each dog object having their own values for those properties.

```kotlin
var myDog = Dog("Candy", 30, "Mix")
```

- Creates a Dog object
- arguments passed are used to to assign values to object properties
- reference to the new object is stored in a variable



#### How to access properties and functions:

Properties of a class can be accessed using the dot operator ( **.** )

```kotlin
println(myDog.name)
```



Properties values can also be updated if they were defined using `var` keyword.

**Note**: attempting to update `val` define properties will result in compiler error. 

```kotlin
myDog.weight = 40
```



The dot operator can also be used for calling object's functions:

```kotlin
myDog.bark()
```



#### What if the Dog is in a Dog array:

```kotlin
var dogs = arrayOf(Dog("Bubbles", 40, "Mixed"), Dog("Candy", 25, "Poodle"))
```

This creates an array of the type Dog `Array<Dog>` and populates it with `Dog` objects. Then stores a reference to the `Array<Dog>` in variable `dogs`.



###### We can access the properties and functions of each object int the array using its index:

`dogs[0].weight = 45`: This updates the first object's `weight` property.

`dogs[1].weight = 30`: This updates the second object's `weight` property.



#### The miracle of object creation

Declaring and assigning an object consists of three steps:

1. Declaring a variable:

   ```kotlin
   var myDog
   ```

2. Creating the object:

   ```kotlin
   Dog("Bubbles", 40, "Mix")
   ```

3. Linking the object to the variable by assigning its reference:

   ```kotlin
   var myDog = Dog("Bubbles", 40, "Mix")
   ```



#### How objects are created:

`Dog("Bubbles", 40, "Mix")` looks as if we're calling a `Dog` function and passing in arguments into the function. But its not!

A Constructor:

- a constructor contians the code needed to initialize an object.
- it runs **before** the object can be assigned to a reference.



#### What the Dog constructor looks like: 

```kotlin
class Dog(val name: String, var weight: Int, val breed: String)
```

`(val name: String, var weight: Int, val breed: String)`: is the`Dog` class constructor. Also known as **primary constructor**.

The Dog constructor defines three properties: name, weight and breed.

Each Dog will have these properties.

When Dog is created, the constructor will assign a value, **initialize**, to each property.



#### Behind the scenes: calling the Dog constructor (IMPORTANT)

```kotlin
var myDog = Dog("Bubbles", 40, "Mix")
```

1. The system creates an object for each argument passed into the constructor:
   -  String object with value "Bubbles"
   - Int object with value 40
   - String object with value "Mix"
2. The system allocates space for the new Dog object, and the constructor is called.
3. The Constructor defines three properties:
   - Behind the scnenes, a property is a variable!
   - A variable of the appropriate type is created for each property.
4. Each Dog's property variable is assigned a reference the appropriate value object.
   - name property is assigned a reference to the "Bubbles" String object, and so on.
5. Reference to the Dog object is assigned to a new Dog variable named `myDog`.

**REMEMBER**: a property is a variable that is local to the class. This means that everything that applies to variables applies to properties as well.

Objects are sometimes referred to as *instances of a class* and properties as *instance variables*.



#### Going deeper into properties

Our Dog constructor defines three properties *name, weight and breed* for each `Dog` object and assignes values to each property (**initialize**) when the constructor is called.

This is done so *conciesly* because the constructor code uses a shortcut for performing this task.

The same code without the shortcut would look like this:

```kotlin
class Dog(name_param: String, weight_param: Int, breed_param: String) {
  val name = name_param
  var weight = weight_param
  val breed = breed_param
}
```

`(name_param: String, weight_param: Int, breed_param: String)`: 

- constructor parameters no longer have `val` or `var` prefixes.
- means they no longer define properties.

instead, the *name, weight and breed* properties are defined inside the function body.

Each one is assigned the value of the assosiated constructor parameter.



#### **Q**: why would you want to define a property inside the class body?

1. If you want to assign a *default value to a property* without including it in the constructor.

   <u>Example</u>: adding `activities` property to the `Dog` class and *initializing it* with a default array containing the value "Walks".

   So, each `Dog` object created will have an `activities` property and its initial value will be an array containing the value "Walks" 

   ```kotlin
   class Dog(val name: String, var weight: Int, breed_param: String) {
     var activities = arrayOf("Walks")
   }
   ```

2. If you want to tweak the value of a constructor parameter (`breed_param`) *before* assigning it to the property (`breed`):

   <u>Example</u>: updating the breed value being passed into the constructor into an uppercase.

   ```kotlin
   class Dog(val name: String, var weight: Int, breed_param: String) {
     val breed = breed_param.toUpperCase() // toUpperCase deprecated: lookup the alternative
   }
   ```

   This works fine if you want to assign a simple value or an expression.



If you want to:

- initialize a property to something more complex than a simple expression, or
- want extra code to run when each object is created, we can then use one or more **initializer blocks**.

#### How to use Initializer blocks

Initializer blocks:

- exectued when the object is initialized, **immediately after the constructor is called**. 
- prefixed with `init` keyword.

```kotlin
class Dog(val name: String, var weight: Int, breed_param: String) {
  var activities = arrayOf("Walks")
  val breed = breed_param.toUpperCase()
}
```



A class can have multiple initializer blocks.

Each runs in the order in which it appears in the class body.

```kotlin
class Dog(val name: String, var weight: Int, breed_param: String) {
  init {
    println("Dog $name has been created.")
  }
  
  var activities = arrayOf("Walks")
  var breed = breed_param.toUpperCase()
  init {
    println("updated breed value is: $breed")
    // can i print the value of breed_parm? play around with this to better understnd the concept
  }
}
```



**NOTE**: 

- all properties must be initialized before using them
- if you're certain that you can't assign an initial value to a property when the constructor is called:
  - we can prefix it with `lateinit`: tells the compiler that we're aware it hasn't been initialized and we'll do it at a later stage. 
  - we've seen this in Android Development. 

```kotlin
class Dog(val name: String, var weight: Int, breed_param: String) {
  var activities = arrayOf("Walks")
  val breed = breed_param.toUpperCase()
  
  /*
   * van temperament = ""
   * or
   * lateinit var temperament
   */ 
  var temperament: String // this won't compile since the parameter isn't initialized
}
```



###### Empty constructors:

If we want to be able to create object quickly without passing values for any properties, we can define a class with no consturctor:

```kotlin
class Duck { // there's not () after the class name, so the class has no defined constructor
  fun quack() {
    println("Quack!")
  }
}
```

When a class is defined without a constructor the compiler however writes one for you!

It adds and empty constructor to your compiled code.

Therefore, in order for us to create a `Duck` object, we use the code:

```kotlin
var myDuck = Duck() // var myDuck = Duck won't compile
```

 

#### How to you validate property values?

Allowing direct access to our properties without a form of sanitization is a bad practice. 

That's why, we must **validate** the values before assigning them to our properties.



#### The solutions: custom getters and setters

They allow us to get and set property values.

<u>Getter</u>: sends back a return value (value of whatever it is that particular getter is supposed to be getting)

<u>Setter</u>: take an argument value, and use it to the set the value of a property

Custom getters and setters:

- protects our property values, and
- gives us more control over what values are returned or assigned.



#### How to write a custom getter

Example: writing a custom getter to return the Dog's weight in kilograms

Two things to perform this: 

​	(1) add new property to the Dog class named `weightInKgs` .

​	(2) write custom getter which will *return* the appropriate value.

```kotlin
class Dog(val name: String, var weight: Int, breed_param: String) { 
  var activities = arrayOf("Walks")
  val breed = breed_param.toUpperCase()
  
  val weightInKgs: Double
			get() = weight / 2.2 // try using field instead of weight: Won't work. google why
}
```

`get() = weight / 2.2`: this line defines the getter.

It's a no parameter function named `get` that you add to the property `weightInKgs` by writing it immediately below the property decleration.

Its return type **must** match that of the property whose value you want to return. else, code won't compile.

Note: we didn't have to initialize `weightInKgs` property because it's value is being derived in the *getter*.



#### How to write a custom setter

Example: adding custom setter to the `weight` property so that it can only be updated to a value greater than 0. Two things to perform this:

​	(1) the weight property definition should be in the class body rather than the constructor.

​	(2) adding setter to the property.

```kotlin
class Dog(val name: String, weight_param: Int, breed_param: String) { 
  var weight = weight_param
  		set(value) {
        if (value > 0) field = value
      }
  
```

Setter is a function named `set` that's added to the property by writing it beneath the property declerationo.

Setter usually has one parameter named ***value***: which is the new proposed value of the property. In our case the value is the value parameter `weight_param`.

in the example: the value of the `weight` property is only updated if the *value parameter* is greater than zero.

**Important**: the setter updates the value of the `weight` property by means of the ***field*** identifier.

*field*: refers to the property's backing field.

Using *field* in our getter and setters in place of the property name is important, as it stops us from getting stuck in an endless loop.

```kotlin
class Dog(val name: String, weight_param: Int, breed_param: String) { 
  var weight = weight_param
  		set(value) {
        if (value > 0) weight = value // this will result in an endless loop ? Google it
      }
```

 

###### Data hiding up close:

**Remember**:

- custom **getters** lets you **control what value is returned** when the property value is requested. 
- custom **setters** lets you **validate a value before assigning** it to a property.

Behind the scenes, the compiler automatically creats getters and setters for all properties that already don't have one:

- if a property is defined with val: the compiler adds a getter.
- if a property is defined with var: the compiler adds both a getter and a setter.

So, when writing:

```kotlin
var myProperty: String
```

The compiler adds the following when the code is compiled:

```kotlin
var myProperty: String
		get() = field
		set(value) {
      field = value
    }
```





The full code for the Dogs project:

```kotlin
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

/*
 * 					The Output // notice the order
 *
 * Dog Bubbles has been created!
 * The breed is MIXED
 * Woof!
 * Weight in kgs is 34.090909090909086
 * Weight is 75
 * My Dog Bubbles enjoys Walks
 * My Dog Bubbles enjoys Fetching sticks
 * My Dog Bubbles enjoys Frisbee
 * Dog Candy has been created!
 * The breed is WESTIE
 * Dog Ripper has been created!
 * The breed is POODLE
 * Yip!
 * Weight for Ripper is 15
 */
```



![Screen Shot 2022-01-11 at 2.43.57 PM](/Users/Elomara/Desktop/Android Journey/Head First Kotlin/Chap04/Screen Shot 2022-01-11 at 2.43.57 PM.png)