//eq, == examples covered.
//https://www.scala-exercises.org/std_lib/objects
package ScalaExercises

object TestObjects extends App {

  object Greeting {
    def english = "Hi"

    def espanol = "Hola"
  }

  val x: Greeting.type = Greeting //Greeting is a object of type Greeting.type
  val y: Greeting.type = x

  println(x eq y) //o/p- true

  val z: Greeting.type = Greeting //here z is pointing to same object Greeting, no new object is created, Greeting is a singleton object.

  println(x eq z) //o/p- true


  //https://www.scala-exercises.org/std_lib/case_classes

  case class Person(first: String, last: String)

  val p1 = new Person("Fred", "Jones")
  val p2 = new Person("Shaggy", "Rogers")
  val p3 = new Person("Fred", "Jones")

  println((p1 == p2)) //o/p-false
  println((p1 == p3)) //o/p- true
  println((p1 eq p2)) //o/p-false
  println((p1 eq p3)) //o/p-false

//https://www.scala-exercises.org/std_lib/lists
  val a = List(1, 2, 3)
  val b = List(1, 2, 3)
  println((a eq b))//o/p-false
  println((a == b))//o/p- true

  //List.apply- makes a new object in memory ie why a eq b is  false
  //== and equals are same, they test equality (same content) in case of case classes, reference in case of normal class.
  //eq tests identity (same object), eq checks for reference equality, pointer equality
}
