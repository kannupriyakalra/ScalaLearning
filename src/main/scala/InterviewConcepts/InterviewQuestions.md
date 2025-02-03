1. What is the difference between a var, a val and def?

In Scala, there are three ways of defining things: val, var, and def. Here are the main differences between them:

•val defines a *fixed value* that cannot be modified after declaration. It is similar to a final variable in Java. For example: val x = 10
•var defines a *variable* that can be modified after declaration. It is similar to a regular variable in Java. For example: var y = 20
•def defines a *method* that can take parameters and return a value. It is similar to a method in Java. For example: def add(a: Int, b: Int) = a + b

One of the benefits of using val over var is that it makes the code more *immutable* and *functional*, which can prevent unwanted side effects and improve readability. However, sometimes var is necessary for certain situations, such as updating a counter or a buffer.

One of the benefits of using def over val or var is that it allows for *lazy evaluation* and *recursion*, which can improve performance and expressiveness. However, sometimes val or var is more convenient for simple expressions or constants.



In Scala, `var`, `val`, and `def` are used to declare variables and methods, but they serve different purposes and have distinct characteristics.

1. **`var` (Variable):**
  - `var` is used to declare mutable variables, meaning their values can be changed or reassigned.
  - Example:
    ```scala
    var x = 10
    x = 20 // Valid, since x is mutable
    ```

2. **`val` (Value):**
  - `val` is used to declare immutable variables, meaning their values cannot be changed once they are assigned.
  - Example:
    ```scala
    val y = 30
    // y = 40 // Error: Reassignment to val is not allowed
    ```

3. **`def` (Method/Function):**
  - `def` is used to define methods or functions in Scala. Methods are reusable blocks of code that can take parameters and return a value.
  - Example:
    ```scala
    def add(a: Int, b: Int): Int = {
      a + b
    }
    ```

In summary:
- Use `var` when you need a mutable variable whose value can change.
- Use `val` when you want an immutable variable with a constant value.
- Use `def` when you want to define a method or function.

Here's a quick comparison:

| Keyword | Mutability | Scope | Purpose                                |
|---------|------------|-------|----------------------------------------|
| `var`   | Mutable    | Local | Declare mutable variables             |
| `val`   | Immutable  | Local | Declare immutable variables           |
| `def`   | N/A        | Local | Define methods or functions           |

It's worth noting that Scala encourages the use of `val` and immutable data structures whenever possible, as immutability can help in writing safer and more functional code.


In Scala, there are three ways of defining things: `val`, `var`, and `def`. Here are the main differences between them:

- `val` defines a **fixed value** that cannot be modified after declaration. It is similar to a `final` variable in Java. For example: `val x = 10`
- `var` defines a **variable** that can be modified after declaration. It is similar to a regular variable in Java. For example: `var y = 20`
- `def` defines a **method** that can take parameters and return a value. It is similar to a method in Java. For example: `def add(a: Int, b: Int) = a + b`

One of the benefits of using `val` over `var` is that it makes the code more **immutable** and **functional**, which can prevent unwanted side effects and improve readability. However, sometimes `var` is necessary for certain situations, such as updating a counter or a buffer.

One of the benefits of using `def` over `val` or `var` is that it allows for **lazy evaluation** and **recursion**, which can improve performance and expressiveness. However, sometimes `val` or `var` is more convenient for simple expressions or constants.

For more information on Scala's core features, you can refer to this [web page](^1^) or this [Stack Overflow question](^2^)..

Source: Conversation with Bing, 18/1/2024
(1) Def, Var & Val in Scala | Baeldung on Scala. https://www.baeldung.com/scala/def-var-val.
(2) What is the difference between a var and val definition in Scala?. https://stackoverflow.com/questions/1791408/what-is-the-difference-between-a-var-and-val-definition-in-scala.
(3) Use of def, val, and var in scala - Stack Overflow. https://stackoverflow.com/questions/4437373/use-of-def-val-and-var-in-scala.
(4) Scala: The Differences Between `val` and `def` When Creating Functions .... https://alvinalexander.com/scala/fp-book-diffs-val-def-scala-functions/.
(5) en.wikipedia.org. https://en.wikipedia.org/wiki/Scala_(programming_language).



GetActivationAppointmentsV2Endpoint.scala in this the companion object and companion class- is the companion object here a singleton object?
-

#HELP
-does this usage of companion object and companion class have a design pattern name? 
-/Users/kkl07/dev/product-orchestration/src/main/scala/com/nowtv/pav/po/Application.scala this is which design pattern?

3. What is a case class?

4. Is the implementation of .equals different in case of a case class and a normal class in scala?

Yes, the default implementation of the `equals` method differs between case classes and regular classes in Scala.

### Case Classes:

1. **Structural Equality by Default:**
    - Case classes automatically generate an `equals` method that compares the values of their fields, not their references.
    - Two case class instances with the same field values are considered equal by default.

2. **Automatically Generated `equals` and `hashCode`:**
    - Case classes also automatically generate `hashCode` methods based on the values of their fields.
    - This is convenient for using case classes in collections like sets and maps.

3. **Pattern Matching in `equals`:**
    - The case class `equals` method uses pattern matching internally to compare fields.

   ```scala
   case class Point(x: Int, y: Int)

   val point1 = Point(1, 2)
   val point2 = Point(1, 2)

   println(point1 == point2)  // true
   ```

### Regular Classes:

1. **Reference Equality by Default:**
    - Regular classes, by default, inherit the `equals` method from the `Object` class, which performs reference equality.
    - Two instances are equal only if they reference the same object in memory.

2. **Manual Implementation Required for Value-Based Equality:**
    - If you want to perform a value-based equality check for regular classes, you need to manually override the `equals` method and possibly the `hashCode` method.

   ```scala
   class Point(val x: Int, val y: Int) {
     override def equals(obj: Any): Boolean = {
       obj match {
         case other: Point => this.x == other.x && this.y == other.y
         case _ => false
       }
     }

     override def hashCode(): Int = {
       // Implement hashCode based on fields
       // ...
     }
   }
   ```

In summary, the implementation of `equals` is different in the sense that case classes automatically provide value-based equality based on their fields, while regular classes require manual implementation for such equality checks.


5. What is the difference between abstract class and trait in scala? TODO

6.ADT what is algebraic data type? give an example