package PracticedWithMentor

object TestArray2 extends App {

  //Q1. Implement foldLeft on an array
  def myArrayFoldLeft[A, B](arr: Array[A])(z: B)(op: (B, A) => B): B = {
    def go(i: Int, result: B): B = {
      if (i >= arr.length) result
      else go(i + 1, op(result, arr(i)))
    }
    go(0, z)
  }

  //Q2. Implement foldRight on an array
  def myArrayFoldRight[A, B](arr: Array[A])(z: B)(op: (B, A) => B): B = {
    def go(i: Int, result: B): B = if (i < 0) result else go(i - 1, op(result, arr(i)))
    go(arr.length - 1, z)
  }

  val arr = Array(1, 2, 3)
  println(arr.foldLeft(0)((acc, a) => acc + a)) //o/p- 6
  println(arr.foldRight(0)((a, acc) => a + acc)) //o/p- 6
  println("myArrayFoldLeft called: " + myArrayFoldLeft(arr)(0)((acc, a) => acc + a)) //o/p- myArrayFoldLeft called: 6
  println("myArrayFoldRight called: " + myArrayFoldRight(arr)(0)((acc, a) => acc + a)) //o/p- myArrayFoldRight called: 6

}

/*
myArrayFoldLeft-
FoldLeft-collapse a array into a single value
Make an inner method , Just like factorial, Use some int i in there as index to go from left to right of array ,make a Inner recursive method with result and i
go(0, z)--we are starting array from index 0 and default value of result as z
base case  if (i >= arr.length)  - if i ie index of array >= array.length , return result
go is used to recursively traverse array from left to right and add result to each element from L to R
op(result, arr(i))--update result to o/p of op(result, arr(i)) ie value at ith index + z-- value at 0th index ie 1 + z which is 0 for first case
in def myArrayFoldLeft[A,B], we have to write [A,B] to use it in function signature like arr: Array[A],(z: B)
 */

/*
myArrayFoldRight-
go(2,0)
go(1, op(0, arr(2)) ) --go(1, 3)
go(0,  op(3, arr(1)) ) --go(0, 5)
go(-1, op(5, arr(0)) ) --go(-1, 6)
result = 6
each i is varying from arr.length- 1 to 0, reducing i in every recursion and we stop at i<0 as we have to include arr[0] also in result
 */