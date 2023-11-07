package ScalaBasics

import scala.Array._

object TestArray extends App {

  val arr1 = Array(45, 52, 61)

  //printing array elements
  println(arr1) //o/p- [I@b684286 as Array type has not override toString

  //1st way
  arr1.foreach(println)
  //def foreach[U](f: A => U): Unit , Apply f to each element
  //o/p- 45
  //52
  //61

  arr1.foreach(x => println(x)) //desugar of line 26, equivalent to line 13 arr1.foreach(println)

  //2nd way
  println(arr1.mkString(" ")) //o/p- 45 52 61
  //  final def mkString(sep: String): String , Displays all elements of this collection in a string using a separator string. here sep is space.

  //3rd way
  val x: Unit = for (x <- arr1) { //when yield is not mentioned at end of for loop its return type is Unit, x: Unit
    println(x)
  }
  //o/p- 45
  //52
  //61

  println("number of elements in the array: " + arr1.length) //o/p- number of elements in the array: 3
  println("another way to find number of elements in the array: " + arr1.size) //o/p- another way to find number of elements in the array: 3

  // Adding element in an array
  var arr2 = new Array[String](4)
  arr2(0) = "hi"
  arr2(1) = "are"
  arr2(2) = "you"
  arr2(3) = "there"
  for (x <- arr2) println(x)
  //  o/p-
  //  hi
  //  are
  //  you
  //  there


  //concatenate two array - In concat() method we can pass more than one array as arguments. Added import Array._ for this method
  var arr3 = concat(arr1, arr1, arr1)
  for (x <- arr3) println(x) /*o/p- 45
52
61
45
52
61
45
52
61*/

  //Scala arrays are compatible with Scala sequences - you can pass an Array[T] where a Seq[T] is required. Finally, Scala arrays also support all sequence operations like:
  val arr4 = arr1.map(_ * 3)
  println("map example: ")
  for (x <- arr4) print(x + " ")
  println()
  //o/p- map example:
  //135 156 183


  val arr5 = Array(3, 6, 9)
  val arr6 = arr5.filter(_ % 2 != 0)
  println("filter example: ")
  for (x <- arr6) print(x + " ")
  println()
  //o/p- filter example:
  //3 9


  val arr7 = arr5.reverse
  println("reverse example: " + arr7.mkString(" ")) //o/p- reverse example: 9 6 3


  // Accessing an array element
  var name = Array("gfg", "geeks", "GeeksQuize", "geeksforgeeks")
  println("Accessing an array element: "+ name(1)) //o/p- Accessing an array element: geeks

  // Updating an element in an array
  name(1) = "employee" //as name is var
  println("Updating an element in an array: "+ name(1)) //o/p-Updating an element in an array: employee

  val xx = Array("1","2")
  name = xx // as name is var so assigned a new array to it
  println("assigned xx to name: "+ name.mkString(" ")) //o/p- assigned xx to name: 1 2

//  xx = name not allowed ass xx is val
  xx(1) = "abc"// xx will always point to same location in memory but the object itself is mutable so its elements can be changed
  println("array is mutable example: " + xx.mkString(" ")) //o/p- array is mutable example: 1 abc


  // Multidimension array
  val rows = 2
  val cols = 3
  val names = Array.ofDim[String](rows, cols)

  // Allocating values
  names(0)(0) = "gfg"
  names(0)(1) = "Geeks"
  names(0)(2) = "GeeksQuize"
  names(1)(0) = "GeeksForGeeks"
  names(1)(1) = "Employee"
  names(1)(2) = "Author"
  for {
    i <- 0 until rows
    j <- 0 until cols
  }
    println(s"($i)($j) = ${names(i)(j)}")
  /*o/p-
  (0)(0) = gfg
  (0)(1) = Geeks
  (0)(2) = GeeksQuize
  (1)(0) = GeeksForGeeks
  (1)(1) = Employee
  (1)(2) = Author
   */


  //Append and Prepend elements to an Array in Scala
  // Appending 1 item
  val b = arr1 :+ 27
  println("Array b ")
  for (x <- b) print(x + " ")
  println() //added println to get a line gap for seeing next o/p
  //o/p- Array b
  //45 52 61 27

  // Appending 2 item
  val c = b ++ Array(1, 2)
  println("Array c ")
  for (x <- c) print(x + " ")
  println()
  //o/p- Array c
  //45 52 61 27 1 2

  // Prepending 1 item
  val d = 3 +: c
  println("Array d ")
  for (x <- d) print(x + " ")
  println()
  //o/p-Array d
  //3 45 52 61 27 1 2

  // Prepending 2 item
  println("Array e ")
  val e = Array(10, 25) ++: d
  for (x <- e) print(x + " ")
  println()
  //o/p-Array e
  //10 25 3 45 52 61 27 1 2


}
