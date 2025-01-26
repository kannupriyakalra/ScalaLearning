package ScalaBasics

object TestOption2 extends App {

  // orElse implementation
  def orElse[A](first: Option[A], second: Option[A]): Option[A] = {

    first match {
      case Some(_) => first
      case None => second
    }
  }

  println(orElse(Some(1), Some(2))) //Some(1)
  println(orElse(None, Some(2))) //Some(2)
  println(orElse(Some(1), None)) //Some(1)
  println(orElse(None, None)) //None

  // getOrElse implementation
  def getOrElse[A](first: Option[A], second: A): A = {

    first match {
      case Some(x) => x
      case None => second
    }
  }

  println(getOrElse(Some(1), 2)) //1
  println(getOrElse(None, 2)) //2

}
