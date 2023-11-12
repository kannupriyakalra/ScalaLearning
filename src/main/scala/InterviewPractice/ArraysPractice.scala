package InterviewPractice

/*
Write a function:

object Solution { def solution(a: Array[Int]): Int }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
 */
object ArraysPractice extends App {

  def solution(a: Array[Int]): Int = {
    a.sorted.foldLeft(1)((acc, e) => if (e > 0 && acc == e) acc + 1 else acc)
  }

  println(solution(Array(1, 3, 6, 4, 1, 2))) //o/p-5
  println(solution(Array(1, 2, 3)))//o/p-4
  println(solution(Array(-1, -3)))//o/p-1

}
//sorting-nlogn + foldLeft - n = O(nlogn)
//nlogn + n = nlogn ie the lower limit ie min this much iterations will be required.

