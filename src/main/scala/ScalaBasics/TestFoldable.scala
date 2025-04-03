package ScalaBasics

//Foldable is a type class, it is a way to achieve adhoc polymorphism in Scala. Foldable is a type class that defines a fold method.
trait Foldable[F[_]] {
  def fold[A, B](input: F[A])(initial: B)(func: (B, A) => B): B
}

object Foldable {
  implicit val listFoldable: Foldable[List] = new Foldable[List] {
    override def fold[A, B](input: List[A])(initial: B)(func: (B, A) => B): B =
      input.foldLeft(initial)(func)
  }

  implicit val optionFoldable: Foldable[Option] = new Foldable[Option] {
    override def fold[A, B](input: Option[A])(initial: B)(func: (B, A) => B): B =
      input match {
        case Some(value) => func(initial, value)
        case None => initial
      }
  }

  implicit val setFoldable: Foldable[Set] = new Foldable[Set] {
    override def fold[A, B](input: Set[A])(initial: B)(func: (B, A) => B): B =
      input.foldLeft(initial)(func)
  }

  //extension method
  implicit class FoldableOps[F[_], A](input: F[A]) {
    def fold[B](initial: B)(func: (B, A) => B)(implicit foldable: Foldable[F]): B =
      foldable.fold(input)(initial)(func)
  }

}

object TestFoldable extends App {

  case class Triple[A](i: A, j: A, k: A)

  object Triple {

    //foldable instance for Triple
    implicit def tripleFoldable[A]: Foldable[Triple] = new Foldable[Triple] {
      override def fold[A, B](input: Triple[A])(initial: B)(func: (B, A) => B): B =
        func(func(func(initial, input.i), input.j), input.k)
    }
  }

  val tripleObject: Triple[Int] = Triple(1, 2, 3)

  import Foldable.FoldableOps

  println(tripleObject.fold(0)((acc, i) => acc + i)) //o/p- 6

  def reduce[F[_], A](input: F[A])(implicit monoid: Monoid[A], foldable: Foldable[F]): A =
    input.fold(monoid.empty)(monoid.combine)

  println(reduce(List(1, 2, 3, 4, 5))) //o/p- 120
  println(reduce(tripleObject))
  println(reduce(Set(1, 2, 3, 4, 5)))

  println(reduce(Set("1", "2", "3", "4", "5")))
  println(reduce(List(Score(2), Score(3), Score(4), Score(5))))
  println(reduce(List(List(1, 2), List(3), List(4), List(5))))

  println(reduce(List(List("1", "2"), List("3"), List("4"), List("5"))))
  println(reduce(List(List(Score(2), Score(3)), List(Score(4), Score(5)))))
  println(reduce(Option(1)))

}
