package Hackerrank

//https://www.hackerrank.com/challenges/fp-filter-array/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
object FilterArray {

  def f(delim: Int, ls: List[Int]): List[Int] = {
    ls match {
      case ::(head, next) => if (head < delim) head :: f(delim, next) else f(delim, next)
      case Nil => Nil
    }
  }

  def main(args: Array[String]): Unit = {

    f(3, List(1, 2, 3, 4, 5, 6)).foreach(println)
    // f(3, List()).foreach(println)
    //f(7, List()).foreach(println)
    // f(-1, List()).foreach(println)

  }
}
