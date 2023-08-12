import scala.annotation.tailrec

//google- how to create threads in scala/implementation of threads in scala/scala multithreading
object TestThreads extends App {

  @tailrec
  def f1(): Unit = { //task made for thread to infinitely print hello
    println("hello")
    f1()
  }

  @tailrec
  def f2(): Unit = { //a recursive infinite loop
    println("hi")
    f2()
  }

  //Thread creation by Extending Runnable Interface ie by creating object of Runnable using anonymous class
  //to create a thread, object of type Thread is made and in that object of type runnable is to be passed as object of type runnable is the task that has to be run by thread, runnable interface
  // has only 1 method run, so run can be implemented using a single abstract method syntactic sugar
  // as runnable is a interface, its object is created using anonymous class and f1() the task to be performed by our thread is given in it.
  //to summarise run method in Runnable interface has to be overriden to create a thread. A thread begins its life inside run() method. Start() invokes the run() method on the Thread object.


  val r1: Runnable = () => f1()// , () => f1() is object of runnable
  val thread1 = new Thread(r1)

  val thread2 = new Thread(() => f1()) // () means unit ie no input

  val thread3 = new Thread(new Runnable {
    override def run(): Unit = f1() //as return type of f1 is unit so it can be assigned to run as that also requires something that has unit return type
  })

  val thread4 = new Thread(() => f2())

  //thread1, thread2, thread3 are equivalent
  thread1.start()
  thread2.start()
  thread3.start()
  thread4.start()



  //a thread is supposed to be given a task, which we are giving as above function f1, f2

  //revise - single abstract method from TestAnonymous.scala,
  // google single abstract method - https://bargsten.org/scala/anonymous-class-and-sam/
  // read- https://www.geeksforgeeks.org/scala-multithreading/
  // what is a thread- https://www.geeksforgeeks.org/thread-in-operating-system/
  //https://www.geeksforgeeks.org/difference-between-process-and-thread/
  //https://stackoverflow.com/questions/5201852/what-is-a-thread-really


}

/*
-there are 3 ways to create object of runnable, shown for thread1, thread2, thread3, all 3 are each other's syntactic sugar. if you click Runnable in thread3 and press option enter it will show
how thread 2 is written by converting to its syntactic sugar.
-command click Thread, in that command click Runnable, in Runnable.java on line 43, "void run();" is mentioned which means to implement Runnable interface run method has to be override and run is a
method which takes unit as input and returns unit as o/p.
-"(new Runnable {
    override def run(): Unit = f1() //as return type of f1 is unit so it can be assigned to run as that also requires something that has unit return type
  })" this expression can be converted to single abstract method ie (() => f1())
-Runnable interface represents an operation that does not return a result. Command click Runnable on line 18 same thing is happening.
- Future is a wrapper over these threads, next topic is future.
- testThreads.scala, command click thread on line 25, in Thread.java, line 1202, Runnable task public Thread(Runnable task) {..}
Runnable task -- task is a object of type Runnable, this is java file, in java this is how object is made.


Terminal type- eg-class, object
Non terminal type- eg-trait(their object can't be created directly, created either by anonymous class or by extending them)

 */
