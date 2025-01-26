package ScalaBasics

object TestZipList extends App {

  // Write a function that zips 2 lists
  def zip[A, B](first: List[A], second: List[B]): List[(A, B)] = {
    val both: (List[A], List[B]) = (first, second)
    both match {
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (h1 :: tail1, h2 :: tail2) => (h1, h2) :: zip(tail1, tail2) // zip(tail1, tail2).::(elem = (h1, h2))
    }
  }

  println(zip(List(1, 2, 3), List(4, 5, 6))) // List((1, 4), (2, 5), (3, 6))
  println(zip(List(1, 2, 3), List(4, 5))) // List((1, 4), (2, 5))
  println(zip(List(1, 2), List(4, 5, 6))) // List((1, 4), (2, 5))
  println(zip(Nil, List(4, 5, 6))) // List()
  println(zip(Nil, Nil)) // List()

  // Write a function that zips 3 lists using the zip function above.

  def zip3[A, B, C](first: List[A], second: List[B], third: List[C]): List[(A, B, C)] = {
    val both: List[(A, B)] = zip(first, second)
    val all: List[((A, B), C)] = zip(both, third)

    // all.map(t2 => (t2._1._1, t2._1._2, t2._2))
    all.map { case ((a, b), c) => (a, b, c) }
  }

  println(zip3(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))) // List((1, 4, 7), (2, 5, 8), (3, 6, 9))
  println(zip3(Nil, List(4, 5, 6), List(7, 8, 9))) // List()
  println(zip3(List(1), Nil, List(7, 8, 9))) // List()
  println(zip3(List(1, 2, 3), List(4, 5, 6), Nil)) // List()

}
