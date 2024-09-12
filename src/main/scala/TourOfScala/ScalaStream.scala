package TourOfScala

/*
// https://tourofscala.com/scala/stream
Scala Stream

An other data structure today ! After seeing List, Set. Let's talk about Stream, now called LazyList in Scala 2.13+.

This is a bit of a longer exercise because there are several ways to create a LazyList and several ways to use it. Remember, this is your journey. If you only have the time to complete the beginning, you can move on or come back to it later.

In the first section, you saw how to create a LazyList recursively using the symbol #::. You also learned that you can take as many elements as you want, but since Scala values immutability, doing it several times in a row will always returns the same elements. Make sure to read the log statements carefully.

In the second section, we are using LazyList.from to use the build-in tools that allow you to start a sequential stream starting at n and increasing one by one. You also see that map can be used to modify the outcome of the LazyList. And finally, take can only reduce the size of the stream.

In the third and final section, we are implementing factorial, once recursively and then using a LazyList. Have you notice the use of the foldLeft from previous SKB !?
 */
object ScalaStream extends App {

  val startN: Int = 2
  val increment: Int = 3

  // FIRST STREAM //
  println("- First stream -")

  // Stream was renamed to LazyList in scala 2.13
  def stream1(n: Int = 0): LazyList[Int] = {
    n #:: stream1(n + increment)
  }

  val s1: LazyList[Int] = stream1(startN)
  val takeN: Int = 5

  // is not computed yet. not materialized.
  println(s1.take(takeN)) //o/p- LazyList(<not computed>)

  // always return the same thing
  println(s1.take(takeN).toList) //o/p- List(2, 5, 8, 11, 14)
  println(s1.take(takeN).toList) //o/p- List(2, 5, 8, 11, 14)
  println(s1.take(takeN).toList) //o/p- List(2, 5, 8, 11, 14)

  val r1 = s1.take(takeN).sum
  assert(r1 == 40, r1)

  // SECOND STREAM //
  println("- Second stream -")

  def stream2(n: Int = 0): LazyList[Int] = {
    LazyList
      .from(n)
      .map(a => a * increment)
  }

  val s2: LazyList[Int] = stream2(startN)

  // can take less
  println(s2.take(6).take(5).take(4).toList)
  // cannot take more
  println(s2.take(4).take(5).take(6).toList)

  val r2 = s2.take(takeN).sum
  assert(r2 == 60, r2)

  // FACTORIAL //
  println("- Factorial -")

  // without stream
  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1)
  }

  // with stream
  def factorialStream(n: Int): Int = {
    // all those 'val' would be removed in production code. This is just for the exercise.
    val start: Int = 1
    val takeN: Int = n

    def multiply(a: Int, b: Int): Int = a * b

    LazyList
      .from(start)
      .take(takeN)
      .foldLeft(1)(multiply)
  }

  (0 to 10).foreach { n =>
    val f: Int = factorial(n)
    val fs: Int = factorialStream(n)
    println(s"$f == $fs")
    assert(f == fs)
  }

  println(
    "Congratulations ! 'The secret of getting ahead is getting started.' - Mark Twain"
  )

}
