package LeetCode.Easy

//https://leetcode.com/problems/longest-common-prefix/description/
object LongestCommonPrefix extends App {

  // based on question constraints:   //time complexity = O(n2), space complexity = O(n)
  //time complexity = O(n*l), space complexity = O(m); n = size of array, l = max size of string, m = min size of string

  def longestCommonPrefix(strs: Array[String]): String = {

    def loop(i: Int): String = {
      if (checkIndex(strs, i)) loop(i + 1)
      else strs(0).substring(0, i)
    }

    loop(0)
  }

  //to check if i th character is same in all the strings and return true if it is. eg- to check 1st character of all strings.
  def checkIndex(strs: Array[String], i: Int): Boolean = {
    if (i < strs(0).length) { //to avoid StringIndexOutOfBoundsException
      val sample: Char = strs(0)(i) //comparing all strings with a sample
      strs.forall(str => i < str.length && str(i) == sample) //to check if 1st element of every string is f or not.
    }
    else false
  }

  println(longestCommonPrefix(Array("flower", "flow", "flight")))
  println(longestCommonPrefix(Array("dog", "racecar", "car")))
  println(longestCommonPrefix(Array("", "racecar", "car")))

}
