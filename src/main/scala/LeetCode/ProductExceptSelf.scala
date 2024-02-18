package LeetCode

//https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=leetcode-75
object ProductExceptSelf {
  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    val n = nums.length
      /*
      *         [(result, productSoFar) , num ]
      *         [([1], 1), 1]
      *         [([1,1], 1), 2]
      *         [([1,1,2], 2), 3]
      *         [([1,1,2,6], 6), 4]
      *   o/p = [([1,1,2,6,24], 24), _]
      * */

    /*
          *         [(result, productSoFar) , num ]
          *         [([1], 1), 1]
          *         [([1,1], 1), 2]
          *         [([1,1,1], 2), 3]
          *         [([1,1,1,2], 6), 4]
          *   o/p = [([1,1,1,2,6], 24), _]
          * */
    // Left pass: compute the product of elements to the left of each element
    val leftProducts: Array[Int] = nums.foldLeft((List(1), 1)) { case ((result, productSoFar), num) =>
      val newProduct = productSoFar * num
      (result :+ productSoFar, newProduct)
    }._1.toArray

    println("LEFT PASS RESULT :: ", leftProducts.toList)

    // Right pass: compute the product of elements to the right of each element
    val endResult = nums.foldRight((leftProducts, 1)) { case (num, (result, productSoFar)) =>
      val newProduct = productSoFar * num
      (result.updated(0, result(0) * productSoFar), newProduct)
    }._1

    println("RIGHT PASS RESULT :: ", endResult.toList)
    endResult
  }


  def main(args: Array[String]): Unit = {
    val nums = Array(1, 2, 3, 4)
    val result = productExceptSelf(nums)
    println(result.mkString(", ")) // Output: 24, 12, 8, 6
  }


}
