package TourOfScala

//https://tourofscala.com/scala/option-map

/*
Scala Option map

let's focus only on the map method.
map is part of what is called a functor.
MONAD word is going to show up soon.

try:

What happen when input is set to None?
What happen if you replace getOrElse by get ? with Some and with None.
try replacing a => a by _.

 */
object ScalaOptionMap extends App {

  val input: Option[Int] = Some(1)
  //val input: Option[Int] = None
  println(input)

  //val mapped: Option[Int] = input.map(a => a + 1)
  val mapped: Option[Int] = input.map(_ + 1)
  println(mapped)

  val result: Int = mapped.getOrElse(0)
  //val result: Int = mapped.get

  assert(result == 2)
 // assert(result == 0)

  println(
    "Congratulations ! There are no pressure to be happy, take your time."
  )

}
