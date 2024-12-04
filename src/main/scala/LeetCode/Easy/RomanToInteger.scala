package LeetCode.Easy

//https://leetcode.com/problems/roman-to-integer/description/
object RomanToInteger extends App {

  val m: Map[Char, Int] = Map(
    'I' -> 1,
    'V' -> 5,
    'X' -> 10,
    'L' -> 50,
    'C' -> 100,
    'D' -> 500,
    'M' -> 1000
  )

  def romanToInt(s: String): Int = {

    def loop(input: List[Char]): Int =
      input match {
        case head1 :: head2 :: tail => (if (m(head1) < m(head2)) -1 else 1) * m(head1) + loop(tail)
        case head :: tail => m(head) + loop(tail)
        case Nil => 0
      }

    loop(s.toList)

  }

  println(romanToInt("III"))
  println(romanToInt("LVIII"))
  println(romanToInt("MCMXCIV"))

}
