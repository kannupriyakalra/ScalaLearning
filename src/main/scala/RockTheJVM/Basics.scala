package RockTheJVM

object Basics extends App { //extends App means everything ie written in {} is executable as a standalone application

  //defining a value
  val meaningOfLife: Int = 42 // const int meaningOfLife = 42;
  // in scala, we work with values, they are synonyms to constant of other languages like java, it means they cannot be reassigned
  // meaningOfLife = 43 reassignment to val is illegal

  val aBoolean = false //type mentioning is optional mostly but sometimes there are exceptions.
  //even when type is not mentioned, its inferred

  //Int, Boolean, Char, Double, Float, String are some standard types in scala

  //strings and string operations
  val aString = "i love scala"
  val aComposedString = "i" + " " + "love" + " " + "scala"
  val anInterpolatedString = s"The meaning of life is $meaningOfLife" //inject another value inside a string

  // in scala, we have to think in terms of values and functions and right hand side of a value is an expression, unlike other languages like javascript, cpp, java, python where we think in terms of instructions
  // which are the things that computer does sequentially like do this do that increment this value. in scala we think in terms of values and composing these value to create new values.

  //expressions = structures that can be reduced to a value, if statement, code blocks is also an expression as it can be reduced to a value
  val anExpression = 2 + 3

  //if-expression
  val ifExpression = if (meaningOfLife > 43) 56 else 999
  val chainedIfExpression = { //this whole expression is reduced to a single value based on condition, so we are thinking in terms of values and not following instructions
    if (meaningOfLife > 43) 56
    else if (meaningOfLife < 0) -2
    else if (meaningOfLife > 999) 78
    else 0
  }

  //code blocks- in a code block we can define local value, function, classes, inner code block.
  val aCodeBlock = {
    //definitions
    val aLocalValue = 67
    //value of the block is the value of the last expression, at the end we have to return something. type of code black is type of last expression value ie int here
    aLocalValue + 3
  }

  //defining a function
  //def myFunction(x: Int, y: String): String = y + " " + x // y + " " + x this expression is the returned value of this function, we can use code block for larger functions as code block is
  // also an expression.
  /* Pure function- total(function works for all values of i/p type ie fxn should not throw any exception, eg divide function is not a pure fxn as for a/b it doesn't work for b = 0),
   deterministic(for every same i/p like 1, 2 for add answer is always 3), pure(no side effects)- tdp
 this is a pure function as its return type is not unit, it has no side effects. */
  def myFunction(x: Int, y: String): String = {
    y + " " + x
  }

  //recursive function - in scala, we don't use iteration or loops(you ll be fired for it), we use recursion
  def factorial(n: Int): Int = //it will return a product int from 1 to n.
    if (n <= 1) 1
    else n * factorial(n - 1)

  /*
  factorial(5) = 5 * factorial(4) = 5 * 24 = 120
  factorial(4) = 4 * factorial(3) = 4 * 6 = 24
  factorial(3) = 3 * factorial(2) = 3 * 2 = 6
  factorial(2) = 2 * factorial(1) = 2 * 1 = 2
  factorial(1) = 1
   */

  //the unit type- no meaningful value === "void" in other languages, its the return type for SIDE EFFECTS which are operations that have nothing to do with computing some meaningful information
  // and would rather just do something like println prints on screen and its return type is unit and does not do any meaningful computation, other examples of side effects are sending something on
  // server, showing something.
  // Everything that we wrote in this code was somehow related to a meaningful value and its type.

  println("i love scala") //System.out.println printf print Console.log  return void in other languages

  def myUnitReturningFunction(): Unit = {
    println(" i love myself ")
  }

  val theUnit: Unit = () //the only value that unit type contains is () and () is returned by every function returning unit and all side effects .Unit is a type and () is a object. Unit is a
  // singleton type ie it has one object, ANY has infinite objects, NOTHING has zero objects.
}
