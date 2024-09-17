package TourOfScala

/*
https://tourofscala.com/scala/either

Either, you will see, is kind of a in-between Try and Option.

Try to experiment to see the similarity and differences.

Similar to Option, you can create Either in two ways:

Right which would be similar to Some
Left which, over time, became similar to None, except you can store information. I am saying “over time” because up until Scala v2.12+, Either was not Right-bias. Left and Right were just two Types.
 But now, Left is accepting to carry error messages and Right to be the channel for successes.
You can test which Type is Either using isLeft and isRight. You can also use map, flatMap, etc… once again. You must be starting to know those functions pretty well by now. This is where the
Right-bias come into play. map will take a function that modify the type contained in Right and will not do anything if the Either contains a Left.

If you would like to specifically act on Left or Right, you can use the .left and .right methods to project the Either on one side of the other. For instance, to modify the content of the Left
 when it is there, you can do .left.map(...).

One major difference with Try is that Either will not catch the Exception. If an exception is thrown inside an Either it will propagate. Inside a Try it will be captured inside the Failure channel.

 */
object ScalaEither extends App {

  val lEi: List[Either[String, Int]] = List(
    Left("abc"),
    Right(12)
  )

  println(lEi)

  def divide(a: Double, b: Double): Either[String, Double] = {
    if (b == 0) Left("Cannot divide by zero") else Right(a / b)
  }

  val a1: Double = 5.0
  val b1: Double = 0.0
  val badResult: Either[String, Double] = divide(a1, b1)
  assert(badResult.isLeft)
  badResult.left.foreach(l => println(s"Error: $l"))

  val a2: Double = 24.0
  val b2: Double = 2.0
  val goodResult: Either[String, Double] = divide(a2, b2)

  // Either is Right-bias, map is applied on Right by default
  val resultModified: Either[String, Double] = goodResult.map(r => r + 1)
  val resultGet: Double = resultModified.getOrElse(1.0)
  assert(resultGet == 13)
  assert(goodResult.isRight)


  println(
    "Congratulations ! 'No bird soars too high if he soars with his own wings.' - William Blake"
  )


}
