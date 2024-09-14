package TourOfScala

/*
https://tourofscala.com/scala/implicit-class

Scala implicit class

Slowly getting into the more advanced features of Scala. Starting with implicit class.

implicit class allows you to add methods to a type.

The syntax is like this:

implicit class NAME(value_name: TypeToOverride) {

  def methodToAdd: OutputType = ???

}

You might have noticed an other form:

implicit class NAME(val value_name: TypeToOverride) extends AnyVal {

  def methodToAdd: OutputType = ???

}

The second form will be more efficient, it will not create a new instance. The first form will create a new instance of the Type which can be inefficient if overused.
Then, why not use the AnyVal form all the time ? There are a multitude of reasons, I could go over each and every ones of them, but this Stackoverflow
answer (https://stackoverflow.com/questions/14929422/should-implicit-classes-always-extend-anyval/14931302#14931302) explains it in a lot of details.

When "extends AnyVal" is used, new object in heap memory is not created.
 */
object ScalaImplicitClass extends App{

  object Implicits {
    implicit class IntExtra(val i: Int) extends AnyVal {
      def isEven: Boolean = i % (2 : Int) == 0

      def increaseByN(n: Int = 1): Int = i + n
    }

    implicit class ListExtra(list: List[Int]) {
      def everyNMap(n: Int)(f: Int => Int): List[Int] = {
        list.zipWithIndex.map {
          case (element, i) if i % n == 0 => f(element)
          case (element, _) => element
        }
      }
    }
  }

  import Implicits._

  val listEnd: Int = 10

  val input: List[Int] = (0 to listEnd).toList
  println(input)

  val output: List[Int] = input
    .filter(_.isEven)
    .everyNMap(2)(_.increaseByN(3))

  println(output)

  val result: Int = output.sum
  val expected: Int = 39
  assert(result == expected, result)

  println(
    "Congratulations ! 'It does not matter how slowly you go as long as you do not stop.' – Confucius"
  )


}
