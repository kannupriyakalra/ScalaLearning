package LeetCode.Easy

// https://leetcode.com/problems/valid-palindrome/description/

/*
1. Filter out the string.
2. Check if its a palindrome.
 */

// Time complexity- O(n), Space Complexity- O(n)
object ValidPalindrome extends App {

  def isPalindrome(s: String): Boolean = {

    val filteredString = s.filter(c => c.isLetterOrDigit).toLowerCase()

    filteredString == filteredString.reverse

  }

  println(isPalindrome("A man, a plan, a canal: Panama"))
  println(isPalindrome("race a car"))
  println(isPalindrome(" "))

}

/*
Space Complexity = O(3n) = O(n) ; filtered string, lower case string, reverse string - 3 times string is made
Time Complexity = O(3n) = O(n) ; filtered string, lower case string, reverse string - 3 times string is traversed
 */
