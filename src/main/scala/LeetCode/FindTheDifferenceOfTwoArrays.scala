package LeetCode

import scala.annotation.tailrec

//https://leetcode.com/problems/find-the-difference-of-two-arrays/description/?envType=study-plan-v2&envId=leetcode-75
object FindTheDifferenceOfTwoArrays extends App {

  //solution-1

  //  def findDifference(nums1: Array[Int], nums2: Array[Int]): List[List[Int]] = { //time complexity- O(n), space complexity- O(n)
  //
  //    val s1 = nums1.toSet
  //    val s2 = nums2.toSet
  //
  //    List(s1.diff(s2).toList, s2.diff(s1).toList)
  //  }

  //solution-2

//    def findDifference(nums1: Array[Int], nums2: Array[Int]): List[List[Int]] = { //time complexity- O(n), space complexity- O(n)
//
//      val s1 = nums1.toSet
//      val s2 = nums2.toSet
//
//      List(s1.filter(element => !s2.contains(element)).toList, s2.filter(element => !s1.contains(element)).toList)
//    }

  //solution-3

  //  def findDifference(nums1: Array[Int], nums2: Array[Int]): List[List[Int]] = { //time complexity- O(n), space complexity- O(n)
  //
  //    val s1 = nums1.toSet
  //    val s2 = nums2.toSet
  //
  //    val l1 = s1.foldLeft(Set[Int]())((result, elem) => if (s2.contains(elem)) result else result + elem).toList
  //    val l2 = s2.foldLeft(Set[Int]())((result, elem) => if (s1.contains(elem)) result else result + elem).toList
  //    List(l1, l2)
  //  }


  //solution-4

  //  def findDifference(nums1: Array[Int], nums2: Array[Int]): List[List[Int]] = { //time complexity- O(n), space complexity- O(n)
  //
  //    var m1: Map[Int, Boolean] = Map()
  //    for (i <- nums1) {
  //      m1 = m1.updated(i, true)
  //    }
  //
  //    var m2: Map[Int, Boolean] = Map()
  //    for (i <- nums2) {
  //      m2 = m2.updated(i, true)
  //    }
  //
  //    val l1: List[Int] = (for {
  //      k <- m1.keys
  //      if !m2.contains(k)
  //    } yield k).toList
  //
  //    val l2: List[Int] = (for {
  //      k <- m2.keys
  //      if !m1.contains(k)
  //    } yield k).toList //The yield keyword is used to collect these transformed values into a new iterator and then the combined values of for loop are assigned to l2 after transforming into a list.
  //
  //    List(l1, l2)
  //
  //  }

  //solution-5
  def findDifference(nums1: Array[Int], nums2: Array[Int]): List[List[Int]] = {

    @tailrec
    def findUniqueElements(set1: Set[Int], set2: Set[Int], acc: List[Int]): List[Int] = {
      if (set1.isEmpty) acc
      else {
        if (!set2.contains(set1.head)) findUniqueElements(set1.tail, set2, set1.head :: acc)
        else findUniqueElements(set1.tail, set2, acc)
      }
    }

    val s1 = nums1.toSet
    val s2 = nums2.toSet

    val l1 = findUniqueElements(s1, s2, Nil).reverse
    val l2 = findUniqueElements(s2, s1, Nil).reverse

    List(l1, l2)
  }


  println(findDifference(Array(1, 2, 3), Array(2, 4, 6)))


}
