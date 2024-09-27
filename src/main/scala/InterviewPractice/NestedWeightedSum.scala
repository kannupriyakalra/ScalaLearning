package InterviewPractice

//https://www.youtube.com/watch?v=BkcY854TtD4&ab_channel=RoadToFAANG
object NestedWeightedSum extends App {

  def nestedWeightedSumFunction(input: List[Any], depth: Int = 1): Int = {

    depth * {
      input.map { x: Any =>
        val result: Int = x match {
          case i: Int => i
          case ls: List[Any] => nestedWeightedSumFunction(ls, depth + 1)
        }
        result
      }.sum
    }

  }

  assert(nestedWeightedSumFunction(List(1)) == 1)
  assert(nestedWeightedSumFunction(List(1, 2, 3)) == 6)
  assert(nestedWeightedSumFunction(List(1, List(2, 3), 4)) == 15) // 1 * 1 + 2 * (2 + 3) + 1 * 4
  assert(nestedWeightedSumFunction(List(1, List(2, List(3, 4)), 5)) == 52) // 1 * 1 + 2 * (2 + 3 * (3 + 4)) + 1 * 5
  assert(nestedWeightedSumFunction(List(1, List(2, List(3, 4)), List(5, 6))) == 69) // 1 * 1 + 2 * (2 + 3 * (3 + 4)) + 2 * (5 + 6)

  //Time Complexity = O(n)
  //Space Complexity = O(n) where n = no. of ints present regardless of depth
}
