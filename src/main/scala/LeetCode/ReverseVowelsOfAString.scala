package LeetCode

//https://leetcode.com/problems/reverse-vowels-of-a-string/?envType=study-plan-v2&envId=leetcode-75
object ReverseVowelsOfAString extends App{

  //Solution- 1
  def reverseVowels(s: String): String = {
    val VOWELS = "aeiouAEIOU"
    val vowels: Iterator[Char] = s.reverseIterator.filter(VOWELS.contains(_))
    val result: String  =s.map(c => if (VOWELS.contains(c)) vowels.next() else c)
    result
  }

  //Solution- 2

//  val isVowel: Map[Char, Boolean] = Map('a' -> true, 'e' -> true, 'i' -> true, 'o' -> true, 'u' -> true, 'A' -> true, 'E' -> true, 'I' -> true, 'O' -> true, 'U' -> true).withDefaultValue(false)
//
//  def helper(forwardIndex: Int, reverseIndex: Int, acc: Vector[Char]): Vector[Char] = {
//    if (forwardIndex >= reverseIndex) return acc
//    (isVowel(acc(forwardIndex)), isVowel(acc(reverseIndex))) match {
//      case (true, true) =>
//        val temp = acc(forwardIndex)
//        val updatedVector = acc.updated(forwardIndex, acc(reverseIndex))
//          .updated(reverseIndex, temp)
//        helper(forwardIndex + 1, reverseIndex - 1, updatedVector)
//
//      case (false, true) => helper(forwardIndex + 1, reverseIndex, acc)
//      case (true, false) => helper(forwardIndex, reverseIndex - 1, acc)
//      case (false, false) => helper(forwardIndex + 1, reverseIndex - 1, acc)
//    }
//  }
//
//  def reverseVowels(s: String): String = {
//    helper(0, s.length - 1, s.toVector).mkString
//  }

  //Solution- 3

//  val vowels = Set('a', 'e', 'i', 'o', 'u')
//
//  def reverseVowels(s: String): String = {
//    if (s.length == 1) return s
//
//    var l = 0;
//    var r = s.length - 1
//    val buff: Array[Char] = new Array(s.length)
//
//    while (l <= r) {
//
//      while (l <= r && !vowels.contains(s.charAt(l).toLower)) {
//        buff(l) = s.charAt(l)
//        l += 1
//      }
//      while (r >= l && !vowels.contains(s.charAt(r).toLower)) {
//        buff(r) = s.charAt(r)
//        r -= 1
//      }
//
//      if (l <= r) {
//        // Swap
//        buff(l) = s.charAt(r)
//        buff(r) = s.charAt(l)
//        l += 1
//        r -= 1
//      }
//    }
//
//    buff.mkString
//  }

  //Solution- 4

//  private val vowels = Set('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

//  def reverseVowels(s: String): String = {
//    reverseVowels(s.toCharArray, 0, s.length - 1)
//  }
//
//  private def reverseVowels(s: Array[Char], leftIdx: Int, rightIdx: Int): String = {
//    if (leftIdx >= rightIdx) s.mkString
//    else if (!vowels.contains(s(leftIdx))) reverseVowels(s, leftIdx + 1, rightIdx)
//    else if (!vowels.contains(s(rightIdx))) reverseVowels(s, leftIdx, rightIdx - 1)
//    else {
//      val tmp = s(leftIdx)
//      s(leftIdx) = s(rightIdx)
//      s(rightIdx) = tmp
//      reverseVowels(s, leftIdx + 1, rightIdx - 1)
//    }
//  }

  println(reverseVowels("leetcode"))


}
