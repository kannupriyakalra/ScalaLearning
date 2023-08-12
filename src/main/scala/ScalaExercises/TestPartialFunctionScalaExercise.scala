//https://www.scala-exercises.org/std_lib/partial_functions
package ScalaExercises

object TestPartialFunctionScalaExercise extends App{

 // A partial function is a trait that when implemented can be used as building blocks to determine a solution .The trait PartialFunction requires that the method isDefinedAt and apply be implemented:

  val doubleEvens: PartialFunction[Int, Int] =
    new PartialFunction[Int, Int] {
      //States that this partial function will take on the task
      def isDefinedAt(x: Int) = x % 2 == 0

      //What we do if this partial function matches
      def apply(v1: Int) = v1 * 2
    }

  val tripleOdds: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
    def isDefinedAt(x: Int) = x % 2 != 0

    def apply(v1: Int) = v1 * 3
  }

  // orElse- Composes this partial function with a fallback partial function which gets applied where this partial function is not defined. The method orElse allows chaining another PartialFunction
  // to handle input outside the declared i/p domain
  val whatToDo: PartialFunction[Int, Int] = doubleEvens orElse tripleOdds //Here we chain the partial functions together

  println(whatToDo(3))//o/p-9
  println(whatToDo(4))//o/p-8

  //Case statements are a quick way to create partial functions. When you create a case statement, the apply and isDefinedAt methods are created automatically.
  val doubleEvens2: PartialFunction[Int, Int] = {
    case x if (x % 2) == 0 => x * 2
  }
  val tripleOdds2: PartialFunction[Int, Int] = {
    case x if (x % 2) != 0 => x * 3
  }

  val whatToDo2 = doubleEvens2 orElse tripleOdds2 //Here we chain the partial functions together
  println(whatToDo2(3)) //o/p-9
  println(whatToDo2(4)) //o/p-8

  //The result of partial functions can have an andThen function added to the end of the chain:
  //andThen function-Composes this partial function with a transformation function that gets applied to results of this partial function
  val addFive: Int => Int = (x: Int) => x + 5
  val whatToDo3 = doubleEvens2 orElse tripleOdds2 andThen addFive //Here we chain the partial functions together

  println(whatToDo3(3)) //o/p-14
  println(whatToDo3(4)) //o/p-13

//andThen can be used to continue onto another chain of logic:
  val printEven: PartialFunction[Int, String] = {
    case x if (x % 2) == 0 => "Even"
  }
  val printOdd: PartialFunction[Int, String] = {
    case x if (x % 2) != 0 => "Odd"
  }

  val whatToDo4 = doubleEvens2 orElse tripleOdds2 andThen (printEven orElse printOdd)

  println(whatToDo4(3)) //o/p-Odd
  println(whatToDo4(4)) //o/p-Even
}
