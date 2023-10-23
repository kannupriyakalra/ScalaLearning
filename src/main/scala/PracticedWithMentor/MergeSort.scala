//PreRequisite- Revise Merge2SortedList.scala before going through this.

package PracticedWithMentor

import scala.annotation.tailrec

object MergeSort {

  //merge 2 already sorted lists recursively-- in interview this kind of implementation is expected, this is main merge.
  //its complexity is O(n)- as recursion is done n times when list has n elements
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

    val l = List(10, 8, 5, 6, 1, 2, 7, 9, 4, 3)
    println(mergeSort(l)) //o/p- List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }

}

//ordered - means for list, element at index 1 will be at 1, ordered doesn't mean sorted, set n Map are not ordered collections, list is a ordered collection.

/*
mergeSort-
https://www.youtube.com/watch?v=4VqmGXwpLqc&ab_channel=MichaelSambol
merge sort algorithm - continuously splits a list into half of it and does the same for all sublist until each sublist has only one item left. It then merges those sublist into a sorted list.
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




