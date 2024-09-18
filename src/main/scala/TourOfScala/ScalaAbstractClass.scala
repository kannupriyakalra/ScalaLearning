package TourOfScala

/*
https://tourofscala.com/scala/abstract-class

This SKB is going to continue our progress in the Object Oriented Programming aspect of Scala.

abstract class are pretty similar to trait we saw before. But there are some slight differences.

You recognize the same keywords we learned about when we learned about trait. You can see extends, protected and override.

There are more differences but for the moment let's go one step at a time.

The main difference you can notice compare to trait is that you are able to have a constructor directly when you extends the abstract class. This is pretty convenient to build simple pattern. We are going to see more about this in the context of enumeration.

To practice the concepts of Object Oriented Programming, Try to access the fields of Shape in the child classes. You notice that only lengthOfSides is protected and not the other ones. Remember class ? By default fields are private in a class. Try to add methods, maybe the area ?
 */
object ScalaAbstractClass extends App {

  abstract class Shape( //elements of class are by default private, for a case class they are by default public.
                        name: String,
                        protected val lengthOfSides: Int,
                        numberOfSides: Int
                      ) {
    def circumference: Double = numberOfSides * lengthOfSides

    def area: Double

    override def toString: String =
      s"$name shape of size $lengthOfSides " +
        f"with $numberOfSides sides have a circumference of $circumference%1.2f" +
        f" with area of $area%1.2f"
  }

  case class Square(size: Int) extends Shape("Square", size, 4) {
    override def area: Double = size * size
  }

  case class Triangle(size: Int) extends Shape("Triangle", size, 3) {
    override def area: Double = (Math.sqrt(3) / 4) * size * size
  }

  case class Circle(radius: Int) extends Shape("Circle", radius, 0) {
    override lazy val circumference: Double = scala.math.Pi * 2.0 * lengthOfSides //def has no arguments ie why overriden with val. , 2pie r is circumference

    override def area: Double = scala.math.Pi * radius * radius
  }

  val square: Square = Square(size = 4)
  val triangle: Triangle = Triangle(size = 3)
  val circle: Circle = Circle(radius = 2)

  val shapes: List[Shape] = List(
    square,
    triangle,
    circle
  )

  shapes.foreach(println)

  assert(square.circumference == 16)
  assert(triangle.circumference == 9)
  assert(Math.abs(circle.circumference - 12) <= 1)

  assert(square.area == 16)
  assert(triangle.area - 3 <= 1)
  assert(Math.abs(circle.area - 12) <= 1)

  println(
    "Congratulations ! 'Ever tried. Ever failed. No matter. Try Again. Fail again. Fail better.' - Samuel Beckett"
  )

}
