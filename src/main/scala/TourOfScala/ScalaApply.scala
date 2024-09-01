package TourOfScala

/*
https://tourofscala.com/scala/apply

Scala apply

apply is a magic Scala method. There is no need to call apply explicitly to execute it. And this is part of the tool that the case class use.

apply can be called like any other method ( Person.apply(...) ) but it can be used without calling its name explicitely ( Person(...) ). And that's it.

In fact, you could implement a function yourself:

val add = new Function2[Int, Int, Int] {

  def apply(a: Int, b: Int): Int = a + b

}

val result = add(1, 2)

assert(result == 3)

Try and copy it above in the editor.

You could also implement if yourself ! Try it by yourself before looking at Scastie.

Solution: https://scastie.scala-lang.org/ZMltOxDQSQ2yusDiU6LNSg
 */
object ScalaApply extends App {

  class Person(
                // 'val' to make the fields "public"
                val firstName: String,
                val lastName: String
              ) {
    lazy val fullName: String = s"$firstName $lastName"

    def apply(talk: String): String = s"$fullName says: '$talk'"

    // we are going to learn about 'override' in a later SKB
    override def toString: String = s"Person($firstName, $lastName)"
  }

  object Person {
    // the "case class" version of "apply"
    def apply(firstName: String, lastName: String): Person = {
      new Person(firstName, lastName)
    }

    // "enhanced" constructor
    def apply(fullName: String): Person = {
      // split is cutting the 'String' into pieces based on the given separator
      val parts = fullName.split(" ")
      // we are going to learn about this later. It gets complicated.
      val firstName: String = parts.lift(0).getOrElse("N/A")
      val lastName: String = parts.lift(1).getOrElse("N/A")
      // instantiating the class
      new Person(firstName, lastName)
    }
  }

  // use the 'new' to instantiate the class
  val leo: Person = new Person("Leo", "Benkel")
  println(leo)

  // use the "case class" version of "apply"
  val tesla: Person = Person("Nikola", "Tesla")
  println(tesla)

  // use the "enhanced" constructor
  val eddison: Person = Person.apply("Thomas Edison")
  //val eddison: Person = Person("Thomas Edison")
  println(eddison)

  // use the apply inside the class
  println(tesla("I want to help bring free energy to the world!"))
  println(eddison.apply("Thomas Edison"))

  val p: Person = new Person("Wonderful", "You")

  assert(leo.firstName == "Leo")
  assert(tesla.lastName == "Tesla")
  assert(p("Hello World") == s"Wonderful You says: 'Hello World'")

  println("Congratulations ! 'Don’t cry because it’s over. Smile because it happened.' - Dr. Seuss")


}
