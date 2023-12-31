package ScalaBasics

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TestOptionT extends App {

  val f1: Future[Int] = Future[Int](1)
  val f2: Future[Option[Int]] = Future(Some(1))

  val f11: Future[Int] = f1.map(a => a + 1) //map is used to change the type inside, do a computation on value inside
  val f22: Future[Option[Int]] = f2.map(a => a.map(x => x + 1))

  println(f1) //o/p- Future(Success(1))
  println("increase value in f1 by 1 :" + f11) //o/p- increase value in f1 by 1 :Future(Success(2))

  println(f2) //o/p- Future(Success(Some(1)))
  println("increase value in f2 by 1 :" + f22) //o/p- increase value in f2 by 1 :Future(Success(Some(2)))

  // Incrementing Future[Option[Int]] using 2 map seemed complicated which is why OptionT type was created.

  // Implementation of OptionT
  // OptionT is a wrapper over Future[Option[A]] with a convenience method map so we can directly access and do computations on A like f1.map in f11.
  case class OptionT[A](value: Future[Option[A]]) {

    def map[B](f: A => B): OptionT[B] = OptionT(value.map(a => a.map(f)))

  }

  // How to increment f2: Future[Option[Int]]
  val obj: OptionT[Int] = OptionT(f2)
  val inc = obj.map(a => a + 1)
  Thread.sleep(5000) //o/p without using sleep - OptionT: OptionT(Future(<not completed>))
  println("OptionT: " + inc) //o/p- OptionT: OptionT(Future(Success(Some(2))))

  val x = obj.value
  println("x: " + x) //o/p- x: Future(Success(Some(1)))

  // Alternate way-
  val f23: Future[Option[Int]] = OptionT(f2).map(a => a + 1).value
  Thread.sleep(5000)
  println(f23) // o/p- Future(Success(Some(2)))

  def abcd[A, B](o1: OptionT[A], f: A => B): OptionT[B] = o1.map(f)


}
