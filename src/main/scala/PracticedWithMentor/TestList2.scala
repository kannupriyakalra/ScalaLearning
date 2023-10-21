package PracticedWithMentor

object TestList2 extends App {

  //Q1- delete nth element of a list, n-index
  def deleteElem(l: List[Int], n: Int): List[Int] = {
    l match {
      case head :: tail => if (n == 0) tail else head :: deleteElem(tail, n - 1)
      case Nil => Nil
    }
  }

  //Q2- tail recursive version of delete nth element of a list
  def deleteElemTail(l: List[Int], n: Int, result: List[Int] = Nil): List[Int] = {
    l match {
      case head :: tail => if (n == 0) result.reverse ++ tail else deleteElemTail(tail, n - 1, head :: result)
      case Nil => Nil
    }
  }

  //Q3- insert element at nth index of a list
  def insertElem(l: List[Int], n: Int, elem: Int): List[Int] = {
    l match {
      case head :: tail => if (n == 0) elem :: l else head :: insertElem(tail, n - 1, elem)
      case Nil => Nil
    }
  }

  //Q4- tail recursive version of insert element at nth index of a list
  def insertElemTail(l: List[Int], n: Int, elem: Int, result: List[Int] = Nil): List[Int] = {
    l match {
      case head :: tail => if (n == 0) result.reverse ++ (elem :: l) else insertElemTail(tail, n - 1, elem, head :: result)
      case Nil => Nil
    }
  }

  val l1 = List(1, 2, 3, 4, 5)
  println(deleteElem(l1, 1)) //o/p- List(1, 3, 4, 5)
  println(deleteElemTail(l1, 1)) //o/p- List(1, 3, 4, 5)
  println(insertElem(l1, 2, 9)) //o/p- List(1, 2, 9, 3, 4, 5)
  println(insertElemTail(l1, 2, 9)) //o/p- List(1, 2, 9, 3, 4, 5)
}

/*
deleteElem-
traverse list from left to right and find the element to delete and recursively back track and create a new list.
list is either empty or not, if its empty return empty list. if list is not empty, if n is starting index ie 0 we return tail as o/p list ie deleted head.
if n != 0 else runs where we prepend head to o/p of deleteElem(tail, n - 1) ie we are calling deleteElem recursively on tail as new list and index of the element to be deleted as n-1 as we removed
head from list.
val l1 = List(1, 2, 3, 4, 5)
println(deleteElem(l1, 2))
deleteElem(l1, 2)
deleteElem(List(1, 2, 3, 4, 5), 2)
2 != 0  1 :: deleteElem(List(2, 3, 4, 5), 1)
1 != 0  1 :: ( 2 :: deleteElem(List(3, 4, 5), 0))
0 == 0  1 :: ( 2 :: List(4, 5)) as n=0 here we returned tail ie how head got deleted as it was not included in o/p.
List(1, 2, 4, 5)
index of list is from zero as we wrote n==0, if we write n==1 then index of list will be considered from 1
 */

/*
tail recursive deleteElem-
all tail recursive follow same approach as tail recursive of factorial n tail recursive of merge ie the part outside of recursion call is added to result and taken along.
val l1 = List(1, 2, 3, 4, 5), deleteElemTail(l1, 3)
         deleteElemTail( List(1, 2, 3, 4, 5), 3, List() )
3 != 0   deleteElemTail( List(2, 3, 4, 5), 2, List(1) )
2 != 0   deleteElemTail( List(3, 4, 5), 1, List(2, 1) )
1 != 0   deleteElemTail( List(4, 5), 0, List(3, 2, 1) )
0 == 0   List(3, 2, 1).reverse ++ List(5) = List(1, 2, 3, 5)
 */

/*
insertElem-
we are recursively calling insertElem method until n==0 and ie where we prepend the elem to the list. until n!=0 we keep removing head and reducing n to n-1 and recursively calling insertElem,
once n becomes 0, all the heads ie no. before it will be prepended as the o/p list is made.
 */
