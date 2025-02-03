package ScalaBasics

object testTraits {
  trait A {
    def foo = "A"
  }
  // defined trait A

  trait M {
    def foo = "M"
  }
  // defined trait M

  trait B extends A {
    override def foo = "B" + super.foo
  }
  // defined trait B


  trait C extends M {
    override def foo = "C" + super.foo
  }
  // defined trait C

  class D extends B with C {
    override def foo = "D" + super.foo
  }
  // defined class D

  def main(args: Array[String]): Unit = {
    val oD = new D

    println(oD.foo)
  }
}



