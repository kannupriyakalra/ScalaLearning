package Hackerrank

//https://www.hackerrank.com/challenges/fp-hello-world-n-times/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
object HelloWorldNTimes extends App {

  // Solution- 1: Recursive solution - best solution
  def f(n: Int): Unit = {
    if (n > 0) {
      println("Hello World")
      f(n - 1)
    }
  }

  //Alternate Solution- 2
  def f2(n: Int): Unit = {
    for (i <- 1 to n) println("Hello World")
  }

  //Alternate Solution- 3 : desugar of above for loop solution
  def f3(n: Int): Unit = {
    (1 to n).foreach(i => println("Hello World"))
  }

  //Alternate Solution- 4
  def f4(n: Int): Unit = {
    var i = n
    while (i > 0) {
      println("Hello World")
      i -= 1
    }
  }

  var n = scala.io.StdIn.readInt
  f(n)
  f2(n)
  f3(n)
  f4(n)

}

//println is not a pure function, it has a side effect of printing to the console. So code containing it is not part of pure functional programming paradigm.