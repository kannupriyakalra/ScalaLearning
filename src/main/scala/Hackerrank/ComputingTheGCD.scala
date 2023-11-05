//https://www.hackerrank.com/challenges/functional-programming-warmups-in-recursion---gcd/problem?isFullScreen=true

/*
GCD-Greatest Common Divisor value of x and y using the Euclidean algorithm = GCD(x,y) = GCD (y,x) --order of x,y doesn't matter. Also it doesn't matter if x > y or y > x.
x/y ~ dividend/divisor , example - 12/15 -when done using long division method (when remainder becomes 0, last divisor is gcd)

gcd(12, 15)
gcd (15, 12%15) = gcd(15, 12)
gcd (12, 15%12) = gcd(12, 3)
 gcd (3, 12%3)  =  gcd(3, 0)

above iterations happen in long division and we can make a pattern of them.

gcd(x, y)
gcd(y, x%y) // with every call y = x%y, x = y
gcd(y, 0) //as here y = 0 , x = y is the answer
 */
package Hackerrank

import scala.io.StdIn.readLine

object ComputingTheGCD {

  def gcd(x: Int, y: Int): Int = {
    if (y == 0) x
    else gcd(y, x % y)
  }


  /** This part handles the input/output. Do not change or modify it * */
  def acceptInputAndComputeGCD(pair: List[Int]) = {
    println(gcd(pair.head, pair.reverse.head))
  }

  def main(args: Array[String]): Unit = {
    /** The part relates to the input/output. Do not change or modify it * */
    acceptInputAndComputeGCD(readLine().trim().split(" ").map(x => x.toInt).toList)
  }
}

/*

test cases-
println(gcd(128, 96)) //o/p- 32
/*
gcd(128,96)
gcd(96, 128 % 96) = gcd(96, 32)
gcd(32, 96 % 32) = gcd(32,0)
y==0, 32 answer
 */
println(gcd(0, 5))//o/p- 5
println(gcd(5, 0))//o/p- 5

gcd-

Mod  (%) operator
a/b , a is dividend, b is divisor, answer is quotient
a % b , answer is remainder

5/3 =1
5%3 =2

14/21 = 0
14%21 = 14

Greatest common divisor- https://byjus.com/maths/greatest-common-divisor/#:~:text=The%20greatest%20common%20divisor%20(GCD,can%20be%20divided%20by%205.
 */