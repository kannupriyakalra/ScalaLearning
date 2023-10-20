package ManningFunctionalProgrammingInScala

object GroupByExample {

  /*
  def groupByTest(charges: List[Charge]): List[Charge] = {
    charges.groupBy(_.cc).values.map(_.reduce {
      case (c1, c2) => c1.combine(c2)
    }).toList
  }
   */

  def groupByTest(charges: List[Charge]): List[Charge] = charges.groupBy(_.cc).values.map(_.reduce( _ combine _)).toList
  def groupByTestWithoutReducing(charges: List[Charge]): Map[String, List[Charge]] = {
    charges.groupBy(_.cc)
  }

  def main(args: Array[String]): Unit = {
    groupByTestWithoutReducing(List(Charge("firstCharge", 1.0), Charge("secondCharge", 2.0), Charge("firstCharge", 3.0))).map { case (key, value) => println(s"key is $key, value is $value") }
    groupByTest(List(Charge("food", 1.0), Charge("travel", 2.0), Charge("food", 3.0), Charge("food", 7.0))).map(println)
  }

  case class Charge(cc: String, amount: Double) {
    def combine(other: Charge): Charge = {
      if (cc == other.cc) {
        Charge(cc, amount + other.amount)
      } else {
        throw new Exception("Can't combine charges of two different cards")
      }
    }
  }
}
