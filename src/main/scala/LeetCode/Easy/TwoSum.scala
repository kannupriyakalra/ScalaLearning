package LeetCode.Easy

//https://leetcode.com/problems/two-sum/description/

/*
Strategy-

nums = [2,7,11,15], target = 9

9-2 = 7
find 7 in array

for each number n :
  find difference d = target - n in rest of the array
  if d is found
  return index of d and n

 */
object TwoSum extends App {

  // Time complexity- O(n2), Space Complexity- O(n2)
  //  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
  //
  //    val result: Seq[Array[Int]] = for {
  //      i <- (0 until nums.length)
  //      j <- (i + 1 until nums.length)
  //      if (nums(i) + nums(j) == target)
  //    } yield Array(i, j)
  //
  //    //desugar:
  ////    val result: Seq[Array[Int]] = (0 until nums.length)
  ////      .flatMap(i =>
  ////        (i + 1 until nums.length)
  ////          .withFilter(j => (nums(i) + nums(j) == target))
  ////          .map(j => Array(i, j))
  ////      )
  //
  //    result.head
  //
  //  }

  //  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
  //
  //    val valueIndexMap: Map[Int, Array[(Int, Int)]] = nums.zipWithIndex.groupBy(x => x._1)
  //    println(valueIndexMap)
  //    val t: Map[Int, Array[Int]] = valueIndexMap.map(x => (x._1, x._2.map(_._2)))
  //    println(t)
  //
  //
  //    val result = for {
  //      element <- nums
  //      if valueIndexMap.contains(target - element) && (valueIndexMap(element) != valueIndexMap(target - element))
  //    } yield Array(valueIndexMap(element), valueIndexMap(target - element))
  //
  //
  //    result.head
  //  }

  // Time complexity- 3n = O(n), Space Complexity- 2n = O(n)
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val valuesWithIndex: Array[(Int, Int)] = nums.zipWithIndex
    val valueIndexMap: Map[Int, Int] = valuesWithIndex.toMap

    val result: Seq[Array[Int]] = for {
      (value, index) <- valuesWithIndex
      if valueIndexMap.contains(target - value) && valueIndexMap(target - value) != index
    } yield Array(index, valueIndexMap(target - value))

    println(result.map(_.toList))
    result.head
  }

  println(twoSum(Array(2, 7, 11, 15, 2, 2), 9).mkString(","))
  println(twoSum(Array(3, 2, 4), 6).mkString(","))
  println(twoSum(Array(3, 3, 3), 6).mkString(","))
}
