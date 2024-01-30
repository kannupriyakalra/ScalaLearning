package LeetCode

import scala.annotation.tailrec

//https://leetcode.com/problems/leaf-similar-trees/description/?envType=study-plan-v2&envId=leetcode-75
case class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}


object LeafSimilarTrees extends App {

  // Solution - 1

  /*
  We are using list as a Stack


              tail
      _  _   _  _  _  _         _   _   __  ...
      3         1   1   1   1         1     1    1
                5       2   2               4    4
                        6                   7



          3         5       6         2     7
          head              leafNode        leafNode

      [6]
      [6, 7]
  */

  //  Time Complexity O(n) | Space Complexity O(n)
  //  def leafSimilar(root1: TreeNode, root2: TreeNode): Boolean = {
  //    def returnLeafElements(treeState: List[TreeNode], leafNodes: List[Int]): List[Int] = {
  //      if (!treeState.isEmpty) {
  //        val root = treeState.head
  //        if (root.left == null && root.right == null) {
  //          // leaf node
  //          returnLeafElements(treeState.tail, root.value :: leafNodes)
  //
  //        } else if (root.left == null && root.right != null) {
  //          //node with empty left child
  //          returnLeafElements(root.right :: treeState.tail, leafNodes)
  //
  //        } else if (root.left != null && root.right == null) {
  //          //node with empty right child
  //          returnLeafElements(root.left :: treeState.tail, leafNodes)
  //
  //        } else {
  //          // normal node
  //          returnLeafElements(root.left :: root.right :: treeState.tail, leafNodes)
  //        }
  //
  //      } else {
  //        return leafNodes
  //      }
  //
  //    }
  //
  //    val leafNodesRoot1 = returnLeafElements(root1 :: Nil, List[Int]())
  //    val leafNodesRoot2 = returnLeafElements(root2 :: Nil, List[Int]())
  //    println(leafNodesRoot1)
  //    println(leafNodesRoot2)
  //
  //    leafNodesRoot1 == leafNodesRoot2
  //  }


  // Solution - 2 Better - fail fast   //  Time Complexity O(n) | Space Complexity O(n)
  def isLeafNode(node: TreeNode): Boolean = {
    (node.left == null) && (node.right == null)
  }

  @tailrec
  def nextLeaf(state: List[TreeNode]): (Int, List[TreeNode]) = {
    if (isLeafNode(state.head)) return (state.head.value, state.tail)
    val tail1 = if (state.head.right != null) state.head.right :: state.tail else state.tail
    val tail2 = if (state.head.left != null) state.head.left :: tail1 else tail1
    nextLeaf(tail2)
  }

  @tailrec
  def helper(state1: List[TreeNode], state2: List[TreeNode]): Boolean = {
    // helper(list(TreeNode(3),list(TreeNode(3)))
    (state1, state2) match {
      case (Nil, Nil) => true
      case (Nil, _) | (_, Nil) => false
      case _ =>
        val (leafValue1, nextState1) = nextLeaf(state1) // (6, (2,1)) | (7, (4,1)) | (4, (1)) | (9, (8)) | (8, Nil)
        val (leafValue2, nextState2) = nextLeaf(state2) // (6, (7,1)) | (7, (1))   | (4, (2)) | (9, (8)) | (8, Nil)

        if (leafValue1 != leafValue2) return false

        helper(nextState1, nextState2) // ((2,1), (7,1)) | ((4,1), (1)) | ((1), (2)) | ((8),(8)) | (Nil, Nil)
    }
  }

  def leafSimilar(root1: TreeNode, root2: TreeNode): Boolean = {
    helper(List(root1), List(root2))
    // helper(list(TreeNode(3),list(TreeNode(3)))
  }



  /*
  Algo-
    1. store the Tree roots in respective stacks (List)
    2. check the stack for state of tree
      2.1 check if both tree stacks are Nil then return true which means both the trees are leaf similar
      2.2 check for parsing an unequle size trees, if one of the tree is smaller than other tree the smaller tree state will reach Nil state first which means the trees are not leaf similar.
      2.3 if both tree states are not Nil then find next Leaf Node for both the trees.

  */


  val t1: TreeNode = TreeNode(3, TreeNode(9, null, null), TreeNode(20, TreeNode(5, null, null), TreeNode(7, null, null)))
  val t2: TreeNode = TreeNode(3, TreeNode(9, null, null), TreeNode(20, TreeNode(5, null, null), TreeNode(7, null, null)))
  val t3: TreeNode = TreeNode(3, TreeNode(9, null, null), TreeNode(20, TreeNode(5, null, null), TreeNode(70, null, null)))

  println(leafSimilar(t1, t2))
  println(leafSimilar(t1, t3))

}


/*
Solution- 2 Time complexity analysis:

The code you provided is a function that checks if two binary trees are leaf-similar, meaning they have the same sequence of leaf values from left to right¹. To do this, the code uses a helper function that traverses both trees in a depth-first manner using lists as stacks². The time complexity of this function depends on the number of nodes in each tree, denoted by $$n_1$$ and $$n_2$$ respectively.

The helper function performs the following operations for each node in both trees:

•Checking if the node is a leaf node, which takes constant time.
•Calling the nextLeaf function, which pops the current node from the stack and pushes its children if any, which also takes constant time.
•Comparing the leaf values of both trees, which takes constant time.
•Recursively calling the helper function with the updated stacks, which takes linear time in the worst case.

Therefore, the total time complexity of the helper function is $$O(n_1 + n_2)$$, since it visits each node in both trees once. The leafSimilar function simply calls the helper function with the root nodes of both trees, so it has the same time complexity.


Solution- 2 Space complexity analysis:

The space complexity of your code is the amount of memory that your code uses as a function of the input size¹. To calculate the space complexity, you need to consider the space used by the input, the output, and any auxiliary data structures that you create in your code².

In your code, the input consists of two binary trees, root1 and root2, which have $$n_1$$ and $$n_2$$ nodes respectively. The output is a Boolean value, which takes constant space. The auxiliary data structures are the lists state1 and state2, which are used as stacks to store the nodes of the trees in a depth-first order³. The size of these lists depends on the height of the trees, which can be as large as $$n_1$$ and $$n_2$$ in the worst case (when the trees are skewed).

Therefore, the space complexity of your code is $$O(n_1 + n_2)$$, since the space used by the lists is proportional to the number of nodes in the trees. 

 */

