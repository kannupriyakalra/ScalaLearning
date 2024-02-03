package LeetCode

import scala.annotation.tailrec

//https://leetcode.com/problems/search-in-a-binary-search-tree/description/?envType=study-plan-v2&envId=leetcode-75
object SearchInBST extends App {

  // Definition for a binary tree node.
  case class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  @tailrec
  def searchBST(root: TreeNode, `val`: Int): TreeNode = {

    if (root == null || `val` == root.value) root
    else if (`val` < root.value) searchBST(root.left, `val`)
    else searchBST(root.right, `val`)

  }

  val t: TreeNode = TreeNode(4, TreeNode(2, TreeNode(1, null, null), TreeNode(3, null, null)), TreeNode(7, null, null))
  //println(searchBST(t, 2))
  println(searchBST(t, 100))

  //ASK - time, space complexity- couldn't understand from chatgpt

}
