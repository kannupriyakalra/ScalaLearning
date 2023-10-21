package PracticedWithMentor

object TestTree extends App {

  sealed trait Tree[+A] {

    //implement map method in Tree trait
    def map[B](f: A => B): Tree[B] = {
      this match {
        case Empty => Empty
        case Node(value, left, right) => Node(f(value), left.map(f), right.map(f))
      }
    }
  }

  case object Empty extends Tree[Nothing]

  case class Node[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

  //tree example, printing tree, creating object of tree
  val t: Tree[Int] = Node(10, Empty, Empty)
  println(t) //o/p- Node(10,Empty,Empty), single element tree
  val t1: Tree[Int] = Node(10, Node(2, Empty, Empty), Empty)
  println(t1)//o/p- Node(10,Node(2,Empty,Empty),Empty)
  val t2: Tree[Int] = Node(10, Node(2, Empty, Empty), Node(7, Empty, Empty))
  println(t2)//o/p- Node(10,Node(2,Empty,Empty),Node(7,Empty,Empty))
  val t3: Tree[Int] = Empty
  println(t3) //o/p- Empty, empty tree

  val n = t3.map(a => a + 1)
  println(n) //o/p- Empty, explicitly updated type of t3 to Tree[Int] from Tree[Nothing] so map function f can resolve.
  val m = t2.map(a => a + 1)
  println(m) //o/p- Node(11,Node(3,Empty,Empty),Node(8,Empty,Empty))

}

/*
tree-
tree - node of a tree has element, leaf means empty. example- for a node 5 having 2 on left and 6 on right, on left and right of 2 there is empty ie 2 leaf, on left and right of 6 there is empty ie
2 leaf. Replaced Leaf by empty to not get confused. How to create object of tree? we have 2 implementations use one based on type of tree you want to create Empty or Node.
this is a normal tree in which data can be added in any order, lets make binary tree now in testBinary.scala

printing a tree-
as Node and Leaf are of type case classes ie why we could use println directly to print tree as case class have println that has a inbuilt toString. We couldn't use println to print array directly as array didn't
have a case class so we had to print each element.

generic type parameters doubts-
What is the meaning of [B] after map(that we are giving a generic type i/p of type B to map function) and
why is it B and not A in map(A is already an i/p to map as its part of trait Tree, so map has 2 i/p type generic parameters A and B),
why is Tree[B] returned and not Tree[A] (Tree[B] is returned as map converts A to B.)
why map[A,B] is not written only map[B] is written(as map is implemented in type Tree[+A] so no need to mention A again as its declared above)
why Tree[A,B] is not written only Tree[A] (as we ll give B wherever required like in map, also because by definition elements of a tree is of a single type A and not 2 types A, B)
type parameter [A] doesn't come in front of object in "object Leaf extends Tree[Nothing] "as object is singleton object it exists in real, it cannot be generic.

map definition-
map method means apply function f to change the type inside from A to B eg convert option[A] to option[B].

map implementation-
Hint-Tree is just like list with two tails, Check list map implementation we did.
map means apply a function f to every node of tree. Tree has 2 types of element Node or Leaf.

What should be returned incase of Leaf?
Leaf has no data, its empty. When map is called on Leaf it returns Leaf or empty same thing as when we
call f function on empty then we get empty output. If a tree is leaf, there is no meaning of map as tree is empty, so we have to return a Tree[B], we return Empty as its a object of type Tree[Nothing] and
as per covariance , Tree[A], Tree[B], Tree[Int], Tree[String], Any are all supertypes of Tree[Nothing], Empty is a object of Tree[Nothing], value inside Tree[A] ie A is covariant, so when map converts it to make
Tree[B] B is also covariant ie the generic type inside tree is covariant, so as Nothing is a subtype of every type and A is covariant, so Empty is a object of Tree[B] also.

we returned Empty which is object of Tree[Nothing] but map s return type is Tree[B], how is that possible as Nothing is a subtype of every type and type inside Tree[A] is covariant, A means generic A can
take any type like B, when A converts to B then also Tree[B] is also covariant so we can write Empty.

If A in Tree[+A] is covariant then after using map on this tree when we get Tree[B] then B is also covariant, type inside of Tree is covariant even after applying operation map.

Nothing is a subtype of all types. Leaf is of type Tree[B], Tree[Nothing].

When map is called on Node val m = t2.map(a => a + 1) , a new object of Node is created where function f is applied on each element, f(value) calls f on current value of Node, left.map(f) calls map
on left subtree as passed f, right.map(f) calls map on right subtree recursively, trust the recursion and finally a Tree[B] is returned.
Implementing map on a tree means if we have a object of type Tree[Int], then we ll use a map method with a function f to convert Tree[Int] to Tree[String] for example.
this means of object of current type, its type here is Tree[A] write this match and select exhaustive from intellij autocorrect.

value: A, left: Tree[A], right: Tree[A] , we have to return Tree[B], Tree[B] has 2 variations Leaf or Node, can't return Leaf as logically wrong, so we return Node. apply f on value as value is of type A,
left.map(f)-- as left s type is Tree[A] and on Tree[A] type we have a map method available that can be called on left.
we got a new left, new right (: Tree[B]) and new value of type B, what should we do with these 3? create a new node of these 3 as return type of tree is either Node or leaf and here we are implementing
map on node. wrap all these 3 in a new node, why? as map s return type is Tree[B] and Tree[B] s object can be created either by creating object of type Leaf or Node.
left.map(f)-this is recursion called inside object

Node(f(value), left.map(f), right.map(f))-- call map recursively on left and right and current value is converted to f right away and using all these 3 we created a new node, trust the recursion,
recursion will be called on left and further on its left n right and when all 3 values will be available then whole object be created.

 */
