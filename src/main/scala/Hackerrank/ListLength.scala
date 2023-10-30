package Hackerrank

//to write your own implementation of a  count, size or length operator.
//https://www.hackerrank.com/challenges/fp-list-length/problem?isFullScreen=true

object ListLength {

  //tail recursive n fold left solution are same level optimised, tail recursion is while loop.
  def f(l: List[Int]): Int = {
    l.foldLeft(0)((length, a) => length + 1)
  }

  //Alternate Solution- 2: use below 2 solutions to understand how to convert a recursive solution to tail recursive, this is the only way
  // tail recursive solution- better than recursive
  def f2(ls: List[Int]): Int = {   //recursive solution
    ls match {
      case head :: tail => f2(tail) + 1 // f5(tail)- gives length of tail + 1- added 1 for length of head
      case Nil => 0
    }
  }


  //Alternate Solution- 3
  def listLength(l: List[Int], count: Int): Int = {
    l match {
      case _ :: next => listLength(next, count + 1) //head unused so replaced by _ , for this head we increased count by 1
      case Nil => count
    }
  }
  def f3(l: List[Int]): Int = {   // tail recursive solution
    listLength(l, 0)
  }


  //Alternate Solution- 4
  def f4(l: List[Int]): Int = { //correct solution but bad as mutable
    var length = 0
    for (num <- l) length = length + 1
    length
  }


  //Alternate Solution- 5
  def f5(l: List[Int]): Int = { //desugar of f4
    var length = 0
    l.foreach(num => length = length + 1)
    length
  }



  def main(args: Array[String]): Unit = {

    println(f(List(1, 2, 3, 4, 5)))
    // println(f3(List(1, 2, 3, 4, 5, 6, 7, 8, 9)))
    //println(f2(List(1, 2, 3, 4, 5)))
  }

}

/*
my wrong attempts and their explanation-
//def f(arr: List[Int]): Int = arr.indexOf(Nil: Int) // o/p = -1, why is this wrong? Nil is of type List[Nothing] we cannot upcast it to Int, its input should
// be Int, we get -1 if element is not found in a list. Nil is not an element of list.

//  def f(arr: List[Int]): Int = arr.indexOf(5) + 1  //how to find last element here and 1, this is a wrong approach as 5 can be present multiple times in a
//  list and in that case it will tell first occurrence

def f7(arr: List[Int]): Int = {
  var length = 0
  for (num <- arr if (num != Nil)) yield length = length + 1 //wrong solution, here if is for further filtering the list. for (num <- arr) already runs for all elements of arr, it doesnt
  //    run infinitely, it doesnt require stopping condition, its desugar is arr.foreach, yield is not required here as we dont want to
  //    return anything for loop. yield means return. This is a mutable solution as var is used. in functional programming we are not allowed to use var.
  length
}

//desugar of f7 implementation
//  def f8(arr: List[Int]): Int = {--wrong
//    var length = 0
//    arr.withFilter(num => (num != Nil)).map(num => length = length + 1)
//    length
//  }

 */
