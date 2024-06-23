package ScalaBasics

object TestFilter extends App {

  // Create a filter on an Option
  def filterOption[A](input: Option[A], f: A => Boolean): Option[A] = {
    input match {
      case Some(value) => if (f(value)) Some(value) else None
      case None => None
    }
  }

  val o: Option[Int] = Some(10)
  val m: Option[Int] = None
  val fxn: Int => Boolean = (input: Int) => input % 2 == 0

  println(filterOption(o, fxn)) // Output: Some(10)
  println(filterOption(m, fxn)) // Output: None

}
