package Hackerrank

//https://www.hackerrank.com/challenges/pascals-triangle/problem?isFullScreen=true

/*
The value at the  row and  column of the triangle is equal to nCr = n!/(r! * (n-r)!)  where indexing starts from 0.

0C0
1C0 1C1
2C0 2C1 2C2
3C0 3C1 3C2 3C3
4C0 4C1 4C2 4C3 4C4

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

dynamic programming- when previously computed results are used to create new results.

4C2 = 3C2 + 3C1 = 3 + 3 = 6 //every element is sum of its top and top left element.

nCr = (n-1)Cr + (n-1)C(r-1) , n- row, r- column

1
1 1
1 2 1
1 3 3  1
1 4 6  4  1
1 5 10 10 5 1
1 6 15 20 15 6 1

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

    val firstRow = Array(1) //as constraint is k lies b/w 2 to 10, so we can set first row.

    //time complexity- O(n^2), space complexity- O(n)
    val (lastRow, result) = (2 to k).foldLeft((firstRow, firstRow.mkString)) ({ case ((prevRow, result), i) => //can write (lastRow, result) as t@(lastRow, result) means tuple named t looks like (lastRow, result)
      val nextRow = computeNextRow(prevRow)
      (nextRow, result + "\n" + nextRow.mkString(" ")) //use StringBuilder instead as this is inefficient, StringBuilder takes all intermediate string and create later. This is inefficient as it creates a new string by merging 2 string at every step and release memory and create new memory. google it.
    })

    //println(t._2)
    println(result)

    //we used case here so we can use pattern matching to further use parts of tuple (prevRow, result) directly in function computation,
    // if we removed case, we ll have to write (t, i) and use t._1 and t._2 in function definition.

    //   Alternative solution - 1 (not a functional solution as var is used)
    //    println(firstRow.mkString(" "))
    //    var prevRow = firstRow
    //    for (i <- 2 to k) {
    //      val nextRow = computeNextRow(prevRow)
    //      prevRow = nextRow
    //      println(nextRow.mkString(" "))
    //    }

    //   Alternative solution - 2 (not a functional solution as println is used repeatedly, println is a side effect so this is not a pure function)
    //            println(firstRow.mkString(" "))
    //            (2 to k).foldLeft(firstRow) { (prevRow, i) =>
    //              val nextRow = computeNextRow(prevRow)
    //              println(nextRow.mkString(" "))
    //              nextRow //here nextRow get updated into prevRow for next value of range
    //            }
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


