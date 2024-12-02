package LeetCode.Easy

// https://leetcode.com/problems/valid-parentheses/description/

/*
1. for every open bracket add it to the stack
2. for every closed bracket pop one char from the stack
3. assert that the popped char is the corresponding open char
 */
object ValidParentheses extends App {

  //time complexity = O(n), space complexity = O(n)
  def isValid(s: String): Boolean = check(s.toList)

  def check(input: List[Char], stack: List[Char] = Nil): Boolean = {
    input match {
      case Nil => stack.isEmpty
      case '(' :: tail => check(tail, '(' :: stack)
      case '[' :: tail => check(tail, '[' :: stack)
      case '{' :: tail => check(tail, '{' :: stack)

      case ')' :: tail => stack match {
        case '(' :: stackTail => check(tail, stackTail)
        case _ => false //as order matching is important, if '(' doesn't get ')' then its not a validParenthesis so return false.
      }

      case ']' :: tail => stack match {
        case '[' :: stackTail => check(tail, stackTail)
        case _ => false
      }

      case '}' :: tail => stack match {
        case '{' :: stackTail => check(tail, stackTail)
        case _ => false
      }

      case _ => false

    }
  }

  println(isValid("()"))
  println(isValid("()[]{}"))
  println(isValid("(]"))
  println(isValid("([])"))
  println(isValid("([)]"))
  println(isValid("["))

}
