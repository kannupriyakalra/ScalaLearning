package TourOfScala

/*
https://tourofscala.com/scala/trait

Scala trait
trait are like interface from other languages.
trait allows you to describe what a class should look like.

You can implement functions that can be overridden. You can describe functions that have to be overridden.

New terms:

to inherit: the action of extending a class
Child: the class that inherit from a trait
Parent: the class that is inherited from
Several new keywords today:

extends: is the keyword to be able to inherit from a class
override: tell that this element has been overridden.
final: tell that this cannot be overridden
protected: similar to private but allow the child class to see this element. private would not allow the child to see it.
 */
object ScalaTrait extends App {

  trait Animal {
    def name: String
    protected def sound: String
    final def talk(): Unit = println(s"$name says $sound")
  }

  case class Dog(override val name: String) extends Animal {
    override protected final lazy val sound = "woof"
  }

  case class Cat(override val name: String) extends Animal {
    override protected final lazy val sound = "meow"
  }

  case class Bird(override val name: String) extends Animal {
    override protected final lazy val sound = "toto"
  }

  val cat: Cat = Cat("Kitty")
  val dog: Dog = Dog("Snuffles")
  val bird: Bird = Bird("Coco")

  val myAnimals: List[Animal] = List(cat, dog, bird)

  myAnimals.foreach(a => a.talk())

  assert(myAnimals.map(a => a.name) == List("Kitty", "Snuffles", "Coco"))

  println(
    "Congratulations ! 'Set your goals high, and don't stop till you get there.' - Bo Jackson"
  )



}
