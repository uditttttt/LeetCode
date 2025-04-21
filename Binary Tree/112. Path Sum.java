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
    
    // Helper function to recursively check if there's a path that sums to targetSum
    public boolean solve(TreeNode root, int targetSum) {
        
        // Base case 1: If the node is null, return false (no path exists)
        if (root == null) {
            return false;
        }
        
        // Base case 2: If we reach a leaf node (both left and right are null)
        if (root.left == null && root.right == null) {
            // If the current node's value matches the remaining target sum, return true
            if (targetSum == root.val) {
                return true;
            }
            // If the sum doesn't match, return false
            else return false;
        }

        // Recursively check the left subtree with the remaining target sum (targetSum - root.val)
        boolean leftPart = solve(root.left, targetSum - root.val);
        
        // Recursively check the right subtree with the remaining target sum (targetSum - root.val)
        boolean rightPart = solve(root.right, targetSum - root.val);
        
        // Return true if either the left or right subtree contains a valid path
        return leftPart || rightPart;
    }
    
    // This is the main function that will be called by the user
    // It calls the recursive helper function to check for the path sum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return solve(root, targetSum);
    }
}
