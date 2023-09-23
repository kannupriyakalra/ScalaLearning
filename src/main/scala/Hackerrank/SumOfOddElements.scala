package Hackerrank

//https://www.hackerrank.com/challenges/fp-sum-of-odd-elements/problem?isFullScreen=true
object SumOfOddElements {

    def f(arr: List[Int]): Int = { //most optimised solution

      arr.foldLeft(0)((arr, a) => if (a % 2 != 0) arr + a else arr  )

    }

  //2nd approach-
  def f2(arr: List[Int]): Int = {

    // arr.filter(x => x % 2 != 0).foldLeft(0)((acc, a) => acc + a)
    arr.filter(_ % 2 != 0).foldLeft(0)(_ + _)

  }

  //3rd approach-
  def f3(arr: List[Int]): Int = {

    // arr.filter(x => x % 2 != 0).foldLeft(0)((acc, a) => acc + a)
    arr.filter(_ % 2 != 0).sum

  }

  //4th approach-

  def f4(arr: List[Int]): Int = {

    //uncomment below solution and click desugar to get withFilter implementation.
    //(for (num <- arr if (num % 2 != 0)) yield num).sum
    (arr.withFilter(num => (num % 2 != 0)).map(num => num)).sum
  }

  //5th approach-

  def f5(arr: List[Int]): Int = {
    arr match {
      case ::(head, next) => if (head % 2 != 0) head + f3(next) else f3(next)
      case Nil => 0
    }
  }

  //6th approach-

  def f6(arr: List[Int]): Int = {
    arr match {
      case ::(head, next) => head % 2 match {
        case 0 => f4(next)
        case _ => head + f4(next)
      }
      case Nil => 0
    }
  }


  def main(args: Array[String]): Unit = {

    // println(f(List(1, 2, 3, 4, 5)))
    println(f(List(-1, 2, -3, 4, -5)))
    //println(f2(List(-1, 2, -3, 4, -5)))
    //println(f3(List(-1, 2, -3, 4, -5)))
    //println(f4(List(-1, 2, 0, 4, -5)))

  }

}
