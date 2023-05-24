import scala.annotation.tailrec
import scala.collection.immutable.::

object TestSort {

  //merge 2 already sorted lists
  // l1 = (1, 4, 5), l2 = (2, 3, 6), result = (1, 2, 3, 4, 5, 6)
  // merge method time complexity - O(n) + O(nlogn) = O(nlogn) (as O(nlogn) is bigger than O(n), + in time complexity means max)
  def merge(l1: List[Int], l2: List[Int]): List[Int] = {
    //    val l = l1.concat(l2) // time complexity- O(n)
    //    val result = l.sorted // time complexity- O(nlogn) , gagan will tell later which sorting technique is internally used n why is complexity nlogn
    //    result
    l1.concat(l2).sorted
  }

  //merge 2 already sorted lists recursively-- in interview this kind of implementation is expected, this is main merge.
  //its complexity is O(n)- as recursion n times chal rahe hai when list has n elements
  def merge2(l1: List[Int], l2: List[Int]): List[Int] = {
    (l1, l2) match {
      case (head1 :: tail1, head2 :: tail2) => if (head1 < head2) head1 :: merge2(tail1, l2) else head2 :: merge2(l1, tail2)
      case (_ :: _, Nil) => l1
      case (Nil, _ :: _) => l2 //head2 :: tail2 is replaced by _ :: _ as it was never used on right side expression
      case (Nil, Nil) => Nil
    }
  }

  //convert merge method to its tail recursive
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

  //implement merge sort recursively-- using merge2 function
  //what is time complexity of merge sort? nlogn why is it nlogn?
  // n -> n/2 -> n/4 -> ... 1 , these are logn base2 values (how many times these are done ie n ko n/2 kitne bar kare ki 1 aaye), log 8base2 =3 because 2^3 = 8 so 3 layers 8 is dived into 4 and 4, 4 into
  // 2 and 2, 2 into 1 and 1 , logn means kitne layers bann rahe hai and on every layer 0(n) operations as merge is called ,depth of mergesort is logn means merge sort is called logn times, on every
  // layer merging has to be done using merge and merge s complextiy is n so nlogn, time complexity means total no. of operations.
  //space complexity-at max extra space reqd- merge method makes new list ie the extra space- new list is O(n) so space complexity is O(n) as at every layer after recursion merging is done and merge creates
  // a new list and o/p memory of previous recursion is taken back by garbage collector.
  def mergeSort(l: List[Int]): List[Int] = {
    if (l.length <= 1) l // can write l also here as l is empty or Nil
    else {
      val n = l.length / 2
      val firstHalf = l.take(n)
      val secondHalf = l.drop(n)
      val firstHalfSorted = mergeSort(firstHalf)
      val secondHalfSorted = mergeSort(secondHalf)
      merge2(firstHalfSorted, secondHalfSorted)
    }
  }

  def main(args: Array[String]): Unit = {

    val l1 = List(4, 5, 7, 8, 9, 10)
    val l2 = List(2, 3, 6)
    println(merge(l1, l2)) // o/p- List(2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(merge2(l1, l2)) // o/p- List(2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(mergeTail(l1, l2)) // o/p- List(2, 3, 4, 5, 6, 7, 8, 9, 10)

    val l3 = List(10, 8, 5, 6, 1, 2, 7, 9, 4, 3)
    println(mergeSort(l3)) //o/p- List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }

}

//ordered - means for list, element at index 1 will be at 1, ordered doesn't mean sorted, set n Map are not ordered collections, list is a ordered collection.

  /*
  merge2-
as we have 2 already sorted list and 2 list ie why 4 pattern match cases
l1 non empty l2 non empty, if (head1 < head2) head1 :: merge(tail1,  head2 :: tail2 head of both list will be compared and if (head1 < head2) head1 is added by prepending to o/p list while calling
merge on the tail1 and l2 list as it is.
l1 non empty l2 empty, add l1 to o/p list directly as its already sorted
l1 empty l2 non empty, add l2 to o/p list directly as its already sorted
l1 empty l2 empty- o/p list is nil
l1 is head1 :: tail1, l2 is head2 :: tail2
minimum element for both list is their respective head n lists are sorted so we compare head, result list is also sorted so element inserted is min of current head n call remaining list recursively
   */

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

/*
mergeSort-
merge sort algorithm - continuously splits a list into multiple sublists until each sublist has only one item left. It then merges those sublists into a sorted list.
time complexity analysis-
whenever we divide a number into half in every step, it can be represented using a logarithmic function, which is log n.
Merge sort repeatedly divides the array into two equally sized parts. Thus merge sort time complexity depends on the number of division stages. The number of division stages is log nbase2.
On each merge stage, n elements are merged.
Merge Sort time complexity is calculated using time per division stage. Since the merge process has linear time complexity, for n elements there will be n∗log 2 n division and merge stages.

dry run-
val l3 = List(10, 8, 5, 6, 1, 2, 7, 9, 4, 3)
println(mergeSort(l3))

10 <= 1 false
n= 10/2 =5
firstHalf = List(10, 8, 5, 6, 1)
secondHalf = List(2, 7, 9, 4, 3)
firstHalfSorted = mergeSort(List(10, 8, 5, 6, 1)) = List(1, 5, 6, 8, 10) (from below)
.
.


mergeSort(List(10, 8, 5, 6, 1))
= 5 <= 1 false , n=2
firstHalf = List(10, 8)
secondHalf = List(5, 6, 1)
firstHalfSorted = mergeSort(List(10, 8)) = List(8,10) (from below)
secondHalfSorted = mergeSort(List(5, 6, 1)) = List(1, 5, 6) (from below)
merge2(List(8,10), List(1, 5, 6) ) = List(1, 5, 6, 8, 10)

mergeSort(List(10, 8))
= 2<=1 false , n= 1
firstHalf = List(10)
secondHalf = List(8)
firstHalfSorted = mergeSort(List(10)) = List(10)
secondHalfSorted = mergeSort(List(8)) = List(8)
merge2(List(10), List(8))= List(8,10)

mergeSort(List(5, 6, 1))
= 3<=1 false, n= 1
firstHalf = List(5)
secondHalf = List(6, 1)
firstHalfSorted = mergeSort(List(5)) = List(5)
secondHalfSorted = mergeSort(List(6, 1)) = List(1,6) (from below)
merge2(List(5), List(1,6))= List(1, 5, 6)

mergeSort(List(6, 1))
= 2<=1 false , n= 1
firstHalf = List(6)
secondHalf = List(1)
firstHalfSorted = mergeSort(List(6)) = List(6)
secondHalfSorted = mergeSort(List(1)) = List(1)
merge2(List(6), List(1))= List(1,6)

//o/p- List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
*/




