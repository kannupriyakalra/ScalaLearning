//https://www.scala-exercises.org/std_lib/classes
//this file is a TestClasses.scala of type class in intellij and not object as 2 classes are outside object. when you comment the 2 classes and everything is in a object only
//then it becomes a object file.

package ScalaExercises

//The program defines an executable application TestClasses in the form of a top-level singleton object with a main method.
class Point(x: Int, y: Int) {
  override def toString(): String = "(" + x + ", " + y + ")"
}

class ClassWithValParameter(val name: String)

object TestClasses {
  def main(args: Array[String]) = { //used main method instead of extends App
    val pt = new Point(1, 2)
    println(pt)//o/p- (1, 2)

    val aClass = new ClassWithValParameter("Gandalf")
    println(aClass.name) //o/p- Gandalf
  }
}

//or we normally do like below without creating main method, comment above code to execute and see
//these are 2 main methods in this file and the one will run which you ll execute

/*
object TestClasses1 extends App{

class Point(x: Int, y: Int) {
  override def toString(): String = "(" + x + ", " + y + ")"
}

  val pt = new Point(1, 3)
  println(pt) //o/p- (1, 3)

class ClassWithValParameter(val name: String)
val aClass = new ClassWithValParameter("Gandalfo")
println(aClass.name)  //o/p- Gandalfo

}

 */


