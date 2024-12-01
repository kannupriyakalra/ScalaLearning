package LeetCode.Easy

//https://leetcode.com/problems/remove-element/description/

object RemoveElement extends App {

  //time complexity = O(n), space complexity = O(n)
  def removeElement(nums: Array[Int], toRemove: Int): Int = {
    val remainingArray = nums.filter(i => i != toRemove)
    //remainingArray.zipWithIndex.foreach { case (elem, index) => nums(index) = elem }

    for (i <- 0 until remainingArray.length) {
      nums(i) = remainingArray(i)
    }

    remainingArray.length
  }


  val array1 = Array(3, 2, 2, 3)
  println(array1.mkString(" ")) //o/p - 3 2 2 3
  println(removeElement(array1, 3)) //o/p - 2
  println(array1.mkString(" ")) //o/p - 2 2 2 3

  val array2 = Array(0, 1, 2, 2, 3, 0, 4, 2)
  println(array2.mkString(" ")) //o/p - 0 1 2 2 3 0 4 2
  println(removeElement(array2, 2)) //o/p - 5
  println(array2.mkString(" ")) //o/p - 0 1 3 0 4 0 4 2

}
