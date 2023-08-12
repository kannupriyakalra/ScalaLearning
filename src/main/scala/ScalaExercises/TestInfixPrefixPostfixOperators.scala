package ScalaExercises
import scala.language.postfixOps
/*
-Scala operators, Infix, postfix, prefix operator in scala
https://www.baeldung.com/scala/operators-intro
https://www.scala-exercises.org/std_lib/infix_prefix_and_postfix_operators

-indexOf() method in scala-
https://www.includehelp.com/scala/string-indexof-method-with-example.aspx
https://www.baeldung.com/scala/find-index-element-in-list
 */
object TestInfixPrefixPostfixOperators extends App{


  //Infix operators do NOT work if an object has a method that takes two parameters unless both the parameters are mentioned in a bracket:

  val g: String = "Check out the big brains on Brad!"

  println(g indexOf 'o')//o/p- 6
  println(g indexOf 'o', 7)//doesn't work, gives first occurrence of "o" ie 6 as answer ignoring starting the search from 7. o/p is (6,7) which is wrong answer. When a method accepts multiple
  // parameters, we have to put all the parameters between a pair of parentheses to use the method in infix notation
  //indexOf(Char, Int) cannot be used as an infix operator unless a bracket is used.
  println(g indexOf ('o', 7)) //o/p-25
  println(g indexOf ('o', 4)) //o/p-6
  println(g.indexOf('o', 7)) //o/p-25 // 7 is the offset referring to the starting index of the search of 'o'

  //postfix operator- - method comes after object
  val g2: Int = 31
  println(g2 toHexString) //o/p- 1f
  //It returns the Hexa decimal form of the specified integer value.
  // toHexString takes no params therefore can be called as a postfix operator.
  //'import scala.language.postfixOps' has to be done to call method using postfix notation- g2 toHexString

 val x = "baeldung"
 val strUpperCase: String = x.toUpperCase //its postfix is  val strUpperCase: String = x toUpperCase, not running here due to compiler giving error, taking other function overriden toUpperCase
 println(strUpperCase) //o/p-"BAELDUNG"

  //Prefix operators- method comes before object. All prefix operators are converted to methods with the name unary_<operator>:
  val g3: Int = 31
  println(-g3) //o/p- -31
  println(g3.unary_-) //o/p- -31

  println((10.unary_- == -10))//o/p-true

  //Here's how to create a prefix operator for our own class. The only identifiers that can be used as prefix operators are +, -, !, and ~:
  class Stereo {
    def unary_+ = "on"
    def unary_- = "off"
  }
  val stereo = new Stereo
  println(+stereo) //o/p- on ,
  println(-stereo) //o/p- off
  println(stereo.unary_+) //o/p- on

  /*
  when method name is "unary_+" then howcome he is using "+"  , assuming they both are same, then if i write "unary_+"  instead of "+" its showing red but when i am doing object.method
  name then its fine but then how did we create a prefix operator for our own class here?
  when compiler sees "unary_+" it treats it like +
  // println(unary_+(stereo)), this doesn't work as to call unary_+ method, object.method has to be done normally
   */

  //Postfix operators have lower precedence than infix operators, precedence means where bracket will be enabled.
  //As both prefix and postfix operators take just one argument, they are also called unary operators.
  /*
  Scala also has two unary operators to indicate whether a number is positive or negative:

  + (method unary_+)
  – (method unary_-)
  + makes a numeric literal positive and – makes a literal negative:


   */
}


