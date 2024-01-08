package LeetCode

//https://leetcode.com/problems/unique-number-of-occurrences/?envType=study-plan-v2&envId=leetcode-75
object UniqueNumberOfOccurrences extends App {

  //Solution- 1

  //functional solution
//  def uniqueOccurrences(arr: Array[Int]): Boolean = { //time complexity = O(n), space complexity =  O(n)
//
//    var noOfOccurenceMap: Map[Int, Int] = Map()
//    for (i <- 0 until arr.length) {
//      if (noOfOccurenceMap.contains(arr(i))) noOfOccurenceMap = noOfOccurenceMap.updated(arr(i), noOfOccurenceMap(arr(i)) + 1)
//      else noOfOccurenceMap = noOfOccurenceMap.updated(arr(i), 1)
//    }
//
//    val countOfValuesInMap = noOfOccurenceMap.values.size
//    val noOfUniqueValuesInMap = noOfOccurenceMap.values.toSet.size
//
//    countOfValuesInMap == noOfUniqueValuesInMap
//
//  }

  //Solution- 2

  def uniqueOccurrences(arr: Array[Int]): Boolean = {

    val counter = arr.foldLeft(Map[Int, Int]().withDefaultValue(0)) {
      case (counter: Map[Int, Int], number: Int) => counter.updated(number, counter(number) + 1)
    }
    counter.values.toSet.size == counter.values.size
  }


  println(uniqueOccurrences(Array(1, 2, 2, 1, 1, 3)))
}
