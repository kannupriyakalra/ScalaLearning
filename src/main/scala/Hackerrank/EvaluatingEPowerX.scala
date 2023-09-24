package Hackerrank

//https://www.hackerrank.com/challenges/eval-ex/problem?isFullScreen=true


object EvaluatingEPowerX {

  // 1 + x + x^2/2! + x^3/3! + x^4/4! + ... + x^9/9!
  // 1 + x/1 + x*x/1*2 + x*x*x/1*2*3 + x*x*x*x/1*2*3*4 + ... + x^9/9!
  //  x*x/1*2 * x/3 = x*x*x/1*2*3

  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1) //5! is 5*4!
  }

  def ePowerX(x: Double): Double = {
    (1 to 9).foldLeft(1.0)((acc, n) => (acc + Math.pow(x, n) / factorial(n)))

  }

  // here n is 1 to 9
  //we cab use foldLeft on scenarios other than list, instead of using a for loop on collection of numbers like 0 to 9 for resolving above expression. (0 to 9).foldLeft()

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    for (nItr <- 1 to n) {
      val x = stdin.readLine.trim.toDouble
      println(ePowerX(x))
    }
  }
}



