package LeetCode.Easy

// https://leetcode.com/problems/length-of-last-word/
/*
1. ignore space characters
2. count non space characters
 */
object LengthOfLastWord extends App {

  // Solution 1:
  //time complexity = O(n), space complexity = O(1)
  //  def lengthOfLastWord(s: String): Int = {
  //    var count = 0
  //    var i = s.length - 1
  //
  //    //step 1:
  //    while (i >= 0 && s(i) == ' ') i = i - 1
  //
  //    //step 2:
  //    while (i >= 0 && s(i) != ' ') {
  //      count = count + 1
  //      i = i - 1
  //    }
  //
  //    count
  //  }

  // Solution 2: functional solution
  //time complexity = O(n), space complexity = O(n)
  def lengthOfLastWord(s: String): Int = {
    s.reverse
      .dropWhile(_ == ' ') //.dropWhile(c => c == ' '), // "   fly me   to   the moon", removed spaces
      .takeWhile(_ != ' ') // "moon"
      .length
  }

  println(lengthOfLastWord("Hello World"))
  println(lengthOfLastWord("   fly me   to   the moon  "))
  println(lengthOfLastWord("luffy is still joyboy"))
  println(lengthOfLastWord(""))
  println(lengthOfLastWord(" "))

}
