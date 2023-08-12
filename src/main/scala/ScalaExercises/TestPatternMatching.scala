//https://www.scala-exercises.org/std_lib/pattern_matching
package ScalaExercises

object TestPatternMatching extends App {

  val stuff = "blue"

  val myStuff = stuff match {
    case "red" =>
      println("RED"); 1
    case "blue" =>
      println("BLUE"); 2
    case "green" =>
      println("GREEN"); 3
    case _ =>
      println(stuff); 0 // case _ will trigger if all other cases fail.
  }

  println(myStuff) //o/p- BLUE 2 , myStuff =2 and not "BLUE" as last statement is returned

  //Pattern matching can wildcard parts of expressions:
  def goldilocks(expr: Any) =
    expr match {
      case ("porridge", _) => "eating"
      case ("chair", "Mama") => "sitting"
      case ("bed", "Baby") => "sleeping"
      case _ => "what?"
    }

  println(goldilocks(("porridge", "Papa"))) //o/p-eating
  println(goldilocks(("chair", "Mama"))) //o/p-sitting

  // case ("porridge", _) in this _ means anything

  //Pattern matching can substitute parts of expressions:
  def goldilocks2(expr: (String, String)) = {
    expr match {
      case ("porridge", bear) =>
        bear + " said someone's been eating my porridge"
      case ("chair", bear) => bear + " said someone's been sitting in my chair"
      case ("bed", bear) => bear + " said someone's been sleeping in my bed"
      case _ => "what?"
    }
  }

  // bear is a variable ie bind with it, bear is assigned as part of pattern

  println(goldilocks2(("porridge", "Papa")))//o/p- Papa said someone's been eating my porridge
  println(goldilocks2(("chair", "Mama")))//o/p- Mama said someone's been sitting in my chair


  /*
  Scala's pattern matching statement is most useful for matching on algebraic types expressed via case classes.
  algebraic types/algebraic data types are - Sum type(values can or cannot be present ie either or)- eg- Option, Product type(all values have to be present)- eg-tuple, a case class with 3 members
   */

  //A backquote can be used to refer to a stable variable in scope to create a case statement - this prevents "variable shadowing":
  // "variable shadowing" means 2 variable with same name then second will shadow first *the one that comes later
  //this prevents "variable shadowing" means if we won't use `foodItem` backquotes it will create a new variable in case statement and bound it to it, another
  // way of doing it is using if statement in case of pattern match like if foodItem = "porridge"

  val foodItem = "porridge"

  def goldilocks(expr: (String, String)) =
    expr match {
      case (`foodItem`, _) => "eating" //it will match value of foodItem above
      case ("chair", "Mama") => "sitting"
      case ("bed", "Baby") => "sleeping"
      case _ => "what?"
    }

  println(goldilocks(("porridge", "Papa")) )//o/p- eating
  println(goldilocks(("chair", "Mama")))//o/p- sitting
  println(goldilocks(("porridge", "Cousin")))//o/p- eating
  println(goldilocks(("beer", "Cousin")))//o/p- what?

    //A backquote can be used to refer to a method parameter as a stable variable to create a case statement:

  def patternEquals(i: Int, j: Int) =
    j match {
      case `i` => true
      case _ => false
    }

  println(patternEquals(3, 3))//o/p- true
  println(patternEquals(7, 9)) //o/p-false


  //To pattern match against a List, the list can be split into parts, in this case the head x and the tail xs. Since the case doesn't terminate in Nil, xs is interpreted as the rest of the list:
  val secondElement = List(1, 2, 3) match {
    case x :: xs => xs.head
    case _ => 0
  }
  println(secondElement)//o/p-2
}





