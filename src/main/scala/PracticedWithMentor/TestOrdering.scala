package PracticedWithMentor

object TestOrdering extends App {

  //implement ordering trait for int type
  object IntOrdering extends Ordering[Int] {

    //this is our own implementation of compare type
    override def compare(x: Int, y: Int): Int = x - y

    //x-y as per line 80 of Ordering.scala
    //Returns an integer whose sign communicates how x compares to y.
    //The result sign has the following meaning:
    //negative if x < y
    //positive if x > y
    //zero otherwise (if x == y)
  }

  case class Person(name: String, age: Int)

  val person1 = Person("bob", 30)
  val person2 = Person("ann", 32)

  // sort by age
  implicit object PersonOrdering extends Ordering[Person] { //we made it implicit so it can be sent implicitly in line34.
    def compare(a: Person, b: Person): Int = a.age.compare(b.age)
  }

  println(PersonOrdering.compare(person1, person2)) // o/p- -1 negative ie person1 is less in age to person2


  //implemented Person Ordering on Tree[Person]
  import TestBinarySearchTree._

  val t: Tree[Person] = Empty.insert(person1).insert(person2)
  println(t) //o/p-//Node(Person(bob,30),Empty,Node(Person(ann,32),Empty,Empty))

  //to insert values inside tree use PersonOrdering as tree s insert method is generic and requires an ordering to compare person based on age as ann is 32 and ie > 30 so it went to right node.
}



