import org.scalatest.Assertions.intercept
//uses of pattern matching
//implemented prepend, map, filter, concat, zip, foldLeft, foldRight, reverse
// scala exercises done here- https://www.scala-exercises.org/std_lib/lists

object TestList {

  //implement prepend method
  def prepend[A](a: A, as: List[A]): List[A] = a :: as //as.::(a)

  //implement map method that can convert a list, locally defined so not conflicted with original scala map
  def map[A, B](l: List[A])(f: A => B): List[B] = l.map(f)

  //implement map method on a list using recursive approach-
  def myMap[A, B](l: List[A])(f: A => B): List[B] = { //list has 2 implementations :: and nil ie why pattern match is showing that. list is a trait and ie it has multiple implementation based on fact
    // its empty or not ie why the 2 cases, also both implementations have case keyword ie why pattern match is possible.
    l match {
      case (head: A) :: (tail: List[A]) => f(head) :: myMap(tail)(f)
      case Nil => Nil //when list is empty, return empty list, Nil denotes empty list.
    }
  }

  def myFilter[A](l: List[A])(f: A => Boolean): List[A] = {
    l match {
      case ::(head: A, next: List[A]) => if (f(head)) head :: myFilter(next)(f) else myFilter(next)(f)
      case Nil => Nil //empty list doesn't filter, as there is no A we have nothing to implement f on.
    }
  }

  // implement a method concat that concatenates 2 list
  // time complexity- no. of times recursion happened- x is traversed until the length of x times then as x gets nil y is added, so O(n) where n is length of x.
  def myConcat[A](x: List[A], y: List[A]): List[A] = {
    x match {
      case head :: tail => head :: myConcat(tail, y)
      case Nil => y
    }
  }

  def myZip[A, B](x: List[A], y: List[B]): List[(A, B)] = { // return type of list is List[Tuple2[A,B]], "List[(A, B)]" is its syntactic sugar
    (x, y) match {
      case (head1 :: tail1, head2 :: tail2) => (head1, head2) :: myZip(tail1, tail2)
      case (head1 :: tail1, Nil) => Nil
      case (Nil, head2 :: tail2) => Nil
      case (Nil, Nil) => Nil
    }
  }

  //veryimportant
  // 1,2,3,4,5 are elements of list and zero is default then order of execution is ((((((0) + 1) + 2) + 3) + 4) + 5) + ...
  def myFoldLeft[A, B](ls: List[A])(z: B)(op: (B, A) => B): B = {
    ls match {
      case ::(head, next) => myFoldLeft(next)(op(z, head))(op) //we are folding the remaining list with the new starting value recursively.
      case Nil => z
    }
  }

  /*(1 + (2 + (3 + (4 + (5) + (0))))), default value 0 is engaged with last element of list, then its o/p is engaged with element from right to left, same as foldLeft just list is folded from
  // right to left instead of left to right
  //list can be traversed left to right only, we are sending z to 5 recursively. how to join 2 ie current head and myFoldRight(tail)(z)(op) as we are trusting recursion that it will bring result from
  // 3 to 5 , head :A, tail : List[A],  myFoldRight(tail)(z)(op) : B, you have a value of type A and type B and we have to return type B, how to combine A n B so answer is type B, using op.
  // compare these brackets with op(head, myFoldRight(tail)(z)(op))
  op(head, myFoldRight(tail)(z)(op))
  op(1 ,   (2 + (3 + (4 + (5) + (0)))))) // (2 + (3 + (4 + (5) + (0))))) is myFoldRight part, 1 is head
  op(1 + (2 ,    (3 + (4 + (5) + (0)))))
  op(1 + (2 + (3 ,    (4 + (5) + (0)))))
  every pair of round bracket represent a recursive function call
  op(head, myFoldRight(tail)(z)(op))-- op(A, B)//sending z n op recursively until rest of list becomes nil
  foldRight- folds from right, recursively compute answer of rest of list and then merge it with head
   */
  def myFoldRight[A, B](ls: List[A])(z: B)(op: (A, B) => B): B = {
    ls match {
      case ::(head, tail) => op(head, myFoldRight(tail)(z)(op)) //we are folding the remaining list with the new starting value recursively.
      case Nil => z
    }
  }
  //another solution to implement foldRight is reverse list, then apply foldLeft

  //veryimportant
  def myReverse[A](ls: List[A]): List[A] = {
    ls.foldLeft(Nil: List[A])((acc, a) => a :: acc)
  }

  def main(args: Array[String]): Unit = {

    val l1: List[Int] = List(1, 2, 3)
    println(l1) //o/p- List(1, 2, 3)

    for (x <- l1) { //display element in list l1 using for loop
      println(x)
    } /*  o/p-
    1
    2
    3   */

    println("Lists can be accessed by position: " + l1(0)) //o/p- Lists can be accessed by position: 1, equivalent to l1.apply(0)
    println("To find index of a element in a list: " + l1.indexOf(1)) //o/p- To find index of a element in a list: 0

// commented below code as it throws exception, intercept throws TestFailedException as the passed function runs normally and doesn't throw IndexOutOfBoundsException which was expected of it. Hover and read about intercept.
//    intercept[IndexOutOfBoundsException] { //o/p-Lists can be accessed by position- IndexOutOfBoundsException: 2
//      println("Lists can be accessed by position- IndexOutOfBoundsException: " + l1(1)) // Exception in thread "main" org.scalatest.exceptions.TestFailedException: Expected exception java.lang.IndexOutOfBoundsException to be thrown, but no exception was thrown
//    }
        intercept[IndexOutOfBoundsException] { //o/p-nothing as IndexOutOfBoundsException was thrown which was expected.
          println("Lists can be accessed by position- IndexOutOfBoundsException: " + l1(3))
        }
    //def intercept[T <: AnyRef](f: => Any)(implicit classTag: ClassTag[T], pos: Position): T, here T is type of exception thrown, (f: => Any) is the block of code

    println(l1.head) //o/p- 1
    println("headOption value: " + l1.headOption) //o/p- headOption value: Some(1)
    println(l1.tail) //o/p- List(2,3)
    println(l1.tail.head) //o/p-2

    println("List is empty or not: " + l1.isEmpty) //o/p- List is empty or not: false
    println("Length of list is: " + l1.length) //o/p-Length of list is: 3

    //Uniform List can be created in Scala using List.fill() method which creates a list and fills it with zero or more copies of an element.
    // Repeats Scala three times.
    val programminglanguage = List.fill(3)("Scala")
    println("Programming Language : " + programminglanguage) //o/p- Programming Language : List(Scala, Scala, Scala)
    // Repeats no. 4, 8 times.
    val number = List.fill(8)(4)
    println("number : " + number) //o/p-  number : List(4, 4, 4, 4, 4, 4, 4, 4)

    println(l1.map(x => x + 1)) //o/p- List(2, 3, 4)
    println(l1.map(x => List(x, x))) //o/p- List(List(1, 1), List(2, 2), List(3, 3)), List[List[Int]]

    println(l1.flatMap(x => List(x, x))) //o/p- List(1, 1, 2, 2, 3, 3)

    println(l1.filter(x => x >= 2)) //o/p- List(2,3)
    println(l1.filter(_ >= 2)) //o/p- List(2,3), same as line 123, equivalent to println(l1.filter(x => x >= 2))
    println("filter value divisible by 2: "+ l1.filter(x => x % 2 == 0)) //o/p- filter value divisible by 2: List(2)

    println("filterNot value: " + l1.filterNot(x => x == 2)) //o/p- filterNot value: List(1, 3) ,filterNot remove where value is 2, Selects all elements of this collection which do not satisfy (x => x == 2)

    val l2: List[Int] = 1 :: 2 :: 3 :: Nil
    val l22: List[Int] = Nil.::(3).::(2).::(1)
    println(l2) //o/p- List(1, 2, 3)
    println(l22) // o/p- List(1, 2, 3) ,l22 is equivalent to l2, l1

    //Lists reuse their tails- a is pointing to 1, b is pointing to 2, c is pointing to 3, they all have common part of tail d
    val d = Nil
    val c = 3 :: d
    val b = 2 :: c
    val a = 1 :: b

    println("Lists reuse their tails:" + a) //o/p- List(1, 2, 3)
    println(a.tail)//o/p- List(2, 3)
    println(b.tail)//o/p- List(3)
    println(c.tail) //o/p- List()

    //List() and Nil are same.

    val l3: List[Int] = Nil //Nil is List[Nothing] type, List[Nothing] <: List[Int] and ie why we could assign l3 as List[Int] because Nothing <: Int and List is covariant ie List[+A], List has 2
    // implementations- one is case class :: ie recursive implementation that has head, tail n other is case object Nil.
    // Every list's last element is nil. To end the list, apply method adds nil by default. Nil is analogous to null of java.
    // List is linked list, it has head of type A, tail of type List[A], we need some default value of tail so we can end the list, otherwise list will be infinite, as for every list we have to give
    // a head and tail and tail is a list, so for last element tail is Nil otherwise list won't stop ie it will be infinite. This is why list is made a trait and it has 2 implementations cons n nil
    //as cons is a recursive implementation ie ended by nil.
    //command click on nil
    // <: means subtype, Nothing is a subtype of all types.
    //List is a effect type like future, option, try. List means multiple, future means time, option means nullability, try means exceptions, these are effects, list is a type constructor, we give
    //List[A] A a type, list adds an effect to an underline type A.
    //Covariant means if A is a subtype of B then f(A) is a subtype of f(B) where f is a type constructor, list is a type constructor not a type, A is a type, List[A] is a type
    //:: cons is called recursive implementation as the constructor argument next is of type List[A] and cons is also of type List[A] and ie self referential type, type recursive.
    //Nil implementation of List is made as Nil is base condition to end recursive implementation of ::, otherwise object won't be created of List.
    println(l3) // o/p- List(), Nil has internally tostring overriden so it gives empty list as output

    val l3e: List[Nothing] = List() //l3e is of type List[Nothing]
    println("empty list: " + l3e) //o/p- empty list: List()

    val l3f: List[String] = Nil

    val l = prepend(4, l1)
    println("prepend called " + l) //o/p- prepend called List(4, 1, 2, 3) //a will get prepend in as list

    val m = map(l1)(x => x.toString + "ee")
    println(m) // o/p-List(1ee, 2ee, 3ee)

    val n = myMap(l1)(_ + 1)
    println(n) //o/p- List(2, 3, 4)

    val o = myFilter(l1)(_ == 2)
    println(o) //o/p- List(2)

    println("concat method called " + l1.concat(l2)) //o/p- concat method called List(1, 2, 3, 1, 2, 3)

    val l4 = List(100, 200, 300)
    val l5 = List(400, 500, 600, 700)
    val p = myConcat(l4, l5)
    println("myConcat called-  " + p) //o/p- myConcat called-  List(100, 200, 300, 400, 500, 600, 700)

    val p2 = l4 ::: l5 // ::: is used for concatenating 2 lists
    println("::: called-  " + p2) //o/p- ::: called-  List(100, 200, 300, 400, 500, 600, 700), this is equivalent to println("::: called-  " + p2.toString()), p2.toString is automatically called
    // by compiler as every object has a toSting method, + is for concatenation
    println("Nil is an empty List:-  " + (l4 ::: Nil)) //o/p- Nil is an empty List:-  List(100, 200, 300)

    println("zip internal method called " + l1.zip(l2)) //o/p- zip internal method called List((1,1), (2,2), (3,3))

    val q = myZip(l4, l5) //o/p- List((100,400), (200,500), (300,600))
    println(q)

    //q1-print sum of all elements of list l1
    println(l1.foldLeft(0)((acc, a) => acc + a)) //o/p -6 //acc- accumulated value ie final result, a- current element

    //q2-print the product of all elements
    println(l1.foldLeft(1)((acc, a) => acc * a)) //o/p -6

    //q3-find sum of all even elements of list -use filter first to find even elements then apply fold left.
    println("sum of all even elements of list " + l1.filter(x => x % 2 == 0).foldLeft(0)((acc, a) => acc + a)) //o/p-2, first filter will return a new list over that foldleft will be called.

    //q4- find sum of all even elements of list by  only using foldLeft
    println(l1.foldLeft(0)((acc, a) => if (a % 2 == 0) acc + a else acc)) //o/p-2, a is every element of list, acc is accumulated result, 0 is default value, if any element of list is even then add else
    // use same acc

    //q5-implement foldLeft
    val r = myFoldLeft(l1)(0)((acc, a) => acc + a)
    println("myFoldLeft called-" + r) //o/p- myFoldLeft called- 6

    println("foldRight " + l1.foldRight(0)((a, acc) => a + acc)) //o/p- foldRight 6
    println("foldRight - using _ as shorthand  " + l1.foldRight(0)(_ + _)) //o/p- foldRight - using _ as shorthand  6

    val s = myFoldRight(l1)(0)((a, acc) => a + acc)
    println("myfoldRight called-" + s) //o/p- myfoldRight called- 6

    println(l1.reverse) //o/p-List(3, 2, 1)
    //q6- implement reverse
    println(myReverse(l1)) //o/p-List(3, 2, 1), as myReverse is not part of l1 and needs list as argument

    println("reduceLeft: " + l1.reduceLeft(_ + _)) //o/p- reduceLeft: 6
    println("reduceLeft * operator: " + l1.reduceLeft(_ * _)) //o/p- reduceLeft * operator: 6

    println("You can create a list from a range: " + (1 to 5).toList) //o/p- You can create a list from a range: List(1, 2, 3, 4, 5)

  }
}

//list is linked-list in scala
/*
in list implementation, browsed companion object and class List, few functions and
case object Nil extends List[Nothing]
 */

/*
::, prepend-

:: is called cons/prepend, it is a method which is given elem:B as an argument and returns a list, it adds the elem at the beginning of the list on which it is called.
 1 :: List(2, 3) = List(2, 3).::(1) = List(1, 2, 3)
def :: [B >: A](elem: B): List[B] =  new ::(elem, this) //'this' is the list on which '::' method is called.

    val l2: List[Int] = 1 :: 2 :: 3 :: Nil
    val l2: List[Int] = 1 :: (2 :: (3 :: Nil))
    val l2: List[Int] = 1 :: (2 :: (Nil.::(3)))
    val l2: List[Int] = 1 :: (2 :: List(3))
    val l2: List[Int] = 1 :: List(3).::(2)
    val l2: List[Int] = 1 :: List(2, 3)
    val l2: List[Int] = List(2, 3).::(1)
    val l2: List[Int] = List(1, 2, 3)

implement prepend method on your own
in prepend method, we are given a element 'a' and a list 'as' and we have to prepend 'a' in that list.
 */

/*
map-
every ds like list, future, either, option have their own map implementation and map can only be called on those ds only and not int which doesn't have a map in it.
here we are implementing a different map of our own, we cannot call that map on list. on list, list s map implementation will be called ie on line 10 l.map(f) when you command click on map you ll see.
currying done on line 81, val m = map(l1)(x => x.toString + "ee") so compiler doesn't get confused.
map on line 10 is different from list s implementation of map internal to it, our map can be called directly and not on a collection like list, we can make fxn of same name, lists map is called list.map
override final def map[B](f: A => B): List[B]
map method is implemented using scala s internal map method on line 10.
 */

/*
myMap-
nil means empty list
case ::(head, next) same as case head :: next same as a :: as same as head :: tail // a is first element of list, as is remaining list
2 cases case nil, case cons are made as list is a abstract class and has these 2 implementations in List.scala file.

head is of type A , f(head) converts A to B
tail is of type List[A]
//implement map method using recursive approach- when list is empty and when list is non empty

//implement map method using recursive approach-
def myMap[A, B](l: List[A])(f: A => B): List[B] = {  //list has 2 implementations ::(head, next) and nil ie why pattern match is showing the 2 cases.
  l match {
    case (head: A) :: (tail: List[A]) => f(head) :: myMap(tail)(f) // in pattern match case- :: is not a method here but a case class. pattern match cannot be done on method, only case class, as
     pattern match is on a object an we see what it looks like on inside.
    case Nil => Nil //when list is empty, return empty list, Nil denotes empty list.
  }
}
f(head) converts type of head from A to B and myMap is a function whose i/p is a list of type A and a function A to B which returns List [B]
when list is not empty f has to be called on every element of list which we did recursively using our myMap, after recursion is done head is prepended
ultimately function f is called on every element of list but using recursive approach.

here l is a list and ie a trait with 2 implementations ie why we are able to write match with it and do pattern matching.
[A, B] are called type parameters.

//def map[A, B](l: List[A])(f: A => B): List[B]

dry run on List(1,2,3), f(x =>x+1)
case ::(head, tail) => f(head) :: myMap(tail)(f)
myMap(List(1,2,3))(f)   =   case ::(1, List(2,3) ) => 2 :: myMap(List(2,3))(f)  = 2 :: List(3,4) = List(2,3,4)
myMap(List(2,3))(f)     =  case ::(2, List(3) ) => 3 :: myMap(List(3))(f)      = 3 :: List(4)   = List(3,4)
myMap(List(3))(f)       =  case ::(3, List() ) => 4 :: myMap(List())(f)        = 4 :: Nil       = List(4)
myMap(List())(f)        =   Nil
*/


/*
myFilter-
Filter is a method that takes i/p as a list and run function f on each element of list, if function condition holds true then add that element to o/p list using ::. if list is non empty, then we have head n
tail of list, we check on head if it follows function condition, if yes, then to add it to the list, we use append and call the remaining list recursively to again check the same for next head, for
the head for which function condition is false, it skips adding to the o/p list and call the remaining list recursively.
if if(f(head)) evaluates to true then prepend head to remaining filtered list ie include current head in final answer otherwise don't include head in else and continue filtering with remaining list.
override def filter(p: A => Boolean): List[A], filter keeps only those elements for which function is true
*/


/*
myConcat-
implement a method concat that concatenates 2 list- only A single type as list is homogeneous collection so after combining both have same type. If we want to concatenate 2 different types A, B , final o/p
should be a list of type ANY
if x has no elements, final output list is y, if x has 1 element then that element :: y is answer, if 2 elements then 1st element :: 2nd element :: y is answer and we can do this recursively fo all
elements of x
tail hai he ni recursively ho jayga, problem ko tod k socho, can current problem be reduced to a sub problem
head hai he ni is nil case, so head hai,
i have head, tail, y, myConcat recursive method(when we send in 2 list here it gives a concatenated list, so we send y, tail), what can i do. now i have o/p list myConcat(tail, y) and head so we can
cons them.

 case ::(head: A, tail: List[A]) => head :: myConcat(tail, y) , means if x has a head n tail, insert head to o/p list and recursively call myConcat on (tail,y) until all elements of x are added to o/p list
 after which case Nil => y executes ie x is empty so y is directly appended to remaining o/p list.

 */

/* myzip-
problem statement- implement a method zip
x-(1,2,3)
y-(4,5,6,7)
o/p- (1,4) (2,5) (3,6)
if one list is longer than other then final list is shorter of the two and ignore the remaining part of longer list
soln- tuple is pair of 2 elements, List[(A, B)] is a short form of tuple2[A,B].
(A, B) is a type of tuple2, List[] k andar ek he type parameter jata hai ie (A,B)
we have to extract head of both list, put it in () ie make a pair tuple2 of it and then recursively call zip on dono ki tail
when extracting head, first check list is not empty
if x match krte then 2 cases, y match karte then 2 cases, now (x,y) so 4 cases
x non empty y non empty, zip(tail1, tail2) will zip 2 lists tail1, tail 2 recursively, (head1, head2) :: myzip(tail1, tail2)--(head1, head2) together is conned with them
x non empty y empty, when 2nd list is longer than first, then leave it, so nil
x empty y non empty, when 2nd list is longer than first, then leave it, so nil
x empty y empty- o/p list is nil
(head1, head2) :: myzip(tail1, tail2)-- insert (head1, head2) in o/p list and recursively call myzip on (tail1, tail2)
*/

/*
foldLeft calling in main
override def foldLeft[B](z: B)(op: (B, A) => B): B
A- is every element of the list
B - final accumulated result jo hum addition operator A k element aur x pe laga k  jo result aayga vo.
foldLeft means reducing list to a single element of type B, using binary operator function op, eg + (addition), left means fold from left to right ie apply operator method
List(1,2,3)--6
empty list- 0, ie z=0, if list is empty default value z is returned
List(a1,a2 a3), z- is initial value
z, a1 --pass to op, o/p pass with a2, o/p pass with a3

l1.filter(x => x % 2 == 0) //only those elements are kept which are giving true for condition (x=>x%2==0)

//why [A] is not mentioned in foldLeft signature? as foldLeft method is part of List and A was there in list and ie why type signature doesnt explicitly mentioned [A]
override def foldLeft[B](z: B)(op: (B, A) => B): B
 in foldLeft function signature and in other signatures of map, flatmap too why is foldLeft[B][A] generic type A not mentioned , A is element of type list, B is type of o/p of adding A and z.
 —as A is part of list trait, (A is list type) where these methods are defined. The  methods we wrote our map, our foldleft we have to explicitly right type of A too .


Collection is a supertype of List, Set, Option, etc

doubt- In default function signature of  foldLeft op is (B, A) (op: (B, A) => B) and in foldRight its (A, B) in (op: (A, B) => B), what is the significance of this, does order of A, B matter?
Order doesn’t matter as we are sending arguments A, B together
 */

/*
myfoldLeft implementation
 override def foldLeft[B](z: B)(op: (B, A) => B): B--copied function definition for myFoldLeft from foldLeft,
 it has to be applied on list i/p so ls,
 if list is empty
 if list is non empty, current element z aur list k first element head pe operator op call kr do (op(z, head))
 recursively fold remaining list with new starting value z ie op(z, head), remaining list next, op function
 */

/*
myReverse --explanation
when we call reverse method, we give a list as i/p and get a list as o/p, they both are of same type.
use foldLeft
if list is empty then empty list is returned, so default value is nil, z=NIL,
if current element of list is a and accumulated result is acc
cannot call myFoldLeft as its not present in ls
go from left to right, first element a uthao nil k aage lagao using cons ::,
1::Nil-- acc- Nil, a-1 -- List(1)
2:: List(1)--List(2,1)
3::List(2,1)--List(3,2,1)
fold from left to right while computing result
 */




