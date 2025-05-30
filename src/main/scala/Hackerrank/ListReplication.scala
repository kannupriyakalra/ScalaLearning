package Hackerrank

//https://www.hackerrank.com/challenges/fp-list-replication/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
object ListReplication {

  //replicate value given num times
  def replicate(num: Int, value: Int): List[Int] = {
    if (num <= 0) Nil //if a value has to be replicated 0 times we return empty list ie base case of recursion
    else value :: replicate(num - 1, value) //replicate(num - 1, value) returns a List of num-1 values and we prepend the value to it.
  }

  //replicate each element of input list num times
  def f(num: Int, ls: List[Int]): List[Int] = {
    //replicate each element of ls list n times ie flatMap will traverse the input list and call 'replicate' method for each element.
    //we used flatMap here as o/p of replicate is List[Int] and it will make our o/p as List[List[Int]] but we need to return List[Int].
    val a: List[Int] = ls.flatMap(x => replicate(num, x))
    a
  }

  //alternate way- use fill function, it does the same thing as replicate
  def f2(num: Int, ls: List[Int]): List[Int] = {
    val a: List[Int] = ls.flatMap(x => List.fill(num)(x))
    a
  }

  def main(args: Array[String]): Unit = {

    // various test cases tried
    // f(2, List(1, 2, 3)).foreach(println)
    // f(0, List(1, 2, 3)).foreach(println)
    // f(-1, List(1, 2, 3)).foreach(println)
    // f(10, List(1, 2, 3)).foreach(println)

    // way to print the list
    // List(1, 2, 3).foreach(println)

    // f2(2, List(1, 2, 3)).foreach(println)
    // f2(0, List(1, 2, 3)).foreach(println)
    // f2(-1, List(1, 2, 3)).foreach(println) //empty list
    // f2(10, List(1, 2, 3)).foreach(println) //List(1, 1, 1, 1, 1, 1, 1, 1, 1, 1,...)


  }

}
