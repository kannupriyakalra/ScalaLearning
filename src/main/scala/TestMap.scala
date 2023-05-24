object TestMap extends App{

  val aMap = Map(1 -> "a", 2 -> "b")

  println(aMap.keys) //o/p- Set(1, 2)
  println(aMap.values) //o/p- Iterable(a, b)
  println(aMap.isEmpty) //o/p- false
  println(aMap.get(1)) //o/p- Some(a)
  println(aMap.get(3)) //o/p- None


}
 /*
 A Map is a collection of key/value pairs where keys are always unique. Scala provides mutable and immutable versions of it. By default, an immutable version of the map is imported.
 keys- Returns an iterable containing all keys of the Map
 values- Returns an iterable containing all values of the Map
 isEmpty- Returns true if the Map is empty
 get- returns an optional value. Its signature in the Map trait is as follows: def get(key: K): Option[V], When the key exists, it returns the value in Some context, whereas if the key does not exist,
 it returns None.
  */