package RockTheJVM

object PatternMatching extends App {

  //switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }
  //Pattern match is an expression.
  println(order) //o/p- 55th

  /*
Pattern matching-  is equivalent to switch expression of other languages like java, c, cpp. You can match "anInteger" value against number of cases instead of making if else chains.
match is a keyword in scala. case _ --means everything else ie denoted by _ , it is the equivalent of default case in other languages.
Pattern match structure is an expression and that means it can be reduced to a value.
Pattern matching takes switch to a whole new level as pattern matching is able to deconstruct data structures like tuple, etc into its constituent parts.
In pattern match, execution goes line by line until a case is matched and as soon as the case is matched it comes out of the expression, no further cases are matched not even default.
 */


  //Pattern match can deconstruct case classes
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 43)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    case _ => "Something else"
  }
  println(personGreeting) // o/p- Hi, my name is Bob and I am 43 years old.

  /*
-Pattern match is used to deconstruct a value that you obtain from somewhere else into its constituent parts. Example I created a string personGreeting, instead of passing a definitive value like 1, 2
in switch case above, we are passing a whole structure here in case Person(n, a), here we are not matching bob to a single value but a structure Person(name: String, age: Int) and if bob matches this
structure then the constituent part of bob n, a are reused in the right hand side of => sign and incase i get something else we return something else. Only when the pattern match is able to match
bob against the structure we get expected output as it assign n to string bob and a to 43 automatically and inject them in s interpolated string else default case runs.
-In ObjectOriented.scala we mentioned benefits of case classes and one of the benefit was being able to deconstruct case class objects via pattern matching, so pattern matching is available mostly for case
classes, it can be available for normal classes if you do a lot of magic behind the scenes not gonna talk about that as its advanced so bear in mind for now pattern matching is only available for case
classes.
 */


  //deconstructing tuples
  val aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre" //o/p- Bon Jovi belongs to the genre Rock
    case _ => "I don't know what you are talking about"
  }
  println(bandDescription)
  /*
  Pattern matching can deconstruct some data structures as well like tuples. Scala can deconstruct tuples into its constituent parts. I can describe a case case (band, genre) that describes the structure
  of tuple. If this tuple conforms to a 2 member tuple structure then let band and genre be the members of that tuple so that i can then reuse them into this s interpolated string. Notice how i am
  reading this pattern match expression, if the tuple conforms to this (band, genre) structure then let these be the members the constituent parts of this tuple so i can then reuse them on the right
  hand side.
   */


  //decomposing lists
  val aList = List(1, 2, 3)
  val listDescription = aList match {
    case List(_, 2, _) => "List containing 2 on its 2nd position"
    case _ => "Unknown list"
  }
  println(listDescription) //o/p- List containing 2 on its 2nd position

  /*
-We can also deconstruct complex data structures like list. I can create a case that describes the structure of the list List(_, 2, _) , _ means anything, so list contains a 1st element as something
that we don't care abot , a no. 2 and something else. If i get something else that doesn't match this structure, i will return unknown list. A 4 element list will not match this pattern, neither a
2 element list. Only a list that has exactly 3 elements and 2nd element is exactly 2 and 1st n 3rd element we don't care about will only match it.
- notice how pattern matching is much more powerful than a simple switch.
- case _ presence is not mandatory but if pattern match doesn't match anything, it will throw a match error which will crash the program this is why for best practice we always create a default case here.
This case is a wildcard as it matches absolutely anything.
- Pattern matching will try all cases in sequence until it finds a match and assign that match s value to aList and return listDescription then it leaves remaining cases., so for below example
where default case is written first, o/p will be "Unknown list"
  val listDescription = aList match {
    case _ => "Unknown list"
    case List(_, 2, _) => "List containing 2 on its 2nd position"
  }
   */

  //example of pattern match
  case class Human(a: Int)

  val obj = Human(10)
  val Human(z) = obj
  println(z) //o/p- 10
}



