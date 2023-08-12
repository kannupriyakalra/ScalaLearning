//retry logic for calling a endpoint again

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TestFuture2 extends App{

  def f1: Future[Int] = Future(1)

  val f2: Future[Int] = f1.map(_ + 1)

  def retry[A](f: => Future[A], shouldRetry: A => Boolean, noOfRetries: Int): Future[A] =
    f.flatMap { a =>
      if (shouldRetry(a))
        if (noOfRetries <= 0) Future.successful(a) else retry(f, shouldRetry, noOfRetries - 1)
      else Future.successful(a)
    }

  val f = retry(f1, (a: Int) => a == 1, 2)
  Thread.sleep(1000)
  println(f)

}
