package TourOfScala

/*
https://tourofscala.com/scala/for-comprehension

Using a lot of map and flatMap can make the code very hard to read as it goes into deep functions of functions.

Scala has a way of handling those cases. It is called a for-comprehension.

A for-comprehension allow you to chain map and flatMap together in an easy to read form.

For instance, those two snippets of code are equivalent:

for { n <- list }

yield n + 1

and
list.map( n => n + 1 )
You can also filter the input using for-comprehension.

val list = List(1, 2, 3)

for {

  n <- list

  if n == 2

} yield n

This snippet is equivalent to:
val list = List(1, 2, 3)

list
  .withFilter(n => n == 2)
  .map(n => n)
In general, everything you can do with pattern matching, you can do within a for-comprehension. The left side of the <- behave similar to a pattern matching.

Sometimes, it can be hard to convert in your head back and forth between for-comprehension and map and flatMap modes. Some IDEs, such as IntelliJ, starting with version 2020, allows you to de-sugar the code and convert the for-comprehension intomap and flatMap.
 */
object ScalaForComprehension extends App {

  val list = List(1, 2, 3)

  for {

    n <- list

    if n == 2

  } yield n

  //This snippet is equivalent to:
  list
    .withFilter(n => n == 2)
    .map(n => n)

  case class Row(id: Int, list: List[Int])

  val howManyInput: Int = 2
  val howManyListItem: Int = 4

  val input: List[Row] = (0 to howManyInput)
    .map(i => Row(i, list = (0 to howManyListItem).toList))
    .toList

  val modFilter: Int = 2
  val increase: Int = 1

  val output1: List[Int] = input.flatMap {
    case Row(id, list) =>
      list.flatMap(n =>
        List(id, n)
          .withFilter(i => id + n % modFilter == 0)
          .map(_ + increase)
      )
  }
  val output1Sum = output1.sum
  println(output1Sum)

  val output2: List[Int] = for {
    Row(id, list) <- input
    n <- list
    i <- List(id, n)
    if id + n % modFilter == 0
  } yield {
    i + increase
  }
  val output2Sum = output2.sum
  println(output2Sum)

  assert(output1Sum == output2Sum, output1Sum)
  assert(output1 == output2)
  val expected: Int = 12
  assert(output1Sum == expected, output1Sum)

  println(
    "Congratulations ! 'Knowing is not enough; we must apply. Willing is not enough; we must do.' – Johann Wolfgang von Goethe"
  )


}
