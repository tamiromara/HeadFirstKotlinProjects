The compiler can infer the variable type from the value assigned to it.

How does this happen: the value is transformed into an *object.*:

- ```kotlin
  var x = 5
  ```

- the value `5` is used to create a new object. 

  - the compiler knows that `5` is an `Int`

  - so, the code creates a new `Int` object with a value of `5`

  - the compiler then uses the *<u>type of the object</u>* to **infere** the <u>*type of the varaible*</u>.

  - **REMEMBER**: ***The variable stays this type forever!***

  - ```kotlin
    var x = 10			// infered Int type
    x = "String"		// illegal operation 
    // Erros: Type mismatch: inferred type is String but Int was expected
    ```

- When an object is assigned to a varaible, the object itself doesn't go into the variable. 

- instead, a *reference to the object* is stored into the variable. 

  - if variable is declared with keyword `var`, the reference to the object **can be replaced** by another value **of the same *type*.** 

  - ```kotlin
    var x = 5
    x = 9 // legal operation as long as its the same type
    ```

    `x = 9` : creates a new object of the type `Int` with the value `9` and they updates/replaces the reference inside the varaible `x`. 
    
    
    
  - if variable is declared with keyword `val`, the reference to the object stays in the variable forever.
  
  - ```kotlin
    val x = 5
    x = 9		// illegal operation. 
    // Error: Val cannot be reassigned
    ```
  
    

### Kotlin Basic Types

Four basic Integer type: **Byte**, **Short**, **Int**, **Long**

#### <u>Integers</u>: 

- Each type holds a fixed number of bits.

  | Type  | Bits |        Value range        |
  | ----- | :--: | :-----------------------: |
  | Byte  |  8   |        -128 to 127        |
  | Short |  16  |      -32768 to 32767      |
  | Int   |  32  | -2147483648 to 2147483647 |
  | Long  |  64  |    -huge to (huge - 1)    |

- By default: if you declare a variable by assigning an integer to it: `var x = 4` : 
  - it creates an object and a varaible of the type `Int`.
  - if the value of `x` exceed the limit of `Int`, the compiler will automatically use `Long` instea: `var x = 2_147_483_700`
  - or we can manually create a `Long` object and varaible by appending the letter `L` to the end of the integer: `x = 9L`



#### <u>Floating Points</u>:

- Two basic floating-point types: **Float** and **Double**:

  | Type   | Bits |        Value range        |
  | ------ | :--: | :-----------------------: |
  | Float  |  32  | -2147483648 to 2147483647 |
  | Double |  64  |    -huge to (huge - 1)    |

- By default, if you declare a varaible by assigning a float-point to it: `var x = 10.5` :

  - it creates an object and a variable of the type `Double`. 
  -  appending `F` or `f` to the end of the integer, a `Float` object will be created instead: `var x = 10.5F` 



#### <u>Characters & Strings:</u> 

- `char` variables used to hold a single character: `var letter: Char = 'd'`
- `String` variables used to hold multiple characters strung together: `var phrase: String = "Hello, World!"`



**Notes:**

-  in Java numbers are primitaves. So a variable holds the actual number. 
- Kotlin on the otherhand, numbers are objects and the variable holds a reference to the object, not the object itself.
- in Java `Char` can be treated as numbers. in Kotlin you can't!



#### <u>Assigning a value to another variable</u>:

- When assigning a value to another variable, they must be of the same type.

  ```kotlin
  var x = 10
  var y = x
  var z: Long = x	// illegal operation since x is Int and z is Long
  ```

- `var x = 10` creates an `Int` object with the value `10` and stores the reference to that object in the variable `x`.

- `var y = x` since both are of the same *type*, the value of variable `x` (the reference to the object with the value `10`) is assigned to the variable `y`.

- therefore, both variables reference the same object in memeory.



#### <u>Converting values from one type to another</u>:

- to assign a value of `Int` to a variable of `Long`, we have to convert the value to the right type.
- this conversion can be conducted using `Int`'s *functions*.
- objects has two things: *state* and *behavior*:
  - *state*: describes the data associated with the object: it's *<u>properties</u>* and *<u>values</u>*. 
  - *behavior*: describes the things an object can do, or can be done to it. 
  - the object's *behavior* is exposed through its *functions*.
- every numeric object has the function `.toLong()`



#### <u>When happens when converting a value from one type to another</u>:

- `var x = 4`: creates an `Int` variable `x` which holds the reference to the `Int` object with the value `4`.
- `var y: Long = x.toLongI()`: 
  - creates a `Long` variable `y`.
  - `.toLong()` function called on `x` which creates a new `Long` object with the value `4`.
  - reference to the `Long` object is placed in `y`.

***NOTE: the above works fine as long as the conversion is from smaller to larger value.***

- converting from `Long` to `Int` for example will work as long as the `Long` value is within the range of values of `Int`.

  ```kotlin
  var x = 42L
  var y: Int = x.toInt()
  ```

  

- if the `Long`'s value is too large for `Int`, then the compiler will chop up the value

- The compiler assumes this is deliberate, so the code compiles!

  ```kotlin
  var x = 1234567890123
  var y: Int = x.toInt() //Value is 1912276171!
  ```

  

- another example, when converting from `floating-point` to `Int`, the compiler will chop off everything after the decimal:

  ```kotlin
  var x = 123.456
  var y: Int = x.toInt() //Value is 123
  ```



#### <u>Storing multiple values in an array</u>:

- Array's are good if you want a **quick and dirtry** group of things.



```kotlin
var myArray = arrayOf(1, 2, 3)
```

- `arrayOf` creates an array with three `int` `items` and assigns the array to the variable `myArray`.
- We can access the array values by referencing the array variable with an index: `myArray[0]`
- Get the size of the array: `myArray.size`



```kotlin
fun main() {
  val wordArray1 = arrayOf("24/7", "multi-tier", "B-to-B", "dynamic", "pervasive") 
  val wordArray2 = arrayOf("empowered", "leveraged", "aligned", "targeted")
  val wordArray3 = arrayOf("process", "paradigm", "solution", "portal", "vision")
  
  val arraySize1 = wordArray1.size 
  val arraySize2 = wordArray2.size 
  val arraySize3 = wordArray3.size
  
  val rand1 = (Math.random() * arraySize1).toInt() 
  val rand2 = (Math.random() * arraySize2).toInt()
  val rand3 = (Math.random() * arraySize3).toInt()
  val phrase = "${wordArray1[rand1]} ${wordArray2[rand2]} ${wordArray3[rand3]}"
  println(phrase) 
}
```

- `val rand1 = (Math.random() * arraySize1).toInt()`:

  - `Math.random()`: generates a random number between 0 to **almost** 1.

  - multiply the result with our `arraySize1`.

  - then force the result to be an integer `toInt()`

- `val phrase = "${wordArray1[rand1]} ${wordArray2[rand2]} ${wordArray3[rand3]}"`:

  - uses **String template** to pick three words and put them together.

  

- **String Templates**:

  - provides a quick way of refering to a **variable** inside a **String**

  - to include the value inside a String we prefix the variable name with `$`

    ```kotlin
    var x = 10
    var value = "Value of x = $x"
    ```

  - We can use String templates to **refer to an object's properties** or **call a function**

    ```kotli
    var myArray = arrayOf(1, 2, 3)
    var arraySize = "myArray has ${arrayOf.size} itmes"
    var firstItem = "The first item in myArray = myArray[0]"
    ```

  - We can also use String templates to evaluate more complex expressions:

    ```kotlin
    var result = "myArray is ${if (myArray > 10) "large" else "small"}"
    ```

  

- **Infering the array's Type:**

  - Arrays hold items of the *same type*

  - Either allow the compiler to infer the type from the array's values, or

    ```kotlin
    var myArray = arrayOf(1, 2, 3)
    ```

  - **explicitly** define array type using `Array<type>`

    ```kotlin
    var myArray: Array<Byte> = arrayOf(1, 2, 3)
    // tells the compiler that we want to create an Array that holds Byte items.
    ```

  - to update an item within the array, it has to be of the same type as the array:

    ```kotlin
    var myArray = arrayOf(1, 2, 3)
    myArray[0] = 10 			// a legal operation
    myArray[0] = "10"			// an illegal operation, it won't compile
    ```

  - ##### REMEMBER: var `myArray` holds a *reference* to an array object, and the items within the array are *references* to byte objects that holds the byte values.

- ##### var and val effects when declalring arrays:

  - when a variable is declared with `var` , we can update the variable so that it holds a reference to a different variable **of the same type**.

  - `var myArray = arrayOf(1, 2, 3)`: creates an array of `Ints` and stores the reference to it in variable `myArray`

  - `myArray = arrayOf(4, 5)`: creates a new array of `Int's`. Reference to it is stored in varialbe `myArray`, replacing the previous reference.

  - unlike `var`, when declaring a variable with `val` it can't be reassigned to hold a reference to another object.

    ```kotlin
    val myArray = arrayOf(1, 2, 3)
    myArray = arrayOf(4, 4) // illegal operation since myArray declared as a `val`
    ```

  - the array itself, however, can be updated. We can still update the varaibles/items of the array.

    ```kotlin
    val myArray = arrayOf(1, 2, 3) 
    myArray[0] = 10
    ```

    

![Screen Shot 2021-12-28 at 4.36.27 PM](/Users/Elomara/Desktop/Android Journey/Screen Shot 2021-12-28 at 4.36.27 PM.png)





