/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
  public TreeNode deleteNode(TreeNode root, int key) {
      // Base case: If the tree is empty, return null
      if (root == null) return null;

      // If the key to be deleted is smaller than the root's value,
      // then it lies in the left subtree
      if (root.val > key) {
          root.left = deleteNode(root.left, key);
      }
      // If the key to be deleted is greater than the root's value,
      // then it lies in the right subtree
      else if (root.val < key) {
          root.right = deleteNode(root.right, key);
      }
      // If the key is equal to the root's value, then this is the node to be deleted
      else {
          // Case 1: Node is a leaf (no children), simply remove it
          if (root.left == null && root.right == null) {
              return null;
          }

          // Case 2: Node has only one child (right child exists, no left child)
          if (root.left == null) {
              return root.right; // Replace the node with its right child
          }
          // Case 3: Node has only one child (left child exists, no right child)
          else if (root.right == null) {
              return root.left; // Replace the node with its left child
          }

          // Case 4: Node has two children
          // Find the inorder successor (smallest value in the right subtree)
          TreeNode Is = findInorderScuccessor(root.right);
          
          // Replace the node's value with the inorder successor's value
          root.val = Is.val;
          
          // Delete the inorder successor (which is now a duplicate in the right subtree)
          root.right = deleteNode(root.right, Is.val);
      }
      return root; // Return the updated root
  }

  // Function to find the inorder successor (smallest value in the right subtree)
  public static TreeNode findInorderScuccessor(TreeNode root) {
      TreeNode curr = root;
      // The inorder successor is the leftmost node in the right subtree
      while (curr.left != null) {
          curr = curr.left;
      }
      return curr;
  }
}
