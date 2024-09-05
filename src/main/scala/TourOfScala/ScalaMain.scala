package TourOfScala

/*

//https://tourofscala.com/scala/main
Scala main

A main is the method that is being called to start your program. It needs a specific structure – called prototype when talking about methods – to be recognized.

Until now we ran all the SKBs in Worksheet mode which hides the main from us.

If you have tried to remove or change the main function, you might have noticed an exception:

java.lang.RuntimeException: No main class detected.
This Exception will be thrown if a main method cannot be found or is not contained within an object. Try to change the code to trigger it.

The main prototype is always of the form:

def main(args: Array[String]): Unit =
The args: Array[String] is where you would be able to read and use the arguments given to the application when started.
That is pretty much it concerning main, so I thought it would be nice to combine some of the things we've seen in the past. This SKB is using Map, object, Option, lazy val, def all into the same project. Try to play with it and make it your own.
 */

  object Database {
    private lazy val fakeDatabase: Map[Int, String] = Map(
      34 -> "abc",
      12 -> "def"
    )

    def apply(key: Int): Option[String] = fakeDatabase.get(key)
  }

  // This could be named anything, try !
  object Main {

    // Try removing or commenting this method to get familiar with the errors.
    def main(args: Array[String]): Unit = {
      assert(Database(12) == Some("def"))
      assert(Database(34) == Some("abc"))
      assert(Database(76) == None)

      println(
        "Congratulations ! 'It does not matter how slowly you go as long as you do not stop.' - Confucius"
      )
    }
  }


