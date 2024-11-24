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

  implicit class FunctorOp[F[_], A](val fa: F[A]) extends AnyVal {
    def map[B](f: A => B)(implicit functor: Functor[F]): F[B] = functor.map(fa)(f)
  }

}


object TestFunctor extends App {

  // F[_] can be List, Option, Future, it cannot be Either, for that F[_,_] as it has 2 type parameters.
  def plusOne[F[_]](input: F[Int])(implicit functor: Functor[F]): F[Int] =
    functor.map(input)(i => i + 1)

  //plusOne is an example of adhoc polymorphism, works with unrelated types.
  println(plusOne(List(1)))
  println(plusOne(Option(1)))
  println(plusOne(Future(1)))

  //lifts the function A => B into F's context, ie F[A] => F[B].
  def lift[F[_], A, B](func: A => B)(implicit functor: Functor[F]): F[A] => F[B] =
    (fa: F[A]) => functor.map(fa)(func)

  println((lift((i: Int) => i + 1)(Functor.listFunctor)).apply(List(1))) //o/p- List(2) //return type of lift is F[A] => F[B] and on that apply is called which is a Function1 and to that
  // we gave List(1) as input.
  println(lift[List, Int, Int]((i: Int) => i + 1).apply(List(1))) //alternative way, as type parameters are mentioned ie why it took functor implicitly.

  val listPlusOne: List[Int] => List[Int] = lift[List, Int, Int]((i: Int) => i + 1)
  println(listPlusOne(List(1))) //alternative way
  println(listPlusOne.apply(List(1))) //alternative way

  case class Score(i: Int)

  val listScore: List[Int] => List[Score] =
    lift[List, Int, Score]((i: Int) => Score(i))

  val optionScore: Option[Int] => Option[Score] = lift[Option, Int, Score]((i: Int) => Score(i))

  println(listScore(List(1, 2, 3))) //o/p- List(Score(1), Score(2), Score(3))
  println(optionScore(Some(1))) //o/p- Some(Score(1))

  //custom type functor:
  case class Runs[A](i: A)

  implicit val runsFunctor: Functor[Runs] = new Functor[Runs] {
    override def map[A, B](input: Runs[A])(func: A => B): Runs[B] = Runs(func(input.i)) // input.i: A, func(input.i): B, Runs(func(input.i)): Runs[B]
  }

  import Functor.FunctorOp

  println(Runs(5).map(i => i + 1)) //o/p- Runs(6)

  //using implicit class we added map method to Runs class.


}

