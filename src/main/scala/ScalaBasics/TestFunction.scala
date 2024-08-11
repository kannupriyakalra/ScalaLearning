package ScalaBasics

object TestFunction extends App{

  //in scala, you can treat methods as functions. line 7 is a method, line 13 is a function. In scala functions are values.

  def makeHelloWorld1: String = "Hello World"

  assert(makeHelloWorld1 == "Hello World")

  def makeHelloWorld2(): String = "Hello World" // type of makeHelloWorld2 is () => String, input type of makeHelloWorld2 method is Unit and output type is String.

  val f: () => String = makeHelloWorld1 _ //_ means this is becoming a function, this is an example of conversion of method to a function.

  val f2: () => String = makeHelloWorld2 // f and f2 are equivalent, as makeHelloWorld1 doesn't have () to compensate it _ is mentioned.

  assert(makeHelloWorld2() == "Hello World")

  println("Congratulations ! You are a great human being !")

}

/*
Scala Methods- A method in programming language is a bit of code that get executed when called and return a value. In Scala, the boundary between Values and Methods is very thin.
If the method do not take arguments, you do not need the (...), the parentheses are optional.
In practice, you have to be careful when calling the method. Be sure to use parentheses when it was defined with them. And don't use the parentheses when the method was defined without them.
 */
