package TourOfScala

/*
https://tourofscala.com/scala/objects

Scala objects
After seeing about class and case class, we are going to learn about object.

I know, it is confusing, because we learned that an object is what we create when we instantiate a class, but this term can also be used to talk about a class that do not need to be instantiated. I know, confusing…

To make it clearer, let's just focus on those object. If you know other programming languages, you might have heard of static, it means that the field and method can be accessed without instantiating the class. Let's talk about it more in the section after the exercise.

Did you notice how the field and method are being accessed ?

You use the name of the object, for instance: Configuration.To access KeyNumberOfFoos that is defined inside of Configuration, you just have to do: Configuration.KeyNumberOfFoos. No need for new or anything special, you can just call the member of the Object directly.

Object are most commonly used when you want to defined constants values that are common for several systems or classes, like the Configuration one we have here. Object are also used to stored “utility methods” or also called “helper methods“.

This helper methods are common tools for several systems that do not have dependencies defined at runtime. That means that you can define the behavior of this method only based on the input parameters, no need to know any more parameters. For instance, a method to sum two numbers a + b, you do not need to know more than the two inputs: a and b. They are self contained systems and can often be reused from one project to another as a library.
 */
object ScalaObjects extends App {

  object Configuration {
    lazy val KeyNumberOfFoos: String = "NumberOfFoos"
    lazy val KeyNumberOfBar: String = "NumberOfBar"
  }

  object Database {
    private val database: Map[String, Int] = Map(
      Configuration.KeyNumberOfFoos -> 567,
      Configuration.KeyNumberOfBar -> 12
    )

    def getDataFromDatabase(key: String): Option[Int] =
      database.get(key)
  }

  val configurationFromDatabase: Option[Int] =
    Database.getDataFromDatabase(Configuration.KeyNumberOfFoos)

  println(configurationFromDatabase)

  assert(configurationFromDatabase.contains(567))

  println("Congratulations ! 'Be nice to yourself, you’re doing your best.'")


}
