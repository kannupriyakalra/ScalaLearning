package PracticedWithMentor

object TestCurrying {

  def add(a1: Int, a2: Int): Int = a1 + a2

  val x: (Int, Int) => Int = add //x is of type function (Int, Int) => Int, x is add function

  def addCurry1(a1: Int)(a2: Int): Int = a1 + a2

  val y: Int => Int => Int = addCurry1 //y has 2 int as i/p and 1 int as o/p, y is a function that takes int and returns a function int => int, in 2nd function when we again send int then we ll get
  // final o/p. y is exactly same as function x

  def addCurry2(a1: Int) = (a2: Int) => a1 + a2

  val w: Int => Int => Int = addCurry2

  def main(args: Array[String]): Unit = {
    println(x(5, 4)) //o/p- 9
    println(y(5)(4)) //o/p- 9
    println(w(5)(4)) //o/p- 9

    val z: Int => Int = y(5) //function argument sent one by one, as one argument less sent ie why type is inferred as Int => Int
    println(z) // o/p- TestCurrying$$$Lambda$20/0x0000000800bee198@75bd9247
    println(z(4)) //o/p- 9

    val yy: Int => Int => Int = (a: Int) => (b: Int) => a + b
  }

  //currying means arguments can be sent one at a time.

  /*
lambda means fxn is returned and defined using '=>' eg x, y, z, function- in scala are objects eg- x,y, z, lambda and function are same things
method- made using def keyword

val x = add, scala compiler auto converted method add to object x.
   */

  //learning to use functions as i/p and o/p

  //Q1- take Function2 as input and return its curried version as output
  def toCurried[A, B, C](f2: (A, B) => C): (A => B => C) = {
    a => b => f2(a, b) //this is a function object created , f2(a, b) gives c, a => b we wrote like this a our return type is like that.
  }

  // Function2[A, B, C] is equivalent (A, B) => C, A => B => C  is curried version of function2.

  //Q2- take curried version and return Function2.
  def unCurry2[A, B, C](fc: A => B => C): (A, B) => C = {
    (a, b) => fc(a)(b) //this is a function, this is a function blueprint, dont think where values will come from who will implement them, think like this function is i/p, this is o/p, how to convert
    // using a function object
    // fc(a)(b) makes c, (a, b) we wrote like this a our return type is like that.
  }

  // a n b are given together as tuple in Function 2, first a is given then b is given in curried version.

  //q3 - write composed function which takes 2 input A=>B and B=>C and returns A=>C
  def composedFunction[A, B, C](f1: A => B, f2: B => C): A => C = {
    a => f2(f1(a)) //as we have to return a function object ie why we wrote a to f2(f1(a))
    // f1(a) when a is passed to f1 , B is returned
    // i/p of f2 is type B ie f1(a) and it will convert it to C
    //we have to write a function object here as o/p of composedFunction method is A => C a function
  }
}
