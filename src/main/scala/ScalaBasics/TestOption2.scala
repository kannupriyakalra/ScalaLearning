package ScalaBasics
object TestOption2 extends App {

  // write a function that takes 2 option as input
  // and returns an option having both of their value
  // and returns none if either of the options is none.

  /*
  1. extract value from both option
  2. wrap the 2 values in a tuple
  3. wrap the tuple in a option
   */
  def combineOptions[A, B](firstOption: Option[A], secondOption: Option[B]): Option[(A, B)] = {
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

  println(combineOptions(Some(10), Some("abc"))) //o/p- Some((10,abc))
  println(combineOptions(None, Some("abc")))
  println(combineOptions(None, None))

  // write a function that takes 2 input
  // and returns a tuple having both of their value

  val a: Unit = println(pair(10, "Hello"))

  println(pair(10, "Hello").toString())
  println(pair(10, "Hello").swap)

  def pair(i1: Int, i2: String): (Int, String) = (i1, i2)

}

