//https://www.scala-exercises.org/std_lib/options
package ScalaExercises

object TestOptionScalaExercise extends App{

  def maybeItWillReturnSomething(flag: Boolean): Option[String] = {
    if (flag) Some("Found value") else None
  }

  //Using getOrElse, we can extract the value if it exists, or return a provided default value. If we have a Some(x) we return x, and for None we return the default value.

  val value1 = maybeItWillReturnSomething(true)
  val value2 = maybeItWillReturnSomething(false)

  println(value1 getOrElse "No value")
   // o/p- "Found value"

  /*Any method which takes a single parameter can be used as an infix operator: a.m(b) can also be written as a m b.
  value1 getOrElse "No value" is equivalent to value1.getOrElse("No value")
   */

  println(value2 getOrElse "No value")
   // o/p-"No value"

  println(value2 getOrElse {
    "default function"
  } ) //o/p- "default function"


  //fold operation will extract the value from the option, or provide a default if the value is None

  val number: Option[Int] = Some(3)
  val noNumber: Option[Int] = None
  val result1 = number.fold(1)(_ * 3)
  val result2 = noNumber.fold(1)(_ * 3)

  println(result1) //o/p-9
  println(result2) //o/p-1

  /*
  @inline final def fold[B](ifEmpty: => B)(f: A => B): B =
    if (isEmpty) ifEmpty else f(this.get)

 Returns the result of applying f to this Option's value if the Option is nonempty. Otherwise, evaluates expression ifEmpty.
  This is equivalent to:
  option match {
    case Some(x) => f(x)
    case None    => ifEmpty
  }

    isEmpty Returns true if the option is None, false otherwise.
    (ifEmpty: => B) is function zero, gives default value, ie for no i/p o/p is this default value B
    f(this.get)- get the value inside Some and apply function f on it.
   */

}
