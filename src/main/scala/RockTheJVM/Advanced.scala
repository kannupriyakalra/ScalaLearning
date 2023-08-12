package RockTheJVM



import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
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
it in a regular value. You can pattern match the try object against the 2 subtypes of try which are Success(value) and Failure(exception). They are in scala.util.{Failure, Success, Try}. Success
contains a valid value and failure contains an exception. So we use try to avoid defensiveness with Try Catches blocks instead we process try like we process normal values.
You can operate on Try like you would do on collections and use map, flatMap, filter.
   */



  //Future type
  /*
Its a medium to evaluate something on another thread
(asynchronous programming)
   */

val aFuture: Future[Int] = Future{
  println("Loading...")
  Thread.sleep(1000)
  println("I have computed a value")
  67
}

  Thread.sleep(2000)

}

  //future is a "collection" which contains a value when it's evaluated and not immediately, at the point when this code block is evaluated then Future will contain a value until then it does not contain any value.
  //future is composable with map, flatmap and filter.

  /*
 - Let me tell you how to evaluate something on another thread in other words asynchronous programming. This is done by another "pseudo collection" known as Future.
 - "import scala.concurrent.Future" this import is required to be able to use Future, else compiler will give error "cannot resolve symbol Future".
 - In order to run a future, we will need to import an execution context ie "import scala.concurrent.ExecutionContext.Implicits.global" , When you import this value compiler will stop giving the
   error 'No Implicits found for parameter executor: ExecutionContext' and is happy that this global value is available to run this future. The global value is equivalent of a thread pool ie a
   collection of threads on which we can schedule the evaluation of this expression. So when we run this expression we will see o/p as - "Loading..." only as the 'main' thread of the JVM ie the
   main thread of the application finished before this Future had the chance to evaluate ie a proof that this future block that I put here inside the constructor of the future was actually evaluated
   on another thread.
 - If you type "Thread.sleep(2000)" under the main jvm thread then this Future will also have the chance to evaluate and the o/p will come as
 "Loading..."
 "I have computed a value"

 -  Future in line 120 is equivalent to below Future.apply
    val aFuture = Future.apply({
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value")
    67
  })
  This code block is passed to the constructor of Future and this expression will be evaluated on another thread.
  In real life code bases we omit the paranthesis () over here as this block will be passed as an argument to Future s apply method.
  Future.apply() -- Starts an asynchronous computation and returns a Future instance with the result of that computation. The result becomes available once the asynchronous computation is completed.

- Monads- We spoke about "pseudo collection" in theoretical terms, Future, Try and Option types are called Monads in functional programming. Monads are a very touchy subject as they are very very
  abstract and hard to explain. We ll talk about them later, for now think of  Future, Try and Option as some sort of collection.

- difference b/w synchronous and asynchronous: https://blog.rockthejvm.com/sync,-async-and-(non)-blocking/#:~:text=In%20Scala%2C%20an%20asynchronous%20computation,be%20evaluated%20on%20another%20thread.&text=The%20value%20after%20the%20call,This%20is%20asynchronous.
-controllable future, promise- https://blog.rockthejvm.com/controllable-futures/

   */

/*
When you get Future object that means some background computation is ongoing. Future is used to implement multithreading in scala. Whatever computation that
you want to get done on another thread other than current ongoing thread main you ll send it in future. The content inside future will be run on another thread
and once you get the o/p of that, then you can do further map, flatmap on it but to do that first we ll have to wait for that thread to finish and give o/p to us.
The background thread on which the computation runs comes from the thread pool inside Execution Context.
If main method had some more instructions where it used the o/p of future then it would have waited for the background task aFuture to finish.
 */



  //Implicits basics 15:20 from video


