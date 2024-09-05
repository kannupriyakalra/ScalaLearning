package TourOfScala

/*

https://tourofscala.com/scala/list-parallel
Scala List parallel

I am going to give you an introduction to parallelization. For this, I will be using par.

However, you have to know, that to use this in Scala 2.13+ you need extra steps. You can read more on Stackoverflow (https://stackoverflow.com/questions/57287607/missing-par-method-from-scala-collections/57290463#57290463). I made the choice to still be using it because, I think, it is a great stepping stone to understand more complex subjects.
https://stackoverflow.com/questions/56542568/missing-import-scala-collection-parallel-in-scala-2-13
Let's dive in!

Compare the output of the two map. In the first one, you see the numbers in the same order they are in the source Range. In the second one, the order is random, try running it several times ; you will see the order of the print statements change.

This happen because all the operation executed in the map happen at the same time, in parallel.

Remember the SKB on Thread.sleep, this was the introduction to the concept of Threads. To allow each operation to happen at the same time, Scala will manage a pool of threads for you. Each operation will be allocated to thread that the computer will compute and then return the result for each operation. Finally, the result will be combined before being returned to you.

In further SKBs, we are going to learn more about threads in more details. We are going to talk about Fiber, Future, asynchronous and more. Stay tuned!
 */

import scala.collection.parallel.CollectionConverters._

object ScalaListParallel extends App{

  val from: Int = 0
  val end: Int = 12
  // have fun with the step. You know "Range" now.
  val step: Int = 4
  val expected: Int = 28

  val result1: Int = (from to end by step).map { a =>
    val adder: Int = 1
    println(s"#seq> $a + $adder")
    a + adder
  }.sum
  assert(result1 == expected, result1)

  println("With 'par':")
  val result2: Int = (from to end by step).par.map { a =>
    val adder: Int = 1
    println(s"#par> $a + $adder")
    a + adder
  }.sum
  assert(result2 == expected, result2)

  println(
    "Congratulations ! 'Do today what others won’t so tomorrow you can do what others can’t.' – Jerry Rice"
  )

}
