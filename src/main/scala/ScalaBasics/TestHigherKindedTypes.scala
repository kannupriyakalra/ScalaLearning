package ScalaBasics

import scala.collection.LinearSeq

// Every object has a type, the type of 5 is Int, "abcd" has type String.
// What is the type of a type? its called Kind.
// Int has kind *
// String has kind *
// case class Score(i: Int), Score has kind *
// List[Int] has kind *

// Higher kinded types:
// List has kind * -> *, (A -> List[A]), List is a type constructor; which means when we give a type in a list for example Int then List [Int] is made ie a concrete type is made.
// Either has kind * -> * -> *; as either has 2 type parameter so we need them both to make a concrete type.
// Functor has kind (* -> *) -> *

object TestHigherKindedTypes {

  def getFirst[A](inp: List[A]): Option[A] = inp.headOption

  def getFirst[A](inp: Array[A]): Option[A] = inp.headOption

  def getFirst[A](inp: Seq[A]): Option[A] = inp.headOption

  def getFirst[A](inp: Vector[A]): Option[A] = inp.headOption

  // they all can be written once, abstracted, using higher kinded types. F[_] takes either List | Array | Vector,
  // type constructor (takes a type to become complete type, like give String to List to become List[String]), eg- List , Array , Vector

  //F[_] refers Seq or List or Array, this is the way to write type constructor in type arguments. This is how you abstract over type constructors.

  def getFirst[F[_] <: LinearSeq[A], A](inp: F[A]): Option[A] = inp.headOption

  // LinearSeq is common trait of all of them, if we don't put that constraint, then can't use headOption

  //same solution- using inheritance
  def getFirstt[A](inp: LinearSeq[A]): Option[A] = inp.headOption

  //List, Array, Seq are subtype of LinearSeq, wherever LinearSeq is defined as type we can send its subtypes like List, Array, Seq.
  //LinearSeq - Linear means one after another Seq means any collection. Tree is not a LinearSeq as it has 2 branches.


}
