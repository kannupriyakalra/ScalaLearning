package TourOfScala

/*
https://tourofscala.com/scala/tuple

Scala Tuple
Imagine, for instance, you would like to pair together an identification number (Int) and a name (String).

Let's see how to do that in Scala.

By now, you probably have understood that a Tuple is a way to combine two or more types into one. For instance you can combine a Int and a String into a tuple (Int, String). You can combine up to 22 Types ! ( You can read more why on here )

The other important aspect is the accessors. For instance, if you have a Tuple (Double, Int, String), to access the first element you have to use _1, the second with _2, etc…

Tuples are also involved in the concept of pattern matching that we are going to learn more about later.

As a note about good practices: whenever possible, you should use a case class rather than a Tuple. In the long run, it makes things much easier to manage and maintain.
 */
object ScalaTuple extends App{

  // Learn more about Scala on https://leobenkel.com

  // Tuple2:

  val a: (Int, String) = (12, "abc")

  // Accessors

  val first: Int = a._1
  val second: String = a._2

  assert(first == 12)
  assert(second == "abc")

  // Flip a Tuple 2

  val flip = a.swap

  val expectedFlip: (String, Int) = "abc" -> 12

  assert(flip == expectedFlip)

  // Tuple3

  val tripleList: List[(Int, String, Int)] = List(
    (1, "a", 1),
    (2, "haha", 2),
    (3, "c", 3)
  )

  // tuple in map
  val modifiedTripleList: List[(Int, String)] =
    tripleList.map(r => (r._1 + r._3) -> r._2)

  val expectedModifiedTripleList: List[(Int, String)] = List(
    2 -> "a",
    4 -> "haha",
    6 -> "c"
  )

  assert(expectedModifiedTripleList == modifiedTripleList)

  println("Congratulations ! Do not be afraid, you are not alone.")


}
