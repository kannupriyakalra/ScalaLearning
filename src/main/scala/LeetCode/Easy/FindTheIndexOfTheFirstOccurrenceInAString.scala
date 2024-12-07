package LeetCode.Easy

//https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
object FindTheIndexOfTheFirstOccurrenceInAString extends App {

  //time complexity = O(n), space complexity = O(1)
  //  def strStr(haystack: String, needle: String): Int = {
  //    haystack.indexOf(needle)
  //  }

  //brute force approach:
  //time complexity = O(m*n), space complexity = O(1), m = length of haystack, n = length of needle.
  // Even though we made range, but range space complexity = O(1) as range has just 3 variables, it is a space efficient datatype.
  def strStr(haystack: String, needle: String): Int = {

    // i - index of haystack
    def loop(i: Int): Int = {
      if (i >= haystack.length) -1
      else {
        //(i + j < haystack.length) added to ensure StringIndexOutOfBound doesn't happen.
        val found = (0 to needle.length - 1).forall(j => (i + j < haystack.length) && needle(j) == haystack(i + j))
        if (found) i else loop(i + 1)
      }
    }

    loop(0)

  }

  println(strStr("sadbutsad", "sad"))
  println(strStr("leetcode", "leeto"))

}

/*
KMP algorithm is used for substring search, exactly what this question is doing. - we ll do it later.
 */
