package Hackerrank

//https://www.hackerrank.com/challenges/fp-filter-positions-in-a-list/problem?isFullScreen=true
object FilterPositionsInAList {

  // in question we are told to remove odd positioned elements from a list, as in list we start index from 0, so we need to remove elements at even index.
  def f(ls: List[Int]): List[Int] = {
    functionToRemoveElements(ls, 0)
  }

  def functionToRemoveElements(ls: List[Int], idx: Int): List[Int] = {
    ls match {
      case ::(head, next) => if (idx % 2 == 0) functionToRemoveElements(next, idx + 1) else head :: functionToRemoveElements(next, idx + 1) //complexity is O(n) as list is traversed once
      case Nil => Nil
    }
  }

  //alternate way 2- in this approach we are using pattern matching to match 2 elements at a time, and then recursively calling the function on the tail of the list.
  def f0(ls: List[Int]): List[Int] = ls match {
    case _ :: second :: tail => second :: f0(tail)
    case _ => Nil
  }

  //Time complexity is O(n) as we are traversing the list once, and for each element we are doing constant time work (adding to result list).

  //alternate way 3-

  def f3(ls: List[Int]): List[Int] = ls.zipWithIndex.filter { case (value, index) => index % 2 != 0 }.map(_._1)

  //Time complexity is O(n)= ls.zipWithIndex is O(n), then for filter is O(n), then for map is O(n) = O(n) + O(n) + O(n) = 3 O(n) = O(n)

  //explanation:
  //zipWithIndex creates a list of tuples, where each tuple contains the element and its index in the original list.
  //  val a: List[(Int, Int)] = List(2, 5, 3, 4, 6, 7, 9, 8).zipWithIndex
  //  a.foreach(println)  //Output: List((2,0), (5,1), (3,2), (4,3), (6,4), (7,5), (9,6), (8,7))
  // map(_._1) is map(x:(Int,Int) =>x._1)

  //alternate way 4-

  def f4(ls: List[Int]): List[Int] = ls
    .foldLeft(0, Nil: List[Int]) { case ((idx, resultList), a) => (idx + 1, if (idx % 2 == 0) resultList else a :: resultList) }
    ._2
    .reverse

  //Time complexity is O(n) as we are traversing the list once, and for each element we are doing constant time work (adding to result list).

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

  // Here we are using foldLeft to iterate over the list, where acc is a tuple containing the current index and the result list. a is each element of the list.
  // As we iterate, we increment the index and conditionally add the element to the result list based on whether the index is even or odd.
  // Finally, we reverse the result list to maintain the original order of elements. Reverse is done as in list elements are added to the front of the list, so we need to reverse it
  // at the end to get the correct order.

  def main(args: Array[String]): Unit = {

    // f0(List(0, 1, 2, 3, 4, 5, 6, 7)).foreach(println)
    f(List(2, 5, 3, 4, 6, 7, 9, 8)).foreach(println)
    // f3(List(2, 5, 3, 4, 6, 7, 9, 8)).foreach(println)
   // f4(List(2, 5, 3, 4, 6, 7, 9, 8)).foreach(println)
   // f5(List(2, 5, 3, 4, 6, 7, 9, 8)).foreach(println) //o/p: List(5, 4, 7, 8)


  }
}
