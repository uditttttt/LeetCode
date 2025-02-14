/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
  public boolean isSameTree(TreeNode p, TreeNode q) {
      // Base case: If both trees are empty, they are the same
      if (p == null && q == null) return true;

      // If one tree is empty and the other is not, they are not the same
      if ((p == null && q != null) || (p != null && q == null)) return false;

      // If the values of the current nodes are different, they are not the same
      if (p.val != q.val) return false;

      // Recursively check the left and right subtrees
      boolean left = isSameTree(p.left, q.left);
      boolean right = isSameTree(p.right, q.right);

      // Both subtrees must be the same for the trees to be identical
      return left && right;
  }
}