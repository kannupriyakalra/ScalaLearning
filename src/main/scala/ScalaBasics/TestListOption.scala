package ScalaBasics

object TestListOption {

  // returns Some if all elements of input list exist ie they are all Some and none of them is None,
  // returns None if any element of input list is None
  def convert[A](lo: List[Option[A]]): Option[List[A]] =
    if (lo.forall(x => x.nonEmpty))
      Some(lo.map(x => x.get)) //to change content inside option we use map
    else None

  def convertt[A](lo: List[Option[A]]): Option[List[A]] =
    lo match {//here head :: tail is broken in 2 parts as head is of type Option[A] can be Some(h) and None
      case Some(h) :: tail => convertt(tail).map(t => h :: t)//convertt(tail) return type is Option[List[A]] and we need to prepend h inside it so we use map, h :: t, t is List[A] inside option.
      case None :: tail => None
      case Nil => Some(Nil)
    }

  // return True if f is true for all elements, or list is empty
  // return False otherwise
  def forAll[A](ls: List[A])(f: A => Boolean): Boolean = {
    ls.foldLeft(true)((acc, a) => acc && f(a))
  }

  // return True only if f is true for at least one element in the List
  // return False otherwise
  def forAny[A](ls: List[A])(f: A => Boolean): Boolean = { //if list has no element then it should return false
    ls.foldLeft(false)((acc, a) => acc || f(a))
  }

  def main(args: Array[String]): Unit = {

    val inp = List(Some(1), Some(2), Some(3))
    println(convert(inp)) //o/p- Some(List(1, 2, 3))

    val inp2 = List(Some(1), Some(2), None)
    println(convert(inp2)) //o/p- None

    val l1 = List(1, 2, 3)
    val l2: List[Int] = Nil
    println(forAll(l1)(x => x > 0)) //o/p- true
    println(forAll(l2)(x => x > 0))//o/p- true
    println(forAll(l1)(x => x > 1))//o/p- false

    println(forAny(l1)(x => x > 2)) //o/p-true
    println(forAny(l2)(x => x > 2)) //o/p-false
    println(forAny(l1)(x => x > 3)) //o/p-false
  }

}

//(x => x.nonEmpty) this function checks if x is Some, forall runs this function on each element and returns true if al of them return true
//Some(lo.map(x => x.get)) explanation: type of lo is List[Option[A]] and we need to return List[A] ie why inside Some we did lo.map(x => x.get) so for
//every element options value is returned by get function and get is safe to use here as we have already checked all elements are Some, so no exception will be thrown.
