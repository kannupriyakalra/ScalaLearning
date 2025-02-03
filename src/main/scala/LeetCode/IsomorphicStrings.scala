package LeetCode

//https://leetcode.com/problems/isomorphic-strings/
object IsomorphicStrings extends App {

  //paper-12134
  //  def encoding(str: String): List[Int] = {
  //
  //    var a: Map[Char, Int] = Map()
  //    var i = 1
  //
  //    for (c <- str) {
  //      if (a.get(c).isEmpty) {
  //        a = a + ((c, i))
  //        i = i + 1
  //      }
  //    }
  //
  //    str.toList.map(c => a(c))
  //
  //  }

  //Solution 2-
  def encoding(str: String): List[Int] = { //time complexity = O(n), space complexity = O(n)

    val initial: (Map[Char, Int], Int) = (Map(), 1)
    val a = (str.foldLeft(initial) { case ((a, i), c) =>
      if (a.contains(c)) (a, i)
      else (a + ((c, i)), i + 1)
    })._1

    str.toList.map(c => a(c))

  }

  //def isIsomorphic(s: String, t: String): Boolean = encoding(s) == encoding(t)

  def isIsomorphic(s: String, t: String): Boolean = encoding(s).equals(encoding(t))

  println(isIsomorphic("paper", "title"))
}

/*
Pseudo code-
1. Create encoding for every string using a Map where every character of the string is key and a number is its corresponding value.
2. Convert string to its encoding.
3. If encoding is same for both the string they are isomorphic.


Solution 2-
time complexity analysis:

The foldLeft function is applied to the input string str, and each character is processed once.
Inside the foldLeft block, the operations are constant time for each character.
The Map lookup and insertion operations have an average time complexity of O(1).
The foldLeft iteration is performed n times, where n is the length of the input string.
Therefore, the overall time complexity is O(n), where n is the length of the input string.
 */

//why am i not able to do command click on == on line 36? as the code to convert == in .equals is part of scalac compiler