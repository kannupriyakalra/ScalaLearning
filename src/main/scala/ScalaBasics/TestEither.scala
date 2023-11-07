package ScalaBasics

object TestEither {

  //implementation of our either using this trait n the 2 case classes
  sealed trait Eeither[+A, +B] {
    def isRight: Boolean

    def isLeft: Boolean

    def fold[C](f: A => C, g: B => C): C //functions are also types in scala, we can create object of this type

    // utility methods where we are converting either to option, this is not related to either implementation we just made them
    def toOption1: Option[B] = {
      val x: Eeither[A, B] = this // x is Eeither[A, B] as we are under Eeither[A, B] type
      x match {
        case Rright(b) => Some(b) //Some(b) as its return type is Option[B]
        case Lleft(_) => None // Option[Nothing] is supertype of None, Option[B] is supertype of Option[Nothing] as Nothing is bottom type of all types by defination,
        // _ means i/p parameter value is ignored, ie parameter name is ignored as its not used, its 2nd meaning of _
      }
    }

    // Eeither[A, B] => Option[B] , all these option methods are converting Either[A,B] to Option[B] and B is on right in Either ie why Some is mentioned in right
    def toOption2: Option[B] = {
      val x: Eeither[A, B] = this
      x.fold(m => None, n => Some(n)) //given fold will be implemented later by the class extending this trait, this Option2 method will be implemented completely, for now its partial implementation
    }

    def toOption3: Option[B] = fold(_ => None, Some(_)) //fold is directly available

    def toOption4: Option[B] = fold(a => None, b => Some(b))

    def toOption5: Option[B] = this match {
      case Rright(b) => Some(b)
      case Lleft(_) => None
    }

  }

  case class Rright[B](b: B) extends Eeither[Nothing, B] {
    override def isRight: Boolean = true

    override def isLeft: Boolean = false

    override def fold[C](f: Nothing => C, g: B => C): C = g(b) //can't write f(Nothing) here to get o/p as C because type Nothing has no object so we have nothing to send inside fxn as
    // i/p so we cannot call this fxn.
  }

  case class Lleft[A](a: A) extends Eeither[A, Nothing] {
    override def isRight: Boolean = false

    override def isLeft: Boolean = true

    override def fold[C](f: A => C, g: Nothing => C): C = f(a)
  }

  def main(args: Array[String]): Unit = {

    val x: Rright[Int] = Rright(10) //when making object of case class, we don't have to write new
    val y: Eeither[Nothing, Int] = Rright(10)
    val z: Eeither[String, Int] = Rright(10) // String >: Nothing, Eeither[String, Int] >: Eeither[Nothing, Int], type A is covariant, >: means supertype, we could write this type only because A, B
    // are covariant, how can supertype replace -- as Nothing is bottom type of all types by defination,due to inheritance supertype can replace type
    val a: Any = Rright(10)
    val zz: Eeither[String, Int] = Lleft("abcd") // Eeither[String, Nothing] or Eeither[String, Int] anything can be written due to covariance

    println(z.isRight) //o/p- true
    println(z.fold(identity, x => x.toString)) //o/p- "10", as z has Rright(10), fold implementation of Rright case class is called
    println(zz.fold(x => x, x => x.toString)) //o/p- "abcd", as zz has Lleft("abcd"), fold implementation of Lleft case class is called

    val zzz: Eeither[Double, Int] = if (x.isRight) Rright(15) else Lleft(15.5) // val zzz = Rright(15)

    println(zzz) //Rright(15)
    println(z.toOption1) //Some(10)
    println(z.toOption2) //Some(10)

    //in built Either usage, read either definiton by hovering on either

    def myPrint[A, B](e: Either[A, B]): Unit = println(e)

    val r = Right(10)
    val l = Left("abc")

    myPrint(r) //o/p- Right(10)
    myPrint(l) //o/p- Left(abc)

    def safeDivide(n: Int, d: Int): Either[Exception, Double] = if (d != 0) Right((n * 1.0) / d) else Left(new RuntimeException("don't divide by zero"))

    println(safeDivide(1, 0)) // Left(java.lang.RuntimeException: don't divide by zero)
    println(safeDivide(2, 3)) // Right(0.6666666666666666)
    //[Exception, Double] - Exception is type of Left, Double is type of Right
    //did multiplication by 1.0 in (n * 1.0) to get double


    import scala.io.StdIn._ //import can be written in b/w and will be in action from 92 onwards
    val in = readLine("Type Either a string or an Int: ") //reads the i/p from console, readline takes only string as i/p
    val result: Either[String, Int] =
      try Right(in.toInt)
      catch {
        case e: NumberFormatException => Left(in)
      }

    println {   //value returned by {} block of code will go in println
      result match {
        case Right(x) => s"You passed me the Int: $x, which I will increment. $x + 1 = ${x+1}" // another \ is applied to escape(\) the escape character, \ means escape character
        case Left(x) => s"You passed me the String: $x"
      }
    }

    //Either is right-biased, which means that Right is assumed to be the default case to operate on. If it is Left, operations like map and flatMap return the Left value unchanged:
    println(result.map(_ + 1))
    /*
    Type Either a string or an Int: kannu
You passed me the String: kannu
it goes to try first then code breaks when kannu.toInt is run and then catch is run

no call is made in pattern matching, only pattern is compared
try Right(in.toInt) is equivalent to Right(in.toInt).apply--here object of Right is created based on i/p from readline and it goes in either, new is not written as case class Right

Type Either a string or an Int: 5
You passed me the Int: 5, which I will increment. 5 + 1 = 6
Right(6)

Type Either a string or an Int: gagan
You passed me the String: gagan
Left(gagan)  // in case of case left, map is not run as either is right biased and map only run in right case as we saw in the internal implementation of map by doing command click

either s map implementation
  def map[B1](f: B => B1): Either[A, B1] = this match {
    case Right(b) => Right(f(b)) // this is object of Right
    case _        => this.asInstanceOf[Either[A, B1]] // _ is all the remaining cases
  }

  as we don't need Left ie why Left is not written and order of cases is important as they run one by one so Right has to be written first and after that _ case executes
  asInstanceOf means type casting from this type ie Either[A, B] to  Either[A, B1], for remaining cases same this is retunred. ususally pattern matching is used for type casting and not asInstanceOf

     */

    def doubled(i: Int) = i * 2
    println(Right(42).map(doubled)) // Right(84), here we directly made object of Right and called map on it as map is part of Either and Right case class extends Either
    println(Left(42).map(doubled)) // Left(42), command click map, _ case is called in this and ie why 42 is returned map didnt run in this

    // eta expansion (method and function objects/lambda can be used interchangeably eg in line 137 we passed a method, map ka i/p is function object so scala converted lambda to method internally on its own, auto conversion)
   // Since Either defines the methods map and flatMap, it can also be used in for comprehensions:
    val right1 = Right(1)   : Right[Double, Int] // right1 original type is  Right[Nothing, Int] and as Right[Double, Int] :> Right[Nothing, Int]  we can write double
    val right2 = Right(2)
    val right3 = Right(3)
    val left23 = Left(23.0) : Left[Double, Int] // left23 original type is Left[Double, Nothing]
    val left42 = Left(42.0)

    println{
    for {
      x <- right1
      y <- right2
      z <- right3
    } yield x + y + z } // Right(6)

    right1.flatMap(x => //lambda calculus- law 1 substitution (put value of x everywhere)
        right2.flatMap(y =>
            right3.map(z => x + y + z)
          )
      )
    /*as right1 has Right(1) internally ie why flatmap will be called, z => x + y + z ---6
    right3.map(z => x + y + z)  returns Right(6), in map implementation Right(f(b)) is returned in flatmap f(b) is returned

            right2.flatMap(y =>
            right3.map(z => x + y + z)
          ) ---its o/p is Right(6)

          right1.flatMap(x =>
        right2.flatMap(y =>
            right3.map(z => x + y + z)
          )---its o/p is Right(6) as flatmap returns f(b) directly ie o/p of internal function
      )
     */

    for {
      x <- right1
      y <- right2
      z <- left23
    } yield x + y + z // Left(23.0)
/*
at line 177 left doesnt have z as for loop is right biased(flatmap doesnt have left), so code breaks there after this yield line is not executed and final return is that left
option, either are monad and every for loop behave differently, list for loop is nested, either for loop breaks when left comes as map n flat map are right biased, option for loop when none comes
then for loop breaks, implementation of map, flat map is different for each collection
 */
    for {
      x <- right1
      y <- left23
      z <- right2
    } yield x + y + z // Left(23.0)

    // Guard expressions(if else) are not supported:
    /* code commented as it gives below error, which you can uncomment n see
    for {
      i <- right1
      if i > 0
    } yield i
    // error: value withFilter is not a member of Right[Double,Int],

    Either doesnt have withFilter method ie why we cannot use if in the Either's for loop. Option and List have withFilter , if you desugar their for that has if you ll see.
     */
  }


def identity[A](a: A): A = a //function that returns its i/p
}

//pattern matching, case in line 28 is same as case in line 13, switch case of java, as we already defined 2 case classes above ie why we could do pattern matching.
//    z match {
//      case Rright(b) => ???
//      case Lleft(a) => ???
//    }

//covariant concept is introduced to reflect normal type hierarchies into container types like option, either

/*
22-25, fold has no implementation then howcome some, none is written – z.toOption2 will call Option2 of either trait then that will call fold in trait but as that has no implementation,
it will be run time polymorphism and it will see it has right(10 so will call Right s fold implementation so ultimately there is a implementation of fold that will be called.
 */

/*
read Either hover scala documentation, map, flatmap command click codes
 */


/*

A-- type

Effect types- below are effect types, effect is added on A,
Option[A] is effect type, modals presence or absence of value of type A
List[A], models 0 or more occurrences of type A
Either[A,B] models presence of value: A or value: B
Future[A] models time, value A will be available in some time

if type A can be null then we add option
 */

