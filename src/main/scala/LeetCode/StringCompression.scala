package LeetCode

import scala.annotation.tailrec

//https://leetcode.com/problems/string-compression/?envType=study-plan-v2&envId=leetcode-75
object StringCompression extends App {

  // Solution-1

  // Time Complexity O(n) Space Complexity O(1)
  @tailrec
  def helper(chars: Array[Char], firstIndex: Int, secondIndex: Int, fillerIndex: Int): Int = {
    //["a","a","b","b","c","c","c"]
//    println("=============DEBUG============")
//    println(s"chars:: ${chars.toList}")
//    println(s"firstIndex:: $firstIndex")
//    println(s"secondIndex:: $secondIndex")
//    println(s"fillerIndex:: $fillerIndex")
//    println("==============================")

    (chars.lift(firstIndex), chars.lift(secondIndex)) match {
      case (Some(firstValue), Some(secondValue)) if firstValue == secondValue =>
        helper(chars, firstIndex, secondIndex + 1, fillerIndex)

      case (Some(firstValue), Some(secondValue)) if firstValue != secondValue && (firstIndex + 1) == secondIndex =>
        chars(fillerIndex) = firstValue
        helper(chars, secondIndex, secondIndex + 1, fillerIndex + 1)

      case (Some(firstValue), Some(secondValue)) if firstValue != secondValue =>
        chars(fillerIndex) = firstValue
        val opFillerIndex = (secondIndex - firstIndex).toString.foldLeft(fillerIndex) {
          case (fillerIndex, digit) =>
            val opFillerIndex = fillerIndex + 1
            chars(opFillerIndex) = digit
            opFillerIndex
        }
        helper(chars, secondIndex, secondIndex + 1, opFillerIndex + 1)

      case (Some(firstValue), None) if (firstIndex + 1) == secondIndex =>
        chars(fillerIndex) = firstValue
        helper(chars, secondIndex, secondIndex + 1, fillerIndex + 1)

      case (Some(firstValue), None) =>
        chars(fillerIndex) = firstValue
        val opFillerIndex = (secondIndex - firstIndex).toString.foldLeft(fillerIndex) {
          case (fillerIndex, digit) =>
            val opFillerIndex = fillerIndex + 1
            chars(opFillerIndex) = digit
            opFillerIndex
        }
        helper(chars, secondIndex, secondIndex + 1, opFillerIndex + 1)

      case _ => fillerIndex
    }
  }

  def compress(chars: Array[Char]): Int = {
    helper(chars, 0, 0, 0)
  }

  //val array = Array[Char]('a', 'a', 'b', 'b', 'c', 'c', 'c')
  //val array = Array[Char]('a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b')
  //  val array = Array[Char]('a', 'b')
  //  val length = compress(array)
  //  print(array.toList.take(length))
      compress(Array[Char]('a','b','b','b','b','b','b','b','b','b','b','b','b'))
  //    compress(Array[Char]('a','b','b','b','b','b','b','b','b','b','b','b','b'))
}

// Solution-2

//object  StringCompression extends  App  {
//  def compress(chars: Array[Char]): Int = { // Time Complexity O(n) Space Complexity O(1)
//    var groupStartIdx = 0
//    var readIdx = 0
//    var writeIdx = 0
//    // ['a','a','b']
//    while (readIdx <= chars.length) {
//      if (readIdx == chars.length || chars(readIdx) != chars(groupStartIdx)) {
//        chars(writeIdx) = chars(groupStartIdx)
//        writeIdx += 1
//
//        if (readIdx - groupStartIdx > 1) {
//          for (digit <- (readIdx - groupStartIdx).toString.toCharArray) {
//            chars(writeIdx) = digit
//            writeIdx += 1
//          }
//        }
//
//        groupStartIdx = readIdx
//      }
//
//      readIdx += 1
//    }
//
//    writeIdx
//  }
//}



