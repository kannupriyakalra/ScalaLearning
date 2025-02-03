package Hackerrank

//https://www.hackerrank.com/challenges/filter-elements/problem?isFullScreen=true
object FilterElements extends App {

  //Solution -1
  //  def findElementsWithCount(atLeastCount: Int, elements: List[Int]): List[Int] = {
  //    //val elementCount = Map[Int, (Int, Int)](elements.toSet)
  //    var elementCount = scala.collection.mutable.Map[Int, (Int, Int)]().withDefaultValue((Int.MaxValue, 0))
  //    elements.zipWithIndex.foreach {
  //      case (element, index) =>
  //        val (valueIndex, count) = elementCount(element)
  //        elementCount(element) = (if (index < valueIndex) index else valueIndex, count + 1)
  //    }
  //
  //    elementCount.filter {
  //      case (_, (_, count) ) => count >= atLeastCount
  //    }.toList.sortBy {
  //      case (_, (valueIndex, _)) => valueIndex
  //    }.map {
  //      case (element, (_, _)) => element
  //    }
  //  }

  //Solution -2 Best
  def findElementsWithCount(atLeastCount: Int, elements: List[Int]): List[Int] = {

    /*
    * Time complexity  - O(nlogn)
    * Space complexity - O(n)
    * */

    def countElements(
                       remainingElements: List[Int],
                       index: Int,
                       elementCount: Map[Int, (Int, Int)]
                     ): Map[Int, (Int, Int)] = {
      remainingElements match {
        case Nil => elementCount
        case head :: tail =>
          val (valueIndex, count) = elementCount(head)
          val updatedCount =
            elementCount + (head -> (if (index < valueIndex) index else valueIndex, count + 1))
          countElements(tail, index + 1, updatedCount)
      }
    }

    val initialElementCount = Map[Int, (Int, Int)]().withDefaultValue((Int.MaxValue, 0))
    val updatedElementCount = countElements(elements, 0, initialElementCount)

    val filteredElements = updatedElementCount.filter {
      case (_, (_, count)) => count >= atLeastCount
    }.toList.sortBy {
      case (_, (valueIndex, _)) => valueIndex
    }.map {
      case (element, (_, _)) => element
    }

    filteredElements
  }



  val stdin = scala.io.StdIn

  val noOfTestCases = stdin.readLine.trim.toInt

  for (t <- 1 to noOfTestCases) {

    val readline: Array[Int] = stdin.readLine.split(" ").map(x => x.toInt)
    val sizeOfList = readline(0)
    val atLeastCount = readline(1)

    val listOfElements = stdin.readLine.trim().split(" ").map(x => x.toInt).toList

    val result: List[Int] = findElementsWithCount(atLeastCount, listOfElements)
    if (result.isEmpty) println(-1) else println(result.mkString(" "))

  }

}

/*

1. Is Solution 1 functional?



2.

 */
