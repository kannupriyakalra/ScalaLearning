import TestBinarySearchTree.Tree.minimum

object TestBinarySearchTree extends App {

  sealed trait Tree[+A] {

    //Q- insert a element in a binary search tree, when element already exist we are not inserting the element
    def insert[B >: A](elem: B)(implicit ord: Ordering[B]): Tree[B] = { //ordering is sent to compare the value inside generic types ie A and B, here int ordering is going implicitly as insert is
      //called on Tree[Int], so inside Tree value are compared to know where to add element in tree while inserting.
      this match {
        case Empty => Node(elem, Empty, Empty)
        case Node(value, left, right) =>
          if (ord.equiv(elem, value)) this //not inserting duplicates, case where element exist
          else if (ord.lt(elem, value)) Node(value, left.insert(elem), right) //we ll keep inserting recursively until Empty node is found and there base case is run and new node is inserted.
          else Node(value, left, right.insert(elem))
      }
    }

    // delete a element from a binary search tree
    def delete[B >: A](elem: B)(implicit ord: Ordering[B]): Tree[B] = {
      this match {
        case Empty => Empty //if tree is empty, you cant delete anything
        case Node(value, left, right) => //if tree is non empty, find that element
          if (ord.equiv(elem, value)) { // if elem = value, found the element so delete it, so we inspect element s children to see how to delete it
            (left, right) match { //here left is left of value and right is right of value
              case (Empty, Empty) => Empty
              case (Node(_, _, _), Empty) => left // left is of type Tree[A], so left is returned when elem matches value ie Node(value, left, right) = left
              case (Empty, Node(_, _, _)) => right
              case (Node(_, _, _), Node(_, _, _)) => // Node(_, _, _) replaced by _ as internal values inside Node are not used in right part of expression
                minimum(right: Tree[B]) //find minimum element on right subtree(as that is successor of root) using minimum method that returns an Option that has minimum value ie min and on that min value we called map as its wrapped in option
                  .map(min => Node(min, left, right.delete(min))) // to delete value we updated it with min and deleted min from right subtree, this map method is running on an option so changing its value and returning an option ie why we are adding getOrELse whose get is running and returning the value inside option. Map returns Option(node) and getOrElse is called on that Option
                  .getOrElse(Empty) //just to get rid of option type otherwise this is useless because right is not empty and minimum will return Some(min) value
            }//we know right subtree is not empty as we are in case where left n right subtree exist, so getOrElse s else will not be called, getOrElse means if minimum value is found get is called otherwise else is called and an Empty node is created. for get case node is created in map and for that when get of getOrElse is called we return A and not Option[A] so we used this method to get rid of option.
          }// // return type of min is Option[min], return type of map is Option[Node]
          // return type of getOrElse is Node
          else if (ord.lt(elem, value)) Node(value, left.delete(elem), right) //if elem< value, get inside left subtree to find element
          else Node(value, left, right.delete(elem)) //if elem > value, get inside right subtree to find element
      }
    }
//we have to delete a element whose left n right subtree is there so to delete it find minimum element on right subtree(as that is successor of root) using minimum method and replace it with
// current element so current element gets deleted
  }

  // minimum(right: Tree[B]) means calling minimum on right subtree and it returns minimum value from right subtree min: Option[A] ie a value of type A not entire node that has that value, that
  // value is of type Option[A] as minimum value is nullable when tree is empty

  object Tree {

    def minimum[A](t: Tree[A])(implicit ord: Ordering[A]): Option[A] = { //A used as Tree is of type A and it can be Empty also.
      t match { // Node(value, left, _) , we replaced right here with _ as in the right side of => expression right is not used so we chose not to give a variable name here
        case Empty => None
        case Node(value, left, _) => minimum(left).orElse(Some(value))
      } //minimum value can exist in left subtree or current value, it cannot be right subtree so replaced it with _, called recursively minimum on left subtree by minimum(left), if it gets minimum
      //then return that else call orElse method and return the current value.
    }//find min of a bst as its a bst min can be in left subtree only
    //case Node(value, left, _) => minimum(left).orElse(Some(value))---_ means pattern match me vo value use ni krni hai, means dont compare right in pattern match as in the right side of => right is not used
    //recursively find min of left sub tree and if left subtree is empty it goes in OrElse and in that case answer is current value, so wrap that value in Some as OrElse take Option
    //minimum(left) returns Option[A], its None when left subtree is empty and in that case value is min value

  }

  case object Empty extends Tree[Nothing]

  case class Node[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]


  val t: Tree[Int] = Node(10, Node(5, Empty, Empty), Node(15, Empty, Empty))
  val i = t.insert(3)
  println(i) //o/p- Node(10,Node(5,Node(3,Empty,Empty),Empty),Node(15,Empty,Empty))

  // insert an element that already exists
  val i2 = t.insert(5)
  println(i2) //o/p- Node(10,Node(5,Empty,Empty),Node(15,Empty,Empty))

  //insert element in a empty tree
  println(Empty.insert(5)) //o/p- Node(5,Empty,Empty)

  println(Empty.insert(5).insert(7).insert(3).insert(4)) //o/p- Node(5,Node(3,Empty,Node(4,Empty,Empty)),Node(7,Empty,Empty))

  val t2: Tree[Int] = Empty
  val d = t2.delete(10) //deleting from empty tree
  println(d) //o/p- Empty

  val t3 = Node(10, Empty, Empty)
  val d2 = t3.delete(10)
  println(d2) //o/p- Empty

  println(Empty.insert(5).insert(7).insert(3).delete(5)) //o/p- Node(7,Node(3,Empty,Empty),Empty)
}

/*

Definition of binary search tree- All values in the left subtree are less than the current value. All values in the right subtree are greater than the current value.
binary tree- normal tree without above constraint.


covariant concept rule-
on def insert(elem: A): Tree[A] = ??? red line under elem and getting error- Covariant type A occurs in contravariant position in type A of value elem. We got the same error when we implemented getOrElse
in testOption. ie  def getOrElse[B >: A](b: B): B  why we introduced type B here why we didn't just write [A] instead of [B >: A] like def getOrElse(b: A): A , it shows same error ie
Covariant type A occurs in contravariant position in type A of value.
As type A is covariant and elem is also of type A so if type A in argument, rule is function i/p are contravariant and o/p is covariant, so type A being covariant cannot occur in contravariant position.
So we have to make type B which is neither covariant nor contravariant so B can go as argument of function in input.
Here- Covariant type A cannot occur in contravariant position as input that's why we introduced another type parameter B and constraint it to be a supertype of A.
For a Tree[Int] type B can be Int, Any, as it can be int or its super types so int is being added so adding [B >: A] instead of [A] is just a workaround. In our mind we can think of type B as A only
as in type B we can give A also as B is supertype of A. when calling insert, delete methods, type of element given as argument ie of type B should be a supertype of A.
updated def insert(elem: A): Tree[A] = ???---to ---def insert[B >: A](elem: B): Tree[A] = ???
return type of tree will now be Tree[B] as B>:A so Tree[B] >: Tree[A] as type A is covariant and its the type parameter inside tree so type hierarchy will follow that ie why Tree[A] is also covariant.
tree me jo type hai vo covariant hai . as we insert element of type B so whole tree becomes of type B, elements in a tree all are of same type.

insert-
when tree is empty and a element has to be inserted- use node object as node has element. sent 2 empty as left n right are empty.
when tree is non empty-when current elem is < value in current element- insert in left sub tree else in right subtree
Node(value, left.insert(elem), right) else Node(value, left, right.insert(elem))-- this will keep happening until case Empty
when insert is called on a tree with an element as argument, element will keep getting compared to value in a node assuming for Tree[Int] if elem is < value it will go in left subtree else right subtree
and it will keeping going inside tree recursively until it finds its place and element is added when case Empty is run after going to that position.

case Node(value, left, right) => if(true) Node(value, left.insert(elem), right) else Node(value, left, right.insert(elem))
        if(true) instead of true write condition to compare elem and value, as A and B are not always int, so we have to write a generic condition
        command click Ordering trait, see methods, hw- understand this trait, use ord to compare element n value, use ord.methodname , this trait is a comparison trait for 2 generic types, understand ordering
        and complete insert, then make a tree and run it

val i = t.insert(3)(int) --means here (int) is implicit shown only when implicit hints are on intellij, here we have implemented Tree[A] ie generic tree so when we make a Tree[Int] we are implicitly
sending (int) as ordering which is going in insert method parameter " (implicit ord: Ordering[B])" ord = int and in Ordering trait "ord.equiv(elem, value)" and "ord.lt(elem, value)" input to equiv method
and lt(less than) method will be of type int, command click methods to see. elem and value are compared and are told compiler they are of type int so use int version of the generic methods made in
Ordering trait.

if (ord.equiv(elem, value)) this--means if element = value ie element already exist in tree return the tree as it is on which insert is called without adding any element.
else if (ord.lt(elem, value)) Node(value, left.insert(elem), right)--means if elem<value, call insert recursively on left subtree else on right subtree.

" case Node(value, left, right) =>
  if (ord.equiv(elem, value)) this //not inserting duplicates, case where element exist
  else if (ord.lt(elem, value)) Node(value, left.insert(elem), right)
  else Node(value, left, right.insert(elem))  "                          this case Node is used to recursively find where to insert element ie to traverse the tree as soon as that place is found in a
  tree Case Empty is used to insert element.


delete-
2 tasks have to be done: 1.find element to be deleted 2. delete it.

delete has 3 cases:
1. Node to be deleted has no children. - You are on the node to be deleted after traversing tree and it has no children so replace it with empty. case (Empty, Empty) => Empty ie
Node(value, left, right) = Empty
2. Node to be deleted has 1 child.
1st case- case (Node(_, _, _), Empty)
left = Node(_, _, _) --here _ means we don't care about value insides, we only care its non empty subtree on left
Node(value, left, right) = left , so node gets deleted and left subtree takes its place
2nd case- case (Empty, Node(_, _, _))
Node(value, left, right) = right
3. Node to be deleted has 2 children.
Node(value, left, right) = Node(min, left, right.delete(min)) , replaced value with minimum element from right subtree, keep left subtree as it is,
right.delete(min)-- delete min from right subtree as it has replaced the value to be deleted.

https://www.geeksforgeeks.org/deletion-in-binary-search-tree/
Node to be deleted has two children:
inorder successor- smallest value in right subtree (why right sub tree as we want to replace the value of inorder successor with the node to be deleted keeping left subtree of the node as it is so the
element that has to replace node should be greater than left one and current node one)
find inorder successor and delete that and put its value in the current node
doubt -how will other nodes of right subtree be rearranged based on replacing the deleted node with inorder successor?--via recursion

When tree is empty: val t2: Tree[Int] = Empty, val d = t2.delete(10)--when tree is empty this match case Empty => Empty, a Empty tree is returned as noting can be deleted from a empty tree.
When tree is non empty:


    if elem == value, found element to be deleted
    {4 subcases:
    left = Empty, right = Empty (ie its last node)--return empty, replace current node ie found Node(value, left, right) by empty
    left = Element, right = Empty, after deleting the value we return left subtree as right has nothing, case (Node(v1, l1, r1)), Empty) => ??? replace by Node(_, _, _) as v1, l1, r1 are not used we
     returned left subtrree as it is. Replace current node ie found by left subtree so it gets deleted.
    left = Empty, right = Element
    left = Element, right = Element ---replace value with the min element of right subtree and delete the node where value=min from right subtree
    (or max element of left subtree and then delete that from left subtree, this is 2nd approach)
    }
    elem< value, recursively delete from left sub tree
    elem >value, recursively delete from right sub tree

    Node(value, left.delete(elem), right)-- only use to traverse tree, to find element
    if (ord.equiv(elem, value))--delete happens here

    case Node(value, left, right) =>
          if (ord.equiv(elem, value)) {
            (left, right) match {---------means for Node(value, left, right) , value =elem, so check below 4 cases based on this left, right here


Ordering-
example of integer implementation of ordering trait:
in TestSort.scala , line 13     l1.concat(l2).sorted, turn implicit hints on in intellij ie  l1.concat(l2).sorted(int) , command click on sorted, int is implicitly sent to sorted, it means when you
sort a list you have to tell the ordering of elements of a list ie how do you compare 2 elements of a list s generic type, here ordering is Int
in StrictOptimizedSeqOps.scala,  override def sorted[B >: A](implicit ord: Ordering[B]): C = super.sorted(ord)--- (implicit ord: Ordering[B]) is (int), ord= int, super.sorted(int), command click again on this sorted
in Seq.scala, read line 706 to 735, see ord = Int in this example is used in sorted implementation Int.asInstanceOf[Ordering[AnyRef]], B is Int here, as sorted method is written for generic types ie why
we sent ordering implicitly ie int.

ordering is a trait for "comparing" all generic types and it has methods written like equiv, lt, etc for comparison, like 2 objects of person type so to compare them if they have height we can say ordering
is based on height ie compare based on height. in our tree example ordering is of type Int, ie why are telling methods to be used of type int. Turn implicit hints on to see this.

ordering is a trait to model the behaviour of comparison for generic types like list, tree where type can be Int, String. it has a method to compare 2 object of types, comparison behaviour is explicitely
sent by making our own compare for that type based on that type-- see TestOrdering.scala as in our tree example we made Tree[Int] so ordering is sent of int type, so the methods inside ordering are run for
int type.

Scala defines a kind of abstraction which is used to compare two values. In java this interface is called Comparator and in scala it’s called Ordering.
An Ordering[T] is implemented by specifying the compare method, compare(a: T, b: T): Int, which decides how to order two instances a and b.


*/