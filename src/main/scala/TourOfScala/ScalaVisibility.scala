package TourOfScala

/*
https://tourofscala.com/scala/visibility

Scala visibility: Visibility is about what a class or an object expose to the rest of the world.

Let's review the different kind of visibility:

public: In scala, the public keyword does not exist, this is the default behavior. If nothing is specified, the member of this context will be available for all to call.
private: This will make the member not visible to the rest of the world (There is an exception about companion object, we are going to learn about it in a future SKB).
protected: This was not illustrated in this exercise. It is related to object oriented programming and more specifically about inheritance. We are going to learn more about it in a later SKB.
 */
object ScalaVisibility extends App{

  object Foo {
    val visibilityPublic = "a"

    private val visibilityPrivate = "b"
  }

  // try to use 'visibilityPrivate'.
  val result: String = Foo.visibilityPublic

  assert(result == "a")

  println("Congratulations ! You can change the world.")

}
