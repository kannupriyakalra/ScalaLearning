package ScalaBasics

object TestRangeAndTuple extends App{


  val r: Range = 1 to 1000 //equivalent to 1.to(1000), click 'Range', 'to' method and see its implementation, integer type doesnt have to method, so how we supplied here. to add methods to already
  // constructed types like int, we use  RichInt(val self: Int) extends AnyVal with ScalaNumberProxy[Int] with RangedProxy[Int] implementation, go to intellij help- show implicit hints, it shows
  // intWrapper(1 to 1000), command click intWrapper, "implicit def intWrapper(x: Int): runtime.RichInt= new runtime.RichInt(x)" converts int to richint implicitly , richint has 'to' method ie why to method
  //could be used on 1 integer. In scala methods can be extended like this to add more methods to it. all of this could have been done explicitly too.

  for (i <- 1 to 4) println(i) // for loop on range
  //o/p- 1
  //2
  //3
  //4

  for (i <- 1 to 10; if i % 2 == 0) println(i)
  //o/p- 2
  //4
  //6
  //8
  //10
  (1 to 10).withFilter(i => i % 2 == 0).foreach(i => println(i)) // syntactic sugar of line 12
  //o/p- 2
  //4
  //6
  //8
  //10

  //withFilter- same as filter, used when we write if in for loop
  //foreach- def foreach[U](f: A => U): Unit , function f is applied to each element of collection on which foreach is called.

  val a = for {
    i <- 1 to 4
    j <- 1 to i
  } println (i, j)

  /* o/p-
  (1,1)
  (2,1)
  (2,2)
  (3,1)
  (3,2)
  (3,3)
  (4,1)
  (4,2)
  (4,3)
  (4,4)
   */

  val b = for {
    i <- 1 to 4
    j <- 1 to i
    if i + j == 5
  } println (i, j)

 // o/p- (3,2) (4,1)

  val x: Seq[(Int, Int)] = for {
    i <- 1 to 10
    j <- 1 to i
    if i + j == 5
  } yield (i, j) // yield is returning a tuple (i,j),Seq[(Int, Int)] means a tuple2
  // <- is called in

  val y: Seq[(Int, Int)] = (1 to 10) //syntactic sugar of line 54 for, gagan will explain later
    .flatMap(i => (1 to i)
        .withFilter(j => i + j == 5) //j is values b/w 1 to i as j is run on small range 1 to i
        .map(j => (i, j))
    )
// for every element in 1 to 10 ie i a flatmap is run from i to 1 to i and on that small range withfilter is run and for those withfilter hold true for them map is run. withfilter s return type is not
  // range but a sequence
  println(x) //o/p- Vector((3,2), (4,1))

  // override def flatMap[B](f: A => IterableOnce[B]): CC[B] , IterableOnce is any collection ie iterable like list,IterableOnce is a  template trait for collections which can be traversed either once
  // only or one or more times., CC[B] means return type is a collection

  println(x.map(t => t._1))
  println(x.map(_._1)) //accessing first element of sequence of tuples in x //o/p- Vector(3, 4) , _1 is first argument of Tuple2 class and this is the way to access it, click _1 to see, equivalent to line 69

  println(x.map { case (i, i1) => i + i1 }) //o/p- Vector(5, 5), implementing function with pattern matching, (3,2)- 3+2 - 5, (4,1)- 4+1- 5
  println(x.map(t => t._1 + t._2)) //o/p- Vector(5, 5), same as 72, for every t adding first element of tuple2 with 2nd element of tuple2
  /*
  x = Vector((3,2), (4,1))
  (i, i1) = (3,2), (4,1)
  map runs on each element and add them
  case is used as Tuple2 is case class, so we did pattern match
   */

  //Seq is super type of array, list, vector, option
  // Seq[(Int, Int)] means Seq of type tuple 2

  /*
  abstract sealed class Range(start: Int, end: Int, step: Int)
  Range doesnt have actual elements but a way to generate them . eg- start-1, end -10, step-1(by default, can give your own)
   */

  val k = 1 to 10 by 2
  println(k) //o/p- inexact Range 1 to 10 by 2, we are printing range object, it doesnt have elements, it used the 3 values, start - 1, end-10, step-2, to create a tostring

  /*
  command click 'by', search toString in Range.scala ie line 479
   " final override def toString: String = {
    val preposition = if (isInclusive) "to" else "until"
    val stepped = if (step == 1) "" else s" by $step"
    val prefix = if (isEmpty) "empty " else if (!isExact) "inexact " else ""
    s"${prefix}Range $start $preposition $end$stepped"
  }"
  toString is called by println, Any is a supertype of all type, println gets toString from any. println got range type ka object so range type s toString implementation is internally called,
  apply breakpoint on toString to understand how its called.
  value of isExact = false, !isExact = true ie why inexac printed. its not is exact 1,3,5,7,9,11(but our range is 10 ) so inExact = false as its going over 10, the gap is not exact of 1 to 10 by 2.
   */

  val l = 1 to 10
  println(l) //o/p- Range 1 to 10
}
