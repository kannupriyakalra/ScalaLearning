package ScalaBasics

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
object TestOptionT extends App{

  val f1: Future[Int] = Future[Int](1)
  val f2: Future[Option[Int]] = Future(Some(1))

  val f11: Future[Int] = f1.map(a => a + 1) //map is used to change the type inside.
  val f22: Future[Option[Int]] = f2.map(a => a.map(x => x + 1))

  // Incrementing Future[Option[Int]] using 2 map seemed complicated which is why OptionT type was created

  // Implementation of OptionT
  // OptionT is a wrapper over Future[Option[A]] with a convenience method map so we can directly access and do computations on A.
  case class OptionT[A](value: Future[Option[A]]) {

    def map[B](f: A => B): OptionT[B] = OptionT(value.map(a => a.map(f)))

  }

  // How to increment f2: Future[Option[Int]]
  val f23: Future[Option[Int]] = OptionT(f2).map(a => a + 1).value

  def abcd[A, B](o1: OptionT[A], f: A => B): OptionT[B] = o1.map(f)




}
