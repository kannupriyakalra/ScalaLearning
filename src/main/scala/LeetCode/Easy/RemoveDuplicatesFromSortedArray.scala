package LeetCode.Easy

//https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
object RemoveDuplicatesFromSortedArray extends App {

  //time complexity = O(nlogn), space complexity = O(n)
  def removeDuplicates(nums: Array[Int]): Int = {
    val ls = nums.toList //time complexity to create a list is O(n), space complexity = O(n)
    val distinctList = removeDuplicates(ls) //time complexity = O(nlogn), space complexity = O(n)
    val size = distinctList.length //time complexity = O(n), space complexity = O(1)

    //populating the existing array instead of creating a new array as leetcode is not functional. Requirement of question says to mutate the array in place with distinct elements and let the extra elements stay after it.
    distinctList.zipWithIndex.foreach { case (elem, index) => nums(index) = elem } //time complexity = O(n), space complexity = O(2n) as List(tuple)

    size
  }

  def removeDuplicates(input: List[Int]): List[Int] = input.toSet.toList //time complexity to create a set is O(nlogn), as you see higher time complexity element ignore the lower complexity one
  // and move on

  //  //remove duplicates from a list using pattern match and recursion: --tbc
  //  def removeDuplicates(input: List[Int]): List[Int] = {
  //    input match {
  //      case head1 :: head2 :: tail if (head1 == head2) => head1 :: removeDuplicates(tail)
  //      case head :: tail => head :: removeDuplicates(tail)
  //      case head :: Nil => input
  //      case Nil => Nil
  //    }
  //  }

  val array1 = Array(1, 1, 2)
  println(array1.mkString(" "))
  println(removeDuplicates(array1))
  println(array1.mkString(" "))

  val array2 = Array(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)

  println(array2.mkString(" ")) //o/p - Array(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
  println(removeDuplicates(array2))
  println(array2.mkString(" ")) //o/p - Array(0, 1, 2, 3, 4)


}
