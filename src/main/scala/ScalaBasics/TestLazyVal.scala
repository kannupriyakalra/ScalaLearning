package ScalaBasics

object TestLazyVal extends App {

  // Learn more about Scala on https://leobenkel.com
  println("-")
  println("Starting")

  lazy val thisLazyVal: Int = { //lazy val is assigned when called
    println("this lazy val")
    10
  }

  def thisMethod: Int = {
    println("this method")
    5
  }

  val thisValue: Int = { //val once assigned wont be assigned again
    println("this value")
    20
  }

  println("-")
  println("Testing method")
  assert(thisMethod + thisMethod == 10)

  println("-")
  println("Testing Lazy val")
  assert(thisLazyVal + thisLazyVal == 20)

  println("-")
  println("Testing val")
  assert(thisValue + thisValue == 40)

  println("-")
  println("Congratulations ! Stay focused on your journey to greatness !")


}

/*
Scala difference between val, lazy val and def-

Try to figure out by yourself what lazy does. Make sure to read the print statements in the console as you run the code.

Did you figure it out ?

The keyword lazy allows a value to not be evaluated until the very first time it is called. If you call the value again, the previously computed value will be used.

With val the value is computed instantly as the value is declared. You were able to see the print statement early in the log, before all the others.

And for def, this is a method, so each time you call the method, you are recomputing the result.

Summary:

val: Computed once when declared
lazy val: Computed once when called
def: Computed each time it is called
 */
