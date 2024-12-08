package LeetCode.Easy

//https://leetcode.com/problems/search-insert-position/description/
object SearchInsertPosition extends App {

  //time complexity = O(n), space complexity = O(1)
  def searchInsert(nums: Array[Int], target: Int): Int = {

    //i = index
    def loop(i: Int): Int = {
      if (i >= nums.length) nums.length
      else if (nums(i) == target) i
      else if (nums(i) < target) loop(i + 1)
      else i
    }

    loop(0)
  }

  println(searchInsert(Array(1, 3, 5, 6), 5))
  println(searchInsert(Array(1, 3, 5, 6), 2))
  println(searchInsert(Array(1, 3, 5, 6), 7))

}
