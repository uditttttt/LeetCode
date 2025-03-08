// Definition for a binary tree node
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) { val = x; }
}

class Solution {
  public boolean isValidBST(TreeNode root) {
      // Call the helper function with initial min and max values
      // We use Long.MIN_VALUE and Long.MAX_VALUE to handle edge cases with large numbers
      return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public boolean helper(TreeNode root, long min, long max) {
      // Base Case: If the node is null, return true (empty trees are valid BSTs)
      if (root == null) return true;

      // If the current node's value is out of valid range, return false
      if (root.val <= min || root.val >= max) return false;

      // Recursively check the left subtree
      // - The left subtree values should be in range (min, root.val)
      boolean leftTree = helper(root.left, min, root.val);

      // Recursively check the right subtree
      // - The right subtree values should be in range (root.val, max)
      boolean rightTree = helper(root.right, root.val, max);

      // Return true only if both left and right subtrees are valid BSTs
      return leftTree && rightTree;
  }
}
