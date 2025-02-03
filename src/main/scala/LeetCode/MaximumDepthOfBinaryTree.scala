package LeetCode

//https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
object MaximumDepthOfBinaryTree extends App {

  //Solution-1

  // Definition for a binary tree node.
  case class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  //takes a tree node as input and returns its depth
  def maxDepth(node: TreeNode): Int = { //Time Complexity- O(n), Space Complexity- O(1)
    if (node == null) 0
    else Math.max(maxDepth(node.left), maxDepth(node.right)) + 1
  }
  // as there is no new keyword used so heap memory is not used here only stack memory is used. garbage collector is a concept of heap memory, is used to recover heap memory. heap memory is part of
  // ram.
  // stack memory is not part of ram, this is cpu s internal memory to execute program, it is used to maintain recursion stacks. Int is of type AnyVal and they are  not implemented as objects in
  // the underlying host system and ie why not part of heap memory.

  val t: TreeNode = TreeNode(3, TreeNode(9, null, null), TreeNode(20, TreeNode(5, null, null), TreeNode(7, null, null))) //this is heap memory
  println(maxDepth(t))

  val u: TreeNode = t //this is not heap memory, this is stack memory. to refer heap memory you need stack memory.
  //stack memory eg- primitive types usage, reference, recursion stack frames
  //heap  memory eg- Integer, creating objects using new or apply
  //, stack memory is needed for executing the program, when we talk about space complexity we talk about heap memory as no heap memory is used here ie why space complexity is O(1)



  //Solution-2
  //  def maxDepth(root: TreeNode): Int = {
  //
  //    def findDepth(node: TreeNode, depth: Int): Int = {
  //      if (node == null) return depth
  //      Math.max(findDepth(node.left, depth + 1), findDepth(node.right, depth + 1))
  //    }
  //    findDepth(root, 0)
  //  }


  //how to make it tail recursive? //HELP - as there are 2 recursive steps only one recursion can be last, so its not possible using accumulator passing technique to convert it to tail recursion.

  //Solution-3

  //  // Definition of a binary tree node
  //  case class TreeNode(value: Int, left: Option[TreeNode], right: Option[TreeNode])
  //
  //  // Function to calculate the depth of a binary tree
  //  def depthOfBinaryTree(root: Option[TreeNode]): Int = {
  //    root match {
  //      case Some(node) =>
  //        val leftDepth = depthOfBinaryTree(node.left)
  //        val rightDepth = depthOfBinaryTree(node.right)
  //        1 + Math.max(leftDepth, rightDepth)
  //      case None => 0
  //    }
  //  }
  //
  //  val tree = TreeNode(1,
  //    Some(TreeNode(2,
  //      Some(TreeNode(4, None, None)),
  //      Some(TreeNode(5, None, None))
  //    )),
  //    Some(TreeNode(3,
  //      None,
  //      Some(TreeNode(6, None, None))
  //    ))
  //  )
  //
  //  val treeDepth = depthOfBinaryTree(Some(tree))
  //  println(s"The depth of the binary tree is: $treeDepth")

}



