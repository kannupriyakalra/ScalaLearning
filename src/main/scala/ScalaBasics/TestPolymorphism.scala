package ScalaBasics

import org.scalatest.Assertions.assertResult

/*

Polymorphism-

Polymorphism in scala/ functional programming is of 3 types-
1. Parametric Polymorphism
2. Subtype Polymorphism
3. Ad-Hoc Polymorphism
 */
object TestPolymorphism extends App {

  // Parametric Polymorphism

  def pairWiseReverse[A](xs: List[A]): List[A] = xs.grouped(2).flatMap(_.reverse).toList

  val originalInts = List(1, 2, 3, 4, 5)
  val expectedInts = List(2, 1, 4, 3, 5)
  val originalStrings = List("a", "b", "c", "d", "e")
  val expectedStrings = List("b", "a", "d", "c", "e")
  val originalDoubles = List(1.2, 2.7, 3.4, 4.3, 5.0)
  val expectedDoubles = List(2.7, 1.2, 4.3, 3.4, 5.0)

  assertResult(expectedInts)(pairWiseReverse[Int](originalInts))
  assertResult(expectedStrings)(pairWiseReverse[String](originalStrings))
  assertResult(expectedDoubles)(pairWiseReverse[Double](originalDoubles))


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

  val square = Square(10.0)
  val circle = Circle(12.0)

  assertResult(expected = 100.00)(printArea(square))
  assertResult(expected = 452.39)(printArea(circle))


  // Adhoc Polymorphism:
  //1. Function Overloading


  val intList = List(3, 5, 2, 1, 4)
  val sortedIntList = intList.sorted
  assertResult(expected = List(1, 2, 3, 4, 5))(actual = sortedIntList)


  case class StudentId(id: Int)
  //anonymous class- as here Ordering trait is extended and its implementation is created directly by creating object
//  case object StudentId{
//    implicit val a: Ordering[StudentId] = new Ordering[StudentId] {
//      override def compare(x: StudentId, y: StudentId): Int = x.id - y.id
//    }
//  }

  case object StudentId {//Single Abstract Method- as trait Ordering has 'compare' as the single abstract method so we can convert above anonymous function into single abstract class
    implicit val ordering: Ordering[StudentId] = (x: StudentId, y: StudentId) => x.id - y.id
  }

  val studentIds = List(StudentId(5), StudentId(1), StudentId(4), StudentId(3), StudentId(2))
  val sortedStudentIds = studentIds.sorted
  assertResult(
    expected = List(
      StudentId(1),
      StudentId(2),
      StudentId(3),
      StudentId(4),
      StudentId(5)
    )
  )(actual = sortedStudentIds)

}
