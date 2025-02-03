package ScalaBasics

//object testUnapply extends App{
//
////  class Person(val name: String, val age: Int)
////
////  object Person {
////    def unapply(person: Person): Option[(String, Int)] = Some((person.name, person.age))
////  }
////
////  val person: Person = new Person("John", 30)
////
////  person match {
////    case Person(name, age) => println(s"Name: $name, Age: $age")
////    case _ => println("Not a Person")
////  }
//
//  case class Person(name: String, age: Int)
//
//  val person: Person = Person.apply("John", 30)
//
//  person match {
//    case Person(name, age) => println(s"Name: $name, Age: $age")
//    case _ => println("Not a Person")
//  }
//
//}
object testUnapply extends App {
  object ContainsX {
    def unapply(string: String): Boolean = {
      string.contains("X")
    }
  }

  object GiveMeHash {
    def unapply(string: String): Option[(Int)] = {
      Some(string.hashCode)
    }
  }


  val companyName = "Xabc"

  companyName match {
    case ContainsX() => println("it contains X")
    case _ => println("it does not")
  }

  //Boolean -> ContainsX()

  companyName match {
    case GiveMeHash(hash) => println("hash::", hash)
    case _ => println("it does not")
  }
  //Option[(Int)] -> GiveMeHash(Int)



  class Person(val name: String, val age: Int)

  object ~ {
    // Custom extractor method with a different name
    def unapply(person: Person): Option[(String, Int)] = {
      if (person.age < 18) {
        Some((s"Young ${person.name}", person.age))
      } else {
        Some((s"Adult ${person.name}", person.age))
      }
    }
  }
  val person: Person = new Person("John", 20)

  person match {
    case  ~(description, years) => println(s"Description: $description, Age: $years")
    case _ => println("Not a Person")
  }

  person match {
    case description ~ years  => println(s"Description: $description, Age: $years")
    case _ => println("Not a Person")
  }




}

/*


Doubts-
-where exactly is .unapply called like .apply is called when creating object, is it implicitly called( i couldn’t find it post turning implicit hints on, also i couldn’t see .apply as well post turning implicit hints on, is there something wrong?)
-line 8 response has to be option- refer- https://docs.scala-lang.org/tour/extractor-objects.html , explain codes in this

 */