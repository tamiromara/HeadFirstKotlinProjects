# <span style="color: #4A235A;">Data Classes</span>



Each time we use `==` operator, the `equals` function gets called.

Every Kotlin function has `equals` function and its *implementation* determines how it'll behave.

By default, `==` checks if the two variables are holding *reference to the <u>same underlying object</u>.*

`true`: when, and only when, both are referencing the same underlying object.

`false`: if they're holding references to different object (*even if objects hold identical property values*)

```kotlin
val w1 = Wolf("wolfi", 8)
val w2 = Wolf("wolfi", 8)
val w1 == w2		// despite both objects holding identical properties, == returns false
```

```kotlin
val w1 = Wolf()
val w2 = w1			// both objects are holding reference to the same underlying object
val w1 == w2		// evaluates to true
```

Every Kotlin function *inherits* `equals` from the superclass `Any`.

Every class we define is automatically a subclass of `Any`.

When defining a class like `class MyClass {}`, the compiler automatically makes the class a subclass of `Any` like:  `class MyClass: Any() {}`.



##### <span style="color: #7D3C98;">The importance of being a subclass of `Any`</span>:

Ensures every class inherits *important behaviors* that the system relies on.

Allows us to use *polymorphism* with any object. Since `Any` is a supertype to all classes, we can:

- Create functions with *parameters* of the type `Any`, or

- `Any` return type.

- Create *polymorphic arrays*, that holds object of the any type:

  ```kotlin
  // The compiler detects that objects share the same supertype 
  // therefore, it infers the array type as Array<Any>
  val myArray = arrayOf(Car(), Guitar(), Giraffe())
  ```



##### <span style="color: #7D3C98;">Common behaviors inherited by `Any`</span>:

- `equals(any: Any): Boolean`: compare that both variables hold reference to the same object.

- `hashCode(): Int`: returns a hash code for the object.

- `toString(): String`: returns a String message representing the object!

  

- `Any` function provides default implementations for these functions.

- They can be overridden to change their default behavior.



#### <span style="color: #7D3C98;">Data Classes</span>:

- Allows us to create *data objects* whose main purpose is to store data.
- It includes features that are helpful when dealing with data.

<span style="color: #7D3C98;">**How to create a data class**</span>: by prefixing `data` to a class header:

```kotlin
data class Recipe(val title: String, val isVegetarian: Boolean) { ... }
```

**<span style="color: #7D3C98;">How to create data class objects</span>:** the same way we create normal classes (by calling its *constructor*)

```kotlin
val r1 = Recipe("Chicken Bhuna", false)
```



Data classes *automatically overrides their equals functions*, so that `==` checks for *object equality based on values of each objects's properties*.

```kotlin
// this no longer evaluates to false because equals behavior is no longer concerned
// with the underlying objects, but rather the values of each object properties

val r1 = Recipe("Chicken Bhuna", false)
val r2 = Recipe("Chicken Bhuna", false)
r1 == r2
```



**<span style="color: #7D3C98;">Data classes override their inherited behavior</span>:**

Data class automatically provides the implementation for `equals, hashCode & toString` inherited functions.

**`equals()`:**

Continues to return `true` when variables hold reference to the same underlying object.

Additionally, returns `true` if objects have *identical values for the properties defined in the constructor*.

```kotlin
val r1 = Recipe("Chicken Bhuna", false)
val r2 = Recipe("Chicken Bhuna", false)
println(r1.equals(r2))	//true	//same as r1 == r2
```

**`hashCode()`:** 

If two *data objects* considered equal (same property values), `hashCode` function will return the same value for each data object.

```kotlin
val r1 = Recipe("Chicken Bhuna", false)
val r2 = Recipe("Chicken Bhuna", false)
println(r1.hashCode())	//241131113
println(r2.hashCode())	//241131113
```

**`toString()`:** now returns a String of the *values of each property defined in the data class constructor*: 

```kotlin
val r1 = Recipe("Chicken Bhuna", false)
println(r1.toString())	//Recipe(title=Chicken Bhuna, isVegetarian=false)
```



**<span style="color: #7D3C98;">Copying data objects with `copy`</span>:** allows us to copy an object, alter some of its *properties* and leave the rest intact.

Works by calling the `copy` function on the object:

```kotlin
val r1 = Recipe("Chicken Bhuna", false)
val r2 = r1.copy(isVegetarian = true)	// creates a copy of r1 and alters the isVegetarian property's value to true.
```



#### <span style="color: #7D3C98;">Deconstructing Data Objects</span>:

**<span style="color: #7D3C98;">ComponentN functions `copy`</span>:**  

When creating a *data class*, the compiler adds a number of *functions* to our class that we can use as an *<u>alternate way for accessing the object's property values</u>*.

They're known as *ComponentN*: where *N* represents the property number whose value we which to access.

```kotlin
val r = Recipe("Chicken Bhuna", false)
val title = r.component1()	//returns a *reference* held by the *first property* defined in the data class constructor.
// val title = r.title	//same can achieved with this
```

The above two lines can be achieved in a more *concise* way:

```kotlin
val (title, vegetarian) = r	//Create two variables title & vegetarian and assign one of `r`'s properties to each one
```



### `===`

1. First, checks if both variables are of the *same class* ? // TODO verify
2. Checks if both variables are referencing the same underlying object (checks for the *objects identity*).
3. *Always* behaves in the same way irrespective of the type of class.
4. Unlike the `==` operator, it doesn't rely of `equals` function for its behavior.

### `==`

1. First, checks if both variables are of the *same class* ? // TODO verify
2. Checks if the property's values of both objects are the same (checks for *object relevance*)
3. Behavior in data classes overrides default behavior in normal classes.
4. Relies on `equals` function for implementing is behavior.



Generated Functions:

Just like normal classes, we can define properties and functions to a data class by including them in the *class body*.

**The catch**: When the compiler *generates* *implementations* for data class functions (such as overriding *equals* function or creating *copy* function), **it will ONLY include the properties defined in the primary constructor.**

**By default**, any properties or functions defined inside the data class body won't be included in any of the generated functions.

```kotlin
// mainIngredient property is defined and initilized inside the data class body
data class Recipe(val title: String, val isVegetarian: Boolean) {
  var mainIngredient = ""
}
```

```kotlin
// despite updating the mainIngredient property in both objects, equals functions doens't include them in it's comparison
val r1 = Recipe("Thai Curry", false)
r1.mainIngredient = "Chicken"

val r2 = Recipe("Thai Curry", false)
r2.mainIngredient = "Duck"

r1 == r2	// true, equals is only concerned with properties defined in the primary constructor.
```



Default Parameters:

If the constructor defines allot of properties, we can simplify calls to it be *assigning default value or expression* to one or more properties definitions in the primary constructor.

```kotlin
data class Recipe(val title: String, val mainIngredient: String, val isVegetarian: Boolean = false, val difficulty: String = "easy") {...}
```

Two ways for calling constructors with default properties:

1) Passing values in order of declaration:

   ```kotlin
   // This assigns Spaghetti Bolognese and Beef to properties title and mainIngredient
   // then uses the default values provided in the primary constructor for the remaining properties
   val r = Recipe("Spaghetti Bolognese", "Beef")	// values must be passed in order
   ```

2) Named arguments:

   Allows us to explicitly state which property should be assigned which value.

   ```kotlin
   ```

   



















