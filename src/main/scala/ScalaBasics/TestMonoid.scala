package ScalaBasics

//Adhoc polymorphism is achieved using type classes in Scala. Monoid is a type class.
//monoid is a way to combine 2 values, to combine 2 values we need a default value and an operator.
trait Monoid[A] {
  def empty: A

  def combine(first: A, second: A): A

}

//monoid instances for various types created in companion object of Monoid trait:
object Monoid {
  implicit val additionIntMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0

    override def combine(first: Int, second: Int): Int = first + second
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def empty: String = ""

    override def combine(first: String, second: String): String = first + second
  }

  //used def instead of val as we need type parameter [A], val means an object in memory which is of a specific type so it can't be of generic type.
  implicit def setMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set()

    override def combine(first: Set[A], second: Set[A]): Set[A] = first ++ second
  }

  implicit def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
    override def empty: List[A] = Nil

    override def combine(first: List[A], second: List[A]): List[A] = first ++ second
  }

}

//monoid for user defined types:
case class Score(i: Int)

case object Score {
  implicit val scoreMonoid: Monoid[Score] = new Monoid[Score] {
    override def empty: Score = Score(0)

    override def combine(first: Score, second: Score): Score = Score(first.i + second.i)
  }
}


object TestMonoid extends App {

  //reduce here is an example of adhoc polymorphism as reduce function can be used with different behaviours which are addition and multiplication monoid.
  def reduce[A](input: List[A])(implicit monoid: Monoid[A]): A = //comprehend this as List[A] is reduced to A and monoid sent implicitly is of type A using the combine behaviour from monoid.
    input.foldLeft(monoid.empty)(monoid.combine)

  println(reduce(List(2, 2, 2, 2))(Monoid.additionIntMonoid)) // monoid object is passed explicitly here to avoid forward reference
  // (at this point multiplicationIntMonoid is null as it hasn't been declared yet, code executes from top to bottom and if we pass the monoid object
  // implicitly here it picks multiplicationIntMonoid as its in this context. Remove explicit monoid object and see the error.

  implicit val multiplicationIntMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 1

    override def combine(first: Int, second: Int): Int = first * second
  }

  println(reduce(List(2, 2, 2, 2))) //first reduce method looks for implicit parameter within object TestMonoid which is multiplicationIntMonoid and if it can't find a Monoid[Int] there, it finds it in
  //companion object and used additionIntMonoid instead. Basically, compiler looks for implicit parameter in the scope of the function and if it can't find it there, it looks for it in the companion object of the type.
  println(reduce(List("2", "2", "2", "2")))


  println(reduce[Score](List(Score(2), Score(3), Score(4), Score(5)))) //o/p- Score(14)

  println(reduce[List[Int]](List(List(1, 2), List(3), List(4), List(5)))) //o/p- List(1, 2, 3, 4, 5)
  println(reduce[List[String]](List(List("1", "2"), List("3"), List("4"), List("5")))) //o/p- List(1, 2, 3, 4, 5)
  println(reduce[List[Score]](List(List(Score(2), Score(3)), List(Score(4), Score(5))))) //o/p- List(Score(2), Score(3), Score(4), Score(5)) //as type A in reduce is List[Score] ie why the o/p is
  // of type A ie List[Score] as per definition of reduce method.
  println(reduce[Score](reduce[List[Score]](List(List(Score(2), Score(3)), List(Score(4), Score(5)))))) //o/p- Score(14)


  println(reduce[Set[Int]](List(Set(1, 2), Set(3), Set(4), Set(5))))
  println(reduce[Set[String]](List(Set("1", "2"), Set("3"), Set("4"), Set("5"))))

  //monoid for generic type and nested types:
  case class Runs[A](i: A)

  case object Runs {

    //Given a monoid of type Monoid[A], we can get Monoid[Runs[A]].
    implicit def runsMonoid[A](implicit monoid: Monoid[A]): Monoid[Runs[A]] = new Monoid[Runs[A]] {
      override def empty: Runs[A] = Runs(monoid.empty)

      override def combine(first: Runs[A], second: Runs[A]): Runs[A] = Runs[A](monoid.combine(first.i, second.i)) //monoid.combine is used here as we need to combine 2 values of type A
      // and its return type is A. first.i and second.i are of type A.
    }

    //implicit def runsFoldable[A](implicit foldable: Foldable[])
  }

  println(reduce[Runs[Int]](List(Runs(2), Runs(3), Runs(4), Runs(5)))) //Type A = Runs[Int], for Monoid[Runs[Int]] we need Monoid[Int] implicitly.
  //reduce function is called with type A = Runs[Int] and Monoid[Runs[Int]] is needed, so it looks for Monoid[Runs[Int]] in the companion object of Runs[Int] which is Runs object.
  println(reduce[Runs[String]](List(Runs("2"), Runs("3"), Runs("4"), Runs("5"))))

  println(reduce[Runs[Runs[Int]]](List(Runs(Runs(2)), Runs(Runs(3))))) //o/p- Runs(Runs(6))
  //for runsMonoid function if input is of type Monoid[Int] => Monoid[Runs[Int]],
  //if input is of type Monoid[Runs[Int]] => Monoid[Runs[Runs[Int]]]

  println(reduce[Runs[Runs[Runs[Int]]]](List(Runs(Runs(Runs(2))), Runs(Runs(Runs(3)))))) //o/p- Runs(Runs(Runs(6)))




}


