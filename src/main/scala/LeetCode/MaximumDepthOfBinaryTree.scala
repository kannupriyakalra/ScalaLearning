package LeetCode

//https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
object MaximumDepthOfBinaryTree extends App {

  //Solution-1 Time Complexity- O(n), Space Complexity- O(1)

    // Definition for a binary tree node.
    case class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
      var value: Int = _value
      var left: TreeNode = _left
      var right: TreeNode = _right
    }
      def maxDepth(root: TreeNode): Int = {

        def findDepth(node: TreeNode, depth: Int): Int = {
          if (node == null) return depth
          Math.max(findDepth(node.left, depth + 1), findDepth(node.right, depth + 1))
        }

        findDepth(root, 0)
      }

  //how to make it tail recursive? //HELP
//  def maxDepth(root: TreeNode): Int = {
//
//    def findDepth(node: TreeNode, depth: Int, acc: Int): Int = {
//      if (node == null) return acc
//      val leftDepth = findDepth(node.left, depth + 1, acc + 1)
//      val rightDepth = findDepth(node.right, depth + 1, acc + 1)
//      Math.max(leftDepth, rightDepth)
//    }
//
//    findDepth(root, 0, 0)
//
//
//  }

    val t: TreeNode = TreeNode(3, TreeNode(9, null, null), TreeNode(20, TreeNode(5, null, null), TreeNode(7, null, null)))
    println(maxDepth(t))


  //Solution-2

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



