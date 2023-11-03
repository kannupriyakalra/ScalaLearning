//Calculate the nth Fibonacci number, we start counting from Fibonacci(1)=0

//https://www.hackerrank.com/challenges/functional-programming-warmups-in-recursion---fibonacci-numbers/problem?isFullScreen=true

/*
The Fibonacci sequence is a series of numbers where each number is the sum of the two preceding ones, usually starting with 0 and 1. The sequence typically begins as follows:
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, and so on.
Fibonacci(n) = Fibonacci(n-1) + Fibonacci(n-2)

Fibonacci(1) = 0 , n = 1
Fibonacci(2) = 1 , n = 2
Fibonacci(n) = Fibonacci(n-1) + Fibonacci(n-2)  , n > 2

fibonacci(3) = (0+1) = 1
fibonacci(4) = (1+1) = 2
fibonacci(5) = (1+2) = 3
 */
package Hackerrank

import scala.annotation.tailrec
import scala.io.StdIn.readInt

object FibonacciNumbers {
  def fibonacci(n: Int): Int = { //how do i add wrong i/p case here? like case 0 and negative and if i should add it?- in question its mentioned n>0 so we don't have to handle it.
    n match {
      case 1 => 0
      case 2 => 1
      case n => fibonacci(n - 1) + fibonacci(n - 2)
    }
  }

  //Alternate Solution - 2
  def fibonacci2(n: Int): Int = {
    if (n < 3) n - 1 else fibonacci2(n - 1) + fibonacci2(n - 2)
  }

  //Alternate Solution - 3 tail recursive

  // fibonacci3(n)- 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
  //             n- 1, 2, 3, 4, 5, 6, 7,  8,  9, 10, ...
  //             i- 1, 2, 3, 4, 5, 6, 7,  8,  9, 10, ...
  def fibonacci3(n: Int): Int = { // n- index, i- counter
    @tailrec
    def loop(i: Int, lastTerm: Int, secondLastTerm: Int): Int = {
      if (i == n) lastTerm
      else loop(i + 1, lastTerm + secondLastTerm, lastTerm)
    }

    if (n == 1) 0
    else loop(2, 1, 0) //here loop is going from i=2 to n
  }

  /*
  fibonacci3(1)- 0
  fibonacci3(2)- 1
  fibonacci3(3)- iter(2,1,0)- iter(3,1,1) - 1
  fibonacci3(4)-
  fibonacci3(5)-
   */

  //Alternate Solution - 4 tail recursive

  def fibonacci5(n: Int): Int = {
    @tailrec
    def loop(i: Int, lastTerm: Int, secondLastTerm: Int): Int = {
      if (i == 2) lastTerm
      else loop(i - 1, lastTerm + secondLastTerm, lastTerm)
    }

    if (n == 1) 0
    else loop(n, 1, 0) //here loop is going from i=n to 2
  }

  /*
  fibonacci5(5)- loop(5,1,0)- loop(4,1,1) - loop(3,2,1) - loop(2,3,2) - 3
   */

  //Alternate Solution - 5
  def fibonacci4(n: Int): Int = {
    if (n == 1) 0
    else if (n == 2) 1
    else {
      var i = 3
      var lastTerm = 1
      var secondLastTerm = 1
      while (i < n) {
        val nextTerm = lastTerm + secondLastTerm
        secondLastTerm = lastTerm
        lastTerm = nextTerm
        i += 1
      }
      lastTerm
    }
  }

  //Alternate Solution - 6

  def fibonacci6(n: Int): Int = {

    if (n == 1) 0
    else (2 to n).foldLeft((1, 0)) { case ((lastTerm, secondLastTerm), _) => (lastTerm + secondLastTerm, lastTerm) }._1
  }
  /*
  fibonnacci6(3)
  (1,0),2 - (1,1), 3 - (2,1) - 2
   */

  def main(args: Array[String]): Unit = {
    //println(fibonacci(readInt()))
    //println(fibonacci2(readInt()))
    //println(fibonacci3(readInt()))
    println(fibonacci5(readInt()))
  }
}
