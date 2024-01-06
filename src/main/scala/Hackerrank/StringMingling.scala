package Hackerrank

import scala.annotation.tailrec

//https://www.hackerrank.com/challenges/string-mingling/problem?isFullScreen=true
object StringMingling extends App {

  //Solution -1

  //  def mingledStrings(p: String, q: String): String = { //time complexity = O(n), space complexity = O(n)
  //
  //    val result: Seq[String] = for (i <- 0 until p.length) yield s"${p(i)}${q(i)}"
  //    println(result)
  //    result.mkString
  //
  //  }


  //Solution -2

  //  println(p.zipWithIndex.map {  //time complexity = O(n), space complexity = O(n)
  //    case (character, index) => s"$character${q(index)}"
  //  }.mkString)


  //Solution -3

  //  def mingledStrings(p: String, q: String): String = { //time complexity = O(n), space complexity = O(n)
  //
  //    val pList = p.toList
  //    val qList = q.toList
  //
  //    def checker(pList: List[Char], qList: List[Char]): List[(Char, Char)] = {
  //
  //      (pList, qList) match {
  //        case (head1 :: tail1, head2 :: tail2) => (head1, head2) :: checker(tail1, tail2)
  //        case (Nil, Nil) => Nil
  //      }
  //    }
  //
  //    checker(pList, qList).map { case (x, y) => s"$x$y" }.mkString
  //  }


  //Solution -4 Best

//  def mingledStrings(p: String, q: String): String = { //time complexity = O(n), space complexity = O(n)
//
//    val pList = p.toList
//    val qList = q.toList
//
//    @tailrec
//    def checker(pList: List[Char], qList: List[Char], result: List[(Char, Char)] = List[(Char, Char)]()): List[(Char, Char)] = {
//
//      (pList, qList) match {
//        case (head1 :: tail1, head2 :: tail2) => checker(tail1, tail2, (head1, head2) :: result)
//        case (Nil, Nil) => result
//      }
//    }
//
//    checker(pList, qList).map { case (x, y) => s"$x$y" }.reverse.mkString
//  }



  //Solution -5

//  def mingledStrings(p: String, q: String, finalString: String = ""): String = { //time complexity = O(n2), space complexity = O(n)
//    if (p.isEmpty && q.isEmpty) return finalString
//    mingledStrings(p.tail, q.tail, finalString + s"${p.head}${q.head}") //s"$finalString${p.head}${q.head}"
//  }

  /*

Why is Solution -5 Terminated due to timeout for big i/p when it is tail recursive?

Terminated due to timeout means time complexity is high ie O(n2) and my function/algorithm is too slow. This is about heap memory and not stack memory.
this is not related to it being tail recursive. Tail recursive is for stack call optimisation, a concept made to not get stack overflow error in case of recursive functions. A tail recursive
function uses only one stack.

its because string append operation has o(n) complexity whereas in solution 4 prepend has O(1) complexity and time complexity is also based on operations used.
  https://docs.scala-lang.org/overviews/collections/performance-characteristics.html , append is concatenation
Important Conclusion: String concatenation and list concatenation are very time expensive operations with O(n) complexity for just this expression" s"$finalString${p.head}${q.head}" so
 overall making it O(n2) , always avoid them and use cons instead as its complexity is O(1)

How to find time complexity of solution-5 which has string interpolation?

The time complexity for a recursive function that has n depth and performs an O(n) time complexity operation in each recursive call is O(n^2).
 This is because the function will make n recursive calls, and each call will take O(n) time to perform the operation. Therefore, the total
 time complexity is O(n) * O(n) = O(n^2).

 val a: String = ""
  val b: String = ""
  val c: String = a + b

  time complexity = O(n), n is length of a + b
  space complexity = O(n)
  Strings in java are immutable, means no character of string can be updated at its position.

 */


  //Solution -6

  //  def mingledStrings(p: List[Char], q: List[Char], finalString: String = ""): String = {
  //    (p.size, q.size) match {
  //      case (1, 1) => return finalString + s"${p.head}${q.head}"
  //      case (_) => mingledStrings(p.tail, q.tail, finalString + s"${p.head}${q.head}")
  //    }
  //  }
  //
  //  println(mingledStrings(p.toList, q.toList)) //Time limit exceeded for big input


  //Solution -7

//    val stdin = scala.io.StdIn
//    val p = stdin.readLine //global variables for solution
//    val q = stdin.readLine
//
//    def mingledStrings(index: Int, finalStringBuilder: StringBuilder): String = { //time complexity = O(n), space complexity = O(n)
//      if (index == p.length) return finalStringBuilder.toString // .toString  makes it a immutable string
//      finalStringBuilder += p(index)
//      finalStringBuilder += q(index)
//      mingledStrings(index + 1, finalStringBuilder)
//    }
//
//    println(mingledStrings(0, new StringBuilder()))

  /*
   StringBuilder is a mutable collection, we use it for performance optimisation.

   This is not a pure function as StringBuilder is coming from outside. This is why its not a functional solution.

  */

  //Solution -8 best
  def mingledStrings(p: String, q: String): String = { //time complexity = O(n), space complexity = O(n)
    p.zip(q).map { case (x, y) => s"$x$y" }.mkString
  }

//mkString is made of StringBuilder and ie why its very optimised.



  //write a foldLeft solution?

  val stdin = scala.io.StdIn
  val p = stdin.readLine
  val q = stdin.readLine

  println(mingledStrings(p, q)) //o/p- hraacnkkeerr
}

/*

i/p-
hacker
ranker

 */
