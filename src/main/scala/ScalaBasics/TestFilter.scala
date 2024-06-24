package ScalaBasics

object TestFilter extends App {

  // Create a filter on an Option
  def filterOption[A](option: Option[A], predicate: A => Boolean): Option[A] = {
    option match {
      case Some(value) => if (predicate(value)) Some(value) else None
      case None => None
    }
  }

  val someOption: Option[Int] = Some(10)
  val noneOption: Option[Int] = None
  val isEven: Int => Boolean = (number: Int) => number % 2 == 0

  println(filterOption(someOption, isEven)) // Output: Some(10)
  println(filterOption(noneOption, isEven)) // Output: None

  // Implement filter on a List
  def filterList[A](list: List[A], predicate: A => Boolean): List[A] = {
    list match {
      case head :: tail => if (predicate(head)) head :: filterList(tail, predicate) else filterList(tail, predicate)
      case Nil => Nil
    }
  }

  val numbers: List[Int] = List(1, 2, 3, 4, 5)
  val greaterThanTwo: Int => Boolean = (number: Int) => number > 2

  println(filterList(numbers, greaterThanTwo)) // Output: List(3, 4, 5)
}
