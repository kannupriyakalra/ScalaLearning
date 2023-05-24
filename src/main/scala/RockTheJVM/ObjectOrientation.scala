package RockTheJVM

object ObjectOrientation extends App {
  //extends App is java equivalent: public static void main(String[] args)
  //class and instance
  class Animal {
    //define fields
    val age: Int = 0

    //define methods
    def eat() = println("I'm eating")
  }

  val anAnimal = new Animal


  //inheritance, passing argument to class
  class Dog(val name: String) extends Animal //class definition is also the constructor definition. class definition with arguments will also specify the constructor which is then used to instantiate
  // an object.

  //if you don't have anything to add in inherited class, you can leave the code block blank
  val aDog = new Dog("Lassie")
  aDog.name
  /*
constructor arguments are not fields to make them fields, you ll have to put val before the constructor argument.
when val prefix is not mentioned before name, aDog.name is invalid, name is not available, compiler will complain as it cannot resolve name. After the object is created, name cannot be accessed
outside the class.
  */


  //subtype Polymorphism
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() //the most derived method will be called at runtime.
  /*
Scala has the concept of subtype Polymorphism which exists in other statically typed object oriented languages. On left there is Animal type and on right there is Dog type, so when we call a method eat
from animal class, it will actually be called from dog class. If dog class chooses to override the eat method, then that will be called at runtime, the most derived method will be called at runtime. At
compile time, compiler only knows that you are calling the eat method from Animal object but at runtime the eat method will be called from most derived class.
   */


  //abstract class
  abstract class WalkingAnimal {
    val hasLegs = true //by default public, can restrict by adding protected or private

    def walk(): Unit
  }
  /*
abstract class means not necessarily all fields and methods have to have implementation. The class that extends abstract class WalkingAnimal will need to provide implementation for walk method.
access modifier- all fields and methods are by default public, there is no public access modifier in scala, to restrict public access there is private and protected keywords.
private val hasLegs = true, private means only the class has access to this member and method.
protected val hasLegs = true, protected means the class and its descendants have access to this member and method but not outside of the class.
   */


  //interface- ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }
  /*
Trait- Interface is a term of java ie used to denote ultimate abstract type, it is called trait in scala. It means, we can leave all fields and methods unimplemented. Although scala is very quirky and
we can define implementations in traits but usually we use traits to denote characteristics of objects that we can then later use and implement in our concrete classes.
   */

  trait Philosopher {
    def ?!(thought: String): Unit //?! is a valid method name
  }


  //single-class inheritance, multi trait "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating you, animal!")
    //override def eat(): Unit = super.eat()

    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }


  //scala method notation and method naming
  val aCroc = new Crocodile
  aCroc.eat(aDog) //aDog is of type Dog, Animal both
  aCroc eat aDog //infix notation= object method argument, only available for methods with one argument
  aCroc ?! "What if we could fly?"
  /*
1. Scala offers single class and multi trait inheritance ie we can extend as many trait as we like but we can extend only single class. When we add a trait with class in inheritance we call it mixing.
2. We have to implement eat method of carnivore trait as we are forced by compiler as it does not have a implementation or we can make class Crocodile as abstract to skip creating implementation of
 eat method.
3. override- when we implement a method in a class Crocodile ie also available in its supertype trait Carnivore, we call it a override.
4. super- We can also override eat method from Animal, we can either add our own implementation here or use the implementation of eat in the class animal by using super keyword.
5. ?! is a valid method name which might look like an operator, scala is very permissive in what concerns a method name, we can define methods as quirky as we like.
6. ? operator or method is used in akka and ! we use when we want to communicate with actors asynchronously, so this is actually used in practice.
7. in line 70 when first class/trait is extended then 'extends' keyword is written after that 'with' is used. 'with' can only be used with traits, 'extends' can be used with both traits and class.
   */


  //operators in scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) //equivalent


  //anonymous class
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
  }
  /*
  What you tell the compiler:
  class Carnivore_Anonymous_35728 extends Carnivore{ //extends keyword can be used for extending trait also
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
  }
  val dinosaur = new Carnivore_Anonymous_35728

anonymous class- if you worked with other statically typed programming languages like cpp or java, then you know that abstract types like abstract class and interface cannot be instantiated by themselves,
they need to be extended by a concrete class, in scala we can have a concrete type like dinosaur that extends a trait Carnivore and we can provide the implementation of trait on the spot, internally we
are telling the compiler to create me a new class Carnivore_Anonymous_35728 and instantiate that class for me.
   */


  //singleton object, apply
  object MySingleton { //the only instance of the MySingleton type
    val mySpecialValue = 53278

    def mySpecialMethod(): Int = 5327

    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  MySingleton(65) //equivalent to MySingleton.apply(65) as compiler gives special treatment to apply, we can skip writing apply while calling it.

  /*
  singleton object- this concept is particular to scala. When we define an object MySingleton we define the type MySingleton and also the single instance of this type, so MySingleton is the only
   instance of the MySingleton type. Being a type we can also add values and methods. We can use MySingleton just as that directly to call its members, as we use any other value. There is no need
   to create an instance for it as it is its only instance.
   apply- its a special method in scala which can take any arguments and which can be present in any class, in any object, wherever we like. The presence of an apply method in a class allows
   instances of that class to be invoked like functions ie to call the apply method directly without mentioning it explicitly. This is very useful in functional programming which we ll learn in
   video 4.
   */

  //companions- companion object
  object Animal {
    //companions can access each other's private fields and methods
    // singleton Animal and instances of Animal class type are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely //We access canLiveIndefinitely field on singleton object in much the same way as in java or cpp we access "static" fields or methods.

  val x: Animal.type = Animal //x refers to object Animal in line 140

  /*
  1.Companion object- Companion object has same name as class name, consider it as helper area of class where we keep utilities to keep class clean, it is a static version of a class. Animal class
   and Animal object are different just names are same, for animal object, it has 1 object and that object's type is Animal.type. For animal class it can have any no. of objects.
  2.Companions- In the same file ie in the same place when we define a class ie Animal and a singleton object ie Animal with same name, same name is acceptable to compiler, in this case the class
  Animal and object Animal are called companions. Object Animal is called a Companion object as it has the same name as an existing class or trait, this concept can also be applied to traits.
  3.When you click on semi circular umbrella just beside Animal singleton object, it will take you to the similar umbrella near its Companion class in the same file.
  4.Singleton object Animal and instances of Animal class type are different things. We normally never use Animal object as an instance while instances of Animal class are present ie we never
  create instance of singleton Animal object as that itself is the instance. We use singleton Animal companion object directly to access things that do not depend on instances of Animal class ie
   fields of object Animal, example in line 146.
     */


  //case classes
  /*
  case classes = lightweight data structures with some boilerplate
  -sensible 'equals' and 'hash code' methods
  -serialization
  -companion with apply
  -pattern matching
   */
  case class Person(name: String, age: Int)

  //its object can be constructed without 'new' keyword as internally its calling apply method and apply method can be called without mentioning explicitly---val bob = new Person("Bob",54)
  val bob: Person = Person("Bob", 54) //equivalent to Person.apply("Bob", 54)
  println(bob.hashCode()) //o/p- 1788943694

  val billy = Person("Bob", 54)
  // == and .equals are same and they compare value inside object in case of case class. For normal class, they check reference equality.
  println(bob == billy) //o/p-true
  println(bob.equals(billy)) //o/p-true

  val s = Set(1, 1, 2)
  println(s) //o/p- Set(1, 2) a set removes duplicate on its own.

  //pattern match
  bob match {
    case Person(x, y) => println("pattern matching " + x) //x is name, y is age , o/p- pattern matching Bob
  }



  /*
  Case classes are a very common pattern in scala because they are lightweight data structures with some boilerplate. "Case" keyword is used to define a case class.
  When we create a Case class Person, the compiler automatically generates the following for me:
  1.a sensible 'equals' and 'hashcode' method for inclusion into various collections that rely on equality and hashcode.
  2.sensible and quick serialization because we often send these instances over the wire in distributed application in particular for akka for example.
  3.we also have a companion object with apply ie we can emit 'new' keyword while creating class object ie because Person case class also has a companion object with an apply method taking name and
  age as i/p and spitting out a Person instance. So case classes may be constructed without the keyword 'new'.
  4. val bob = Person("Bob", 54) is equivalent to Person.apply("Bob", 54) ie Person companion object is automatically generated by the compiler when you write a case class.
  5. pattern matching --will discuss later in course
   */

  /*
  gagan explained-
  pattern means it can be used in pattern matching.
  boilerplate means apply, unapply, copy, toString, hashCode, equals etc methods are available by default. Normal class doesn't have these and they need to be implemented on your own.
  val bob: Person =  Person.apply("Bob", 54) //try to get into apply you can't as it gets generated at compile time, its a replacement of new, we can never see it, it goes into byte code by compiler.
  hash- when a big string is given as i/p to hash fxn o/p is 10char string ie hash of that string. to compare this big i/p string with another big i/p string , we can find hash of both to compare,
  its kind of a  compression, if 2 hashes are equal then their i/p string/object on which they are called may or may not be equal. if 2 hashes are unequal,then their i/p are not equal. So to get this
  probability first 2 hashes are compared then 2 strings are compared to save time. in java, super class is called Object that has hashCode, as all classes extend it so all have it.
  2 objects are compared by comparing individual values of all fields of bob n billy which is done by equals method. == and .equals are same.

    class Person(name: String, age: Int)

  //its object can be constructed without 'new' keyword as internally its calling apply method and apply method can be called without mentioning explicitly---val bob = new Person("Bob",54)
  val bob: Person = new Person("Bob", 54) // Person.apply("Bob", 54)
  println(bob.hashCode()) //o/p- 1788943694

  val billy = new Person("Bob", 54)
  println(bob == billy) //o/p-false
  println(bob.equals(billy)) //o/p-false

  reference equality is checked in case of normal class, by default. in case class, it overrides this method and make it check value equality for 2 object. ie why we only make case class
  in scala so we can check value equality, we don't usually need reference equality. In case class, if reference is same, they are same objects as they pt to same location in memory, if reference is
  not same, then we compare the content ie compare name n age, in case class it checks first reference n then values. if reference matches it doesn't check values n return answer, if reference doesn't
  match then it check values inorder to save time. Bob n Billy are 2 different objects and they have different references.

  What is reference?
  val x = new Person("anil",10) (x is reference to new Person constructed, x has address and ie called reference and at that address value is kept)
  val y = x (y is pointing to x, x and y are referring to same address , x==y, y has same address as x)
  val z = new Person("anu",11) ( z has new address, z is not equal to x, new likhte he new address banta hai jispe value rakhi jate hai, new means asking os memory)

  collections that rely on equality and hash code means- set , a set has distinct elements, if a element already exists then it doesn't get inserted. A set does .equals on every new element ie to be
   inserted with all existing elements.

  serialization means 2 objects bob and billy when executed will be created in memory ie RAM, if we close laptop without running program they ll be lost, when a program is run, it uses
   RAM memory(volatile memory) and not HDD(persistent storage), eg creating 2 object bob n billy took 2 hrs so we ll serialise their o/p that means write their o/p to disk.
   Serialise means writing an object to disk(HDD) . Deserialize means reading an object from disk.
   over the wire means when a service is called it returns objects json serialised (ie serialise them ie save them so they can be returned and ie done in json format) and after receiving we deserialize
   it ie read it using our json deserializer.
   json is key value pair
   Person{ //json format for class person
   Name: Billy,
   Age: 54
   }
   serialise deserialize means converting into string n they are read using json, writing object into disk n reading from it

   for every case class a companion object is available by default and that has apply method ie why person.apply is called, as person is static class. We don't have to make person companion object
    explicitly unless we have to add some more functionality to it.

   val bob: Person =  Person("Bob", 54), name of companion object is Person and that has a method apply, bob is just a instance of Person class and bob is not that companion object. Companion object
   is the static class ie internally called.

  apply- create objects
  unapply- does pattern matching

  Some, none can be done on case class, case object only, pattern matching is slicing n dicing to see what it is. unapply- call during pattern matching.
  when case keyword is added to class or object both get the same features.
   */

  //exceptions
    try {
      //code that can throw exception
      val x: String = null
      x.length
    } catch { //in java, catch(Exception e){...}
      case e: Exception => "some faulty error message"
    } finally {
      //execute some code no matter what
    }


  /*
  Scala is based on jvm, all scala code we write is compiled to jvm byte code that can be then run on every device that has a jvm installed like android phone, computer etc.Anything that can run java
  can also run scala. JVM has notion of exceptions which means if one of these object is thrown then jvm will interrupt its normal code flow execution and exceptions are special objects treated by the
  jvm with these try catch expressions. In try, we write some code that can throw exception eg in val x: String = null we are calling a method that returns null. Then by x.length i am accessing a
  method on a field on a null object this should normally crash the program but in a try catch expression i can catch all the exceptions that might be thrown with "case e: Exception => "some faulty error message""
  expression. This is similar concept in java, cpp.
  finally- regardless of try code block throwing an exception or not, this finally code is executed no matter what. This is useful for closing connections, files or releasing resources that would
  otherwise be dangerous to leave open.


  When writing this part of code outside try its fine code
  val xxx: String = null
  xxx.length
   */

  /*
  to study- heap vs stack memory google
  objects created by new keyword they go in heap memory.
  oom out of memory exception- if new cannot allocate space in heap.

  val x = 10 , x contains address of location where 10 is kept, x is reference of 10
  val x = null, x has no address,
  x.length --cpu goes to x and couldn't find any address so it doesn't know where to go to find length, so it doesn't know what to do, this is called null pointer exception. x is pointer that has null.
  Exception is raised when expected behaviour doesn't work.

  We don't use null in scala but option type. Equivalent ideal correct code for above try is this--
  x: Option[String] = Nothing
  x.map(_.length)
  x.getOrElse(0) //if string doesn't exist, its empty string or string is null, length is zero

   */


  //generics
  abstract class MyList[T] {
    def head: T

    def tail: MyList[T]
  }

  //using a generic with a concrete type
  val aList: List[Int] = List(1, 2, 3) //equivalent to List.apply(1,2,3)
  val first: Int = aList.head //int
  val rest = aList.tail

  val aStringList = List("hello", "scala")
  val firstString: String = aStringList.head //string

  /*
generics is used in java, cpp. Generics can be applied in classes, traits. T is a type argument, inside the class the methods and fields depend on this type argument. T is a part of method and value
definitions inside the class. When we use MyList[T]'s data members later, we assign a concrete type to T like int, string, etc.  val first = aList.head , head returns an element of type T which becomes
concrete as int for this value first as it is called on a list of type int. Generics is a concept of statically typed language like java, cpp, its not there in python, javascript.
Purpose of generics is to reuse same functionality and apply it to multiple types such as the code for collections, the code for collections should be applicable to any type that you store inside.
We are applying same head method on first a list of int, then a list of string and we are obtaining a int and string value type respectively.
   */

  /*
  in line 314, head is of type internal List and not MyList. MyList methods he never used.
  val aList: List[Int] = Nil
  val first = aList.head

  executed code, exception came- List.scala internal code line 592
  ie why scala promotes calling headOption and not head as that is safe.

  doubt- how list implementation has apply? it has,  when we try to go inside List, List s companion object(line 611 in List.scala) has apply, on doing command click on list, List shows 2
  implementation- apply, List. apply is implemented in Factory.scala class which is super class of all collection including List and has apply.
   */

  /*
  2 IMPORTANT POINTS
  Point #1: in Scala, we usually operate with immutable values/objects.
  Any modification to an object must return another object. So you don't mutate or change the value inside a given object you always return another one.
  Benefits of Point 1:
  1. works miracles in multithreaded/distributed environment as it speed up the development tremendously.
  2. helps making sense of the code much easier specially in large code bases ( ie called "reasoning about")

  Point #2: Scala is closest to object oriented ideal. It is marketed as a mix b/w object oriented and functional language. Scala is a very good object oriented language as all the code and values
  we operate with is inside an instance of some type ie for example all the code we wrote so far is part of an object. There are no values or methods that are outside a class or a object and that makes
  it a true object oriented language.
   */

  val reversedList = aList.reverse //aList is List[int] returns a new list of same type ie example of Point 1.

  /*
  extends App- When we define an object which extends App we are actually inheriting from the App type s main method so we have a main method already implemented in this object. As mentioned earlier,
  methods in Object are similar to java's static methods, we already have a static main method already implemented by extending App ie we already have a java's public static void main(String[] args) ie
  extends App is equivalent to java's public static void main(String[] args). This main method inside App will simply execute this object s body, this is why applications are actually runnable, if
  we remove the extends App notice intellij won't allow us to run this application.
   */

  // Trait has no constructor? No, only concrete classes has, trait s object we don’t create

}
