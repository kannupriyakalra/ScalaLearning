package InterviewPractice

//Explain how TDD works with an example of a factorial of a number.

import org.scalatest.Assertions.intercept
import org.scalatest.funsuite.AnyFunSuite

//Solution - 1

//object FactorialCalculatorTDD extends App {
//  def fact(i: Int):Int = {
//    if(i < 0) throw new IllegalArgumentException(s"unsupported argument $i")
//    if(i == 0 || i == 1)  return 1
//    i * fact(i-1)
//  }
//
//  // tc-1  input 1  =>  output 1
//  assert(fact(1) == 1)
//  // tc-2  input 2  => output 2
//  assert(fact(2) == 2)
//  // tc-3  input 0  => output 1
//  assert(fact(0) == 1)
//  // tc-4  input 3  => output 6
//  assert(fact(3) == 6)
//  // tc-5  input 4  => output 24
//  assert(fact(4) == 24)
//  // tc-6  input 4  => output 24
//  intercept[IllegalArgumentException] {
//    fact(-1)
//  }
//}


//Solution - 2
object FactorialCalculatorTDD extends App {

  def factorial(i: Int): Int = {
    if (i < 0) throw new IllegalArgumentException(s"unsupported argument $i")
    if (i == 0 || i == 1) return 1
    i * factorial(i - 1)
  }

  class FactorialCalculatorTest extends AnyFunSuite {

    // tc-1  input 0  => output 1
    test("Factorial of zero is one") {
      assert(factorial(0) == 1)
    }

    // tc-2  input 1  =>  output 1
    test("Factorial of one is one") {
      assert(factorial(1) == 1)
    }

    // tc-3  input 2  => output 2
    test("Factorial of two is two") {
      assert(factorial(2) == 2)
    }

    // tc-4  input 3  => output 6
    test("Factorial of three is six") {
      assert(factorial(3) == 6)
    }

    // tc-5  input 4  => output 24
    test("Factorial of four is twenty-four") {
      assert(factorial(4) == 24)
    }

    // tc-6  input 4  => output 24
    test("Factorial of a negative number throws an exception") {
      intercept[IllegalArgumentException] {
        factorial(-1)
      }
    }

  }

}














