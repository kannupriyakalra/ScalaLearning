package Hackerrank

//https://www.hackerrank.com/challenges/fp-reverse-a-list/problem?isFullScreen=true&h_r=next-challenge&h_v=zen
object ReverseAList extends App {

  def f(ls: List[Int]): List[Int] = {
    ls.foldLeft(Nil: List[Int])((acc, a) => a :: acc)
  }

  //i have to create a new list which is reverse of original list. list is made by prepending.

  println(f(List(1, 2, 3, 4, 5)))

}
