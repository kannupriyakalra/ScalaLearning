package RockTheJVM

/*
Topics-
1. FunctionX
2. Syntax Sugars
3. Higher Order function - map, flatmap, filter
4. map/flatmap chain, for comprehension
5. collections
*/

object FunctionalProgramming extends App {

  //FunctionX trait
  /*
Before we discuss functional programming, its worth recapping some points that I will be using when proving some function programming principles in scala.
Scala is object oriented language.
Scala has a very nice Apply special method. If we define a apply method in a class Person and if i create instance of this class bob. I can call bob.apply(43) or bob(43) ie basically invoking bob as
a function, this signals to compiler that i am calling apply method ie it is equivalent to bob.apply(43) . So, presence of an apply method allows an instance of a class to be invoked like a function.
This is important as scala runs on the JVM so Java Virtual Machine is the infrastructure on which all java programs run and their many languages that compile to JVM Byte code like scala but JVM was
fundamentally built for java which is a typical object oriented language so JVM knows what an object is but it doesn't know what a function is as a first class citizen. What do we mean by function
as a first class citizen?
In functional programming, we want to work with functions as first class elements of programming, what do we mean by that, we want to be able to work with functions like we work with any other kind of
values, so want to
1. compose functions,
2. pass functions as arguments,
3. return functions as results
The kind of stuff that we normally operate on when we work with objects or plain values, we want to do that with functions as well. So, because the JVM was not fundamentally built for functional
programming, it was built for object oriented programming, how do we implement functional programming on the JVM and the result was Scala people invented some very interesting and clever traits
called FunctionX.
   */
  //Scala is object oriented language.
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years.")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  bob(43) //Invoking bob as a function, this is equivalent to bob.apply(43)

  /*
  Scala runs on the JVM
  Goal of Functional Programming is to:
  -compose functions.
  -pass functions as arguments.
  -return functions as results.

  Conclusion: FunctionX traits = Function1, Function2, ... Function22 (22 is the maximum no. of arguments that you can pass to a function)

difference between the methods that are defined by the keyword def and the functions that are discussed in this video- Defs are methods of classes.
Function values are instances of Function1/Function2/... traits which you can pass around like regular values.

Does this mean that we can only declare at max 22 arguments functions(ie a function that has 22 arguments) in an object/class?-- In Scala 2, yes. In Scala 3, no limit.

Curried functions are also FunctionX instances.
   */

  val f: Int => Int = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  println(f(1)) //o/p- 2


  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }
  println(simpleIncrementer.apply(23)) //o/p-24
  println(simpleIncrementer(23)) //o/p-24 , simpleIncrementer(23) is equivalent to simpleIncrementer.apply(23)
  //defined a function

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION_X TYPES.

  /*
  Example of FunctionX is a function called simpleIncrementer. Function1[Int, Int] is a trait with an apply method,its a function that takes an int and returns an int. We have override apply, Here we
  have defined an instance of Function1 trait. This object simpleIncrementer can be invoked like a function because it has an apply method, so we can say that we basically defined a function because
  this object acts like a function ie takes argument and return output. So conclusion is all scala functions are instances of these FunctionX traits or types.
  So the way that we have implemented a functional language on top of the jvm which is fundamentally built for object orientation is to make functions as the instances of Function1, Function2, Function3,..
  and so on traits.
  Another example is stringConcatenator as a function that takes 2 strings and return a string so we use Function2.

 function1 a inbuilt trait (yes) whose object we have created by implementing it right away, this is similar to anonymous class right? (yes), why is he calling simpleIncrementer a function? (its object
 only but as it has apply and can be called like a function as its behaviour resembles a function, this is how functional programming is implemented in scala by using objects as function by syntactic
 sugar)So every function is a object in scala we can say that, internally function is a object only.

   */

  //function with 2 arguments and a string return type
  val stringConcatenator = new Function2[String, String, String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }
  println(stringConcatenator("I love", " Scala")) //o/p - I love Scala


  //Shorthand Notation/Syntax Sugars
  /*
Now I will show you some bits and pieces here about scala as a functional programming language. First I will show some Syntax Sugars, which means alternative syntax which will replace this much heavier
boiler plate code.
   */
  val doubler: Int => Int = (x: Int) => 2 * x
  println(doubler(4)) //o/p - 8

  /*
  (x: Int) => 2 * x

   is equivalent to

   new Function1[Int, Int] {
    override def apply(x: Int): Int = 2 * x
  }

  and Int => Int    is equivalent to       Function1[Int, Int]

  so above expression is equivalent to

 val doubler: Function1[Int, Int] = new Function1[Int, Int] {
    override def apply(x: Int): Int = 2 * x
  }

We can also emit the type of doubler Int => Int as compiler is smart enough to infer it for us so even if we cut it compiler is still happy with the code and if we hover over doubler we can see the
inferred value.
   */


  //HIGHER ORDER FUNCTIONS
  val aMappedList: List[Int] = List(1, 2, 3).map(x => x + 1)
  println(aMappedList) //o/p- List(2, 3, 4)

  /*
Higher order functions- The methods or functions that take functions as arguments or return functions as results are called Higher Order Functions. Examples are map, flatMap, filter, etc.
map- its a special method on the List type, in that it allows the passing of a function,  map expected a function from Int to some other type B  Int => B, .map(x => x + 1) ---x is an Int as its called
on a List[Int].
x => x + 1 is an anonymous function that I have created  is equivalent to new Function1[Int, Int] { override def apply(x: Int): Int = x + 1}.
As x => x + 1 anonymous function is passed as argument to the map method ie why map method on list type is called a Higher order function.
Returned value of aMappedList is another List.
x => x + 1 this function is applied to every single element of the list and we obtained another list (2,3,4) so return type is List[Int].
As mentioned before in Object Orientation video 3, application of a method here map on a list or on any object that is due to modify the original object will actually return another instance and so
aMappedList that we obtained here is a different list from List(1, 2, 3) on which we applied map method.
 */

  val aFlatMappedList = List(1, 2, 3).flatMap(x => List(x, 2 * x))
  println(aFlatMappedList) //o/p- List(1, 2, 2, 4, 3, 6)

  /*
  A flatMap function is also an higher order function, it takes as argument a function f from Int to another collection of a given type. In above example, for every element in List(1,2,3) applying flatMap
  function will return another list ie List(1,2), List(2,4), List(3,6) and flatMap s job is to concatenate all these small Lists into another big List List(1, 2, 2, 4, 3, 6). Below are 2 quirky
  alternative syntax for flatMap-

  val aFlatMappedList = List(1, 2, 3).flatMap{
  x => List(x, 2 * x)
  }

  val aFlatMappedList = List(1, 2, 3).flatMap{ x =>
   List(x, 2 * x)
  }
   */

  val aFilteredList = List(1, 2, 3, 4, 5).filter(x => x <= 3)
  println(aFilteredList) //o/p- List(1, 2, 3)
  val aFilteredList2 = List(1, 2, 3, 4, 5).filter(_ <= 3) //_ <= 3 equivalent to x => x <= 3

  /*
  Filter- Its another classical higher order function. In above example Filter takes a function from Int => boolean and returns a List[Int] that contains just those values for which the predicate returns
  true. x <= 3 is an expression that returns boolean. x => x <= 3 is an anonymous function. For every x ie returned in result x must be <= 3. Filter takes this anonymous function and returns a new list
  containing only those elements from List(1, 2, 3, 4, 5) for which x <= 3 expression returns true.

  override def filter(p: A => Boolean): List[A]
  Selects all elements of this collection which satisfy a predicate.
Params:
p – the predicate used to test elements.
Returns: a new iterator consisting of all elements of this collection that satisfy the given predicate p. The order of the elements is preserved.
   */

  /*
  In scala, we usually work with immutable objects and immutable collections in this case so every single application to any higher order function like map, flatMap, filter will always return another
  instance of a list . As every single call to map or flatMap or filter will return another instance we can chain applications to map flatmap and filter. Example- Create all pairs between the
  numbers 1,2,3 and letters 'a','b','c' ie we want all combinations 1a, 1b, 1c, 2a, 2b, 2c, 3a, 3b, 3c. There are 2 ways to do this-
  1. map and flatmap the list numbers and letters
  2. for comprehension
   */


  //all pairs between the numbers 1,2,3 and letters 'a','b','c'
  val allPairs = List(1, 2, 3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  println(allPairs) //o/p- List(1-a, 1-b, 1-c, 2-a, 2-b, 2-c, 3-a, 3-b, 3-c)

  /*
  for every single element in List(1,2,3) I will return another List ie for 1 --1a 1b 1c by running a map on (a,b,c).
  for every single element in List(1,2,3), ie for every number, i will run the List('a', 'b', 'c'), ie for every number, i am going to take the List('a', 'b', 'c') and call map to prepend this number
  to every single letter.
  List(1a, 1b, 1c), List(2a, 2b, 2c), List(3a, 3b, 3c) will be created and then flattened by flatmap by concatenating into one list.

Again, I am calling flatmap on the original list such that for every number, i am returning another small list and that small list is obtained by  List('a', 'b', 'c').map(letter => s"$number-$letter")
expression. In this expression, for every single letter containing in the List('a', 'b', 'c'), I am prepending that number to that letter.

  Notice how we are iterating through collections without using for loops, without using any kind of looping or iteration by just calling map, flatmap and maybe filter for passing some condition on
  those numbers.
*/

  /*
  In big scala code bases, chains such as these are hard to read if a logic is complex so scala syntax allows for a pretty human readable chains of map and flatmap and filter in what we call 'for
  comprehension'. Lets make an equivalent example to allPairs that we wrote.
  for is a keyword in scala and it does not mean for loop. for comprehension is an expression that we are going to attribute to alternativePairs value.
  <- is called in, left thin arrow
  On for click option + enter and desugar for comprehension will show allPairs example
  for number in List(1, 2, 3) , letter in List('a', 'b', 'c').
  this " for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter" "
  whole is a single expression that can be reduced to a value.
  Whenever the compiler sees a for comprehension, it will actually deconstruct that into a chain of flatMap and map.  allPairs and alternativePairs expressions are identical to the compiler.

  These kind of chains of map flatmap and for comprehensions are useful in collections, when you are working in a parallel or distributed environment , while working with spark dataframe, rdd s, etc, or
  whatever kind of linear, multidimensional, parallel distributed collection you might work with.
   */


  //for comprehension-- equivalent to allPairs map/flatmap chain above
  val alternativePairs = for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c') //kind of nested loop
  } yield s"$number-$letter"
  println(alternativePairs) //o/p- List(1-a, 1-b, 1-c, 2-a, 2-b, 2-c, 3-a, 3-b, 3-c)
//for every no. in List(1,2,3) then for each letter in List('a', 'b', 'c') below is run


  //Collections

  //lists
  val aList = List(1, 2, 3, 4, 5)
  val firstElement = aList.head
  val rest = aList.tail //List(2, 3, 4, 5)
  val aPrependedList = 0 :: aList //List(0, 1, 2, 3, 4, 5)
  val anExtendedList = 0 +: aList :+ 6 //List(0, 1, 2, 3, 4, 5, 6)


  /*
  List- fundamental collection of functional programming. Head is first element of list, Tail is remainder of list. Head and tail are fundamental operation on a list.
  List can be prepended or appended with an element.
  :: operator is applicable to a list, it is used for prepending.
  +: prepends an element to a list, :+ appends an element to a list.
  map, flatMap, filter are going to work for all the collections so I am not going to mention them for every collection.
   */

  //sequences
  val aSequence: Seq[Int] = Seq(1, 2, 3) // equivalent to Seq.apply(1, 2, 3)
  val accessedElement = aSequence(1) // equivalent to aSequence.apply(1), the element at index 1, o/p- 2
  /*
  Seq has a constructor lets call it Seq(1, 2, 3). Seq has a companion object which has a apply method which takes these arguments and return an instance derived from a Seq trait. So Seq is actually a trait,
  an abstract type . This apply factory method will actually return an instance of a derived type from Seq.
  Main characteristic of a Seq is you can access an element at a given index.
  A Seq is the parent trait for all ordered collections, so it's not fast or slow per se. Vector is an implementation of it, so is List.
  trait Seq has a factory method in the companion object that has apply implemented.
  Companion object Seq extends SeqFactory. In Factory.scala companion object of SeqFactory has apply overriden, so Seq companion object has implementation of Apply method.
   */


  //vectors: fast Seq implementation
  val aVector = Vector(1, 2, 3, 4, 5)
  /*
  Vector is a particular type of Seq which is very fast for large data. Vector has very fast access time and has the same methods as list or seq.
   */


  //sets- collection with no duplicate. It doesn’t maintain any order for storing elements.
  val aSet = Set(1, 2, 3, 4, 1, 2, 3)
  println(aSet) //o/p- Set(1,2,3,4)
  println(aSet.head)  //o/p- 1
  println(aSet.tail) //o/p- Set(2, 3, 4)
  println(aSet.isEmpty) //o/p- false
  val bSet = Set()
  println(bSet) //o/p- Set() , an empty set
  val setHas5 = aSet.contains(5) //o/p- false
  val anAddedSet = aSet + 5
  println(anAddedSet) //o/p- HashSet(5, 1, 2, 3, 4)
  val anRemovedSet = aSet - 3 //o/p- HashSet(1, 2, 4)
  /*
  If i define a set with duplicates like above for 1, 2, 3, in the resulting set they ll appear only once.
  Main property of set is to test whether an element is contained in a set or not which is done by contains method which returns a boolean.
  + is a method name from the Int type, you can add an element to a set using it. ELement can be added in any order, as order is not important in a set.
  - can be used for removing an element from set.
   */

  //ranges
  val aRange = 1 to 1000
  //find all the numbers b/w 2 and 2000 in 2 by 2 increments.
  val twoByTwo = aRange.map(x => 2 * x).toList //this list all elements that are even b/w 2 and 2000 , o/p - List(2,4,6,..2000)
  /*
  ranges is used for iteration, although we use map and flatMap to work on ranges.
  1 to 1000 is a fictitious collection that does not contain all the numbers between 1 and 1000 but it can be processed as if it does.
  .map(x => 2 * x) can be written as .map(2 * _)
  .toList can be called on a collection to converts that collection to a List , .toSet , .toSeq also exist to convert in between the collections.
   */


  //tuple = groups of value under the same value, tuple is defined by simple parenthesis ()
  val aTuple: (String, String, Int) = ("Bon Jovi", "Rock", 1982)


  //Map
  val aPhonebook: Map[String, Int]  = Map(
    ("Daniel", 123),
    "Jane" -> 12344555 // equivalent to ("Jane", 12344555)
  )
  val k = aPhonebook.keys
  println(k) //o/p- Set(Daniel, Jane)
  val l = aPhonebook.values
  println(l) //o/p- Iterable(123, 12344555)

  /*
apply method is called on the companion object of Map.
we can pass in any kind of 2 argument tuples.
Tuple can also be expressed with thin arrow -> ie "Jane" -> 12344555
keys method- Returns an iterable containing all keys of the Map
values method- Returns an iterable containing all values of the Map
   */

}
