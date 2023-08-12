//https://www.scala-exercises.org/std_lib/case_classes
package ScalaExercises

object TestCaseClasses extends App {

  abstract class Term
  case class Var(name: String) extends Term

  case class Fun(arg: String, body: Term) extends Term

  case class App(f: Term, v: Term) extends Term

  //example-For every case class the Scala compiler generates an equals method which implements structural equality and a toString method.
  //== is syntactic sugar of equals, in normal class by x1 == x2 referential equality is checked, in case class structural equality is checked ie value equality.
  val x1: Var = Var("x")
  val x2 = Var("x")
  val y1 = Var("y")
  println("" + x1 + " == " + x2 + " => " + (x1 == x2))
  println("" + x1 + " == " + y1 + " => " + (x1 == y1))

  /*
  o/p-
  Var(x) == Var(x) => true
  Var(x) == Var(y) => false
   */
  val abc: String = "hello"
  println(abc)

//example- It only makes sense to define case classes if pattern matching is used to decompose data structures.
    def printTerm(term: Term): Unit = {
      term match {
        case Var(n) =>
          print(n)
        case Fun(x, b) =>
          print("^" + x + ".")
          printTerm(b)
        case App(f, v) =>
          print("(")
          printTerm(f)
          print(" ")
          printTerm(v)
          print(")")
      }
    }

    def isIdentityFun(term: Term): Boolean = term match {
      case Fun(x, Var(y)) if x == y => true
      case _ => false
    }

    val id = Fun("x", Var("x"))
    val t = Fun("x", Fun("y", App(Var("x"), Var("y"))))
    printTerm(t) //o/p- ^x.^y.(x y)
    println
    println(isIdentityFun(id)) //o/p-true
    println(isIdentityFun(t)) //o/p-false

  //Case classes have an automatic hashcode method that works:
  case class Person(first: String, last: String)

  val p11 = new Person("Fred", "Jones")
  val p2 = new Person("Shaggy", "Rogers")
  val p3 = new Person("Fred", "Jones")

  println("hashcode example- "+ (p11.hashCode == p2.hashCode))//o/p- hashcode example- false
  println("hashcode example- "+ (p11.hashCode == p3.hashCode) ) //o/p-hashcode example- true

  //Case classes can be disassembled to their constituent parts as a tuple:
  case class Person1(first: String, last: String, age: Int = 0, ssn: String = "")

  val p1 = Person1("Fred", "Jones", 23, "111-22-3333")

  val parts = Person1.unapply(p1).get // this seems weird, but it's critical to other features of Scala
//unapply returns Option[tuple] ie  Option[(String, String, Int, String)] which is a some here ie why get which is unsafe is used otherwise getOrElse should have been used.
  //apply -constructs object ie its like new, unapply- deconstructs object ie unapply is run on an object p1 whose data members are returned.
  println(parts) //o/p- (Fred,Jones,23,111-22-3333)
  println(parts._1) //o/p-Fred

  //Case classes are Serializable:
  // Serializable means can be written to disk
  case class PersonCC(firstName: String, lastName: String)

  val indy = PersonCC("Indiana", "Jones")

  println(indy.isInstanceOf[Serializable] )//checks if indy is of type [Serializable] which is true as indy is of type PersonCC which is a case class and every case
  //class extends Serializable. //o/p-true
  //[Serializable] is a type here like int, PersonCC

  println(indy.isInstanceOf[PersonCC]) //o/p-true
}

/*
Case classes are regular classes which export their constructor parameters means the parameter are public
 */