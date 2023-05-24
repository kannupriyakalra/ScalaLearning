package RockTheJVM

import scala.util.{Failure, Success, Try}

/*
Topics-
1. Lazy Evaluation
2. Option type
3. Try type
4. Future type
5. Implicit
*/

object Advanced extends App {

  //Lazy Evaluation
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy")
    43
  }

  val eagerValue = lazyValueWithSideEffect + 1 //o/p- I am so very lazy

  /*
  Lazy Evaluation- means an expression is not evaluated until its first use. It is used in infinite collections.
  Number 2 is not associated with aLazyValue until its used for the first time. This can be illustrated with a side effect. When you run below code, nothing will be printed on the console except process
  finished with exit code 0.
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy")
    43
  }
  If you remove the lazy modifier from "lazyValueWithSideEffect" and run the application, you will see "I am so very lazy" printed as this code block was then evaluated and attributed to
  lazyValueWithSideEffect value.

  val eagerValue = lazyValueWithSideEffect + 1 --even though i am not printing something to console myself here because i am using lazyValueWithSideEffect, its code block will actually get evaluated and
  so when we run this application "I am so very lazy" will be printed as lazyValueWithSideEffect was evaluated when it was used here in other value.
   */



  //Option type
  def methodWhichCanReturnNull(): String = "hello, scala"

  val anOption = Option(methodWhichCanReturnNull()) //Some("hello, scala")
  //option = "pseudo collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained Nothing"
  }
  println(stringProcessing) //o/p- I have obtained a valid string: hello, scala

  /*
  "pseudo collections" : Option, Try, future , they are not collections, they are their own types but you can think of them as a collection.
  Option and Try are very useful in some use cases in large code bases when you have unsafe methods. I have defined an unsafe method methodWhichCanReturnNull() where we have declared it returns a string
  but it might return a string or a null. In large code bases when you don't have the time or you cannot read the implementation of methodWhichCanReturnNull() to guard yourself against nulls,
  you have to write code like below in languages like cpp, java because null pointer exceptions or segmentation faults can actually wreck havoc through our code.

  if(methodWhichCanReturnNull() == null){
  //defensive code against null
  }

  In scala we don't have to write code like this above if we use Option type. Example- We applied Option to methodWhichCanReturnNull(). Option is "pseudo collection" which contains at most one
  element: Some(value) or None. If this method returns a valid value then Option will contain the value inside and ie Some("hello, scala"). Some- subtype of Option abstract type. If this method
  actually returns a null, then the value inside Option will be None. None- is a singleton object, it is the equivalent of null except this is a regular value so there is no risk in accessing
  illegal members or methods. Notice that there are no null checks here that you would normally add for defensive code in other programming languages, all you have to do is to wrap your unsafe code
  in an option and then do a pattern match on it.
  You can operate on Options like you would do on collections and use map, flatMap, filter.
  Option type guards against needing to check for nulls.

   */



  //Try type
  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())
  //Try- A pseudo collection with either a value if the code went well or an exception if the code threw one.

  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(ex) => s"I have obtained an exception: $ex"
  }
  println(anotherStringProcessing) //o/p- I have obtained an exception: java.lang.RuntimeException

  /*
Try type- It is also a pseudo collection. It guards against unsafe methods which can throw exception. Example- methodWhichCanThrowException() , we are declaring that it throws a string but it is
throwing RuntimeException. Exceptions are really bad for JVM programs like scala or java programs as they interrupt and break everything. So in languages like java, we guard against exceptions with
Try Catch code below:

try{
methodWhichCanThrowException()
}
catch{
case e: Exception => "defend against this evil exception"
}

Adding many layers of this try catch code like above will lead to very defensive code adding complexity to large code bases and making code almost unreadable which is why scala uses the Try pseudo
collection from scala.util. val aTry = Try(methodWhichCanThrowException()) -- Try can wrap something like a method which can throw an exception. This "Try(methodWhichCanThrowException())" is a try
object containing either a String if the method runs correctly or it contains the exception that was thrown by the method. So Try will essentially swallow the exception that might be thrown and wrap
it in a regular value. You can pattern match the try object against the 2 subtypes of try which are Success(value) and Failure(exception). They are in scala.util.{Failure, Success, Try}. Success contains
a valid value and failure contains an exception. So we use try to avoid defensiveness with Try Catches blocks instead we process try like we process normal values.
You can operate on Try like you would do on collections and use map, flatMap, filter.
   */


}
