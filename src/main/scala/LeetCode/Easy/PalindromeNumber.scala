package LeetCode.Easy

// https://leetcode.com/problems/palindrome-number/description/
object PalindromeNumber extends App {

  /**
   * For i in 0 to length/2:
   * if x[i] == x[x.length-1-i] true and continue
   * else false
   */

  // Time complexity- O(n), Space Complexity- O(n); n = no. of digits in the integer
  def isPalindrome(x: Int): Boolean = {

    val str: String = x.toString
    val length = str.length

    (0 to length / 2).forall(i => str(i) == str(length - 1 - i))

  }

  // Time complexity- O(n), Space Complexity- O(n)
    def isPalindrome2(x: Int): Boolean = {

      val charArray = x.toString
      val reverseCharArray = charArray.reverse

      charArray == reverseCharArray

    }

  println(isPalindrome(121))
  println(isPalindrome(-121))
  println(isPalindrome(10))

}

