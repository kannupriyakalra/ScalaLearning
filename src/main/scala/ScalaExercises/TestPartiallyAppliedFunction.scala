//https://www.scala-exercises.org/std_lib/partially_applied_functions

package ScalaExercises

object TestPartiallyAppliedFunction extends App {

  //A partially applied function is a function that you do not apply any or all the arguments, creating another function. This partially applied function doesn't apply any arguments.
  def sum(a: Int, b: Int, c: Int) = a + b + c

  val sum3: (Int, Int, Int) => Int = sum _ //equivalent to val sum3= x => sum(x), this is eta expansion, converting method to function

  println(sum3(1, 9, 7)) //o/p-17
  println(sum(4, 5, 6)) //o/p-15

  //Partially applied functions can replace any number of arguments:
  val sumC: Int => Int = sum(1, 10, _: Int)
  println(sumC(4)) //o/p-15

  //sum3 and sumC are examples of Partially applied functions


  //Currying is a technique to transform a function with multiple parameters into multiple functions which each take one parameter:
  def multiply(x: Int, y: Int) = x * y

  println((multiply _).isInstanceOf[Function2[_, _, _]]) //o/p- true

  val multiplyCurried: Int => Int => Int = (multiply _).curried

  println(multiply(4, 5)) //o/p-20
  println(multiplyCurried(3)(2)) //o/p-6

  val multiplyCurriedFour: Int => Int = multiplyCurried(4)
  println(multiplyCurriedFour(2)) //o/p-8
  println(multiplyCurriedFour(4)) //o/p-16

  //Currying allows you to create specialized versions of generalized functions:
  def customFilter(f: Int => Boolean)(xs: List[Int]) =
    xs filter f

  def onlyEven(x: Int) = x % 2 == 0

  val xs = List(12, 11, 5, 20, 3, 13, 2)

  println(customFilter(onlyEven)(xs)) //o/p-List(12, 20, 2)


  val onlyEvenFilter: List[Int] => List[Int] = customFilter(onlyEven) _ // equivalent to val onlyEvenFilter = xs => customFilter(onlyEven)(xs)
  println(onlyEvenFilter(xs)) //o/p-List(12, 20, 2)
}

//-https://www.youtube.com/watch?v=rORf1e9Gksg&list=PLmtsMNDRU0Bzj7INIrLugi3a_WClwQuiS&index=22&ab_channel=RocktheJVM
//-https://blog.rockthejvm.com/eta-expansion-and-paf/
