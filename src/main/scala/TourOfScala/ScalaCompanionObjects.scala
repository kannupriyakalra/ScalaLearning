package TourOfScala

/*
https://tourofscala.com/scala/companion-objects

Scala companion objects

Companion objects are a specific type of object.

Just like the object we saw in the previous SKB, companion object does not need to be instantiated.

The source of the main difference between object and companion object comes from the name that they share with a class.

Did you notice that the class named Animal is able to call a method named convertLegNumberToName that is defined as private?

Other thing to notice is the import. In the previous SKB related to object, we learned how to call the member of an object using its name. When you use the import, you do not need to use the name anymore. Inside the context, where the import is used, with the Animal._, the name is not needed to be called.

To summarize, a companion object is exactly like an object, except that the class that share the same name can access the private members.

It is commonly used when you need to have constant or helper methods related to this class.

Leaving those members inside the class can create memory issues. Let's say you have a large constant – maybe an array or some other complex object. If it is stored inside the class, then each instantiation will make a copy of this constant in the memory. If instead the constant is in the companion object, then the program will only have to create the constant once.

 */
object ScalaCompanionObjects extends App{

  import Animal._

  case class Animal(numberOfLegs: Int) {
    lazy val name: String = convertLegNumberToName(numberOfLegs)
  }

  object Animal {
    val BipedName = "biped"
    val QuadripedName = "quadriped"
    val CentipedName = "centiped"

    private val LegName: Map[Int, String] = Map(
      2 -> BipedName,
      4 -> QuadripedName,
      100 -> CentipedName
    )

    private def convertLegNumberToName(numberOfLegs: Int): String = {
      LegName.get(numberOfLegs).getOrElse(s"$numberOfLegs legged creature")
    }
  }

  val quadriPed: Animal = Animal(4) //here Animal is type and it refers to the case class.

  val a: Animal.type = Animal //here on the right of = is Animal object and its type is Animal.type, both these Animal are of type Animal object

  val biPed: Animal = Animal(2)

  // Try uncommenting this: (remove the '//')
  //println(Animal.convertLegNumberToName(biPed.numberOfLegs))

  assert(quadriPed.name == QuadripedName)
  assert(biPed.name == BipedName)

  println("Congratulations ! We only live 4000 weeks, live them the fullest.")


}
