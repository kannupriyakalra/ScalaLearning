package ScalaBasics

import org.scalatest.Assertions.assertResult
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

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
  assertResult(expected = 452.0)(printArea(circle))


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

  case object StudentId { //Single Abstract Method- as trait Ordering has 'compare' as the single abstract method so we can convert above anonymous function into single abstract class
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


  //2. Operator Overloading
  case class Complex(re: Double, im: Double) {
    def +(op: Complex): Complex = Complex(re + op.re, im + op.im)

    def -(op: Complex): Complex = Complex(re - op.re, im - op.im)

    override def toString: String = s"$re + ${im}i"
  }

  val cmpl1: Complex = Complex(8.0, 3.0)
  val cmpl2 = Complex(6.0, 2.0)

  val cmplSum: Complex = cmpl1.+(cmpl2)
  val cmplDiff = cmpl1 - cmpl2

  println(cmplSum) //o/p- Complex(14.0,5.0) if we comment toString method in Complex as toString default is available in case class, if it were not a case class, it would have printed a weird o/p.
  println(cmplSum.toString) //o/p- 14.0 + 5.0i

  assertResult(expected = "14.0 + 5.0i")(actual = cmplSum.toString)
  assertResult(expected = "2.0 + 1.0i")(actual = cmplDiff.toString)


  //3. Implicit Classes

  sealed trait Currency

  object Currency {
    case object EUR extends Currency

    case object USD extends Currency

    case object GBP extends Currency
  }

  case class Money(amount: Double, currency: Currency)

  object MoneySyntax {
    implicit case class RichMoney(amount: Double) extends AnyVal {
      def euros: Money = Money(amount, Currency.EUR)

      def dollars: Money = Money(amount, Currency.USD)

      def pounds: Money = Money(amount, Currency.GBP)
    }
  }

  import MoneySyntax._

  val amount: Double = 30.5

  //below 2 lines are equivalent:
  // val euros: Money = RichMoney(amount).euros //when remove implicit keyword from line 123 ie how we explicitly do.
  val dollars: Money = amount.dollars
  val pounds: Money = amount.pounds

  assert(amount.dollars == Money(amount, Currency.USD)) //value comparison
  amount.euros shouldBe Money(amount, Currency.EUR)
  amount.pounds shouldBe Money(amount, Currency.GBP)

}
