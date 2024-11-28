package LeetCode.Easy

// https://leetcode.com/problems/merge-two-sorted-lists/description/
object MergeTwoSortedLists extends App {

  // Time complexity- O(n), Space Complexity- O(n)
  def mergeTwoLists1(list1: List[Int], list2: List[Int]): List[Int] = {
    (list1, list2) match {
      case (Nil, Nil) => Nil
      case (_ :: _, Nil) => list1
      case (Nil, _ :: _) => list2
      case (head1 :: tail1, head2 :: tail2) => if (head1 <= head2) head1 :: mergeTwoLists1(tail1, list2) else head2 :: mergeTwoLists1(list1, tail2)
    }
  }

  // Time complexity- O(n), Space Complexity- O(n)
  def mergeTwoLists2(list1: List[Int], list2: List[Int]): List[Int] = {
    (list1, list2) match {
      case (Nil, Nil) => Nil
      case (_ :: _, Nil) => list1
      case (Nil, _ :: _) => list2
      case (head1 :: tail1, head2 :: _) if (head1 <= head2) => head1 :: mergeTwoLists2(tail1, list2)
      case (head1 :: _, head2 :: tail2) if (head1 > head2) => head2 :: mergeTwoLists2(list1, tail2)
    }
  }

  println(mergeTwoLists1(List(1, 2, 4), List(1, 3, 4)))
  println(mergeTwoLists1(List(), List()))
  println(mergeTwoLists1(List(), List(0)))


  // Time complexity- O(n), Space Complexity- O(n)
  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }

  // Time complexity- O(n), Space Complexity- O(n)
  def convertToListNode(input: List[Int]): ListNode = input match {
    case head :: tail => new ListNode(head, convertToListNode(tail))
    case Nil => null
  }

  def convertFromListNode(input: ListNode): List[Int] = if (input == null) Nil else input.x :: convertFromListNode(input.next)

  // Time complexity- O(n), Space Complexity- O(n)
  def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {
    convertToListNode(mergeTwoLists1(convertFromListNode(list1), convertFromListNode(list2)))
  }
}
