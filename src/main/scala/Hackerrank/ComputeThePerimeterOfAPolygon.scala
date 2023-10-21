//explains use of sliding method

package Hackerrank

//https://www.hackerrank.com/challenges/lambda-march-compute-the-perimeter-of-a-polygon/problem?isFullScreen=true
//polygon definition- https://www.youtube.com/watch?v=DUGkQMLowXA&ab_channel=SmileandLearn-English
/*
1. Perimeter of a irregular polygon = sum of each length of each side  = Side1 + Side2 + Side3 + ... + SideN

2. side length = distance between 2 points when their coordinates are given (x1, y1) and (x2, y2) = √ { (x2-x1)^2 + (y2-y1)^2 } //search " how to calculate distance between 2 points when their
coordinates are given" in chatgpt

3. To pair adjacent points of a sequence in Scala and create a list of those pairs, you can use the sliding method. //search "how pair adjacent elements of a sequence in scala and make a list of
them" in chatgpt

val originalSequence : List[Int] = List(1, 2, 3, 4, 5)
val pairedList : List[List[Int]]  = originalSequence.sliding(2).toList // Use the `sliding` method to pair adjacent elements
// `pairedList` now contains List(List(1, 2), List(2, 3), List(3, 4), List(4, 5))
*/


object ComputeThePerimeterOfAPolygon {
  def distanceBetween2Points(p1: (Int, Int), p2: (Int, Int)): Double = {

    val (x1, y1) = p1
    val (x2, y2) = p2

    Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
  }

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    val points: Seq[(Int, Int)] = for (nItr <- 1 to n) yield { //points = Seq(p1, p2, ... ,pn) = Seq((x1, y1), (x2, y2), ... ,(xn, yn))
      val readline = stdin.readLine.split(" ").map(x => x.toInt) //split returns an array of string, map converts array of string to array of int
      val xCoordinate = readline(0) //accessing 0th element of array x
      val yCoordinate = readline(1)
      (xCoordinate, yCoordinate)
    }

    /*
    desugar of for loop-
    val result: Seq[(Int, Int)] = (1 to n)
      .map(nItr => {
        val readline = stdin.readLine.split(" ").map(x => x.toInt) //this map converts array of string to int
        val xCoordinate = readline(0) //accessing 0th element of array x
        val yCoordinate = readline(1)
        (xCoordinate, yCoordinate)
      })
     */

    val edges: List[Seq[(Int, Int)]] = points.sliding(2).toList // edges: List[Seq[(Int, Int)]] every Seq here has 2 elements ie List( Seq( (x1, y1), (x2, y2) ), Seq( (x1, y1), (x2, y2) ) ,... , Seq( (x1, y1), (x2, y2) ) )
    val perimeterOfAPolygon = edges.map {
      case Seq(p1, p2) => distanceBetween2Points(p1, p2) //case Seq( (x1, y1), (x2, y2) )
    }.sum + distanceBetween2Points(points.head, points.last) //points.head gives 1st point and points.last gives last point, we used edges list to find the
    // distance b/w 2 points for all points in it by sliding except the first and last point to close the polygon which is calculated separately.

    println(perimeterOfAPolygon)

    //ask gagan some p3 thing he was about to teach
//    val edges: List[Seq[(Int, Int)]] = points.sliding(2).toList // edges: List[Seq[(Int, Int)]] every Seq here has 2 elements ie List( Seq( (x1, y1), (x2, y2) ), Seq( (x1, y1), (x2, y2) ) ,... , Seq( (x1, y1), (x2, y2) ) )
//    val perimeterOfAPolygon = edges.map {
//      case Seq(p1, p2, p3) => distanceBetween2Points(p1, p2) //case Seq( (x1, y1), (x2, y2) )
//    }.sum + distanceBetween2Points(points.head, points.last) //points.head gives 1st point and points.last gives last point, we used edges list to find the
//    // distance b/w 2 points for all points in it by sliding except the first and last point to close the polygon which is calculated separately.
//
//    println(perimeterOfAPolygon)


  }
}

/*
yield-
In Scala, "yield" is a keyword used with "for comprehensions" to produce a value as a result of iterating over a collection or performing some operations.
It's like saying, "Give me this value as the output of my loop or sequence of operations." It's often used to create a new collection or transform data while
looping over an existing one. So, "yield" is a way to collect and return results from each step of a loop or sequence, making your code more expressive and
functional.
 */
