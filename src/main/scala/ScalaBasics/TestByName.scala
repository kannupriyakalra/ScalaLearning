package ScalaBasics

object TestByName extends App {
  def myIf[A](predicate: Boolean, ifTrue: () => A, ifFalse: => A): A = { //made the type Int as A as nothing related to Int was happening in myIf's definition, by making it generic, we could now use it
    //for a boolean returning function 0 on line 32 as well.
    if (predicate) ifTrue() else ifFalse //execution of ifTrue will happen only when its called.
  }


  lazy val a: Int = {
    throw new Exception("Wrong path")
  }

  lazy val b: Int = {
    println("creating 'b'")
    567
  }

  val decision: Boolean = false

  val result: Int = myIf(
    decision,
    ifTrue = () => a,
    ifFalse = b
  )

  assert(result == 567)

  assert {
    myIf(
      result == 567
      , () => {
        println("Hello true!")
        println("Bye true!")
        true
      }
      , {
        println("Hello false!")
        println("Bye false!")
        false
      }
    )
  }

  println("Congratulations ! 'Do what you have to do, until you can do what you want to do.' Oprah Winfrey")


}

/*

Scala Call-by-name Parameters

Call-by-name is how you pass a block of code around, without executing that block of code. eg- ifTrue in line 4
Call-by-name is equivalent to function0.

Call-by-value is when block of code is evaluated first and then its value is passed. eg- predicate in line 4.

There are two ways to give parameters to a function in most programming languages: 'by-value' and 'call-by-name'.

The 'by-value' way is what we have seen in previous SKBs when we used functions and methods.

In this SKB, focus your attention on the way the parameters of the function are being declared and the syntax, notice anything?

Did you notice the => Int in the parameter list?

You can understand it as if it was () => Int, meaning a function that will return Int when called.

The content of the parameter will not be evaluated until needed. Be aware that it will be re-evaluated for each time it is called. But we will look into it into a more advanced SKB later on.
 */
