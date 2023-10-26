package PracticedWithMentor

import TestBinarySearchTree._ //for importing Tree[K, V] in line 13 case class
object TestMap extends App {

  //implementation of a Map
  trait MyMap[K, V] { //keys are unique in Map.They form a set in Map.

    def get(k: K): Option[V]

    def put(k: K, v: V): MyMap[K, V] //put method puts key, value in a existing Map that we are in ie MyMap and return a new object MyMap as its a immutable type.

    def +(kv: (K, V)): MyMap[K, V] = put(kv._1, kv._2) //+ is same as put

  }

  //implementation of a Map using Binary Search tree

  //t: Tree[Tuple2[K, V]] its synctatic sugar is t: Tree[(K, V)]

  case class MyMapUsingBST[K, V](t: Tree[(K, V)] = Empty)(implicit ord: Ordering[K]) extends MyMap[K, V] { //ordering is of only type key ie K as by definition only keys are non duplicate n important
    override def get(k: K): Option[V] = t.searchBy(p => p._1, k).map(p => p._2) //find key in tree and return the value next to it

    // t.searchBy(p => p._1, k)  its o/p is Option[(K,V)] ie whole element of tree, from that find only value using map

    implicit val ordKV: Ordering[(K, V)] = Ordering.by(p => p._1) //here we have ord above which we pass implicitly to by and Ordering[K] is converted to Ordering[(K, V)] where V is assigned
    // internally as K as value is never used

    override def put(k: K, v: V): MyMap[K, V] = MyMapUsingBST(t.insert((k, v))) //(ordKV) is written for my understanding, it can be skipped

    // t.insert((k, v) it o/p is Tree[(K, V)], when that tree is given in MyMapUsingBST, its object gets created and ie what put does ie creates a new map which we created by our own map.
  }

  val x: MyMap[Int, String] = MyMapUsingBST()
  val x1 = x.put(10, "ten") // o/p- MyMapUsingBST(Node((10,ten),Empty,Empty))
  println(x1)
  val y = x.put(1, "one").put(2, "two").put(3, "three")
  println(y) //MyMapUsingBST(Node((1,one),Empty,Node((2,two),Empty,Node((3,three),Empty,Empty))))
  println(y.get(1)) //Some(one)
  println(y.get(5)) //None


  //hw- implement MyMap using list of type k,v. each element of list is a pair ie tuple- take inspiration from MySetUsingList

  case class MyMapUsingList[K, V](l: List[(K, V)] = Nil) extends MyMap[K, V] {
    override def get(k: K): Option[V] = listSearch(l, k)


    private def listSearch(l: List[(K, V)], elem: K): Option[V] = {
      l match {
        case (k, v) :: tail => if (k == elem) Some(v) else listSearch(tail, elem)
        case Nil => None
      }
    }

    //remove the element where key is k from the list.
    private def listRemove(l: List[(K, V)], elem: K): List[(K, V)] = {
      l match {
        case (k, v) :: tail => if (k == elem) tail else (k, v) :: listRemove(tail, elem)
        case Nil => Nil
      }
    }

    override def put(k: K, v: V): MyMap[K, V] = MyMapUsingList((k, v) :: listRemove(l, k)) // it removes the key value pair from list first using this -listRemove(l, k) and then prepend the new pair
    // using  :: and create a new object
    // if key exist then there will be a value with it and we dont want that value we need this new value, if key
    //exist or doesn't exist regardless it inserts, if key exists then it overrides that key value pair
  }

  val m: MyMap[Int, String] = MyMapUsingList()
  val n = m.put(4, "four").put(5, "five").put(6, "six")
  println(m) //o/p- MyMapUsingList(List())
  println(n) //o/p- MyMapUsingList(List((6,six), (5,five), (4,four)))
  println(n.get(4)) //o/p- Some(four)
  println(n.get(7)) //o/p- None


  val aMap = Map((1, "a"), 2 -> "b")

  println(aMap.keys) //o/p- Set(1, 2)
  println(aMap.values) //o/p- Iterable(a, b)
  println(aMap.isEmpty) //o/p- false
  println(aMap.get(1)) //o/p- Some(a)
  println(aMap.get(3)) //o/p- None
  println(aMap.+((3, "c"))) //Map(1 -> a, 2 -> b, 3 -> c), + means put, - means delete


}
/*
A Map is a collection of key/value pairs where keys are always unique. Scala provides mutable and immutable versions of it. By default, an immutable version of the map is imported.
keys- Returns an iterable containing all keys of the Map
values- Returns an iterable containing all values of the Map
isEmpty- Returns true if the Map is empty
get- returns an optional value. Its signature in the Map trait is as follows: def get(key: K): Option[V], When the key exists, it returns the value in Some context, whereas if the key does not exist,
it returns None.
 */