package TourOfScala

/*
https://tourofscala.com/scala/range
Scala Range

Summary of the keywords:
to: When you do 0 to 3, you will get the numbers 0, 1, 2, 3. Notice that the end boundary is included.
until: When you do 0 until 3, you will get the numbers 0, 1, 2. Notice that the end boundary is not included.
by: When you do 0 to 11 by 3, you will get the numbers 0, 3, 6, 9. The default value is by 1.
 */
object ScalaRange extends App {

  val inputList1 = (0 to 50)
  println(inputList1)
  val result1: Int = inputList1.sum
  val expected1: Int = 1275
  assert(result1 == expected1, result1)

  val step: Int = 3
  val inputList2 = (0 until 20 by step).toList
  println(inputList2)
  val result2: Int = inputList2.length
  val expected2: Int = 7
  assert(result2 == expected2, result2)

  (0 to 50).toList

  println("Congratulations ! 'Happiness is when what you think, what you say, and what you do are in harmony.' - Mahatma Ghandi")

}

/*
scala repl

scala> (0 to 50)
val res1: scala.collection.immutable.Range.Inclusive = Range 0 to 50

scala> (0 to 50).toList
val res2: List[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50)

scala> (0 to 50).toSet
val res3: Set[Int] = HashSet(0, 5, 10, 14, 1, 6, 9, 2, 8, 4, 42, 24, 37, 25, 20, 46, 29, 21, 33, 28, 38, 13, 41, 45, 17, 32, 34, 22, 44, 27, 12, 49, 7, 39, 3, 35, 48, 18, 50, 16, 31, 11, 43, 40, 26, 23, 36, 30, 19, 47, 15)

scala> (0 to 50).toSet + 1 //adding a element, which doesn't add if it exist already
val res4: Set[Int] = HashSet(0, 5, 10, 14, 1, 6, 9, 2, 8, 4, 42, 24, 37, 25, 20, 46, 29, 21, 33, 28, 38, 13, 41, 45, 17, 32, 34, 22, 44, 27, 12, 49, 7, 39, 3, 35, 48, 18, 50, 16, 31, 11, 43, 40, 26, 23, 36, 30, 19, 47, 15)

scala> (0 to 50).toSet + 51
val res5: Set[Int] = HashSet(0, 5, 10, 14, 1, 6, 9, 2, 8, 4, 42, 24, 37, 25, 20, 46, 29, 21, 33, 28, 38, 13, 41, 45, 17, 32, 34, 22, 44, 27, 12, 49, 7, 39, 3, 35, 48, 18, 50, 16, 31, 11, 43, 40, 26, 23, 36, 30, 51, 19, 47, 15)

scala> (0 to 50).toSet - 1
val res6: Set[Int] = HashSet(0, 5, 10, 14, 6, 9, 2, 8, 4, 42, 24, 37, 25, 20, 46, 29, 21, 33, 28, 38, 13, 41, 45, 17, 32, 34, 22, 44, 27, 12, 49, 7, 39, 3, 35, 48, 18, 50, 16, 31, 11, 43, 40, 26, 23, 36, 30, 19, 47, 15)

 */
