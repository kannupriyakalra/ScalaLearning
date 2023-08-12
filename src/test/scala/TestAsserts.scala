package ScalaExercises
//asserts are used for writing unit test
//https://www.scala-exercises.org/std_lib/asserts, https://github.com/scala-exercises/exercises-stdlib/blob/main/src/main/scala/stdlib/Asserts.scala
//add scala test to build.sbt-  https://github.com/scala-exercises/exercises-stdlib/blob/main/build.sbt, https://docs.scala-lang.org/overviews/scala-book/sbt-scalatest-tdd.html

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
class TestAsserts extends AnyFlatSpec with Matchers {

  val left = 1
  val right = 1
  assert(left == right)

  assert(2 == 1 + 1)


  //requires scala test library to be imported in build.sbt to be able to use should method
  true should be(true)

  val v1 = 4
  v1 shouldEqual 4


  //AnyFlatSpec test style-copied below test from AnyFlatSpec documentation
  behavior of "An empty Set"

  it should "have size 0" in {
    assert(Set.empty.size === 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

}

