package LeetCode.Easy

//https://leetcode.com/problems/roman-to-integer/description/

/*

we have to iterate from left to right and compare 2 adjacent characters and if the value of left character is < the value of right character then we do subtraction otherwise we do addition.

for each character c of string going from left to right:
  decide its sign by comparing against the next char, -ve if current < next

sum values of all the characters along with their sign

 */
object RomanToInteger extends App {

  val romanToIntegerMap: Map[Char, Int] = Map(
    'I' -> 1,
    'V' -> 5,
    'X' -> 10,
    'L' -> 50,
    'C' -> 100,
    'D' -> 500,
    'M' -> 1000
  )

    //time complexity = O(n), space complexity = O(n)
  def romanToInt(s: String): Int = {

    def loop(input: List[Char]): Int =
      input match {
        case head1 :: head2 :: tail => (if (romanToIntegerMap(head1) < romanToIntegerMap(head2)) -1 else 1) * romanToIntegerMap(head1) + loop(head2 :: tail) //1000 -100 + 1000 -10 loop ...
        case head :: tail => romanToIntegerMap(head) + loop(tail)
        case Nil => 0
      }

    loop(s.toList)

  }

  println(romanToInt("III"))
  println(romanToInt("LVIII"))
  println(romanToInt("MCMXCIV"))

}

/*
time complexity = n ( to convert to list) + n (to traverse) = O(n)
space complexity = n ( to convert to list a new list is made ) = O(n)
 */
