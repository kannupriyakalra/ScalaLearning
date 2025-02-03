package ScalaBasics

/*
-explored Option, for comprehension(4 ways)
-implement our own Option, Option's methods getOrElse, map. Understand their implementation by comparing to inbuilt getOrElse, map s implementation s.
 */
object TestOption {
  //implement our own option, Option's methods getOrElse, map.
  sealed trait MyOption[+A] {
    def getOrElse[B >: A](b: B): B //if there is a value in option then get that else here is the default value i am giving ie b and ie why return type is B which is common super type of A and B and
    // get means value a in line 18 of this function definition. getOrElse is a safe method, that either gives value or return default value.

    def map[B](f: A => B): MyOption[B] //map method converts MyOption[+A] to MyOption[B] using function f is of type A to B. map can only be called on object of type MyOption as we are implementing this
    //map for Option types
  }

  case class MySome[A](var a: A) extends MyOption[A] {
    override def getOrElse[B >: A](b: B): B = a //b is unused input of method, this is not b=a, a is the value ie returned.
    //on line 46, b gets 5 ie the default value for else case, but a had 10 already ie for get case so 10 is returned, return expectation was of B but we returned of type A , this was possible as we
    // mentioned constrained between A and B. B is supertype of A so we could return value of type A, getOrElse can either return a or b so return type has to be a common supertype of both ie B.

    override def map[B](f: A => B): MyOption[B] = MySome[B](f(a)) // we used f function which is argument of map to convert a:A to B by calling it on a, as the required return type for map is MyOption[B],
    // we create the object of MySome[A] by using the constructor of MySome class, to convert f(a): B to Option[B]. MyOption is a trait so to return it as a type parameter we used its implementation MySome.
    // MyOption is a non leaf node type, so if we need a object of its type we have to either give object of MySome or MyNone as object can be created for only terminal types ie concrete class, trait
    // is not a terminal/leaf node type.
  }
  // MySome constructor is of type A => MySome[A] | MyOption[A] | Any . constructor is also a function. i/p- A, o/p- MySome[A]

  //case keyword means can use this type in pattern matching, it has apply method
  case object MyNone extends MyOption[Nothing] { //singleton object, MyNone is the only object of class MyNone and it is assigned to y which is a object of trait type in line 49.
    // As MyNone extends MyOption[Nothing] so its of type MyOption[Nothing] and since MyOption is covariant MyOption[Nothing] is a subtype of MyOption[Int](because nothing is a subtype of int)
    //object is analogous to static class of java, there is no static keyword in scala. MyNone is of type MyNone and all upstream hierarchy.
    //MyOption[Nothing] is a subtype of all MyOption[*] types as Nothing is a subtype of all so we can assign MyNone infront of all types so we made it MyOption[Nothing].
    override def getOrElse[B >: Nothing](b: B): B = b

    override def map[B](f: Nothing => B): MyOption[B] = MyNone //  MyNone was empty already so we cannot convert it to any type.
    //Nothing <: B , MyOption[Nothing] <: MyOption[B] ie why we could return MyNone.
  } // as it has no arguments ie why case object created and not case class as case object is static class and object doesn't have arguments.
  //as in our option None is MyNone so we wrote MyNone in line 34.

  def main(args: Array[String]): Unit = {

    val x: MyOption[Int] = MySome(10) //when creating a object of case class new keyword is not used
    x match {
      case MySome(a) => println(a) //o/p- 10
      case MyNone => println("empty")
    }
    println(x.getOrElse(5)) //o/p- 10 , 5 is to be returned when no value exist for get case, 5 is the else case, execution goes to line 16 here and not 15 ie method is called and
    // not class when method is called

    val y: MyOption[Int] = MyNone // MyOption[Nothing] is subtype of (<:) MyOption[Int] , because Nothing is subtype of Int and type A is covariant and
    // ie why we could assign MyNone to MyOption[Int]
    y match {
      case MySome(a) => println(a)
      case MyNone => println("empty") //o/p- empty
    }
    println(y.getOrElse(5)) //o/p-5

    //    val zz = MySome(10) //zz is object of MySome class
    //    zz.a = 11 // content inside zz ie a can be changed as we made MySome mutable above in line 15 by making a as var, despite zz being immutable in line 57 with a val.
    //    // bydefault a is val in case class, case class is usually immutable.
    //    println(zz) // o/p- MySome(11) and not 11 as its a case class which has tostring defined inside so it prints whole object.
    /*
    when in line 15 we make "a" as var ie "case class MySome[A](var a: A) extends MyOption[A] { " , uncomment above code and run, then we can change the value of a inside MySome by zz.a = 11
     */

    //val zz = z is not allowed as zz is a val

    val z: Option[Int] = Some(10) // z has Some(10) in it as its type is Option[Int] and not 10 which is when type is int.
    val z1: Option[Int] = z.map(t => t + 1) // Why t is 10 and not Some(10)?---t is type A, t+1 is type B, map is A to B, here z=Some(10) so type A is
    // int ie 10 so t is int and t+1 is int and not Option[Int] or Some(10)
    println(z1) //o/p- Some(11), if val z: Option[Int] = None, o/p- None as per implementation on line 34 of map
    println(z.map(n => "value = " + n.toString)) //o/p- Some(value = 10)
    println {
      z.map(t => t + 1)
        .map(t => t * 2)
        .map(t => t.toString) //o/p- Some(22)
    } //map is used for doing int computations for Option, List datatypes

    val f: Int => Double = t => t / 0.3
    val result: Option[Double] = z.map(f)
    println(z.map(f)) //o/p-Some(33.333333333333336)
    println(z map f) //o/p-Some(33.333333333333336) //shortform of line 82

    val g: Int => Double = _ / 0.3 //shortform of line 80
    println(z.map(g)) //o/p-Some(33.333333333333336)
    println(z map g) //o/p-Some(33.333333333333336)

    println(z.map(ff)) //o/p-Some(33.333333333333336)
    println(z map ff) //o/p-Some(33.333333333333336)

    val z2: Option[Int] = z.flatMap(t => Some(t + 1))
    println("example of flatMap " + z2) //o/p- example of flatMap Some(11)

    val z3 = z.getOrElse(-1)
    println("example of getOrElse- get case " + z3) //o/p- example of getOrElse- get case 10 , z3: Int and not Option[Int]

    //get is a unsafe method as it gives exception in case of none and breaks code, so we made a safe method getOrElse where else gives default value.

    val ze = None
    val z4 = ze.getOrElse(-1)
    println("example of getOrElse- else case " + z4) //o/p- example of getOrElse- else case -1

    //using pattern match to add 2 options
    val answer = z match {
      case Some(v1) =>
        z1 match {
          case Some(v2) => Some(v1 + v2)
          case None => None
        }
      case None => None
    }

    val v: Option[Int] = for { //adding 2 options
      v1 <- z //v1 in z ie 10, extracts the value to add
      v2 <- z1 //v2 is 11
    } yield v1 + v2 // yield means return -10+11-21, yield means part return after it ie v1+v2 will go in innermost map on desugar, in case of integer we know how to add, in case of values of int in
    // option container we dont know how to add ie why for concept is made in for loop, we tell the container to take this function and apply ,this value.

    println("for comprehension v: " + v) //o/p- for comprehension v: Some(21)
    //for loop is a sequence of map n flatmap
    val u: Option[Int] = z.flatMap(v1 => z1.map(v2 => v1 + v2)) //adding 2 options, syntactic sugar of for loop in line 101, z= Some(10), v1 = 10, v2 = 11 ( A in function definition of flatMap and
    // Map is value inside z )
    // execution inside compiler starts from z.flatMap ie Left to right execution
    // z.flatMap(Int => Option[Int]), A is int coming from Some[10], B is int, def flatMap[B](f: A => Option[B]),z1 is Option[Int], z1.map(v2 => v1 + v2) is returning Option[Int], Option[Int] to Option[Int] ie A to B map definition

    println("for comprehension desugar u: " + u) //o/p- for comprehension desugar u: Some(21)

    val s = optionCombine1(z, z1, (a: Int, b: Int) => a + b) //equivalent to line 101, 109, 118
    println("optionCombine1 " + s) //o/p-optionCombine1 Some(21)
    val t = optionCombine2(z, z1, (a: Int, b: Int) => a + b)
    println("optionCombine2 " + t) //o/p-Some(21) optionCombine2 Some(21)

    val name = Map("Nidhi" -> "author",
      "Geeta" -> "coder")
    // Accessing keys of the Map
    val xe = name.get("Nidhi") //Option is used as a type in Map collection to model nullability and to make get method safe.
    val ye = name.get("Rahul")
    // Displays Some if the key is found else None
    println(xe) //o/p- Some(author)
    println(ye) //o/p- None

    val xee = xe.isEmpty
    println(xee) //o/p- false as Option has some and is not empty
    val yee = ye.isEmpty //o/p-true
    println(yee) //o/p-true


  }

  def ff(i: Int): Double = i / 0.3

  def optionCombine1[A, B, C](aopt: Option[A], bopt: Option[B], f: (A, B) => C): Option[C] = for {
    v1 <- aopt
    v2 <- bopt
  } yield f(v1, v2)

  def optionCombine2[X, Y, Z](aopt: Option[X], bopt: Option[Y], f: (X, Y) => Z): Option[Z] = aopt.flatMap((v1: X) => bopt.map((v2: Y) => f(v1, v2): Z): Option[Z])
  //flatmap ki defination me A is option k andar ka type so flatmap takes option k andar ka type and convert it into another option[B], aopt.flatMap(x => Option[Z])

}

/*
MyOption[A+] trait-
B is a supertype of type A, both B and A can be Int. Type A is covariant and ie why we have to do it this way.
purpose of MyOption[A] is to tell that the value of type A may or may not be there (ie defining optionality some or none ) so by making MyOption we want to handle both the cases safely, whosoever
extend it has to define getorelse.
Option- is made so that we can model behavior of null, we want to create a type that can represent null type.  option is a collection in scala, either n future are types not collection
 */

// val x means x points to a location in memory and will always point to it as its a val, val shows reference is immutable, x cannot point to another location,
//val x= Some(10), refernce(location pointing in memory , x can only point to this) is immutable but value inside some cannot change as some is a immutable class but if other
// than some a mutable class which can change on the inside is used then x will point to it only but interally it can change. good practice is we make inside content also immutable as val
// val x = 1 means x is a reference constant

// <- means in
// => call to

//Option[A] means value A may or may not exist, getOrElse means get that default value if it exists. getOrElse is called on object of type Option. trait MyOption is a type and the object of this type
// can call the methods that exist in this type.

/*
object- is a static class of java, its a singleton class ( a class with single object), unit is a singleton type
case class- class is normal class of java, case class is a better class that has default functionalities defined like toString, equals, it can be used in pattern matching. case keyword has same
meaning when its used infront of class or object.
abstract - means signature only, no implementation. only abstract class allow undefined members ie unimplemented class
override- means the function with override keyword s definition will be used irrespective of the definition in upward hierarchy.
final- method cannot be overidden
Map- is dictionary , they are same thing



Functor-
map function helps convert universe of type A to a universe of type where all types are wrapped in Option so all types like List, Future, Either, Option, etc which define map are called Functors.
Functor means mapping b/w categories.
MyOption is a Functor because it defines map method (which is internally converting A and B and ie the meaning of map too and internally if type A doesn't exist(ie None case in option) then Nothing
happens.

Category-
Types, Functions are a example of categories. Category is something that has objects and mapping (denoted by arrow) between objects. Scala type system is a category.

Covariant and Contravariant-
In MyOption[+A] , + means covariant, covariant means type hierarchy is preserved ( that can be super type or sub type both). type hierarchy of A will hold for MyOption[A] too. ie A is a
subtype of B then MyOption[A] is a subtype (denoted by <:) of MyOption[B].
contravariant is opposite of covariant. ie MyOption[A] is a supertype of MyOption[B]. covariant and contravariant concept
exist in scala only and they make the language structured and predictable.- sign is written for contravariant and + for covariant .

//Option and either are inbuilt classes and we created them here to understand scala concepts.

map n flatmap implementations of option understood:
doubt: 1.	TestOptionRevision.scala  Line 109, why is output some(21), isn’t o/p of map some(21) so flatmap o/p?

final def map[B](f: A => B): Option[B]
Returns a Some containing the result of applying f to this Option's value if this Option is nonempty. Otherwise return None.
This is equivalent to:
 option match {
   case Some(x) => Some(f(x))
   case None    => None
 }
 --map modify the value inside option by applying function f and wrap it in some and finally returns an option

   @inline final def map[B](f: A => B): Option[B] =
    if (isEmpty) None else Some(f(this.get))
    --this is object of type calling map eg in z.map(t => t + 1) , 'this' is z and its , as map is in Option class this is of type Option only
    --this is z ie Some(10)
    --this.get = 10, f(10)= (t => t + 1) = 11 , f(this.get) =11, Some(f(this.get)) = Some(11)


    final case class Some[+A](value: A) extends Option[A] {
  def get: A = value



    @inline final def flatMap[B](f: A => Option[B]): Option[B] =
    if (isEmpty) None else f(this.get)
    val u: Option[Int] = z.flatMap(v1 => z1.map(v2 => v1 + v2))
    -- z1.map(v2 => v1 + v2) gave Some(21)
    --f(this.get) gave Some(21)
    --flatMap modify the value inside option by applying function f and finally returns an option

    final def flatMap[B](f: A => Option[B]): Option[B]
  --  i/p of flatMap is a function f A => Option[B]
  final def map[B](f: A => B): Option[B]
  --i/p of map is (f: A => B)


final def getOrElse[B >: A](default: => B): B
Returns the option's value if the option is nonempty, otherwise return the result of evaluating default.
This is equivalent to:
 option match {
   case Some(x) => x
   case None    => default
 }

 line 29 option.scala
   def apply[A](x: A): Option[A] = if (x == null) None else Some(x)

doubt:
1.	TestOptionRevision.scala  Line 101, why is output some(21)—as its Option s for

to change values inside a option (to do operations like addition etc) we give it a function like map, flatmap that changes the value inside option but option wrapper remain, to break option wrapper
 we use pattern matching as in that we get value as o/p for the pattern matched and not Option(value).

doubt:
 override def map[B](f: A => B): MyOption[B] = MySome[B](f(a)) , why MySome[B](f(a))? in map definiton on hovering its Some(f(x)) here f(x) is f(a).

 */