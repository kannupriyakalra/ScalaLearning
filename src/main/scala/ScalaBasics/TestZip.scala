package ScalaBasics

object TestZip extends App {

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

