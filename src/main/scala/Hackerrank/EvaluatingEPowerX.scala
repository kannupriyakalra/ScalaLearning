package Hackerrank

//https://www.hackerrank.com/challenges/eval-ex/problem?isFullScreen=true

object EvaluatingEPowerX {

  // e^x = 1 + x + x^2/2! + x^3/3! + x^4/4! + ... + x^9/9!

  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1)
  }
  def ePowerX(x: Double): Double = { //time complexity- O(1) as (1 to 9 ) is constant, no. of iterations are constant for all values of x.
    (1 to 9).foldLeft(1.0)((acc, n) => (acc + Math.pow(x, n) / factorial(n)))  // here n is 1 to 9
  }

  //this is an example of using foldLeft on scenarios other than list.
  // Instead of using a for loop on collection of numbers like 0 to 9 for resolving above expression, we can use "(0 to 9).foldLeft()"
  //we took range as (1 to 9) and not (0 to 9) as in foldLeft default value is given as 0th term 1.0.

  //Alternate Solution- 2
  // 1 + x + x^2/2! + x^3/3! + x^4/4! + ... + x^9/9!
  // 1 + x/1 + x*x/1*2 + x*x*x/1*2*3 + x*x*x*x/1*2*3*4 + ... + x^9/9!
  //given previous element, next element is previous element * x/n ie next term = previous term * x/n
  //  x*x/1*2 * x/3 = x*x*x/1*2*3, create new term using previous term.

  //x/n is a small no. and in 1st solution x^9/x/n! is huge a huge no. So this solution is more optimised, we don't have to find factorial repeatedly.

  def ePowerX2(x: Double): Double = {
    (1 to 9).foldLeft(1.0, 1.0)((tuple, n) => (tuple._1 + tuple._2 * x / n, tuple._2 * x / n))
  }._1
  //here tuple is (result, previous term), previous term is used to calculate current term


  //Alternate Solution- 3 by pattern match
  def ePowerX3(x: Double): Double = {
    (1 to 9).foldLeft(1.0, 1.0) { case ((result, previousTerm), n) => (result + previousTerm * x / n, previousTerm * x / n) }
  }._1 //this is to get back result

  //based on default value given in foldLeft it knows that pattern match has to be applied on (result, previous_term) this part only.

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    for (nItr <- 1 to n) {
      val x = stdin.readLine.trim.toDouble
      //println(ePowerX(x))
      //println(ePowerX2(x))
      println(ePowerX3(x))
    }
  }
}

/*
Note:
 1.Tuples cannot be directly destructured in method or function parameters.
      Either create a single parameter accepting the Tuple2{Alternate Solution- 2 above},
      or consider a pattern matching anonymous function: `{ case (result, previous_term) => ... }
      writing a tuple as (result, previous_term) means destructuring it which cannot be done unless a case statement is used.

  2. Pattern matching is used to dissect/deconstruct an existing object.
 */


