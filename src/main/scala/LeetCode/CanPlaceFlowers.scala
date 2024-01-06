package LeetCode

//https://leetcode.com/problems/can-place-flowers/?envType=study-plan-v2&envId=leetcode-75
object CanPlaceFlowers extends App {

  //Solution 1-

  /*
  *
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


    def canPlaceFlowers(flowerbed: Array[Int], n: Int): Boolean = { //time complexity-O(n) , space complexity-O(1)
      val (leftZeroesInRow, maxCountOfFlowersThatCanBePlaced) = flowerbed.foldLeft((1, 0)) {
        case ((countOfZerosInRow, countOfFlowers), el) =>
          el match {
            case 1 => (0, countOfFlowers + howManyFlowersCanBePlacedIn(countOfZerosInRow))
            case 0 => (countOfZerosInRow + 1, countOfFlowers)
          }
      }

      maxCountOfFlowersThatCanBePlaced + howManyFlowersCanBePlacedIn(leftZeroesInRow + 1) >= n
    }

    private def howManyFlowersCanBePlacedIn(i: Int): Int = {
      if (i >= 3) Math.ceil((i - 2) / 2.0).toInt
      else 0
    }

  //Solution 2-

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

  approach-
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



  //println(canPlaceFlowers(Array(1, 0, 0, 0, 1), 1))
  //  println(canPlaceFlowers(Array(1, 0, 0, 0, 1), 2))
  //  println(canPlaceFlowers(Array(1,0,0,0,0,1), 2))
  println(canPlaceFlowers(Array(0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0), 4))


}


/*

wrong approach -logically
0 0 0 0 1 0 1 0 0 0  , n= 4 , true

if a(i)= 0 {
   if a(i+1) = 0 {
       if a(i+2) = 0
       result = result + 1
       else i = i+1
                  }
    else i = i+1
            }
else i = i+1

if result = n true else false

if any 3 zeros together then one flower can be planted.

a function that find no. of 3 adjacent zeros in an array- wrong approach

def canPlaceFlowers(flowerbed: Array[Int], n: Int): Boolean = {


  val result = flowerbed.indices.foldLeft(0)((acc, i) =>

    (flowerbed.lift(i), flowerbed.lift(i + 1), flowerbed.lift(i + 2)) match {
      case (Some(0), Some(0), Some(0)) => (acc + 1)
      case _ => acc
    }
  )
  if (result >= n) true else false

}

  //      if (flowerbed.lift(i) == flowerbed.lift(i + 1) == flowerbed.lift(i + 2) == Some(0)) (acc + 1) find another way to do this

 */
