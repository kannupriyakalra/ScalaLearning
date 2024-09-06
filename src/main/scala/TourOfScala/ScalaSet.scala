package TourOfScala

/*
https://tourofscala.com/scala/set
Scala Set

We saw other data structures in previous SKB like List or Map.

Let's look at Set.

Did you notice that Set cannot contain duplicate values? A Set is a list of unique values.

When you combine the two Set, it removes the duplicated values and only keep one of each.

One thing to know is that Set do not guarantee consistent ordering. You should not rely on index of the elements. If you want to learn more, you can read this great answer on Stackoverflow.- https://stackoverflow.com/a/5246204/3357831

Last thing about Set is that it has all the same function as List such as map, flatMap, filter, etc…
 */
object ScalaSet extends App{

  val s1: Set[Int] = Set(6, 0, 2, 3, 1)
  println(s1)
  val s2: Set[Int] = (0 to 8 by 2).toSet
  println(s2)

  val s: Set[Int] = s1 ++ s2
  println(s)

  val increment: Int = 2
  val result = s.map(a => a + increment).sum

  assert(result == 38, result)

  println(
    "Congratulations ! 'The secret of getting ahead is getting started.' - Mark Twain"
  )

}
