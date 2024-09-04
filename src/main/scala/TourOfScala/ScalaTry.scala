package TourOfScala

import scala.annotation.tailrec

/*
//https://tourofscala.com/scala/try
Scala Try

One thing to know first is the concept of Exception. An Exception, in java and Scala, is when an error happen. It will stop the execution of the program and throw an Exception.

The way to manually trigger an Exception is with throw. The code will then spit out what is called a stack trace. The stack trace will display each line of code that was in the stack of operation when the Exception occurred. It is essential to know how to read those when fixing a bug in a software.

Sometimes, a code will trigger an unexpected error, not one you decide to trigger. For instance, with bad math or more commonly from a third party library like a database connection. The connection might fail or timeout, etc… And then you need to react from the error. Maybe it is a critical error and you will decide to let the program stop its execution. But sometimes, you might be able to recover, in the case of a database, you could retry until it works, or retries several times until it succeed.

Try is the way to handle Exception in Scala. It allows you to abstract the potential failure and use the same methods that Option has to manipulate the data that might or might not be there. But instead of None when the Option is empty, you get an Exception when it is not defined which would carry more information about the kind of failure that was encountered. Like Option you can use map, flatMap,get, getOrElse and more.

Go back to the editor and try to make some code using map and flatMap with Try.
 */
object ScalaTry extends App{

  // comment it out ( start the line with '//')
  //throw new Exception("Something is broken")

  //val badBadMath = 5 / 0

  import scala.util.Random
  import scala.util.{Try, Success, Failure}

  val rand = new Random(0)

  val numerator: Int = 12

  def denominator(): Int = if (rand.nextBoolean()) 0 else 1

  def mightFail(): Try[Int] = Try(numerator / denominator())

  @tailrec
  def results(): Int = mightFail() match {
    case Success(v) => v
    case Failure(ex) =>
      println(s"It failed but we are trying again: $ex")
      results()
  }

  assert(results() == 12)

  def badMethod(): Try[Int] = Try(throw new Exception("Bad method"))

  val alternativeResults: Int = badMethod().getOrElse(8)

  assert(alternativeResults == 8)

  println("Congratulations ! Go beyond.")

}
