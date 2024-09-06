package TourOfScala

/*
//https://tourofscala.com/scala/defined-type
Scala defined type

Let's make our own type!

You saw how to parameterize the entire code.

Try change MyType from Int to String.

This seem simple but it is a building block required to understand generic type later.

If you want to start now, try to replace MyType by the following code:

type SubType = Int
type MyType = List[SubType]
Do you see how you can combine types together ? Like Lego !
List can be parameterized, we used it with Int and String in the past, but you can use any type you want.


 */
object ScalaDefinedType extends App{

  type SubType = Int
  type MyType = List[SubType]


  def factory(): MyType = List(3)

  val n: MyType = factory()

  val expected: MyType = List(3)

  assert(n == expected, n)

  //type MyType = Int
  //
  //def factory(): MyType = 3
  //
  //val n: MyType = factory()
  //
  //val expected: MyType = 3
  //
  //assert(n == expected, n)

  println(
    "Congratulations ! 'Well done is better than well said.' – Stephen Hawking"
  )
}
