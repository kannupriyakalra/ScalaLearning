package ScalaBasics

object testSealed extends App{
  sealed abstract class Shape

  case class Circle(radius: Double) extends Shape
  case class Rectangle(width: Double, height: Double) extends Shape
  case class Square(side: Double) extends Shape

  def area(shape: Shape): Double = shape match {
    case Circle(radius) => math.Pi * radius * radius
    //case Rectangle(width, height) => width * height why is error not coming post commenting this //HELP
    case Square(side) => side * side
  }

  area(Square(2.2))

}
