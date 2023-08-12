import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TestFuture extends App {

  val f1: Future[Int] = Future { //creating object of Future which implicitly takes a thread from Execution context which is a thread pool and gives that thread the task we have written in {...}
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value")
    5
  }

  val f2 = f1.map(a => a * 2) // map - Creates a new future by applying a function to the successful result of this future. f2 runs in a spearate new thread.
  println(f2) //Future(<not completed>), it is still executing when we printed.


  Thread.sleep(5000) //so main thread doesn't finish before future is completed.
  println(f2) // Future(Success(10))-- by this time future f2 got computed

  /*
  o/p-

  Loading...
  Future(<not completed>)
  I have computed a value
  Future(Success(10))

  We printed f2 even before f1 got completely executed.
   */

  val f3 = f2.flatMap(a => Future { //jab f2 k andar ki value mil jaygi thode der baad tab uspe chalega, jab int mil jaye on that call flatMap. f3 is a new thread getting made, as flatmap also has execution context in argument
    println("Inside Future f3")
    Thread.sleep(1000)
    2
  })

  Thread.sleep(5000)
  println(f3)
  /*
  o/p-
  Inside Future f3
  Future(Success(2))

  o/p when i comment Thread.sleep(5000) in line 17 after f2-
  Loading...
  Future(<not completed>)
  Future(<not completed>)
  I have computed a value
  Inside Future f3
  Future(Success(2))

  Conclusion: we have to stop main thread from finishing so our 3 background thread can finish.
   */

  f3.foreach(println) //function sent inside foreach will run on the o/p of f3 future, we passed the value computed inside f3 in println. Future has eventually one element when its computed.
 // f3.foreach(x => println) equivalent to line 55
  //foreach- Asynchronously processes the value in the future once the value becomes available. foreach runs the function sent on the value inside future.
  //o/p- 2

  val s = "Hello"
  val f: Future[String] = Future {
    s + " future!"
  }
  f foreach {  //equivalent to f.foreach, this is infix syntax (1 + 2 ), prefix (+12), postfix(12+), command click foreach its inside Future, ie when future f will complete n available successfuly,
    // then on its result foreach will be called, its like attaching a callback to future f after completion, no matter how much time it will take to complete, after that foreach will be called.
    msg => println(msg)
  }//o/p- Hello future!
//  def foreach[U](f: T => U)(implicit executor: ExecutionContext): Unit = onComplete { _ foreach f } , T is of type future when hovering is done on function signature, here in our example T is string
  //  , U is unit, as println s return type is unit, (f: T => U) will run on a separate thread and that thread will come from thread pool inside execution context, execution context creates a thread pool
  //and those threads are reused when they are free from execution. only see type signature and don't see how foreach is implemented in documentation.


//equivalent to above code
  val m: Future[String] = Future.apply( { //this is function0 ie no i/p to 1 o/p, function0 s syntax is block of code.
    s + " future!"
  })
  m.foreach {
    msg => { //this is function1 1 i/p, 1o/p
      println(msg)
    }
  } //o/p- Hello future!

}

/*f1, f2, f3 are 3 different threads that executed from main. on f1 s o/p we executed f2(this will happen only when f1 is completed successfully), on f2 s o/p we executed f3. meanwhile we can go about doing
other things in main. Future is a concept to directly create thread and do operations on it, instead of creating Thread first using Runnable then tracking its value and doing operations on it.

A Future represents a value which may or may not *currently* be available, but will be available at some point, or an exception if that value could not be made available.
Asynchronous computations that yield futures are created with the Future.apply call and are computed using a supplied ExecutionContext, which can be backed by a Thread pool.



final def apply[T](body: => T)(implicit executor: ExecutionContext): Future[T]
Starts an asynchronous computation and returns a Future instance with the result of that computation.
The following expressions are equivalent:
val f1 = Future(expr)
val f2 = Future.unit.map(_ => expr)
val f3 = Future.unit.transform(_ => Success(expr))
The result becomes available once the asynchronous computation is completed.
Params:
body – the asynchronous computation
executor – the execution context on which the future is run
Type parameters:
T – the type of the result
Returns:
the Future holding the result of the computation

body is a function with no i/p and o/p T
this is a function0, body is of type function0
 */