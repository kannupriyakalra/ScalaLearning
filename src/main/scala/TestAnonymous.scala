object TestAnonymous {

  trait Mergable[A] {
    def merge(a1: A, a2: A): A
  }

  object IntMergable extends Mergable[Int] {
    override def merge(a1: Int, a2: Int): Int = a1 + a2
  }

  class DoubleMergable extends Mergable[Double] {
    override def merge(a1: Double, a2: Double): Double = a1 + a2
  }

  def merge3Elements[A](a1: A, a2: A, a3: A)(m: Mergable[A]): A = {
    m.merge(m.merge(a1, a2), a3)
  }

  def main(args: Array[String]): Unit = {
    println(merge3Elements(1, 2, 3)(IntMergable)) //o/p-6
    println(merge3Elements(1.1, 2.2, 3.3)(new DoubleMergable))//o/p-6.6

    val stringMergable1: Mergable[String] = new Mergable[String] {
      override def merge(a1: String, a2: String): String = a1 + a2
    }
    println(merge3Elements("x", "y", "z")(stringMergable1)) //o/p- xyz

    val stringMergable2: Mergable[String] = (a1: String, a2: String) => a1 + a2 //single abstract method
    println(merge3Elements("l", "m", "n")(stringMergable2)) //o/p- lmn
    //stringMergable1 and stringMergable2 are equivalent

    // s1, s2, s3 are equivalent
    val s1: (String, String) => String = (a1: String, a2: String) => a1 + a2
    val s2: Function2[String, String, String] = (a1: String, a2: String) => a1 + a2 //line 23, 28 is same as line 33, 34
    val s3: Function2[String, String, String] = new Function2[String, String, String] {
      override def apply(v1: String, v2: String): String = v1 + v2
    }
    println(s3.apply("Hi", " there?"))
    println(s3("How are", " you maam?")) //equivalent to line 38
    println(s2("How are", " you maam?"))
    println(s1("How are", " you maam?"))

  }

}

//in scala, there are 3 things-type, object and method, object are of a particular type, method is used to manipulate object. eg- val a = 10, a is a object of type int.

//in line 23,stringMergable1, we created a anonymous class, as we have to create a object of trait which is not possible so we directly implemented it there itself
//at line 28, its a syntactic sugar of line 23 when a trait has only 1 method, we can create a single abstract method. the map and other lamda fxn we use are this only.

//anonymous class is a class implemented immediately of a trait

/*Function 2 is a trait that takes 2 i/p and give 1 o/p and has 1 unimplemented method apply which also take 2 input and give 1 output, whenever we use Function2 type, we have to implement apply method
 which is a single abstract method.
this is (String, String) => String a syntactic sugar of Function2[String, String, String]

doubt:testAnonymous.scala, command click Function2, it has more than 1 method, line 54 you told me apply is a single abstract method. How is that possible when Function2 trait has more than one
method.-- as abstract method is one only and ie apply
*/

/*single abstract method- when there is a single method in a trait and it is abstract also ie it has no definition then we can write line 23 to  28 ie stringMergable1 is equivalent to stringMergable2.
trait can have multiple other implemented methods but abstract method should be single only for that method to be called single abstract method.

  trait Mergable[A] {
    def merge(a1: A, a2: A): A
  }

    val stringMergable1: Mergable[String] = new Mergable[String] {
      override def merge(a1: String, a2: String): String = a1 + a2
    }

    val stringMergable2: Mergable[String] = (a1: String, a2: String) => a1 + a2
*/

/*
s1, s2, s3 are objects
 */

/*
line 15 , m is object of Mergeable trait, we can create object of trait in scala? Yes via anonymous class, by using new for a class extending the trait, by object ie extending trait,
by single abstract method implementation
 */

/*
line 16 , m.merge(m.merge(a1, a2), a3) explanation
  val a12 = m.merge(a1, a2)
  val a123 = m.merge(a12, a3)
  a123

line 15, def merge3Elements[A](a1: A, a2: A, a3: A)(m: Mergable[A]): A
we used 2 separate round brackets, we could have used one also. Using 2 is to separate the type for code to look nice and also to help compiler infer A first, then deduce m. this is called currying.

def abc(i: IntMergable.type ): Unit = ()

}

Static class is called an object in scala and it’s a singleton class, its only object is the name of the class. Eg- IntMergable is of type IntMergable.
m is object of type Mergable, a1 is object of type A, as IntMergable is an object, in i:type , i is object , to make i object of type IntMergable, IntMergable.type is written. IntMergable is an object
in literal sense, to know type of an object .type is written

i is an object of type IntMergable.type (we can pass IntMergable in place of i as IntMergable is already an object ie why .type is mentioned as i:type has to be written)

 */