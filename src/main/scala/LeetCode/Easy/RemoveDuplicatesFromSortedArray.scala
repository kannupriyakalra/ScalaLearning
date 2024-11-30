package LeetCode.Easy

//https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
object RemoveDuplicatesFromSortedArray extends App {

  //for solution 2- time complexity = O(n), space complexity = O(n)
  //for solution 1- time complexity = O(nlogn), space complexity = O(n)
  def removeDuplicates(nums: Array[Int]): Int = {
    val ls = nums.toList //time complexity to create a list is O(n), space complexity = O(n)
    val distinctList = removeDuplicates(ls)
    val size = distinctList.length //time complexity = O(n), space complexity = O(1)

    //populating the existing array instead of creating a new array as leetcode is not functional. Requirement of question says to mutate the array in place with distinct elements and let the extra elements stay after it.
    distinctList.zipWithIndex.foreach { case (elem, index) => nums(index) = elem } //time complexity = O(n), space complexity = O(2n) as List(tuple)

    size
  }

  //solution 1:
  //time complexity = O(nlogn), space complexity = O(n)
  //def removeDuplicates(input: List[Int]): List[Int] = input.toSet.toList //time complexity to create a set is O(nlogn), as you see higher time complexity element ignore the lower complexity one
  // and move on

  //solution 2:
  //remove duplicates from a list using pattern match and recursion:
  //time complexity = O(n), space complexity = O(n)
  def removeDuplicates(input: List[Int], previous: Option[Int] = None): List[Int] = {
    (previous, input) match {
      case (None, head :: tail) => head :: removeDuplicates(tail, Some(head))
      case (Some(value), head :: tail) => if (value == head) removeDuplicates(tail, Some(value)) else head :: removeDuplicates(tail, Some(head))
      case (_, Nil) => Nil
    }
  }

  val array1 = Array(1, 1, 2)
  println(array1.mkString(" "))
  println(removeDuplicates(array1))
  println(array1.mkString(" "))

  val array2 = Array(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
  println(array2.mkString(" ")) //o/p - Array(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
  println(removeDuplicates(array2))
  println(array2.mkString(" ")) //o/p - Array(0, 1, 2, 3, 4)


}

/*
Time and space complexity understanding:
new memory allocations are costly, asking heap for memory is costly.
in functional programming, we generally create new objects instead of mutating them, which has a cost so it increases time complexity.
 */
