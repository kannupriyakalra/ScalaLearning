package Revision

object mergeSortPractice {

  def merge(l1: List[Int], l2: List[Int]): List[Int] = {
    (l1, l2) match {
      case (head1 :: tail1, head2 :: tail2) => if (head1 < head2) head1 :: merge(tail1, l2) else head2 :: merge(l1, tail2)
      case (head1 :: tail1, Nil) => l1
      case (Nil, head2 :: tail2) => l2
      case (Nil, Nil) => Nil
    }
  }

  def mergeSort(l: List[Int]): List[Int] = {
    if(l.length <= 1) l
    else {
      val n = l.length / 2
      val firstHalf = l.take(n)
      val remainingHalf = l.drop(n)

      val sortedFirstHalf = mergeSort(firstHalf)
      val sortedRemainingHalf = mergeSort(remainingHalf)
      merge(sortedFirstHalf, sortedRemainingHalf)
    }
  }

  def main(args: Array[String]): Unit = {

    val l = List(10, 7, 1, 2, 5, 6, 8, 9, 3, 4)
    println(mergeSort(l))
  }

}
