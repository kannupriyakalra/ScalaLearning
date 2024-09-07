package TourOfScala

/*
//https://tourofscala.com/scala/pattern-matching
Scala pattern matching

We are going to learn about pattern matching today. At least, an introduction. Pattern matching is one of the key functionality of scala and it contributes to help you write clean and readable code.

The main keyword to use pattern matching is match. But as you saw, you can also use it inside map, as well as flatMap and filter and more.

The overall syntax is:

value match {

  case holder => action

  case _ => default case

}

And similar inside a map or other:
list.map {

  case holder => action

  case _ => default case

}

It works kind of like a switch in other languages. And similar to switch, the case are evaluated in order, the first one that evaluate to true will be executed and none of the other ones will be.

There are plenty of ways that pattern matching can be used and we only saw a few here, let's review:

catch all: case n => ???
catch all without the value: case _ => ???
With condition: case n if n % 2 == 0 => ???
Exact match: case 3 => ??? or case "abc" => ???
List extraction:
Empty list: case Nil => ???
Extract head: case head :: tail => ???
One element: case head :: Nil => ???
Two elements: case first :: second :: Nil => ???
One element with condition: case head :: Nil if head % 2 == 0 => ???
Extract value: case 12 :: tail => ???
With types: case n: String => ???
Case classes: (there will be an SKB about it)
Extraction of field: case Person(firstName, lastName) => ???
Extraction of field with condition: case Person(firstName, lastName) if firstName.startsWith("L") => ???
Extraction of field with exact match: case Person("Leo", lastName) => ???
With regex: There will be an SKB about it
With Scala version of enumeration: There will be an SKB about it
 */
object ScalaPatternMatching extends App{

  type L = List[Int]

  val startR: Int = 0
  val endR: Int = 10
  val l: L = (startR to endR).toList
  println(l)

  val l1: L = l.map {
    case 0 => 0
    case n if n < 5 => n + 4
    case n if n < 8 => n - 3
    case _ => 0
  }
  println(l1)
  val expected1: Int = 35
  assert(l1.sum == expected1, l1)

  def transform(input: L, f: Int => Int): L = {
    def loop(accumulator: L, rest: L): L = {
      println(s"acc: $accumulator")
      rest match {
        case Nil => accumulator
        case head :: tail => loop(accumulator :+ f(head), tail) // :+ means element will be appended at the end of list eg List(1) :+ 2 = List(1, 2),
        // complexity of :+ is O(n) as list is reconstructed.
      }
    }

    loop(Nil, input)
  }

  val l2: L = transform(l1, a => a + 1)
  println(l2)
  val expected2: Int = 46
  assert(l2.sum == expected2, l2)

  println(
    "Congratulations ! 'If you can dream it, you can do it.' – George S. Patton"
  )

}
