package LeetCode

//https://leetcode.com/problems/can-place-flowers/?envType=study-plan-v2&envId=leetcode-75
object CanPlaceFlowers extends App {

  //Solution 1-
  /*
  Solution 1 approach-
  0 0 0 0 1 0 1 0 0 0  , n= 4 , true, sum = 2
  1 0 1 0 1 0 1 0 1 0  , sum = 5, diff = 5-2 = 3 (checked Left to right)
  1 0 1 0 1 0 1 0 0 1  , sum = 5, (checked right to left)

  1.check if left and right index are valid (i - 1 >= 0 ) and exists (i - 1 < 0) and respective elements are 0 and current element is also zero then change current element to 1.

  this is a functional solution as there is no side effect as we created a copy of array and changed that and not the original input array which is a global value.
   */
  def canPlaceFlowers(flowerbed: Array[Int], n: Int): Boolean = { //time complexity = O(n) , space complexity = O(n)

    val l = flowerbed.length

    val arr = Array.copyOf(flowerbed, l)
    val initialCapacity = arr.sum

    for (i <- 0 until l) {
      if ((i - 1 >= 0 && arr(i - 1) == 0 || i - 1 < 0) && arr(i) == 0 && (i + 1 < l && arr(i + 1) == 0 || i + 1 >= l))
        arr(i) = 1
    }

    val newCapacity = arr.sum
    val increasedCapacity = newCapacity - initialCapacity

    if (increasedCapacity >= n) true else false

  }

  println(canPlaceFlowers(Array(0, 0, 1, 0, 1), 1))
  //println(canPlaceFlowers(Array(1, 0, 0, 0, 1), 1))
  //println(canPlaceFlowers(Array(1, 0, 0, 0, 1), 2))
  //println(canPlaceFlowers(Array(1,0,0,0,0,1), 2))
  //println(canPlaceFlowers(Array(0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0), 4))

}

//Solution 2- bad solution as complex

/*
*  Solution 2 approach-
*
* no of zero = 1+1 +1  = 3
    [1,0,1,0]
  L1 -> [0,0,0,1,0]
  countOfZerosInRow = 4  countOfFlowers = 0
  countOfZerosInRow = 0  countOfFlowers = 0 + f(4)
*
* 0[1]0 = 0
* 0[0]0 = 1
* 0[0,1]0 = 0
* 0[0,0,1]0 = 1
* ===============
* howManyFlowersCanBePlacedIn(1) = 0
* howManyFlowersCanBePlacedIn(2) = 0
* ===============
* 0[0,0,0]0
* ===============
* howManyFlowersCanBePlacedIn(3) = 1
* howManyFlowersCanBePlacedIn(4) = 1
* howManyFlowersCanBePlacedIn(5) = 2
* howManyFlowersCanBePlacedIn(6) = 2
* howManyFlowersCanBePlacedIn(7) = 3
* howManyFlowersCanBePlacedIn(8) = 3
*
* howManyFlowersCanBePlacedIn(n) = if(n>=3) ceil((n-2)/2) else 0
*
*
* */


//    def canPlaceFlowers(flowerbed: Array[Int], n: Int): Boolean = { //time complexity-O(n) , space complexity-O(1)
//      val (leftZeroesInRow, maxCountOfFlowersThatCanBePlaced) = flowerbed.foldLeft((1, 0)) {
//        case ((countOfZerosInRow, countOfFlowers), el) =>
//          el match {
//            case 1 => (0, countOfFlowers + howManyFlowersCanBePlacedIn(countOfZerosInRow))
//            case 0 => (countOfZerosInRow + 1, countOfFlowers)
//          }
//      }
//
//      maxCountOfFlowersThatCanBePlaced + howManyFlowersCanBePlacedIn(leftZeroesInRow + 1) >= n
//    }
//
//    private def howManyFlowersCanBePlacedIn(i: Int): Int = {
//      if (i >= 3) Math.ceil((i - 2) / 2.0).toInt
//      else 0
//    }

//Solution 3- bad solution as complex

//  def canPlaceFlowers(flowerbed: Array[Int], n: Int): Boolean = {
//
//    def counter(flowerbed: Array[Int], index: Int, left: Option[Int], main: Option[Int], right: Option[Int], availablePlots: Int): Int = { //time complexity-O(n) , space complexity-O(1)
//      if (flowerbed.size == index) return availablePlots
//      (left, main, right) match {
//        case (None, Some(0), None) | (None, Some(0), Some(0)) | (Some(0), Some(0), Some(0)) | (Some(0), Some(0), None) =>
//          counter(flowerbed, index + 1, Some(1), flowerbed.lift(index + 1), flowerbed.lift(index + 2), availablePlots + 1)
//        case _ =>
//          counter(flowerbed, index + 1, flowerbed.lift(index), flowerbed.lift(index + 1), flowerbed.lift(index + 2), availablePlots)
//      }
//    }
//
//    counter(flowerbed, 0, None, flowerbed.lift(0), flowerbed.lift(1), 0) >= n
//  }

/*

Solution 3 approach-
(None, Some(0), None) --single element case
(None, Some(0), Some(0)) --first two element case
(Some(0), Some(0), Some(0)) --normal case
(Some(0), Some(0), None) --last two element case

we ll update the main value in (left, main, right) while recursively moving the counter forward.


  N [1, 0, 0, 0, 0, 1]
    [1, 0, 1, 0, 0, 1]

    [1, 0, 1, 0, 0, 0] N
    [1, 0, 1, 0, 0, 1] N N

*/



