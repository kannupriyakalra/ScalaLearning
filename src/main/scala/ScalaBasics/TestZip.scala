package ScalaBasics

object TestZip extends App {

  //Write a function that zips 2 list

  /*
  1. extract one element from each list.
  2. pair the respective elements in a tuple.
  3. repeat again for all elements in the list respectively, make a list out of these tuples.
   */
  def zipList[A, B](l1: List[A], l2: List[B]): List[(A, B)] = {

    (l1, l2) match {
      case (h1 :: t1, h2 :: t2) => (h1, h2) :: zipList(t1, t2)
      case (_ :: _, Nil) => Nil
      case (Nil, _ :: _) => Nil
      case (Nil, Nil) => Nil
    }

  }

  val list1: List[Int] = List(1, 2, 3, 4, 5, 6)
  val list2: List[String] = List("a", "b", "c")

  println("output of zipList:  ")
  val resList = zipList(list1, list2)
  println(resList) // List( (1, "a"), (2, "b"), (3, "c") )
  println()


  //Write a function that takes two Maps (dictionaries) and returns a new Map containing only the keys present in both input Maps.
  // The values for these keys should be tuples, where each tuple contains the corresponding values from the two input Maps.

  /*
  For each key of m1 we check if m2 contains that key, we ignore if it doesn't.
  If it has then we pair the 2 values.
   */
  def zipMap[K, V1, V2](m1: Map[K, V1], m2: Map[K, V2]): Map[K, (V1, V2)] = {

    m1.toList.flatMap { case (key, v1) =>
      m2.get(key).map(v2 => (key, (v1, v2))).toList
    }.toMap

  }

  val m: Map[Int, String] = Map((1, "a"), (2, "b"), (3, "c"))
  val n: Map[Int, String] = Map((0, "a"), (2, "e"), (3, "l"), 4 -> "d")
  val o: Map[Int, (String, String)] = zipMap(m, n) //o/p- Map((2, (b,e) ), (3, (c,l) ) )

  println("output of zipMap:  ")
  println(o)
  println()

  //write a function that takes 2 functions as input and returns a new function that takes a tuple as input and returns a tuple as output.

  def zipFunction[A1, B1, A2, B2](f1: A1 => B1, f2: A2 => B2): ((A1, A2)) => (B1, B2) = {

    (x: (A1, A2)) => (f1(x._1), f2(x._2))
  }

  println("output of zipFunction:  ")
  val f: Int => String = (a: Int) => a.toString
  val g: Int => String = (a: Int) => (a * 2).toString
  val r: ((Int, Int)) => (String, String) = zipFunction(f, g)
  println(r(10, 10))
  println(r(1, 2))
  println()

  // write a function that takes 2 either and returns a zipped either that has corresponding left and right zipped together and gives none for one left and one right pair.
  // if both either s are left then some
  // if both either s are right then some
  // in all other cases none

  //zip means make a tuple
  def zipEithers[A, B](e1: Either[A, B], e2: Either[A, B]): Option[Either[(A, A), (B, B)]] = {

    e1 match {
      case Left(l1) =>
        e2 match {
          case Left(l2) => Some(Left((l1, l2)))
          case Right(_) => None
        }
      case Right(r1) =>
        e2 match {
          case Left(_) => None
          case Right(r2) => Some(Right((r1, r2)))
        }
    }
  }

  println("output of zipEithers:  ")
  println(zipEithers(Left(10), Left(10))) //o/p- Some(Left((10,10)))
  println(zipEithers(Left(10), Right("abc"))) //o/p- None
  println(zipEithers(Right("abc"), Left(10))) //o/p- None
  println(zipEithers(Right("abc"), Right("abc"))) //o/p- Some(Right((abc,abc)))
  println()


  // write a function that takes 2 option as input and returns an option having both of their value and returns none if either of the options is none.

  /*
  1. extract value from both option
  2. wrap the 2 values in a tuple
  3. wrap the tuple in a option
   */
  def zipOptions[A, B](firstOption: Option[A], secondOption: Option[B]): Option[(A, B)] = {
    //Option((o1.getOrElse(-1), o2.getOrElse("")))
    firstOption match {
      case Some(o1_value) =>
        secondOption match {
          case Some(o2_value) => Some((o1_value, o2_value))
          case None => None
        }
      case None => None
    }
  }

  println("output of zipOptions:  ")
  println(zipOptions(Some(10), Some("abc"))) //o/p- Some((10,abc))
  println(zipOptions(None, Some("abc")))
  println(zipOptions(None, None))
  println()

  // write a function that takes 2 input and returns a tuple having both of their value

  println("output of pair:  ")
  val a: Unit = println(pair(10, "Hello"))

  println(pair(10, "Hello").toString())
  println(pair(10, "Hello").swap)
  println()

  def pair(i1: Int, i2: String): (Int, String) = (i1, i2)

}

