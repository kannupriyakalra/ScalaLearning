package TourOfScala

/*
https://tourofscala.com/scala/list-flatten

Scala List Flatten
map allows you to transform a List[A] into a List[B].

What will happen if B is a List as well ?
flatten allows you to turn a List[List[A]] into a List[A].

 */
object ScalaListFlatten extends App {

  val l: List[Int] = List(1, 2, 3, 4)

  val ll: List[List[Int]] = l.map(a => List(a - 1, a, a + 1))
  println(s"Map: $ll")

  val flatList: List[Int] = ll.flatten
  println(s"Flat: $flatList")

  val sum: Int = flatList.sum

 assert(sum == 30, sum)

  println("Congratulations ! Hapiness = Reality - Expectation")

  val a: Option[Int] = Some(12)

  val aa: Option[Option[Int]] = a.map(x => Some(x))
  println(aa)

  val flattenA: Option[Int] = aa.flatten
  println(flattenA)


}
