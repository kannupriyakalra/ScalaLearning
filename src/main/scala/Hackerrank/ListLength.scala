package Hackerrank

//https://www.hackerrank.com/challenges/fp-list-length/problem?isFullScreen=true

object ListLength {

  //tail recursive n fold left solution are same level optimised, tail recursion is while loop.
  def f(arr: List[Int]): Int = {
    arr.foldLeft(0)((length, a) => length + 1)
  }

  //use below 2 solutions to understand how to convert a recursive solution to tail recursive- this is the only way
  //tail recursive solution- better than recursive
  def myLength(arr: List[Int], count: Int): Int = {
    arr match {
      case _ :: next => myLength(next, count + 1) //head unused so replaced by _ , for this head we increased count by 1
      case Nil => count
    }
  }
  def f1(arr: List[Int]): Int = {
    myLength(arr, 0)
  }

  //recursive solution
  def f2(ls: List[Int]): Int = {
    ls match {
      case head :: tail => f2(tail) + 1 // f5(tail)- gives length of tail + 1- added 1 for length of head
      case Nil => 0
    }
  }

  def f3(arr: List[Int]): Int = { //correct solution but bad as mutable
    var length = 0
    for (num <- arr) length = length + 1
    length
  }

  def f4(arr: List[Int]): Int = { //desugar of f3
    var length = 0
    arr.foreach(num => length = length + 1)
    length
  }

  def main(args: Array[String]): Unit = {

    println(f(List(1, 2, 3, 4, 5)))
    // println(f3(List(1, 2, 3, 4, 5, 6, 7, 8, 9)))
    //println(f2(List(1, 2, 3, 4, 5)))
  }

}

//my wrong attempts and their explanation-
//def f(arr: List[Int]): Int = arr.indexOf(Nil: Int) // o/p = -1, why is this wrong? Nil is of type List[Nothing] we cannot upcast it to Int, its input should
// be Int, we get -1 if element is not found in a list. Nil is not an element of list.

//  def f(arr: List[Int]): Int = arr.indexOf(5) + 1  //how to find last element here and 1, this is a wrong approach as 5 can be present multiple times in a
//  list and in that case it will tell first occurance

//  def f(arr: List[Int]): Int = {
//    var length = 0
//    for (num <- arr if (num != Nil)) yield length = length + 1 //wrong solution, here if is for further filtering the list. for (num <- arr) already runs for all elements of arr, it doesnt
//    run infinitly, it doesnt require stopping condition, its desugar is arr.foreach, yield is not required here as we dont want to
//    return anything for loop. yield means return. This is a mutable solution as var is used. in functional programming we are not allowed to use var.
//    length
//  }

//desugar of f1 implementation
//  def f2(arr: List[Int]): Int = {--wrong
//    var length = 0
//    arr.withFilter(num => (num != Nil)).map(num => length = length + 1)
//    length
//  }
