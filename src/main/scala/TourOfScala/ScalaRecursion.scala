package TourOfScala

/*
https://tourofscala.com/scala/recursion

Recursion is a major tool in the toolbox of the software engineer.

It can allow to describe a problem from a different perspective.

In the context of Functional Programming this is also a major trick to avoid using mutable variables.

This is a very basic example to be able to understand the concept of recursion fully. In the future, we will see more complex application of recursion.

To build a successful recursion, you need several things:

Stopping condition: this is the most important thing. Without it the recursion will keep going for ever and eventually fail with an exception: StackOverflowError.
The method needs to call itself. In this example, you can see the loop function is called inside of itself.
Increment: something needs to change when calling the function again, otherwise, same as without a stopping condition, the code will never stop running since the stopping condition will never get reached. In this example you can see that when loop is called, it is called with modified arguments.
Most of the time you will have some kind of accumulator that hold the final result of the computation. In our example, this would be the argument named acc.
Initial condition: we also need a starting value for the accumulator and the other arguments that might be required to perform the computation. In this example, we need to set acc equals to 0.
The most famous example of recursion in books are always factorial or Fibonacci. I would love to see your implementation, feel free to submit a Scastie link in the comment section or on Discord.
 */
object ScalaRecursion extends App{

  def sumUpTo(until: Int): Int = {//5
    def loop(n: Int = 0, acc: Int = 0): Int = { //0,0
      if (n >= until) n + acc //0>5 false ,..., 15
      else loop(n + 1, acc + n) //loop(1,0), loop(2,1), loop(3,3), loop(4, 6), loop(5, 10)
    }

    loop()
  }

  val result: Int = sumUpTo(5)
  val expected: Int = 15
  assert(result == expected, result)

  println(
    "Congratulations ! 'Knowing is not enough; we must apply. Willing is not enough; we must do.' - Johann Wolfgang von Goethe"
  )


}
