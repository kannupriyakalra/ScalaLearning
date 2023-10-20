package Hackerrank

import scala.annotation.tailrec
import scala.math.abs


//https://www.hackerrank.com/challenges/fp-update-list/problem?isFullScreen=true&h_r=next-challenge&h_v=zen
object UpdateList {

  def f(ls: List[Int]): List[Int] = { //O(n) + O(n)(-for reverse done in the end) = O(n)as going to left to right only once.

    ls.foldLeft(Nil: List[Int])((resultList, elem) => if (elem < 0) (-1) * elem :: resultList else elem :: resultList).reverse

  }

  def f2(ls: List[Int]): List[Int] = { //O(n)
    ls.foldRight(Nil: List[Int])((elem, resultList) => if (elem < 0) (-1) * elem :: resultList else elem :: resultList)
  }

  def f3(ls: List[Int]): List[Int] = { //O(n) as list is traversed only once, recursion is also like a loop, recursive call is made n times until n elements of list get nil.
    ls match {
      case ::(head, next) => head.abs :: f3(next)
      case Nil => Nil
    }
  }

  @tailrec
  def absList(inputList: List[Int], resultList: List[Int]): List[Int] = { //O(n) as input list is traversed once
    inputList match {
      case head :: tail => absList(tail, head.abs :: resultList)
      case Nil => resultList.reverse //as input list become nil we return the resultList
    }
  }

  def f4(ls: List[Int]): List[Int] = {
    absList(ls, Nil)
  }

  def f5(ls: List[Int]): List[Int] = { //bad solution - as time complexity O(n2)- n times concatenating with a list of n elements

    ls.foldLeft(Nil: List[Int])((resultList, elem) => if (elem < 0) resultList ::: List((-1) * elem) else resultList ::: List(elem))

  }

  def f6(ls: List[Int]): List[Int] = { //O(n) as list is traversed once while implementing the function
    ls.map(i => i.abs) //turn implicit hint on , command click on intWrapper and abs
    //ls.map(_.abs)
  }

  def f7(ls: List[Int]): List[Int] = {
    ls.map(abs(_))
    //ls.map(x => abs(x))
  }


  def main(args: Array[String]): Unit = {

    //println(f(List(2, -4, 3, -1, 23, -4, -54)))
    //println(f1(List(2, -4, 3, -1, 23, -4, -54)))
    //println(f2(List(2, -4, 3, -1, 23, -4, -54)))
    //println(f4(List(2, -4, 3, -1, 23, -4, -54)))
    // println(f5(List(2, -4, 3, -1, 23, -4, -54)))
    println(f6(List(2, -4, 3, -1, 23, -4, -54)))

  }

}
