package ScalaBasics

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Functor[F[_]] {
  def map[A, B](input: F[A])(func: A => B): F[B]
}

object Functor {
  implicit val listFunctor: Functor[List] = new Functor[List] {
    override def map[A, B](input: List[A])(func: A => B): List[B] =
      input.map(func)
  }

  implicit val optionFunctor: Functor[Option] = new Functor[Option] {
    override def map[A, B](input: Option[A])(func: A => B): Option[B] =
      input match {
        case Some(value) => Some(func(value))
        case None => None
      }
  }

  implicit val futureFunctor: Functor[Future] = new Functor[Future] {
    override def map[A, B](input: Future[A])(func: A => B): Future[B] =
      input.map(func)
  }

  //set breaks functor laws ie why not made here, TBC
}

object TestFunctor extends App {

  // F can be :List, Option, Future
  def plusOne[F[_]](input: F[Int])(implicit functor: Functor[F]): F[Int] =
    functor.map(input)(i => i + 1)

  println(plusOne(List(1)))
  println(plusOne(Option(1)))
  println(plusOne(Future(1)))

}
