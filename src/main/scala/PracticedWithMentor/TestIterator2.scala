package PracticedWithMentor

import java.util.UUID

//use of Iterator
object TestIterator2 extends App{

    //use of Iterator

    val v: Iterator[Int] = Iterator(5, 1, 2, 3, 6, 4)
    println(v.size) //o/p- 6
    println(v.length) //o/p- 6
    while (v.hasNext) print(v.next) //o/p-5123645

    println()

    //when size/length of iterator is checked after iterating it once, it comes as zero because a iterator can be traversed only once.
    println(v.size) //o/p- 0
    println(v.length) //o/p- 0


    //Defining an iterator for a collection(Arrays, Lists, etc)
    val v2 = Array(5, 1, 2, 3)
    val i: Iterator[Int] = v2.iterator
    while (i.hasNext)
      print(i.next + " ") //o/p- 5 1 2 3

    println()

    //Ways to access elements of Iterator- using while, foreach, for

    val v3 = List(15, 11, 22, 33, 66, 44)
    val i2: Iterator[Int] = v3.iterator
    while (i2.hasNext)
      print(i2.next + " ") //o/p-15 11 22 33 66 44

    println()
    /*
    uncomment one at a time to see execution because a iterator can be traversed only once- other ways to print all elements returned by an iterator
    i2 foreach println //o/p-15 11 22 33 66 44
    i2.foreach(println) //o/p-15 11 22 33 66 44

    for(k <- i2) print(k + " ") //o/p-15 11 22 33 66 44

    An important part of using an iterator is knowing that it’s exhausted after you use it. As you access each element, you mutate the iterator, and the previous element is discarded.
    For instance, if you use foreach to iterate over an iterator’s elements, the call works the first time. But when you attempt the same call a second time, you won’t get any output, because the iterator has been exhausted.
     */

    val it: Iterator[String] = Iterator("a", "number", "of", "words")
    val it2: Iterator[Int] = it.map(_.length) // it.map(_.length) same as it.map(x => x.length)
    it2 foreach println
    //o/p-
    //1
    //6
    //2
    //5

    val feeder: Iterator[Map[String, String]] = Iterator.continually(Map("random" -> UUID.randomUUID().toString, "postcode" -> "NW44SZ"))
    val x: Map[String, String] = feeder.next
    val y = feeder.next
    println(x) //o/p-Map(random -> 849a23ef-2d90-4e54-8e27-19b60f92f057, postcode -> NW44SZ)
    println(y) //o/p-Map(random -> 47021315-ae9c-420a-a89b-8749e5933ebe, postcode -> NW44SZ)
}


/*
-Iterators are data structures that allow to iterate over a sequence of elements. They have a hasNext method for checking if there is a next element available, and a next method which returns the next element and advances the iterator.
An iterator is mutable: most operations on it change its state. While it is often used to iterate through the elements of a collection, it can also be used without being backed by any collection (see constructors on the companion object).
-An iterator is not a collection, but rather a way to access the elements of a collection one by one.
-operations on Iterator are lazy.A lazy operation does not immediately compute all of its results. Instead, it computes the results as they are individually requested.
-Iterators are generally not used except when you’re working with very large files(or datasets), it’s not practical to read the entire file into memory.An important part of using an iterator is
 knowing that it’s exhausted after you use it. As you access each element, you mutate the iterator, and the previous element is discarded.
 */

/*
-go through scala doc by command clicking iterator.
-https://www.geeksforgeeks.org/iterators-in-scala/ full read
-https://www.tutorialspoint.com/scala/scala_iterators.htm full read
-https://alvinalexander.com/scala/how-to-use-iterators-collections-classes-scala-cookbook/ full read
-https://docs.scala-lang.org/overviews/collections-2.13/iterators.html skimmed read
 */
