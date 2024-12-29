package LeetCode.Easy

//https://leetcode.com/problems/plus-one/description/
object PlusOne extends App {

  //time complexity = O(n), space complexity = O(n)
  def plusOne(digits: Array[Int]): Array[Int] = {

    def loop(input: List[Int], carry: Int): List[Int] = {

      input match {
        case head :: tail => if (head + carry <= 9) (head + carry) :: tail else 0 :: loop(tail, 1)
        case Nil => if (carry == 0) Nil else List(carry)
      }

    }

    loop(digits.toList.reverse, 1).reverse.toArray
  }

  println(plusOne(Array(1, 2, 3)).mkString(" "))
  println(plusOne(Array(4, 3, 2, 1)).mkString(" "))
  println(plusOne(Array(9)).mkString(" "))
  println(plusOne(Array(9, 9, 9, 9)).mkString(" "))

}

/*
as array size cannot be increased ie why list is used.
with list as we can only work on head at a time ie why we had to reverse. to work on head we use pattern match.
we used recursion to traverse.
time complexity = O(n)--as one time traversed, space complexity = O(n)-- as only one list is created
 */