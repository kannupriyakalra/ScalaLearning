package TourOfScala

/*
//https://tourofscala.com/scala/curry
Scala Curry
It describes the transformation of a method that takes several arguments into a series of function that each take one of those arguments. Simple? Try on the exercise.

See? Simple. Some coding is worth a thousand words!

Writing the code that way has a few advantages.

For instance, you can decompose the function into partially applied functions, like add2 in the exercise. This example is simple, but imagine a complex function that takes a database connector and/or configurations. You could set those arguments, and then only reuse the partially applied function when needed, I like to call it a pre-configured operation.

I also like using it for aesthetics, It allows to use like in r3 in the exercise.

And you can have as many arguments chained that way, As well as also combinations, for instance the first block could have 2 arguments and then 1 and then 3 arguments. Use it wisely depending on your needs.
 */
object ScalaCurry extends App{

  def add(a: Int)(b: Int): Int = a + b

  val add2: Int => Int = add(2)

  val r1: Int = add2(4)

  assert(r1 == 6)

  val r2 = add(4)(3)

  assert(r2 == 7)

  val r3 = add(3) {
    3 + 4
  }

  assert(r3 == 10)

  println("Congratulations ! Don't stop going forward!")

}
