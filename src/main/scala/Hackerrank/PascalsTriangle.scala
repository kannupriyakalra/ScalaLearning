package Hackerrank

//https://www.hackerrank.com/challenges/pascals-triangle/problem?isFullScreen=true

/*
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
....

nCr = n!/(r! * (n-r)!)  ie no. of ways to select r items from a given set of n items.
3C0 = 3C3 = 1
3C1 = 3
3C2 = 3
1 3 3 1

4C0 = 1
4C1 = 4
4C2 = 6
4C3 = 4
4C4 = 1

dynamic programming

4C2 = 3C2 + 3C1 = 3 + 3 = 6

nCr = (n-1)Cr + (n-1)C(r-1) , n- row, r- column

1 3 3 1
1 4  6  4 1
1 5 10 10 5 1
1 6 15 20 15 6 1

every element is sum of its top and top left element.

 */
object PascalsTriangle {

  //given previous row, to compute next row-
  def computeNextRow(prev: Array[Int]): Array[Int] = { //time complexity- O(n), space complexity- O(n), n- size of array

    val n = prev.length + 1
    val next = new Array[Int](n)

    next(0) = 1
    next(n - 1) = 1

    for (i <- 1 to n - 2) {
      next(i) = prev(i - 1) + prev(i)
    }
    next
  }

  def main(args: Array[String]): Unit = {

    val stdin = scala.io.StdIn
    val k = stdin.readLine.trim.toInt

    val firstRow = Array(1)
    // println(firstRow.mkString(" "))

    //   Alternative solution - 1 (not a functional solution as var is used)
    //    var prevRow = firstRow
    //    for (i <- 2 to k) {
    //      val nextRow = computeNextRow(prevRow)
    //      prevRow = nextRow
    //      println(nextRow.mkString(" "))
    //    }

    //   Alternative solution - 2 (not a functional solution as println is used repeatedly, println is a side effect so this is not a pure function)
    //        (2 to k).foldLeft(firstRow) { (prevRow, i) =>
    //          val nextRow = computeNextRow(prevRow)
    //          println(nextRow.mkString(" "))
    //          nextRow
    //        }

    //time complexity- O(n^2), space complexity- O(n)
    val (lastRow, result) = (2 to k).foldLeft((firstRow, firstRow.mkString)) { case ((prevRow, result), i) =>
      val nextRow = computeNextRow(prevRow)
      (nextRow, result + "\n" + nextRow.mkString(" ")) //use StringBuilder instead
    }

    println(result)

  }

}
/*
time complexity- O(n^2)
computeNextRow- O(n)
(2 to k) - O(n)
n * n

space complexity- O(n)
as that's the maximum extra space ie required. eg- for n = 10 , at a time only 9th row is required for 10th, every previous row is printed n memory released.
 */

/*
given previous row, to compute next row,
1st element(index 0) of next row = 1st element of previous row
last element(index n-1) of next row = last element of previous row

def computeNextRow (prev: Array[Int]): next[] Array[Int] = {
for j in 1 to n-2
next[j] = previous[j] + previous[j-1]
}

Now use fold left on a range and call computeNextRow

pre requisite- TestArray.scala
 */


