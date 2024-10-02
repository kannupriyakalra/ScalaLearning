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


object ScalaRepeatedParameters extends App {

  case class AnnoyingInput(l: List[Int]) {
    lazy val sum = l.sum
  }

  case class NiceLookingInput(l: Int*) {
    lazy val sum = l.sum
  }

  val a1: AnnoyingInput = AnnoyingInput(List(1, 2, 3, 4, 5))
  val b1: NiceLookingInput = NiceLookingInput(1, 2, 3, 4, 5)
  println(a1) //o/p- AnnoyingInput(List(1, 2, 3, 4, 5))
  println(b1) //o/p- NiceLookingInput(ArraySeq(1, 2, 3, 4, 5))
  assert(a1.sum == b1.sum)

  val input: List[Int] = List(1, 3, 5)

  val a2: AnnoyingInput = AnnoyingInput(input)
  val b2: NiceLookingInput = NiceLookingInput(input: _*) // here we are sending fixed no. of input at a place ie expecting variable number of input and to do this writing 'input: _*' was
  // important as that converts List[Int] to Int*
  assert(a2.sum == b2.sum)

  println(
    "Congratulations ! 'It always seems impossible until it's done.' - Nelson Mandela"
  )

  case class Foo(f: Foo*)

  Foo(Foo(Foo(), Foo()), Foo()) // a recursive datatype, its base case is Foo()

  case class Bar(b: Bar) // to construct Bar object without Bar so we can create 1st object of Bar

  lazy val b: Bar = Bar(???) // ??? is of type Nothing which is a subtype of all types including Bar so can be sent as object of type Bar.
  // Subtype can be sent as a replacement of super type (Liskolv subsititution principle). We can send object of subtype where object of super type is requested.
  //This is just a hack, this code will fail at runtime as object of Nothing doesn't exist, can see that by removing lazy.

  val bb: Bar = Bar(bb)
  println(bb) //o/p- Bar(null) , bb was used before it was assigned (execution is Right to Left)

  case class Foo2(f: List[Foo2])

  Foo2(List(Foo2(List(Foo2(List()), Foo2(List()))), Foo2(List())))

  // Foo* and List[Foo2] both represent 0 or more occurrences of type Foo.
}
