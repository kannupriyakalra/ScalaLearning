//https://alvinalexander.com/scala/concurrency-with-scala-futures-tutorials-examples/ code examples understood-

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
object TestFuture3 extends App{

  //Run one task, but block-shows how to create a future and then block to wait for its result.

  implicit val baseTime = System.currentTimeMillis

  val f = Future {
    sleep(500)
    1 + 1
  }

  //this is blocking (blocking is bad)- The Await.result method call declares that it will wait for up to one second for the Future to return. If the Future doesn’t return within that time,
  // it throws a java.util.concurrent.TimeoutException.
  val result = Await.result(f, 1 second)
  println(result) //o/p-2
  sleep(1000)

  def sleep(time: Long) { Thread.sleep(time) }

}

/*
Future definition-
A Future[T] is a container that runs a computation concurrently, and at some future time may return either (a) a result of type T or (b) an exception.
Computation of your algorithm starts at some nondeterministic time after the future is created, running on a thread assigned to it by the execution context.
The result of the computation becomes available once the future completes.
When it returns a result, a future is said to be completed. It may either be successfully completed, or failed.
As shown in the examples, a future provides an interface for reading the value that has been computed. This includes callback methods and other approaches, such as a for-comprehension, map, flatMap, etc.
An ExecutionContext executes a task it’s given. You can think of it as being like a thread pool.
The ExecutionContext.Implicits.global import statement shown in the examples imports the default global execution context.

Callback methods-
The following statements describe the use of the callback methods that can be used with futures:
Callback methods are called asynchronously when a future completes.
The callback methods onComplete, onSuccess, onFailure, are demonstrated in the Solution.
A callback method is executed by some thread, some time after the future is completed. From the Scala Futures documentation, “There is no guarantee that it will be called by the thread that completed the future or the thread that created the callback.”
The order in which callbacks are executed is not guaranteed.
onComplete takes a callback function of type Try[T] => U.
onSuccess and onFailure take partial functions. You only need to handle the desired case.
onComplete, onSuccess, and onFailure have the result type Unit, so they can’t be chained. This design was intentional, to avoid any suggestion that callbacks may be executed in a particular order.
 */
