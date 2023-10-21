package PracticedWithMentor

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!") //println is a method and not keyword, its mentioned wrong in gfg identifier article that its a keyword

    println(1.+(2)) //1 is on abject of class Int, + is a method in class int, 2 is an argument

    val x = List(1,2)
    val y = List(3,4)
    println(x ++ y) // ++ is list concatenation, its a method name which can be called without .
    println(x.++(y)) //o/p- List(1, 2, 3, 4)

    // operator is an identifier like + , println is an identifier as these are method names. scala only has method, no operator concept.

    val list: List[Any] = List(
      "a string",
      732,  // an integer
      'c',  // a character
      true, // a boolean value
      () => "an anonymous function returning a string"
    )
    //=> tells that this is a function, with input () unit and output string. this is known as lamda also.

    list.foreach(element => println(element)) // (element => println(element)) is a function object or lambda
    /*
o/p- a string
732
c
true
Main$$$Lambda$18/0x0000000800c06fa8@668bc3d5
     */

    println(x.head) // o/p- 1
    println(x.headOption) // o/p- Some(1)
    /* saw implementation of option, option is made to implement null in scala, to call safe methods that don't throw exception,
     Null is provided mostly for interoperability with other JVM languages and should almost never be used in Scala code. headOption should be used instead ie
     why it was made
     */


  }
}