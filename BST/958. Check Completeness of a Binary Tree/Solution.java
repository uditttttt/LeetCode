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
// class Solution {
//     public boolean isCompleteTree(TreeNode root) {
//         // If the tree is empty, it is trivially complete
//         if(root == null)
//             return true;

//         // Step 1: Count the total number of nodes in the tree
//         int totalNodes = totalNodes(root);

//         // Step 2: Check completeness using indexing method
//         return isCompleteTree(root, 0, totalNodes);
//     }

//     // Helper function to check completeness based on index in a full binary tree structure
//     public boolean isCompleteTree(TreeNode root, int index, int totalNodes) {
//         // If we reach a null node, it does not break completeness
//         if(root == null)
//             return true;

//         // If a node's index is greater than or equal to the total number of nodes,
//         // it means a gap exists, and the tree is not complete.
//         if(index >= totalNodes)
//             return false;

//         // Recursively check left and right subtrees
//         // Left child has index: 2 * index + 1
//         // Right child has index: 2 * index + 2
//         return isCompleteTree(root.left, 2 * index + 1, totalNodes) &&
//                isCompleteTree(root.right, 2 * index + 2, totalNodes);
//     }

//     // Helper function to count the total number of nodes in the tree
//     public int totalNodes(TreeNode root) {
//         // If the node is null, return 0
//         if(root == null)
//             return 0;

//         // Count the current node + left subtree nodes + right subtree nodes
//         return 1 + totalNodes(root.left) + totalNodes(root.right);
//     }
// }

/*

Why Does This Approach Work?
Indexing Mimics Level-Order Traversal

If the tree is complete, node indices will range from 0 to totalNodes - 1 without skipping values.
If an index is missing (i.e., index >= totalNodes), the tree is not complete.
Ensures No Right-Side Gaps

A missing left child can still be complete (if no further children exist).
But a missing child in the middle (before another child appears) means the tree is not complete.
Efficient Checking in O(N) Time

Counting nodes (totalNodes) takes O(N).
Checking completeness recursively also takes O(N), leading to O(N) overall complexity.

*/
 


class Solution {
  public boolean isCompleteTree(TreeNode root) {
      // Use a queue to perform level-order traversal (BFS)
      Queue<TreeNode> q = new LinkedList<>();
      q.add(root); // Start with the root node
      
      boolean past = false; // A flag to indicate if we've seen a null node

      while (!q.isEmpty()) { // Continue until all nodes are processed
          TreeNode curr = q.poll(); // Get the front node from the queue

          if (curr == null) { 
              // If we encounter a null node, set 'past' to true
              // This means any non-null node after this would indicate an incomplete tree
              past = true;
          } else {
              // If we see a non-null node after a null node, it's not a complete tree
              if (past == true) return false; 

              // Add left and right children to the queue for further checking
              q.add(curr.left);
              q.add(curr.right);
          }
      }

      return true; // If no violations were found, the tree is complete
  }
}



