//Partial function
object TestPartialFunction extends App {

  //Partial Function-  x is a function from Int to String that doesn't work on all inputs. Pattern match is a type of partial function.
  // Partial function can be a total function, its a subtype of Function1.
  val x: PartialFunction[Int, String] = {
    case 1 => "one"
    case 2 => "two"
  }
  println(x(2)) //o/p- two
  println(x.isDefinedAt(1)) //isDefinedAt-check if a function is defined for a i/p or not, this is the safe way of calling partial function. //o/p-true
  println(x.isDefinedAt(3)) //o/p-false

  //below is example of Total Function- all normal functions are total functions, works on all inputs.
  //Int => String is same as Function[Int, String]
  val y: Function[Int, String] = {
    case 1 => "one"
    case 2 => "two"
    case _ => "many" // case _ will trigger if all other cases fail.
  }

  val z = Some(1).map(_ + 1).map { case i => i + 1 }
  println(z) //o/p-Some(3)

  val z1 = Some(1).map(_ + 1).map { case i => i + 1 }.map { case 3 => 5; case 4 => 6 }
  println(z1) //o/p-Some(5)
  //{ case 3 => 5; case 4 => 6 } this a function given to map ie of property partial function as it runs on some i/p only, another example is divide which is a partial function as it doesnt run for every i/p.
  //{ case 3 => 5; case 4 => 6 } this pattern match is a way of giving partial function.

  // val z2 = Some(10).map(_ + 1).map { case i => i + 1 }.map { case 3 => 5; case 4 => 6 }
  //  println(z2) //o/p-scala.MatchError, this is to show "{ case 3 => 5; case 4 => 6 }" partial function doesn't work for all input and is unsafe.

  //in line 27, semicolon used to write multiple lines in one line, no need if you format it in 2 lines, last line "case 4 => 6" need no semicolon


  //collect method-Builds a new collection by applying a partial function to all elements of this collection on which the function is defined.
  // The method collect is described as "filter + map" because it uses a PartialFunction to select elements to which the function is applied.
  val M: PartialFunction[Int, Int] = {
    case x if (x % 5) != 0 => x * 5
  }
  val y1 = List(7, 15, 9) collect {
    M
  }
  println(y1) //o/p-List(35, 45)

  //map method-Builds a new collection by applying a function to all elements of this collection
  val parseRange: PartialFunction[Any, Int] = {
    case x: Int if x > 10 => x + 1
  }
  val y2 = List(15, 3, "aString") collect {
    parseRange
  }
  println(y2) //o/p-List(16)

  val y3 = List(15, 20, 25) map {
    parseRange
  }
  println(y3) //o/p-List(16, 21, 26)

  //  val y4 = List(15, 3, "aString") map {
  //    parseRange
  //  }
  //  println(y4) //o/p-scala.MatchError at runtime because the pattern match doesn’t know how to handle a String, 3 (which is less than 10 so doesn't fulfill condition)


  //filter
  /*
filter is similar to collect, except filter returns a collection of elements that satisfy a provided condition.
We can see the different results returned using the same partial function to show that the collect and filter methods behave differently:
here we defined the partial function as an anonymous function.
   */

  val z2 = List(1, 2, 11) collect { case i: Int => i > 10 }
  val z3 = List(1, 2, 11) filter { case i: Int => i > 10 }
  println(z2) //o/p- List(false, false, true)//returns true/false as for this partial function only isDefinedAt is made and no apply.
  println(z3) //o/p- List(11)


  //partial function scala doc code implemented in intellij-

  val sample = 1 to 10

  def isEven(n: Int) = n % 2 == 0

  val eveningNews: PartialFunction[Int, String] = {
    case x if isEven(x) => s"$x is even"
  }

  // The method collect is described as "filter + map" because it uses a PartialFunction to select elements to which the function is applied.
  val evenNumbers = sample.collect(eveningNews)
  println(evenNumbers) //o/p- Vector(2 is even, 4 is even, 6 is even, 8 is even, 10 is even)

  val oddlyEnough: PartialFunction[Int, String] = {
    case x if !isEven(x) => s"$x is odd"
  }

  // The method orElse allows chaining another PartialFunction to handle input outside the declared domain.
  val numbers = sample.map(eveningNews orElse oddlyEnough)
  println(numbers) //o/p- Vector(1 is odd, 2 is even, 3 is odd, 4 is even, 5 is odd, 6 is even, 7 is odd, 8 is even, 9 is odd, 10 is even)

  // same as
  //applyOrElse-Applies this partial function to the given argument when it is contained in the function domain. Applies fallback function where this partial function is not defined.
  //Note that expression pf.applyOrElse(x, default) is equivalent to
  //if(pf isDefinedAt x) pf(x) else default(x)
  val numbers2 = sample.map(n => eveningNews.applyOrElse(n, oddlyEnough))
  println(numbers2) //o/p- Vector(1 is odd, 2 is even, 3 is odd, 4 is even, 5 is odd, 6 is even, 7 is odd, 8 is even, 9 is odd, 10 is even)

  val half: PartialFunction[Int, Int] = {
    case x if isEven(x) => x / 2
  }

  // Calculating the domain of a composition can be expensive.
  val oddByHalf = half.andThen(oddlyEnough)

  // Invokes `half.apply` on even elements!
  val oddBalls = sample.filter(oddByHalf.isDefinedAt)
  println(oddBalls) //o/p-Vector(2, 6, 10)

  // Better than filter(oddByHalf.isDefinedAt).map(oddByHalf)
  val oddBalls2 = sample.collect(oddByHalf)
  println(oddBalls2) //o/p-Vector(1 is odd, 3 is odd, 5 is odd)

  //collect is described as "filter + map"
  val oddBalls3 = sample.filter(oddByHalf.isDefinedAt).map(oddByHalf)
  println(oddBalls3) //o/p-Vector(1 is odd, 3 is odd, 5 is odd)

  // Providing "default" values.
  val oddsAndEnds = sample.map(n => oddByHalf.applyOrElse(n, (i: Int) => s"[$i]"))
  println(oddsAndEnds)//o/p- Vector([1], 1 is odd, [3], [4], [5], 3 is odd, [7], [8], [9], 5 is odd)

  //[1], [3], [4], [5], [7], [8], [9] are o/p when (i: Int) => s"[$i]") function is running as oddByHalf partial function is not defined for i/p 1, 3, 5, 7, 9 which are odd and not defined for i/p
  // like 4 whose half is 2 and is not odd ie the completed oddByHalf is not defined for 4 input.
}

/*
-/Users/kannupriyakalra/intellijCodes/ScalaLearning/src/main/scala/ScalaExercises/TestPartialFunctionScalaExercise.scala
-go through entire scala documentation by hovering on PartialFunction
-https://www.geeksforgeeks.org/partial-functions-in-scala/ - full read
-https://www.scala-lang.org/api/current/scala/PartialFunction.html
-https://www.baeldung.com/scala/partial-functions - full read
 */
