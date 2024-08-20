package ScalaBasics

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/*

Polymorphism-

Polymorphism in scala/ functional programming is of 3 types-
1. Parametric Polymorphism
2. Subtype Polymorphism
3. Ad-Hoc Polymorphism
 */
class TestPolymorphism extends AnyFlatSpec with Matchers {

  // Parametric Polymorphism

  def pairWiseReverse[A](xs: List[A]): List[A] = xs.grouped(2).flatMap(_.reverse).toList

  "pairWiseReverse" should "pair-wise reverse lists of any type" in {
    val originalInts = List(1, 2, 3, 4, 5)
    val expectedInts = List(2, 1, 4, 3, 5)
    val originalStrings = List("a", "b", "c", "d", "e")
    val expectedStrings = List("b", "a", "d", "c", "e")
    val originalDoubles = List(1.2, 2.7, 3.4, 4.3, 5.0)
    val expectedDoubles = List(2.7, 1.2, 4.3, 3.4, 5.0)

    pairWiseReverse(originalInts) shouldEqual expectedInts
    pairWiseReverse(originalStrings) shouldEqual expectedStrings
    pairWiseReverse(originalDoubles) shouldEqual expectedDoubles
  }

  // Subtype Polymorphism

  trait Shape {
    def getArea: Double
  }

  case class Square(side: Double) extends Shape {
    override def getArea: Double = side * side
  }

  case class Circle(radius: Double) extends Shape {
    override def getArea: Double = Math.PI * radius * radius
  }

  def printArea[T <: Shape](shape: T): Double = (math.floor(shape.getArea) * 100) / 100

  "Shapes" should "compute correct area" in {
    val square = Square(10.0)
    val circle = Circle(12.0)

    printArea(square) shouldEqual 100.00
    printArea(circle) shouldEqual 452.39
  }
}

