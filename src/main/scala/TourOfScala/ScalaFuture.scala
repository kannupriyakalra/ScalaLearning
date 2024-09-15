package TourOfScala

/*
https://tourofscala.com/scala/future

Finally diving into asynchronous standard library !

Talking about Future, the good and the bad.

Lots going on here but hopefully the comments helped breaking it down into bits size knowledge.

First of all, to use Future, you have to define an ExecutionContext. There are plenty of ways to do it but to stay simple, we are going to be using the default global one.

Then, let's start with the first part. In this part, we are discovering Future. Simply create then using Future {...} and you can have any code in there. The return type will be Future[A], where A is the return type of what is inside the {...}.

You can chain futures together using for-comprehension, like we saw in previous episode. As well as the traditional map, flatMap, etc…

We are already encountering one danger to know about Future: they start as soon as they are created. Also, the next operation is executed when the first one return, this is why the futures f3 to f5 are waiting on each other to start. To start them at the same time, you would have to create them outside of the for-comprehension like f1 and f2.

The second part is to illustrate the biggest danger about Future. They are NOT cancellable !

As you can see, even when the Await time is smaller and would throw an exception, the Future keep running in the background. Which can be dangerous if you are doing some insert in a database for instance and believe that it was cancel, it will not be.

Other framework more advanced like ZIO https://github.com/zio/zio offer better asynchronous features using Fibers instead of Threads. But this will have to wait for us to be master in functional programming and more advanced concepts before exploring this library.

 */
object ScalaFuture extends App{

  // imports

  import java.util.concurrent.TimeUnit
  import scala.util.Try
  import scala.concurrent.duration.Duration
  import scala.concurrent.{
    Await,
    ExecutionContext, //this is a thread pool
    ExecutionContextExecutor, //this is a thread pool
    Future
  }

  implicit val ex: ExecutionContextExecutor = ExecutionContext.global

  println(">> Start exercise")

  // making futures
  // notice the type `=> Int`
  def createCompute(name: String)(operation: => Int): Future[Int] = {
    Future {
      println(s"Start $name")
      val output: Int = operation
      println(s"Done $name")
      output
    }
  }

  // start as soon as created
  val f1: Future[Int] = createCompute("f1") { //Future is eager, f1 gets created instantly but the value inside f1 will be available in some time as its executing in a different thread in background.
    Thread.sleep(100)
    100
  }
  // support map and all the other flatMap, ...
  val f2: Future[Int] = createCompute("f2") {
    Thread.sleep(50)
    200
  }.map(a => a + 1)

  // chained together
  val fTotal: Future[Int] = for {
    f1Result: Int <- f1 //the execution will wait at this line until f1 completes and assigns the value to f1Result
    // not running in parallel ie sequential execution as on every line we are waiting for future to complete and assign its result.
    f3Result <- createCompute("f3") {
      Thread.sleep(50)
      300
    }
    f4Result <- createCompute("f4") {
      Thread.sleep(50)
      400
    }
    f5Result <- createCompute("f5") {
      Thread.sleep(50)
      500
    }
    f2Result <- f2
  } yield {
    f1Result + f2Result + f3Result + f4Result + f5Result
  }

  // try different wait time
  val outputTotal: Int =
    Await.result(fTotal, Duration(400, TimeUnit.MILLISECONDS)) //here we are waiting for fTotal to complete in 400ms, after that it will throw an error.

  assert(outputTotal == 1501, outputTotal)

  // Future can be dangerous
  println(">> Start Part 2")

  val f: Future[Int] = Future {
    println("start")
    // On a second run, try reducing this number from 1500 to 500 to see assertion pass.
    Thread.sleep(500)
    // this will print, Future are not cancellable
    println("finish")
    1
  }

  // or increase this number
  val output: Int =
    Try(Await.result(f, Duration(1, TimeUnit.SECONDS))).getOrElse(5)

  Thread.sleep(750)

  assert(output == 1, output)

  println(
    "Congratulations ! 'Ever tried. Ever failed. No matter. Try Again. Fail again. Fail better.' – Samuel Beckett"
  )


}
