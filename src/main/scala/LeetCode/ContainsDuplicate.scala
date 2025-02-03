package LeetCode

//https://leetcode.com/problems/contains-duplicate/description/
object ContainsDuplicate extends App {

  // Solution 1-

  //  def containsDuplicate(nums: Array[Int]): Boolean = { //time complexity = O(n2), space complexity = O(1)
  //    for (a <- nums) {
  //      var counter = 0
  //      for (b <- nums) {
  //        if (a == b) counter = counter + 1
  //        println("a: " + a + " b: " + b + " counter: " + counter)
  //        if (counter > 1) return true
  //      }
  //    }
  //    false
  //  }

  // Solution 2-

  //  def checkContainsDuplicate(a: Int, s: scala.collection.mutable.Set[Int]): Boolean = { //time complexity = O(1) , space complexity = O(1)
  //
  //    if (s.contains(a)) true
  //    else {
  //      s.add(a)
  //      false
  //    }
  //
  //  }
  //
  //  def containsDuplicate(nums: Array[Int]): Boolean = { //time complexity = O(n), space complexity = O(n) as we are maintaining a set.
  //
  //  this is implementation of 'toSet' ie conversion of array to set
  //
  //    val s = scala.collection.mutable.Set[Int]()
  //
  //    for (a <- nums) {
  //      if (checkContainsDuplicate(a, s)) return true
  //    }
  //    false
  //  }

  // Solution 3- Best

  def containsDuplicate(nums: Array[Int]): Boolean = { //time complexity = O(n) as its using Hash set, space complexity = O(n)

    val s: Set[Int] = nums.toSet
    s.size != nums.length

  }

  // Solution 4-

//    def containsDuplicate(nums: Array[Int]): Boolean = { //time complexity = O(n) , space complexity = O(n)
//
//      //converting array into set using foldLeft
//      val s = nums.foldLeft(Set[Int]())((s, a) => s + a)
//      s.size != nums.length
//
//    }

  // Solution 5-

//  def containsDuplicate(nums: Array[Int]): Boolean = { //time complexity = O(n) , space complexity = O(n)
//
//    //converting array into set using foldLeft while checking the duplicate found, after duplicateFound= true, no further element is added to set.
//    val initial: (Set[Int], Boolean) = (Set(), false)
//    val duplicateFound = (nums.foldLeft(initial) { case ((set, duplicateFound), a) =>
//      if (duplicateFound || set.contains(a)) (set, true)
//      else (set + a, false)
//    })._2
//    duplicateFound
//
//  }

  println(containsDuplicate(Array(1, 2, 3, 1)))
  println(containsDuplicate(Array(1, 2, 3, 4)))
  println(containsDuplicate(Array(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)))

}


/*

Space and Time Complexity Analysis:

  // Solution 1-

The space complexity of the provided method containsDuplicate is O(1), which means it is constant with respect to the size
of the input array (nums). This is because the method only uses a constant amount of additional space regardless of the size
 of the input.

Let's analyze the space usage:

Input Space: The input array nums is used, but its space complexity is not considered in the analysis, as it is part of the input.

Additional Space: The only additional variables used are a, b, and counter. These variables do not depend on the size of the input array; they are used independently of the array size. Therefore, the space complexity is constant, or O(1).

It's worth noting that the time complexity of this method is O(n^2), where n is the length of the input array, due to the nested
loop structure. The method checks for duplicates by comparing each element with every other element in the array, leading to a
quadratic time complexity. This can be inefficient for large arrays. If efficiency is a concern, you might consider alternative
approaches to check for duplicates in linear time, such as using a HashSet.

  // Solution 2-

  The space complexity of the provided method containsDuplicate is O(n), where n is the length of the input array (nums).

Here's the breakdown:

Input Space: The input array nums is used, and its space complexity is considered to be O(n), where n is the length of the
 array.

Additional Space: The method creates a mutable Set (s) to keep track of unique elements. The space complexity of creating
this Set is O(m), where m is the number of unique elements in the array. In the worst case, where all elements are unique,
 m would be equal to n (the length of the array).

Additionally, the checkContainsDuplicate method uses a single integer (a) and the mutable Set (s). The space complexity of
 the checkContainsDuplicate method is O(1) because it uses a constant amount of space regardless of the input size.

Therefore, the space complexity is dominated by the space used by the mutable Set (s), and it is O(n) in terms of the size
 of the input array.

The time complexity of this method is O(n) because it involves iterating through the array and performing constant-time
 operations for each element.

  // Solution 3-


The space complexity of the provided method containsDuplicate is O(n), where n is the length of the input array (nums).

Here's the breakdown:

Input Space: The input array nums is used, and its space complexity is considered to be O(n), where n is the length of the array.

Additional Space: The method creates a Set (s) using the toSet method, which contains distinct elements from the array. The
space complexity of creating this Set is O(m), where m is the number of unique elements in the array. In the worst case, where
 all elements are unique, m would be equal to n (the length of the array).

Therefore, the space complexity is dominated by the space used by the Set (s), and it is O(n) in terms of the size of the input array.

The time complexity of this method is O(n) because it involves creating a Set (linear time complexity) and comparing the size
of the Set with the length of the array. This is more efficient than the quadratic time complexity in the previous example with
 nested loops.
 */


