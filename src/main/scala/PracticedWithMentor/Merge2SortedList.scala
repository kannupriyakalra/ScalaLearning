package PracticedWithMentor

import scala.annotation.tailrec

object Merge2SortedList {

  //q1- merge 2 already sorted lists
  // l1 = (1, 4, 5), l2 = (2, 3, 6), result = (1, 2, 3, 4, 5, 6)
  // merge method time complexity - O(n) + O(nlogn) = O(nlogn) (as O(nlogn) is bigger than O(n), + in time complexity means max)
  def merge(l1: List[Int], l2: List[Int]): List[Int] = {
    //    val l = l1.concat(l2) // time complexity- O(n)
    //    val result = l.sorted // time complexity- O(nlogn) , mentor will tell later which sorting technique is internally used n why is complexity nlogn
    //    result
    l1.concat(l2).sorted
  }

  //q2- merge 2 already sorted lists recursively-- in interview this kind of implementation is expected, this is main merge.
  //its complexity is O(n)- as recursion is done n times when list has n elements
  def merge2(l1: List[Int], l2: List[Int]): List[Int] = {
    (l1, l2) match {
      case (head1 :: tail1, head2 :: tail2) => if (head1 < head2) head1 :: merge2(tail1, l2) else head2 :: merge2(l1, tail2)
      case (_ :: _, Nil) => l1
      case (Nil, _ :: _) => l2 //head2 :: tail2 is replaced by _ :: _ as it was never used on right side expression
      case (Nil, Nil) => Nil
    }
  }
  /*
  merge2-
as we have 2 already sorted list and 2 list ie why 4 pattern match cases
l1 non empty l2 non empty, if (head1 < head2) head1 :: merge2(tail1,  head2 :: tail2) here head of both list will be compared and if (head1 < head2) head1 is added by prepending to o/p list while
calling merge recursively on the tail1 and l2 list.
l1 non empty l2 empty, add l1 to o/p list directly as its already sorted
l1 empty l2 non empty, add l2 to o/p list directly as its already sorted
l1 empty l2 empty- o/p list is nil
l1 is head1 :: tail1, l2 is head2 :: tail2
minimum element value in both list is their respective head and as lists are sorted so we compare head, result list is also sorted so element inserted is min of current head and remaining list is
called recursively
   */

  //q3- convert merge method to its tail recursive
  //its complexity is O(n) as n operations are done recursively
  @tailrec
  def mergeTail(l1: List[Int], l2: List[Int], result: List[Int] = Nil): List[Int] = {
    (l1, l2) match {
      case (head1 :: tail1, head2 :: tail2) => if (head1 < head2) mergeTail(tail1, l2, head1 :: result) else mergeTail(l1, tail2, head2 :: result)
      case (_ :: _, Nil) => result.reverse ++ l1
      case (Nil, _ :: _) => result.reverse ++ l2
      case (Nil, Nil) => result.reverse
    }
  }

  /*
mergeTail-
do like u did factorial
- recursively ek result list sath le k chal in merge and prepend o/p in that and reverse that list in the end.
head1 :: result--adding head1 to result list
case (head1 :: tail1, Nil)-- result jo abhi tak gather hua hai vo reverse tarikay se hua hai so uss result me l1 add krna hai.  reverse ++ l1
case (Nil, Nil)-- means came to end of both list, so return the result by reversing it
 //why merge2 didnt require reversing result of result list, head 1 is first element its decided then that is prepended to o/p of recursive calling of merge2 which will be sorted.
++ alias for concat
result: List[Int] = Nil----default initialised to Nil so not required to be sent while calling merge3

dry run
  val l1 = List(4, 5, 7, 8, 9, 10), val l2 = List(2, 3, 6)
  4<2 false, merge3(List(4, 5, 7, 8, 9, 10), List(3, 6), List(2))
  4<3 false, merge3(List(4, 5, 7, 8, 9, 10), List(6), List(3, 2))   ,result list--3 :: List(2)
  4<6 true, merge3(List(5, 7, 8, 9, 10), List(6), List(4, 3, 2))   ,result list--4 :: List(3, 2)
  5<6 true, merge3(List(7, 8, 9, 10), List(6), List(5, 4, 3, 2))
  7<6 false merge3(List(7, 8, 9, 10), List(), List(6, 5, 4, 3, 2))
  goes to case  case (head1 :: tail1, Nil) => result.reverse ++ l1
  List(2, 3, 4, 5, 6) ++ List(7, 8, 9, 10)
  List (2, 3, 4, 5, 6, 7, 8, 9, 10)
 */
  def main(args: Array[String]): Unit = {

    val l1 = List(4, 5, 7, 8, 9, 10)
    val l2 = List(2, 3, 6)
    println(merge(l1, l2)) // o/p- List(2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(merge2(l1, l2)) // o/p- List(2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(mergeTail(l1, l2)) // o/p- List(2, 3, 4, 5, 6, 7, 8, 9, 10)

  }

}
