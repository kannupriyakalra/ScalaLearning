package Hackerrank

//https://www.hackerrank.com/challenges/lambda-march-compute-the-area-of-a-polygon/problem?isFullScreen=true

/*
The statement "points of a polygon are not self-intersecting in nature" means that the vertices or corners of the polygon, when connected in order to form the sides of the polygon, do not cross over
each other. In other words, the edges of the polygon don't intersect or cross within the interior of the polygon itself. example-  triangles, squares.

to calculate the area of a polygon when its coordinates are given we ll use shoelace formula = 0.5 * ( x1y2 − x2y1 + x2y3 − x3y2 +. . .  + xny0 − x0yn)

(x1,y1), (x2,y2) => x1y2 − x2y1
(x2,y2), (x3,y3) => x2y3 − x3y2
.
.
.
(xn,yn), (x0,y0)=> xny0 − x0yn //firstPoint and last point

twoPointPairs is a list of sequence of pairs ie List[Seq[(Int, Int)]] from 1st point until last point. For first and last point part of shoelace formula ie xny0 − x0yn sum will have to be calculated separately.
 */

object ComputeTheAreaOfAPolygon {

  def partOfShoelaceFormula(p1: (Int, Int), p2: (Int, Int)): Double = {

    val (x1, y1) = p1
    val (x2, y2) = p2

    (x1 * y2 - x2 * y1 )

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

    val twoPointPairs: List[Seq[(Int, Int)]] = points.sliding(2).toList // p2p3pairs: List[Seq[(Int, Int)]] every Seq here has 2 elements ie  List( Seq( (x1, y1), (x2, y2) ), Seq( (x2, y2), (x3, y3) ) ,... , Seq( (xn-1, yn-1), (xn, yn) ) )

    val areaOfPolygon = 0.5 * (twoPointPairs.map {
      case Seq(p1, p2) => partOfShoelaceFormula(p1, p2)
    }.sum + partOfShoelaceFormula(points.last, points.head)) //here (lastPoint, firstPoint) should be the order

    println(areaOfPolygon)

  }
}

/*
wrong answer as for a concave polygon in this link https://www.omnicalculator.com/math/irregular-polygon-area when we calculate area of triangle for 3 points for some triangles it will fall outside of polygon
as there is a inward bend in the polygon and ie why my answer was coming more for some cases. Below solution is only valid for convex polygon(search "what is a convex polygon" in chatgpt)

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
twoPointPairs is a list of sequence of pairs ie List[Seq[(Int, Int)]]

given 3 edges of different lengths a, b, c the area of triangle = √ [s * (s-a) * (s-b) * (s-c) ] ; s = semiperimeter of the triangle = (a+b+c)/2
//search " given 3 different edges what is the area of triangle" in chatgpt
1.Calculate the lengths of the three sides of the triangle (a, b, and c)
2.Calculate the semiperimeter (s)
3.Plug the values of a, b, c and s into Heron's formula to calculate the area (A).
 */

/*
object ComputeTheAreaOfAPolygonWrong {
  def areaOfTriangle(p1: (Int, Int), p2: (Int, Int), p3: (Int, Int)): Double = {
    val a = distanceBetween2Points(p1, p2) //edge1
    val b = distanceBetween2Points(p2, p3) //edge2
    val c = distanceBetween2Points(p1, p3) //edge3

    val s = (a + b + c) / 2 //semiperimeter

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

    val areaOfPolygon = twoPointPairs.map {
      case Seq(p2, p3) => areaOfTriangle(firstPoint, p2, p3)
    }.sum

    println(areaOfPolygon)

  }
}
*/

