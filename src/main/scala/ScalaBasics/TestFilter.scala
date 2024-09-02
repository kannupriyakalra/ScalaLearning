package ScalaBasics

object TestFilter extends App {

  // Create a filter on an Option- filter out the contents of the option if it doesn't satisfy the predicate.
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
  println(someOption.filter(isEven)) // Output: Some(10)
  println(noneOption.filter(isEven)) // Output: None



  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



  // Implement filter on a List- only the elements that pass the predicate function get to stay
  def filterList[A](list: List[A], predicate: A => Boolean): List[A] = {
    list match {
      case head :: tail => if (predicate(head)) head :: filterList(tail, predicate) else filterList(tail, predicate)
      case Nil => Nil
    }
  }

  val numbers: List[Int] = List(1, 2, 3, 4, 5)
  val greaterThanTwo: Int => Boolean = (number: Int) => number > 2

  println(filterList(numbers, greaterThanTwo)) // Output: List(3, 4, 5)
  println(numbers.filter(greaterThanTwo)) // Output: List(3, 4, 5)



  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



  // Typeclasses-   trait Filterable is a type class
  // Make a generic filter that works on both list and option
  // trait Filterable has a type parameter F which is higher kinded type.
  // Higher Kinded Types, F[_] is another type parameter that we have introduced and its a higher kinded type. List, Option, Array above is replaced by F.
  // Below code is an example of adhoc polymorphism- adhoc polymorphism means doing polymorphism in an adhoc manner eg here we found commonality between list and option filterable s implementations
  // and implemented a genericFilter.
  trait Filterable[F[_]]{
    def filterElements[A](input: F[A], predicate: A => Boolean): F[A]

  }

  implicit val listFilterable: Filterable[List] = new Filterable[List] {
    override def filterElements[A](input: List[A], predicate: A => Boolean): List[A] = input.filter(predicate)
  }

  implicit val optionFilterable: Filterable[Option] = new Filterable[Option] {
    override def filterElements[A](input: Option[A], predicate: A => Boolean): Option[A] = input.filter(predicate)
  }

  implicit val optionFilterable2: Filterable[Option] = new Filterable[Option] {
    override def filterElements[A](input: Option[A], predicate: A => Boolean): Option[A] = input.filter(predicate)
  }

  def genericFilter[F[_], A](input: F[A], predicate: A => Boolean)(implicit filterable: Filterable[F]): F[A] =
    filterable.filterElements(input, predicate)

  println(genericFilter(someOption, isEven)(optionFilterable2)) //Implicit only does type matching ie tries to find Filterable[Option] which when it found 2 it got confused so we had to explicitly
  // pass (optionFilterable2), compiler only complains whe there are more than one option, otherwise it works implicitly like in line 77.
  println(genericFilter(someOption, greaterThanTwo)(optionFilterable2))

  println(genericFilter(numbers, greaterThanTwo))

}

