package TourOfScala

/*
https://tourofscala.com/scala/repeated-parameters

Little break in Object Oriented Programming and Functional Programming features.

Today is about a little “nice to know”.

You probably understood that this allow you to take an array as input without having to actually create an array when calling the function.

It can be very useful with nested case class with list of list. It makes the code more readable.

case class Foo(f: Foo*)

Foo(

  Foo(

    Foo(),

    Foo()

  ),

  Foo()

)

Imagine this code with having to create List(Foo()) each time, it would make the code really hard to digest.
But, like everything in life, there is a drawback… If you already have an array, you need to use the weird :_*. It is a minor issue, with experience, you are going to know those few characters by heart. It basically turns a list of item into a series of arguments.
 */
object ScalaRepeatedParameters extends App{

  case class AnnoyingInput(l: List[Int]) {
    lazy val sum = l.sum
  }

  case class NiceLookingInput(l: Int*) {
    lazy val sum = l.sum
  }

  val a1: AnnoyingInput = AnnoyingInput(List(1, 2, 3))
  val b1: NiceLookingInput = NiceLookingInput(1, 2, 3)
  println(a1)
  println(b1)
  assert(a1.sum == b1.sum)

  val input: List[Int] = List(1, 3, 5)

  val a2: AnnoyingInput = AnnoyingInput(input)
  val b2: NiceLookingInput = NiceLookingInput(input: _*)
  assert(a2.sum == b2.sum)

  println(
    "Congratulations ! 'It always seems impossible until it's done.' - Nelson Mandela"
  )

}
