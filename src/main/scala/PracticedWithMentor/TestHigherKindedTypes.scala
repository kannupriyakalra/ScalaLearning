package PracticedWithMentor

import scala.collection.LinearSeq

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
