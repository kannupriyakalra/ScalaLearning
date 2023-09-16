package Hackerrank

//https://www.hackerrank.com/challenges/fp-hello-world-n-times/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
object HelloWorldNTimes extends App {

  def f(n: Int): Unit = {
    if (n > 0) {
      println("Hello World")
      f(n - 1)
    }
  }

  def ff(n: Int): Unit = {
    for (i <- 1 to n) println("Hello World")
  }

  def ff2(n: Int): Unit = {
    (1 to n).foreach(i => println("Hello World")) //desugar of above for loop solution
  }
  def fff(n: Int): Unit = {
    var i = n
    while (i > 0) {
      println("Hello World")
      i -= 1
    }
  }

  var n = scala.io.StdIn.readInt
  f(n)
  ff(n)
  fff(n)

}
