package TourOfScala

//https://tourofscala.com/scala/case-class

/*
Scala Case Class- They serve the same purpose which is to provide a blueprint to create objects. But case class provides a lot of build-in advantages.
As a start, notice how the case class is being instantiated, and how the fields are being accessed.
We do not need new to create the instance of the class.
We do not need a function to access any of the fields.

In Scala, case class provide build-in things:

Constructor: To instantiate an object from a case class, no need for the new keyword. Note that class will not need new either in Scala 3. To understand how this is done, remember the words apply and Companion Object, we are going to come back to it later.
Field accessors: in case class, the field are public ( we are going to talk about visibility later ) by default. So you can access their values without the need to a method that will return it for you.
And more; remember the terms unapply, Product and Serialization for later SKBs.
 */
object ScalaCaseClass extends App {

  case class Person(firstName: String, lastName: String) {

    def getName: String = {
      println(s"name is $firstName + $lastName")
      firstName + lastName
    }

  }

  val result: Person = Person("Leo", "Benkel")

  assert(result.lastName == "Benkel")

  result.getName //if you write getName() then you are passing () unit, so mention () in line 7 getName definition as well

  println(
    "Congratulations ! 'Next to trying and winning, the best thing is trying and failing.' ― Lucy Maud Montgomery"
  )


}
