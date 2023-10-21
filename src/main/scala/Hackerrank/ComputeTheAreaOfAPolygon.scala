package Hackerrank

//https://www.hackerrank.com/challenges/lambda-march-compute-the-area-of-a-polygon/problem?isFullScreen=true

/*
The statement "points of a polygon are not self-intersecting in nature" means that the vertices or corners of the polygon, when connected in order to form the sides of the polygon, do not cross over
each other. In other words, the edges of the polygon don't intersect or cross within the interior of the polygon itself. example-  triangles, squares.

to calculate the area of a polygon -
1. Divide the polygon into triangles
2. Calculate the area of each triangle
3. Sum the areas of the triangles

Divide the polygon into triangles = for 7 points as i/p = p1, p2, p3, p4, p5, p6, p7
p1, p2, p3
p1, p3, p4
p1, p4, p5
p1, p5, p6
p1, p6, p7
p1 is common ie firstPoint
twoPointPairs is a list of sequence of pairs List[Seq[(Int, Int)]]

given 3 edges of different lengths a, b, c the area of triangle = √ [s * (s-a) * (s-b) * (s-c) ] ; s = semiperimeter of the triangle = (a+b+c)/2
//search " given 3 different edges what is the area of triangle" in chatgpt
1.Measure or obtain the lengths of the three sides of the triangle (a, b, and c)
2.Calculate the semiperimeter (s)
3.Plug the values of a, b, c and s into Heron's formula to calculate the area (A).
 */
object ComputeTheAreaOfAPolygon {
  def areaOfTriangle(p1: (Int, Int), p2: (Int, Int), p3: (Int, Int)): Double = {
    val a = distanceBetween2Points(p1, p2) //edge1
    val b = distanceBetween2Points(p2, p3) //edge2
    val c = distanceBetween2Points(p1, p3) //edge3

    val s = (a + b + c)/2 //semiperimeter

    Math.sqrt(s * (s-a) * (s-b) * (s-c))
  }

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

    val firstPoint: (Int, Int) = points.head
    val remainingPoints: Seq[(Int, Int)] = points.tail

    val twoPointPairs: List[Seq[(Int, Int)]] = remainingPoints.sliding(2).toList // p2p3pairs: List[Seq[(Int, Int)]] every Seq here has 2 elements ie List( Seq( (x1, y1), (x2, y2) ), Seq( (x1, y1), (x2, y2) ) ,... , Seq( (x1, y1), (x2, y2) ) )

    val areaOfPolygon = twoPointPairs.map{
      case Seq(p2, p3) => areaOfTriangle (firstPoint, p2, p3)
    }.sum

    println(areaOfPolygon)

  }
}
