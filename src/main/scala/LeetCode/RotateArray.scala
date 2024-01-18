package LeetCode

//https://leetcode.com/problems/rotate-array/


object RotateArray {

  //Solution-1
  //Time Complexity = O(n2), Space Complexity = O(1)
//  def rotateByOne(nums: Array[Int]):Unit = {
//    val temp: Integer = nums(nums.size - 1)
//    for(index <- (nums.size - 1) to 1 by -1) {
//      nums(index) = nums(index-1)
//    }
//    nums(0) = temp
//  }
//
//  def rotate(nums: Array[Int], k: Int): Unit = {
//    val newK = k % nums.size
//    (1 to newK).foreach(_ => rotateByOne(nums))
//  }

  // k = n - 1  => worst case  => n * (n - 1) => n^2 -n
  // k = n => best case
  // k = 0 => best case

  //Solution-2
  //Time Complexity = O(n), Space Complexity = O(n)
//  def rotate(nums: Array[Int], k: Int): Unit = {
//    val rotateBy = (nums.size) - (k % nums.size)
//    if (rotateBy == nums.size) return
//    val take = nums.take(rotateBy)
//    val drop = nums.drop(rotateBy)
//    (drop ++ take).copyToArray(nums)
//  }

  //Solution-3
  //Time Complexity = O(n), Space Complexity = O(1)
  // Helper function to reverse a subarray from start to end (inclusive)
  def reverse(nums: Array[Int], start: Int, end: Int): Unit = {
    var i = start
    var j = end
    while (i < j) {
      // Swap the elements at i and j
      val temp = nums(i)
      nums(i) = nums(j)
      nums(j) = temp
      // Move the pointers closer
      i += 1
      j -= 1
    }
  }

  // Main function to rotate the array by k positions
  def rotate(nums: Array[Int], k: Int): Unit = {
    val n = nums.length
    // Handle the case when k is larger than n
    val newK = k % n
    // Reverse the whole array
    reverse(nums, 0, n - 1)
    // Reverse the first k elements
    reverse(nums, 0, newK - 1)
    // Reverse the remaining n - k elements
    reverse(nums, newK, n - 1)
  }


}
