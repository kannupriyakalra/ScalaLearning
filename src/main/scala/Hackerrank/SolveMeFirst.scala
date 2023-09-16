package Hackerrank

object SolveMeFirst {

  def main(args: Array[String]): Unit = {
    val input: Iterator[String] = io.Source.stdin.getLines() // console input, line by line
    val ints: Iterator[Int] = input.take(2).map(_.toInt)
    println(ints.sum)
  }

}
