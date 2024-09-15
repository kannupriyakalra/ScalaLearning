package TourOfScala

/*
https://tourofscala.com/scala/implicit-val

Scala implicit val

implicit val can do a lot but, for now, we are just going to learn about the basic use case.

This exercise has a lot of code not related to what we are learning, but I am trying to illustrate some kind of real use case rather than a one line exercise. I think it makes it more exciting. If you disagree, please come let me know on our Discord community !

I made sure to reuse only parts we have seen before, and we have seen a lot ! Congratulations for going this far on this series, I hope that it is beneficial to you ! If there are specific things you would like to learn, just let me know and I'll add to the TODOs of episodes to write.

You can see the use of case class, methods with def, private, object but the new thing here is implicit val.

In a method, you would declare it like any other argument. Except, all implicit arguments must be in their own “bucket” of arguments and this “bucket” must be the last one of them all for this method. And you have to precede the list of argument by the keyword implicit. Like this:

def methodName(arg1, arg2, arg3)(arg4, arg5)(implicit arg6, arg7, arg8): OutputType = ???
Each argN would be the name: Type combo.
Now, when you actually call the method containing the implicit argument, you do not have to give a specific argument if one is present in the context. You can see it declare like this:

implicit val name: Type = ???
Note that in this example it was set to private.
You can also overwrite or just pass the argument like any other normal arguments.

Note that you shouldn't abuse this feature. In fact, it can become dangerous as well as make the code extremely hard to read. Imagine the extreme case where every arguments are implicit, it would be extremely hard to know what is happening. It makes the code hard to read in static environments like GitHub. And, in case of ambiguous possibilities, it can be tricky to understand what is going on.

I would recommend to only use this feature for elements that would be part of a configuration or such. Elements that would have to be copy paste and pass to each and every functions down the line. Only use it if it makes the code more readable.

With great power comes great responsibility.
 */
object ScalaImplicitVal extends App{

  case class Tracker(name: String)

  object Utils {
    def add(a: Int, b: Int)(implicit t: Tracker): Int = {
      println(s"${t.name} add")
      a + b
    }

    def multiply(a: Int, b: Int)(implicit t: Tracker): Int = {
      println(s"${t.name} multiply")
      a * b
    }
  }

  object Foo {
    implicit private val t: Tracker = Tracker("[Foo]")

    private val a: Int = 5
    private val b: Int = 3
    val out = Utils.add(a, b)
  }

  object Bar {
    implicit private val t: Tracker = Tracker("[Bar]")

    private val a: Int = 6
    private val b: Int = 10
    val out = Utils.multiply(a, b)
  }

  val fooOut = Foo.out
  val barOut = Bar.out

  assert(fooOut == 8, fooOut)
  assert(barOut == 60, barOut)

  val oneOff = Utils.add(10, 24)(Tracker("[OneOff]"))
  assert(oneOff == 34, oneOff)

  println(
    "Congratulations ! 'When something is important enough, you do it even if the odds are not in your favor.' – Elon Musk"
  )

}
