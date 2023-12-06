package Hackerrank

object Fibonacci extends App {

  val MOD = 100000007

  val fib = new Array[Int](10001)

  fib(0) = 0
  fib(1) = 1
  for (i <- 2 to 10000) {
    fib(i) = (fib(i - 1) + fib(i - 2)) % MOD //O(1)
  }

  val stdin = scala.io.StdIn
  val noOfTestCases = stdin.readLine.trim.toInt

  for (i <- 1 to noOfTestCases){ //O(noOfTestCases)
    val valueOfEachTestCase = stdin.readLine.trim.toInt
    println(fib(valueOfEachTestCase))
  }
}

//Time complexity = O(1) + O(noOfTestCases) = O(T), array and value are created for 10^4 elements in line 12 despite any value of noOfTestCases, valueOfEachTestCase.
//Space complexity = O(1)