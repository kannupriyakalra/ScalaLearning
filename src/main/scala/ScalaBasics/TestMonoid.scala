package ScalaBasics

//monoid is a way to combine 2 values, to combine 2 values we need a default value and an operator.
trait Monoid[A] {
  def empty: A

  def combine(first: A, second: A): A

}

object Monoid { //companion object
  implicit val additionIntMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0

    override def combine(first: Int, second: Int): Int = first + second
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def empty: String = ""

    override def combine(first: String, second: String): String = first + second
  }

}

object TestMonoid extends App {

  //reduce here is an example of adhoc polymorphism as reduce function can be used with different behaviours which are addition and multiplication monoid.
  def reduce[A](input: List[A])(implicit monoid: Monoid[A]): A =
    input.foldLeft(monoid.empty)(monoid.combine)

  println(reduce(List(2, 2, 2, 2))(Monoid.additionIntMonoid)) // monoid object is passed explicitly here to avoid forward reference
  // (at this point multiplicationIntMonoid is null as it hasn't been declared yet, code executes from top to bottom and if we pass the monoid object
  // implicitly here it picks multiplicationIntMonoid as its in this context. Remove explicit monoid object and see the error.

  implicit val multiplicationIntMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 1

    override def combine(first: Int, second: Int): Int = first * second
  }

  println(reduce(List(2, 2, 2, 2)))
  println(reduce(List("2", "2", "2", "2")))

}
