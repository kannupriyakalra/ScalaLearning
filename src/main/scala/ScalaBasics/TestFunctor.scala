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

  // F[_] can be List, Option, Future, it cannot be Either, for that F[_,_].
  def plusOne[F[_]](input: F[Int])(implicit functor: Functor[F]): F[Int] =
    functor.map(input)(i => i + 1)

  println(plusOne(List(1)))
  println(plusOne(Option(1)))
  println(plusOne(Future(1)))

  //lifts the function A => B into F's context, ie F[A] => F[B]
  def lift[F[_], A, B](func: A => B)(implicit functor: Functor[F]): F[A] => F[B] =
    fa => functor.map(fa)(func)

  println(lift((i: Int) => i + 1)(Functor.listFunctor).apply(List(1))) //o/p- List(2)
  println(lift[List, Int, Int]((i: Int) => i + 1).apply(List(1))) //alternative way

  val listPlusOne = lift[List, Int, Int]((i: Int) => i + 1)
  println(listPlusOne(List(1))) //alternative way

  case class Score(i: Int)

  val listScore: List[Int] => List[Score] =
    lift[List, Int, Score]((i: Int) => Score(i))

  val optionScore: Option[Int] => Option[Score] = lift[Option, Int, Score]((i: Int) => Score(i))

  println(listScore(List(1, 2, 3))) //o/p- List(Score(1), Score(2), Score(3))
  println(optionScore(Some(1))) //o/p- Some(Score(1))
}
