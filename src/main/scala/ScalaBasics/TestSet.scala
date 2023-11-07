package ScalaBasics

import ScalaBasics.TestBinarySearchTree._

object TestSet extends App {

  //implementation of a Set
  sealed trait MySet[A] {
    def contains(elem: A): Boolean

    def add(elem: A): MySet[A]

    def remove(elem: A): MySet[A]
  }

  //implementation of a Set using list
  case class MySetUsingList[A](l: List[A] = Nil) extends MySet[A] {

    //override def contains(elem: A): Boolean = l.contains(elem)
    override def contains(elem: A): Boolean = listContains(l, elem)

    private def listContains(l: List[A], elem: A): Boolean = {
      l match {
        case head :: tail => if (head == elem) true else listContains(tail, elem)
        case Nil => false
      }
    }

    override def add(elem: A): MySet[A] = if (contains(elem)) this else MySetUsingList(elem :: l)

    override def remove(elem: A): MySet[A] = {
      def go(l: List[A], result: List[A] = Nil): List[A] = {
        l match {
          case head :: tail => if (head == elem) tail ++ result else go(tail, head :: result)
          case Nil => result
        }
      }

      MySetUsingList(go(l))
    }
  }

  //implementation of a Set using Binary Search Tree
  case class MySetUsingBST[A](t: Tree[A] = Empty)(implicit ord: Ordering[A]) extends MySet[A]{
    override def contains(elem: A): Boolean = t.contains(elem)

    override def add(elem: A): MySet[A] = MySetUsingBST(t.insert(elem))//duplicate element case is handled in tree insert, we are creating a new object of MySetUsingBST as set is immutable, also as
    //t.insert will return Tree[A] and we want to return a new set of type MySet

    override def remove(elem: A): MySet[A] = MySetUsingBST(t.delete(elem))
  }


  val x: MySet[Int] = MySetUsingList()
  println(x) //o/p- MySetUsingList(List())
  val y: MySet[Int] = x.add(5).add(6).add(7).add(5)
  println(y) //o/p- MySetUsingList(List(7, 6, 5))
  val z = y.contains(9)
  println(z) //o/p- false
  val z1 = y.add(10)
  println(z1) //o/p- MySetUsingList(List(10, 7, 6, 5))
  val z2 = z1.add(10)
  println(z2) //o/p- MySetUsingList(List(10, 7, 6, 5)), returned same set as element exists
  val z3 = z2.remove(6)
  println(z3) //o/p- MySetUsingList(List(5, 7, 10))
  val z4 = x.remove(6)
  println(z4) //o/p- MySetUsingList(List())
  val z5 = z2.remove(15) //o/p- MySetUsingList(List(5, 6, 7, 10))
  println(z5)


  // implement a method to add element to a set
  def add[A](elem: A, current: MySet[A]): MySet[A] = if (current.contains(elem)) current else {
    current match {
      case MySetUsingList(l) => MySetUsingList(elem :: l)
    }
  }

  println(add(8, y)) //o/p- MySetUsingList(List(8, 7, 6, 5))


  val s: MySet[Int] = MySetUsingBST()
  println(s) //o/p- MySetUsingBST(Empty)
  val l: MySet[Int] = s.add(15)
  println(l) //o/p- MySetUsingBST(Node(15,Empty,Empty))
  val l1: MySet[Int] = l.add(10)
  println(l1) //o/p- MySetUsingBST(Node(15,Node(10,Empty,Empty),Empty))
  val l2: MySet[Int] = l1.add(5)
  println(l2) //o/p- MySetUsingBST(Node(15,Node(10,Node(5,Empty,Empty),Empty),Empty))
  println(l2.contains(5)) //o/p- true
  println(l2.contains(20)) //o/p- false
  println(s.contains(5)) //o/p- false, empty set doesn't contain element

  println(l2.remove(20)) //o/p- MySetUsingBST(Node(15,Node(10,Node(5,Empty,Empty),Empty),Empty)), return same set as 20 doesn't exist
  println(l2.remove(5)) //o/p- MySetUsingBST(Node(15,Node(10,Empty,Empty),Empty)) , removed 5
  println(s.remove(5)) //o/p- MySetUsingBST(Empty), removing from empty set returns empty set
}






/*
implementation of a Set- requires implementation of 3 elements- contains, add, remove

1st implementation of contains using contains method in list.
//override def contains(elem: A): Boolean = l.contains(elem)

//2nd implementation of contains
    override def contains(elem: A): Boolean = {
      def go(l: List[A]): Boolean = {
        l match {
          case head :: tail => if (head == elem) true else go(tail)
          case Nil => false
        }
      }
      go(l)
    }

//3rd implementation of contains
    override def contains(elem: A): Boolean = listContains(l, elem)

    private def listContains(l: List[A], elem: A): Boolean = {
      l match {
        case head :: tail => if (head == elem) true else listContains(tail, elem)
        case Nil => false
      }
    }

implementation of a Set using list- val l: List[A] = Nil this is a default list that we have given as data member to case class so we can use it to implement a set. in val z2 = z1.add(10),
z1= MySetUsingList(List(10, 7, 6, 5)), so list in z1 will be used and not default list as list is sent with object. by default data members in case class are val, so removed val.
l.contains(elem) //can write l.contains as list has it, implemented contains of set using contains of list.
if (contains(elem)) this---- this means when y = x.add(5) contains 5 already then return object of y itself ie the object which called add. As set contains non duplicate elements ie why its important
to check this.
override def add(elem: A): MySet[A] = if (contains(elem)) this else MySetUsingList(elem :: l)----- else MySetUsingList(elem :: l) means --else  when y = x.add(5) is called and x doesn't consist of 5
create an object of y type ie MySet[Int] type ie a object of case class MySetUsingList and add elem in default list by elem :: l. this is because add methods return an object of MySet[A] type.
y is of type MySet[A], MySetUsingList[A] both
val z1 = y.add(10)-- here y= MySetUsingList(List(7, 6, 5)) already and when add is called as 10 is not in y, a new object of MySetUsingList is created where the list in y is prepended with element.

def add[A](elem: A, current: MySet[A])-- add method adds an element to a set current, as we defined it outside trait and case class we have to give i/p set explicitly otherwise the object
on which it is called is the set to which element has to be added, used pattern match as object of set is object of a case class ie set is implemented using case class.

x.add(5).add(6).add(7).add(5)--as set is default immutable so could add chain of methods, each creating a new set.

terminal type/ leaf node type/ concrete type-- class , singleton object, case classes
non terminal type/ non leaf node type- abstract class, trait

doubt- when we implement add method outside case class why do we have to use pattern match why cant we directly create object of case class in else like we did for the add in case class?
like   def add[A](elem: A, current: MySet[A]): MySet[A] = if (current.contains(elem)) current else MySetUsingList(elem :: l) --as l is not accessible, as current: MySet[A] and we send MySetUsingList
there is no way to access l from it. even if we send child class, to extract its value we have to use pattern match. --done

remove-
create recursively using go, remove a element from a list, using pattern match, take inspiration from testList
recursively traverse the list and as it matches head return the tail, while meanwhile collecting previous head elements in a new list.
doubt- when implementing remove method if (contains(elem)) condition without it also its running? as it will traverse the list and when cant find return the list, yes if (contains(elem))  is not requird
to be added as it will anyways check with every element and return the new list ie created even when element is not found--done
elem need not be passed again in go as its already there.

//recursive implementation of remove
//    override def remove(elem: A): MySet[A] = {
//        def go(l: List[A]): List[A] = {
//          l match {
//            case head :: tail => if (head == elem) tail else head :: go(tail)
//            case Nil => Nil
//          }
//        }
//        MySetUsingList(go(l))
//      }
  val z3 = z2.remove(6)
  println(z3) //o/p- MySetUsingList(List(10, 7, 5))
  val z4 = x.remove(6)
  println(z4) //o/p- MySetUsingList(List())
  val z5 = z2.remove(15) //o/p- MySetUsingList(List(10, 7, 6, 5))
  println(z5)


tail recursive implementation of remove-
MySetUsingList(List(10, 7, 6, 5)), elem=6
        go(List(10, 7, 6, 5), Nil)
10 != 6 go(List(7, 6, 5), List(10))
7 != 6  go(List(6, 5), List(7, 10))
6 = 6   List(5, 7, 10)
case Nil => result--if while traversing list reach end then return result
no need to reverse result as order of elements doesnt matter in set, so running remove tail recursive is reversing order of list here due to how we implemented it.


implicit-
when you remove "(implicit ord: Ordering[A])" from MySetUsingBST class, you ll get error in contains, add, remove methods that "No implicits found for parameter ord: Ordering[A]". When we
are calling contains, add, remove methods then we are not explicitly sending ord parameter though it is expected in those methods, this is what implicit means that parameters can go implicitly without
mentioning.


 */