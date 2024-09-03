package TourOfScala

/*
https://tourofscala.com/scala/thread-sleep
Scala Thread Sleep
This seem like a simple thing but it will allow us to introduce the idea of Thread, which is a much more complex beast.

Thank you for iamkpr from our Discord channel for suggesting going toward asynchronous computation, threading and Future.

A thread is a stream of computation. For instance, you would do:

val c = a + b val d = c * 2
Those two operations happen one at a time. There is no way for d to be computed before c, in this situation.

Now imagine that you want, for instance, retrieve some data somewhere from two different places. You could query those sources one at a time but most of the time it would be more efficient and faster to start requesting the data from both places so that we can collect the data from both places at once.

You might have heard of multi-threading maybe in the past. This means that the application is able to do more than one thing in parallel.

This SKB was not treating any of those topics about asynchronous operation and parallelization. However, it gaves you a taste of what it is like to have an operation that takes time. The method Thread.sleep( ) allows you to pause the current thread for a number of milliseconds.

When you work on real life projects, time is a very important factor. And being able to leverage threads to run several things at once is extremely important. But don't panic, we are going to slowly approach the problem, one SKB at a time
 */
object ScalaThreadSleep extends App {


  /**
   * Simple function to get current time
   */
  def now = {
    import java.util.Calendar
    Calendar.getInstance().getTime()
  }

  println(s"$now - Start")

  val seconds: Int = 2
  val milliSeconds: Int = seconds * 1000

  println(s"$now - Waiting $seconds seconds")

  Thread.sleep(milliSeconds)

  println(s"$now - Done")

  println("Congratulations ! You are the best You there is, so far !")

}
