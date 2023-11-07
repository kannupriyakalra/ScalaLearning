package ScalaBasics

//https://www.oreilly.com/library/view/scala-cookbook/9781449340292/ch05s08.html#:~:text=Use%20_*%20to%20adapt%20a%20sequence&text=This%20operator%20tells%20the%20compiler,fruits%20as%20a%20single%20argument
object TestVarargsSplatOperator extends App{

  /*
  To make a method more flexible, you want to define a method parameter that can take a variable number of arguments, i.e., a varargs field.
  Define a varargs field in your method declaration by adding a * character after the field type:
  Given that method declaration, the printAll method can be called with zero or more parameters:
   */
  def printAll(strings: String*) {
    strings.foreach(println)
  }

  printAll()
  printAll("foo")
  printAll("hello", "bar")
  printAll("hi", "mango", "baz")

  // _* is “splat” operator. This operator tells the compiler to pass each element of the sequence to printAll as a separate argument, instead of passing fruits as a single argument.

  val fruits = List("apple", "banana", "cherry")
  printAll(fruits: _*)
}
