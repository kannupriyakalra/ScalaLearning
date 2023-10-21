package PracticedWithMentor

import java.util.UUID
import scala.None
//implementation of Iterator- ListIterator(to provide the ability to iterate over a list), OptionIterator(to provide the ability to iterate over a option)
//implement a method to print all elements of a iterator
//implement continually-hint-use anonymous class, copy signature from continually scala doc


trait MyIterator[A] {
  def hasNext: Boolean

  def next: A //next returns the element and takes control to next
}

case class ListIterator[A](var ls: List[A]) extends MyIterator[A] {
  override def hasNext: Boolean = ls.nonEmpty
  //hasNext returns true if list has more elements. hasNext tells if iterator has more elements or not. hasNext is true when list is not empty ie can use !ls.isEmpty
  // isEmpty tells if list is empty or not.

  override def next: A = { //next returns next element of iterator.
    val result = ls.head
    ls = ls.tail //update list so second time next is called same element is not returned.
    result
  }
}

case class OptionIterator[A](var o: Option[A]) extends MyIterator[A] {
  override def hasNext: Boolean = o.nonEmpty

  override def next: A = {
    val result = o.get
    o = None
    result
  }
}


case object MyIterator {
  def fromList[A](ls: List[A]): MyIterator[A] = ListIterator(ls) //ListIterator(ls) is object of ListIterator class and as it extends MyIterator[A] its of type MyIterator[A] too.

  def fromOption[A](o: Option[A]): MyIterator[A] = OptionIterator(o)

  //implement a method to print all elements of a iterator
  def printAll[A](itr: MyIterator[A]): Unit = {

    if (itr.hasNext) {
      println(itr.next)
      printAll(itr)
    }
  }
  //[A] is called type parameter

  def continually[A](elem: => A): MyIterator[A] = new MyIterator[A] {
    override def hasNext: Boolean = true

    override def next: A = elem
  }

  //(elem: => A) is function 0 which gives A for no input
  /*
  continually- Creates an infinite-length iterator returning the results of evaluating an expression. The expression is recomputed for every element.
  Params:
  elem – the element computation.
  Returns:
  the iterator containing an infinite number of results of evaluating elem.
   */

}

object TestIterator extends App {

  val l1 = List(10, 20, 30)

  val itr: MyIterator[Int] = MyIterator.fromList(l1)

  println(itr.next) //o/p- 1
  println(itr.next) //o/p- 2
  println(itr.next) //o/p- 3
  //println(itr.next) gives exception as no element ie why use if(itr.hasNext) before calling next

  if (itr.hasNext) println(itr.next) else println("no more elements in the list") //o/p- no more elements in the list


  val o1 = Some(1)

  val itro = MyIterator.fromOption(o1)

  println(itro.next) //o/p- 1
  // println(itro.next) // o/p- NoSuchElementException: None.get

  if (itro.hasNext) println(itro.next) else println("no more elements in the option") //o/p- no more elements in the option

  val itr2: MyIterator[Int] = MyIterator.fromList(l1) //created again as itr got exhausted by calling in line 55-57

  val x = MyIterator.printAll(itr2)

  //o/p-10
  //20
  //30

  val y: MyIterator[Int] = MyIterator.continually(5)
  //MyIterator.printAll(y) //prints 5 inifinetly

  val feeder: MyIterator[Map[String, String]] = MyIterator.continually(Map("random" -> UUID.randomUUID().toString, "postcode" -> "NW44SZ"))
  val x1: Map[String, String] = feeder.next
  val y1 = feeder.next
  //MyIterator.printAll(feeder) //prints Map(random -> ce1e33b9-ca79-4696-bed4-cee5cf46a9ca, postcode -> NW44SZ) infinitely

}


//Iterator is a way of iterating through collection. It is mutable ie why usually not used.
//ListIterator is mutable ie why (var ls: List[A])  var used.


