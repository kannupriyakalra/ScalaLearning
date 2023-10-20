package Hackerrank

import scala.io.StdIn.readInt

// https://www.hackerrank.com/challenges/fp-array-of-n-elements/problem?isFullScreen=true
object ArrayOfNElements extends App {

  def f(num: Int): List[Int] = (1 to num).toList //created a range and converted it to list.

  //alternate way 2-
  def f2(num: Int): List[Int] = List.fill(num)(num) //List is companion object which extends StrictOptimizedSeqFactory and fill is part of that, command click on both to see ie why we could use it to create a list.

  /*how we could put (elem: => A) as num?
  answer-
  def elem() = 5
 // this is a function with no input and output int. function zero
 // (elem: => A) here A is a block code with single value so no need to put {}, num is a block of code with single line, we put {} when we want to bind multiple lines
  val x: () => Int = elem
   */

  //alternate way 3-
  def f3(num: Int): List[Int] = List.fill(num)(0)

  //println(f(readInt)) //readInt reads a value from console/file
  println(f2(readInt))
}
