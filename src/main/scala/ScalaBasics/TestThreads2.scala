package ScalaBasics

/* undertanding setName, getName, setPriority, getPriority thread methods
 from   //read https://blog.knoldus.com/things-to-know-about-multithreading-in-scala/ in this link thread is created wrongly so o/p are wrong ie the
 right way - var thread1= new SetPriorityGetPriority() , i corrected n executed below program.
 https://prwatech.in/blog/scala/scala-multithreading/
 */

object TestThreads2 extends App{

  class SetPriorityGetPriority extends Thread {

    override def run(): Unit = {
      for (i <- 1 to 3) {
        println(this.getName)
        println(this.getPriority)
        Thread.sleep(400)
      }
    }
  }

    var thread1= new SetPriorityGetPriority()
    var thread2= new SetPriorityGetPriority()
    thread1.setName("One")
    thread2.setName("Two")
    thread1.setPriority(Thread.MIN_PRIORITY)
    thread2.setPriority(Thread.MAX_PRIORITY)
    thread1.start()
    thread2.start()
}

/*
o/p-
Two
One
10
1
Two
10
One
1
One
1
Two
10
 */
