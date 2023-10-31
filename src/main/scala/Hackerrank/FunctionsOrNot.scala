package Hackerrank

//identifying a relation which represents a valid function
//https://www.hackerrank.com/challenges/functions-or-not/problem?isFullScreen=true

/*
a relation b/w (x,y) represents a valid function if for same i/p x, their is same o/p y. function means always for same i/p you ll get same o/p.

Map has all keys unique ie their is 1 value for 1 key. If for the Map we get, we check each (x,y) and see that for each x their is same y, ie (1,2) exist then (1,3) cannot exist and
if it does it means for same i/p 1 sometimes we are getting o/p as 2 and sometimes as 3 and thus relation b/w these pairs don't make for a valid function.

step 1- take i/o pairs in Map. convert i/p Seq to a Map.
step 2- for every element pair key of Seq match it with key in Map to see if their values match. eg- if corresponding to 1 is 3 in Seq , and in map for key = 1 value is 2, that is violation that is
discrepancy not a function, use forAll for this to check for every new i/p, same o/p is coming or not.

 */
object FunctionsOrNot {

  def checkFunctionsOrNot(pairs: Seq[(Int, Int)]): Boolean = {

    val pairsMap: Map[Int, Int] = pairs.toMap

    //pairs.forall(x => pairsMap(x._1) == x._2) // alternate way of doing , for every pair in Seq x: (Int, Int) it finds value corresponding to keyInSeq and matches it with valueInSeq and re
    pairs.forall { case (keyInSeq, valueInSeq) => pairsMap(keyInSeq) == valueInSeq }

  }

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    val t = stdin.readLine.trim.toInt //t = no. of test cases
    for (nItr <- 1 to t) {

      val n = stdin.readLine.trim.toInt // n = number of (x,y) pairs in this test case
      val pairs: Seq[(Int, Int)] = for (nItr <- 1 to n) yield {
        val readline: Array[Int] = stdin.readLine.split(" ").map(x => x.toInt)
        val xCoordinate = readline(0)
        val yCoordinate = readline(1)
        (xCoordinate, yCoordinate)
      }

      if (checkFunctionsOrNot(pairs)) println("YES") else println("NO")
    }

  }
}

/*
➜  ScalaLearning git:(main) ✗ scala
Welcome to Scala 3.2.2 (20.0.1, Java OpenJDK 64-Bit Server VM).
Type in expressions for evaluation. Or try :help.

scala> Map((1,1),(2,2))
val res0: Map[Int, Int] = Map(1 -> 1, 2 -> 2)

scala> Map((1,1),(2,2),(2,3))
val res1: Map[Int, Int] = Map(1 -> 1, 2 -> 3)

scala> val m = Map((1,1),(2,2),(2,3))
val m: Map[Int, Int] = Map(1 -> 1, 2 -> 3)

scala> m(1)
val res2: Int = 1

scala> m(2)
val res3: Int = 3

scala> m(5)
java.util.NoSuchElementException: key not found: 5
  at scala.collection.immutable.Map$Map2.apply(Map.scala:296)
  at scala.Function1.apply$mcII$sp(Function1.scala:71)
  at scala.Function1.apply$mcII$sp$(Function1.scala:71)
  at scala.collection.AbstractMap.apply$mcII$sp(Map.scala:405)
  ... 33 elided

scala> m.isDefinedAt(1)
val res5: Boolean = true

scala> m.isDefinedAt(5)
val res6: Boolean = false

Line 37 why cant i write List here? How do i take List here instead of Seq? Should i not bother as List is Seq. val pairs: Seq[(Int, Int)], seq is a supertype of list so don’t bother

 */



