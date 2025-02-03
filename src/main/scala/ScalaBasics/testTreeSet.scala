package ScalaBasics

object testTreeSet extends App{

  import scala.collection.immutable.TreeSet

  // Create an empty TreeSet
  var treeSet = TreeSet.empty[Int]

  // Insert elements into the TreeSet
  treeSet += 3
  treeSet += 1
  treeSet += 4
  treeSet += 2

  // Print the TreeSet
  println(treeSet)

}
