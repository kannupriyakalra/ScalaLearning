package ScalaBasics

/*
The Try type represents a computation that may either result in an exception, or return a successfully computed value. Instances of Try[T], are either an instance of Success[T] or Failure[T].
For example, Try can be used to perform division on a user-defined input, without the need to do explicit exception-handling in all of the places that an exception might occur.

Go through Try.scala class.

flatMap- Returns the given function applied to the value from this `Success` or returns this if this is a `Failure`.
def flatMap[U](f: T => Try[U]): Try[U]

map- Maps the given function to the value from this `Success` or returns this if this is a `Failure`.
def map[U](f: T => U): Try[U]

final case class Success[+T](value: T) extends Try[T]
final case class Failure[+T](exception: Throwable) extends Try[T]

Errors are treated as data, they are not thrown. Exceptions are not thrown in scala, they are made into a object and sent to failure. This is why scala is safe language.
 */

import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object TestTry extends App{

  @tailrec
  def divide: Try[Int] = {
    val dividend: Try[Int] = Try(StdIn.readLine("Enter an Int that you'd like to divide:\n").toInt)
    val divisor: Try[Int] = Try(StdIn.readLine("Enter an Int that you'd like to divide by:\n").toInt)
    val problem: Try[Int] = dividend.flatMap(x => divisor.map(y => x/y))  //as you have to divide 2 try[Int] so do it via flatmap n map to be able to extract value of x and y,
    // divisor.map(10/0) --returns failure, 2nd approach is via pattern match, dividend match 2 cases success n failure and in success divisor match 2 cases success n failure
    problem match {
      case Success(v) =>
        println("Result of " + dividend.get + "/"+ divisor.get +" is: " + v)
        Success(v)
      case Failure(e) =>
        println("You must've divided by zero or entered something that's not an Int. Try again!")
        println("Info from the exception: " + e.getMessage)
        divide //if you put in wrong i/p then call divide again so you are given a second chance.
    }
  }

  divide

  /*
  o/p- Enter an Int that you'd like to divide:
10
Enter an Int that you'd like to divide by:
2
Result of 10/2 is: 5


Enter an Int that you'd like to divide:
a
Enter an Int that you'd like to divide by:
b
You must've divided by zero or entered something that's not an Int. Try again!
Info from the exception: For input string: "a"
Enter an Int that you'd like to divide: //again divide is called here


Enter an Int that you'd like to divide:
15
Enter an Int that you'd like to divide by:
0
You must've divided by zero or entered something that's not an Int. Try again!
Info from the exception: / by zero
Enter an Int that you'd like to divide:
   */

}
