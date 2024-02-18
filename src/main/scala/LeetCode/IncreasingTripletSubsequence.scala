package LeetCode

//https://leetcode.com/problems/increasing-triplet-subsequence/description/?envType=study-plan-v2&envId=leetcode-75
object IncreasingTripletSubsequence extends App {

//  def increasingTriplet(nums: Array[Int]): Boolean = {
//
//    val n = nums.length
//
//    val result: Seq[Boolean] = (0 until n)
//      .flatMap(i =>
//        (i + 1 until n)
//          .withFilter(j => nums(i) < nums(j))
//          .flatMap(j =>
//            (j + 1 until n)
//              .withFilter(k => nums(j) < nums(k))
//              .map(k => true)
//          )
//      )
//
//    result.nonEmpty
//
//  }

  def increasingTriplet(nums: Array[Int]): Boolean = {

    val n = nums.length

    val result: Seq[Boolean] = (0 until n)
      .flatMap(i =>
        (i + 1 until n)
          .withFilter(j => nums(i) < nums(j))
          .flatMap(j =>
            (j + 1 until n)
              .withFilter(k => nums(j) < nums(k))
              .map(k => true)
          )
      )

    result.nonEmpty

  }

  println(increasingTriplet(Array(1, 2, 3, 4, 5)))
  println(increasingTriplet(Array(5, 4, 3, 2, 1)))
  println(increasingTriplet(Array(2, 1, 5, 0, 4, 6)))
  println(increasingTriplet(Array(4, 3, 9, 5, 2, 7)))
}

/*

Time complexity - O(n3)
Space complexity - O(1)

for i in 0 until n:
  for j in i+1 until n:
    if (nums[i] < nums[j]):
      for k in j+1 until n:
        if (nums[j] < nums[k]):
          return true

 */

//Super type of range is sequence.