package PracticedWithMentor

import scala.annotation.tailrec
//important interview question
object TestTailRecursion {

  // recursive example of factorial-
  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1)
  }

  //tail recursive example of factorial-
  def factorialTail(n: Int): Int = {
    @tailrec //tell compiler explicitly that this method is tail recursive, this ensures its executed tail recursively. if annotation is not there check by debugging by putting break point by how stacks are made n if its done tail recursively in myFoldLeft in testList.scala
    def iter(x: Int, result: Int): Int = //creating a method locally in a method is allowed in scala and calling it in that scope
      if (x == 0) result
      else iter(x - 1, result * x)

    iter(n, 1)
  }

  //2nd way of doing tail recursive example of factorial-
  def factorialTail2(n: Int, result: Int = 1): Int = {
    if (n == 0) result
    else factorialTail2(n - 1, result * n)
  }
  // result: Int = 1 default value giving is allowed in scala

  def main(args: Array[String]): Unit = {
    println(factorial(5)) //o/p- 120
    println(factorial(20)) //o/p- -2102132736, negative as integer range got over flown from positive side so it got to negative side, thats how it move in circle, this is integer overflow
    println(factorial(2000)) //o/p- 0, wrong answer as its out of int range
    // println(factorial(20000)) //o/p- Exception in thread "main" java.lang.StackOverflowError, as we asked for 20000 stack frames in memory which it doesnt have., commented so we dont get exception,
    // created to learn about StackOverflowError practically.

    println(factorialTail(5)) //o/p- 120
    println("-----" + factorialTail(2000)) // -----0 , wrong answer sent, but code didnt give StackOverflowError, this is integer overflow

    println("factorialTail2 : " +factorialTail2(5)) //o/p- factorialTail2 : 120, as default result value is already present ie why not sent

  }

}

/*
tail Recursive/ tail calls-
Tail recursion is defined as a recursive function in which the recursive call is the last statement that is executed by the function. So basically nothing is left to execute after the recursion call.
There is no need to keep record of the previous state.
if you have a recursive function that calls itself as its last action, then you can reuse the stack frame of that function. This is called tail recursion. And by applying that trick, a tail recursive
function can execute in constant stack space, so it's really just another formulation of an iterative process. We could say a tail recursive function is the functional form of a loop, and it executes
just as efficiently as a loop.
if the last action of a function consists of calling another function, maybe the same, maybe some other function, the stack frame could be reused for both functions. Such calls are called tail calls.
in testList.scala, hover on circle beside myFoldLeft in myFoldLeft, recursive call is last statement- yes. reuse the current stack frame after every recursion as the recursive call is last
statement there is nothing after it so existing recursion results are not required to be saved in cache.
In Scala, only direct recursive calls to the current function are optimized. One can require that a function is tail-recursive using a @tailrec annotation above the tail recursive method.

Recursive-
in myZip, result of recursive call is used in cons, its not last statement, ie result of recursive call is used so its normal recursion. We cannot throw current stack frame as we still
need to use it for computing further result, new stack frame is created as we go deeper in recursion, 500elements in list then 500 stack frames, so when stack frames are over a limit, no new stack
frames are created as it run out of stack memory and its called stack overflow error.
in factorial recursive example on line 4, after factorial(n - 1) is calculated its answer is to be multiplied by n ie it contains a recursive call not in tail position ie why it is non tail recursive.
On the other hand, if you look at factorial again, then you'll see that after the call to factorial(n - 1), there is still work to be done, namely, we had to multiply the result of that call with the
number n. So, that recursive call is not a tail recursive call, and it becomes evident in the reduction sequence, where you see that actually there’s a buildup of intermediate results that we all
 have to keep until we can compute the final value. So, factorial would not be a tail recursive function.


tail recursive functions better than non tail recursive functions because tail-recursion can be optimized by compiler.

2 kinds of memory-
1.stack- analogous to RAM
2. heap- new keyword used to create object, analogous to hdd
 */

/*
In TestSort.scala mergeTail everytime result is called recursively is the result = nil default value not updated ? Default value is used when no value is given when a value is there that is used
 */

//Note- Not all recursive functions can be converted into tail recursive. Tail recursive solution is more optimised than normal recursive as it uses the same recursion stack repeatedly instead of
// creating a new one.


