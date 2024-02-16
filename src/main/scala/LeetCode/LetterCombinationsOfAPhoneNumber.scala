package LeetCode

import scala.annotation.tailrec

//https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=leetcode-75
object LetterCombinationsOfAPhoneNumber extends App {
    implicit class Operations(left: List[String]) {
      def multiply(right: List[String]): List[String] = {
        if (left.isEmpty) return right
        for {
          leftOperand <- left
          rightOperand <- right
        } yield leftOperand + rightOperand
      }
    }

    val lookUp: Map[Char, List[String]] = Map(
      '2' -> List("a", "b", "c"),
      '3' -> List("d", "e", "f"),
      '4' -> List("g", "h", "i"),
      '5' -> List("j", "k", "l"),
      '6' -> List("m", "n", "o"),
      '7' -> List("p", "q", "r", "s"),
      '8' -> List("t", "u", "v"),
      '9' -> List("w", "x", "y", "z"))


    /*
        =========== DEBUG =========
        Digits ::23
        Accumulator ::List()
        =========== END =========
        =========== DEBUG =========
        Digits ::3
        Left:: Accumulator ::List(a, b, c)
        Right:: lookUp letters :: List("d","e","f")
        =========== END =========
        =========== DEBUG =========
        Digits ::
        Accumulator ::List(ad, ae, af, bd, be, bf, cd, ce, cf)
        =========== END =========
    */

  @tailrec
    def possibleMappings(digits: String, accumulator: List[String]): List[String] = {
      println("=========== DEBUG =========")
      println("Digits ::" + digits)
      println("Accumulator ::" + accumulator)
      println("=========== END =========")
      if (digits.isEmpty) return accumulator
      val letters: List[String] = lookUp(digits.head)
      // left::accumulator * right::letters
      // (new operations(accumulator)).multiply(letters)
      possibleMappings(digits.tail, accumulator.multiply(letters))
    }
    def letterCombinations(digits: String): List[String] = {
      possibleMappings(digits, Nil)
    }

  println(letterCombinations("23"))
  println(letterCombinations(""))
  println(letterCombinations("2"))

}


