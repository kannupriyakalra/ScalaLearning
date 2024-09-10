package TourOfScala

/*
Scala foldLeft

Let me introduce accumulators and aggregations.

foldLeft is the generic concept that is under most of the function programming transformations. You can replace map, flatMap, filter and more by a foldLeft.

In this exercise, you can see two use cases of foldLeft. But first let's explain the syntax:

foldLeft(initialValue) {

  case (accumulator, currentElement) =>

    // return the new value of the accumulator

}

Note that when currentElement is the first element of the List, then accumulator is equal to initialValue. Also, if the the list is empty, then the returned value will be the initialValue.
The returned value can be anything, for instance:

foldLeft(List.empty) {

  case (accumulator, currentElement) =>

    accumulator :+ currentElement

}

would return a new List with the same content as the input list.
An other example:

foldLeft(0) {

  case (accumulator, currentElement) =>

    accumulator + currentElement

}

would return the total sum of the item of the List. This is similar to the first example in today's exercise. And scala provide a shortcut for it: .sum, this would be a special case of the exercise when the initial value is 0.
In the second use case, there is a bit more going on. It uses pattern matching to implement different behavior based on the current element and create a new list element by element.

As an extra exercise, try to compute the average of the list by changing the initialized value from startFold to (0, 0) and modify the function to aggregate the values.

You can also try to implement map, flatMap and, filter using foldLeft. Share your solution with our community on Discord !
 */
object ScalaFoldLeft extends App {

  val startR: Int = 0
  val endR: Int = 21
  val stepR: Int = 3
  val l: List[Int] = (startR until endR by stepR).toList
  println(l)

  val startFold: Int = 1
  // try to replace '(a, b) => a + b' by '_ + _'
  val r1: Int = l.foldLeft(startFold)((a, b) => a + b)
  assert(r1 == 64, r1)


  val factor: Int = 2

  def isEven(n: Int): Boolean = n % factor == 0

  val r2 = l.foldLeft(List.empty[Int]) {
    case (accumulator, n) if isEven(n) => accumulator :+ (n / factor)
    case (accumulator, n) if !isEven(n) => accumulator :+ (n * factor)
  }

  assert(r2 == List(0, 6, 3, 18, 6, 30, 9), r2)

  println(
    "Congratulations ! 'If you're going through hell, keep going.' - Franklin D. Roosevelt"
  )

  //compute the average of the list
  val (sum, count): (Int, Int) = l.foldLeft((0, 0))((acc, a) => (acc._1 + a, acc._2 + 1))
  println("average of the list is :" + sum / count)

  //implement map using foldLeft.
  def mapUsingFoldleft[A, B](l: List[A], f: A => B): List[B] = {
    l.foldLeft(List.empty[B])((acc, a) => f(a) :: acc)
  }.reverse

  assert(mapUsingFoldleft(l, (a: Int) => (a + 1).toString) == List("1", "4", "7", "10", "13", "16", "19"))

  //implement flatMap using foldLeft.

  def flatMapUsingFoldleft[A, B](l: List[A], f: A => List[B]): List[B] = {
    l.foldLeft(List.empty[B])((acc, a) => acc ++ f(a))
  }

  assert(flatMapUsingFoldleft(l, (a: Int) => List((a + 1).toString, "abc")) == List("1", "abc", "4", "abc", "7", "abc", "10", "abc", "13", "abc", "16", "abc", "19", "abc"))

  //implement filter using foldLeft.

  def filterUsingFoldleft[A](l: List[A], f: A => Boolean): List[A] = {
    l.foldLeft(List.empty[A])((acc, a) => if (f(a)) a :: acc else acc)
  }.reverse

  assert(filterUsingFoldleft(l, a => isEven(a)) == List(0, 6, 12, 18))

}
