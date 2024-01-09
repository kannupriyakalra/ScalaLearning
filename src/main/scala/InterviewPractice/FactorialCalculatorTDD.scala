package InterviewPractice

import org.scalatest.Assertions.intercept

//Explain how TDD works with an example of a factorial of a number.
//object FactorialCalculatorTDD extends App {
//
//  def factorial(i: Int): Int = {
//    if (i < 0) throw new IllegalArgumentException(s"unsupported argument $i")
//    if (i == 0 || i == 1) return 1
//    i * factorial(i - 1)
//  }
//
//
//
//    // tc-1  input 0  => output 1
//    test("Factorial of zero is one") {
//      assert(factorial(0) == 1)
//    }
//
//    // tc-2  input 1  =>  output 1
//    test("Factorial of one is one") {
//      assert(factorial(1) == 1)
//    }
//
//    // tc-3  input 2  => output 2
//    test("Factorial of two is two") {
//      assert(factorial(2) == 2)
//    }
//
//    // tc-4  input 3  => output 6
//    test("Factorial of three is six") {
//      assert(factorial(3) == 6)
//    }
//
//    // tc-5  input 4  => output 24
//    test("Factorial of four is twenty-four") {
//      assert(factorial(4) == 24)
//    }
//
//    // tc-6  input 4  => output 24
//    test("Factorial of a negative number throws an exception") {
//      intercept[IllegalArgumentException] {
//        factorial(-1)
//      }
//    }
//
//
//
//}

import org.scalatest.funsuite.AnyFunSuite

// Define the class to test
//class FactorialCalculator {
//
//  // Define the method to calculate the factorial of a number
//  def fact(n: Int): Int = {
//    // Check if the input is valid
//    require(n >= 0, "n must be non-negative")
//    // Use recursion to calculate the factorial
//    if (n <= 1) 1 else n * fact(n - 1)
//  }
//}

// Define the test class
//class FactorialCalculatorTest extends AnyFunSuite {
//
//  // Create an instance of the class to test
//  val calculator = new FactorialCalculator()
//
//  // Write the test cases
//  test("Factorial of zero is one") {
//    assert(calculator.fact(0) == 1)
//  }
//
//  test("Factorial of one is one") {
//    assert(calculator.fact(1) == 1)
//  }
//
//  test("Factorial of two is two") {
//    assert(calculator.fact(2) == 2)
//  }
//
//  test("Factorial of three is six") {
//    assert(calculator.fact(3) == 6)
//  }
//
//  test("Factorial of four is twenty-four") {
//    assert(calculator.fact(4) == 24)
//  }
//
//  test("Factorial of a negative number throws an exception") {
//    intercept[IllegalArgumentException] {
//      calculator.fact(-1)
//    }
//  }
//}










