package Hackerrank

//https://www.hackerrank.com/challenges/fp-filter-positions-in-a-list/problem?isFullScreen=true
object FilterPositionsInAList {
  def f(ls: List[Int]): List[Int] = {
    f2(ls, 0)
  }

  def f2(ls: List[Int], idx: Int): List[Int] = {
    ls match {
      case ::(head, next) => if (idx % 2 == 0) f2(next, idx + 1) else head :: f2(next, idx + 1) //complexity is O(n) as list is traversed once
      case Nil => Nil
    }
  }

  //alternate way 2-
  def f0(ls: List[Int]): List[Int] = {
    ls match {
      case ::(head, next) => if (ls.indexOf(head) % 2 == 0) f(next) else head :: f(next) //complexity is O(n2) if we use indexOf as complexity of indexOf is O(n) and complexity of traversing list is O(n)
      case Nil => Nil
    }
  }

  //alternate way 3-

  def f3(ls: List[Int]): List[Int] = ls.zipWithIndex.filter { case (value, index) => index % 2 != 0 }.map(_._1)
  //map(_._1) is map(x:(Int,Int) =>x._1)

  //alternate way 4-

  def f4(ls: List[Int]): List[Int] = ls
    .foldLeft(0, Nil: List[Int]) { case ((idx, resultList), a) => (idx + 1, if (idx % 2 == 0) resultList else a :: resultList) }
    ._2
    .reverse

  /*
 - on List(2, 5, 3, 4, 6, 7, 9, 8) foldLeft is applied from left to right with z is of type tuple (0, Nil: List[Int]),
 - consider "((idx, result), a) => (idx + 1, if (idx % 2 == 0) result else a :: result)" as function ie (acc,a) => acc where acc is accumulated result ie (idx, result) and a is each element on list.
 - case is another way of writing this, case is for pattern matching, we did pattern match in  input arguments itself, this is a new syntax.
 - override def foldLeft[B](z: B)(op: (B, A) => B): B
  op will run on every A, A is element of list, folding the list from left to right while updating the state.
 -   _2 is done on result of foldLeft and on that result reverse is done.

  - Dry run-
  List(2, 5, 3, 4, 6, 7, 9, 8)
  case ((0, Nil), 2) => (1, Nil ) = (1, Nil)
  case ((1, Nil), 5) => (2, 5 :: Nil ) = (2, List(5) )
  case ((2, List(5) ), 3) => (3, List(5) )
  case ((3, List(5) ), 4) => (4, 4 :: List(5) ) = (4, List(4, 5) )
  case ((4, List(4, 5) ), 6) => (5, List(4, 5) )
  case ((5, List(4, 5) ), 7) => (6, 7 :: List(4, 5) ) = (6, List(7, 4, 5) )
  case ((6, List(7, 4, 5) ), 9) => (7, List(7, 4, 5) )
  case ((7, List(7, 4, 5) ), 8) => (8, 8 :: List(7, 4, 5) ) = (8, List(8, 7, 4, 5) )

  (8, List(8, 7, 4, 5) )._2.reverse = List(5, 4, 7, 8)
   */

  def f5(ls: List[Int]): List[Int] = ls
    .foldLeft(0, Nil: List[Int]) ( (acc, a) => (acc._1 + 1, if (acc._1 % 2 == 0) acc._2 else a :: acc._2) )
    ._2
    .reverse


  def main(args: Array[String]): Unit = {

    //f0(List(0, 1, 2, 3, 4, 5, 6, 7)).foreach(println)
    f(List(2, 5, 3, 4, 6, 7, 9, 8)).foreach(println)
    // f3(List(2, 5, 3, 4, 6, 7, 9, 8)).foreach(println)
   // f4(List(2, 5, 3, 4, 6, 7, 9, 8)).foreach(println)

  }
}
