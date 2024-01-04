package Hackerrank

import scala.annotation.tailrec

//https://www.hackerrank.com/challenges/string-o-permute/problem?isFullScreen=true


object StringOPermute extends App {

  //Solution 1 -

//    def swapTheCharacters(s: String): String = { //time complexity = O(n), space complexity = O(n)
//      def swapHelper(string: List[Char]): List[Char] = string match {
//        case Nil => Nil
//        case x :: y :: rest => y :: x :: swapHelper(rest)
//      }
//
//      swapHelper(s.toList).mkString("")
//    }

  //Solution 2 -

  def swapTheCharacters(s: String): String = { //time complexity = O(n), space complexity = O(n)
    @tailrec
    def swapHelper(string: List[Char], result: List[Char] = List()): List[Char] = string match {
      case Nil => result
      case x :: y :: tail => swapHelper(tail, x :: y :: result)
    }
    swapHelper(s.toList).reverse.mkString("")
  }

  //Solution 3 -

  //  def swapTheCharacters(inputString: String): String = { //time complexity = O(n), space complexity = O(n)
  //
  //    val charList = inputString.toList
  //    val ListOfTwo: List[List[Char]] = charList.grouped(2).toList
  //
  //    def swap(toSwapList: List[Char]): List[Char] = {
  //      List(toSwapList(1), toSwapList(0))
  //    }
  //
  //    val swappedList: List[List[Char]] = ListOfTwo.map(x => swap(x))
  //    val resultList: String = swappedList.flatMap(x => x).mkString
  //    resultList
  //
  //  }

  val stdin = scala.io.StdIn

  val noOfTestCases = stdin.readLine.trim.toInt

  for (t <- 1 to noOfTestCases) {
    val inputString = stdin.readLine
    println(swapTheCharacters(inputString))
  }

}


//Solution 4-

//import scala.io.StdIn._
//object StringOPermute {
//
//  def stringOPermute(word: Array[Char], index: Int , accumulator:Vector[Char] ): String = { //time complexity = O(n), space complexity = O(n)
//    if(index == word.size) return accumulator.mkString
//    stringOPermute(word, index + 2, accumulator.updated(index, word(index+1)).updated(index+1, word(index)) )
//  }
//
//
//  def main(args: Array[String]) {
//    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution
//*/
//    val numberOfTestCase:Int = readInt()
//    (1 to numberOfTestCase).map( _ =>{
//      val word: String = readLine()
//      stringOPermute(word.toArray, 0 , word.toVector )
//    }).foreach(println)
//  }
//}

