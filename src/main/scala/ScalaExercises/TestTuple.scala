package ScalaExercises

import java.util.Date

object TestTuple extends App{

  val t: (Int, String, Console.type) = (1, "hello", Console) //Console is object
  println(t) //o/p- (1,hello,scala.Console$@7f63425a)

  //equivalent to t
  val t2 = new Tuple3(1, "hello", Console)

  val tuple = ("apple", "dog")
  val fruit: String = tuple._1
  val animal = tuple._2
  println(fruit)//o/p- apple
  println(animal)//o/p-dog

  val tuple5 = ("a", 1, 2.2, new Date(), "five") //new Date() is object, all values inside tuple are object

  println(tuple5._2) //o/p- 1
  println(tuple5._4) //o/p- Sun Jun 11 16:55:18 BST 2023
  println(tuple5._5) //o/p- five

  //You can assign multiple variables at once using tuples
  val student = ("Sean Rogers", 21, 3.5)
  val (name, age, gpa) = student //pattern match

  val tuple2 = ("apple", 3).swap //swap method can be used to swap the elements of a Tuple2
  println(tuple2._1) //o/p-3
  println(tuple2._2) //o/p-apple

}

//https://www.scala-exercises.org/std_lib/tuples
//tuple- Scala tuple combines a fixed number of items together so that they can be passed around as a whole. They are one-indexed(ie index starts from 1 and not 0).
// Unlike an array or list, a tuple can hold objects with different types but they are also immutable.