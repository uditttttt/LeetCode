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
//  import java.util.*;

// class Solution {
//     public List<Integer> rightSideView(TreeNode root) {
//         List<Integer> ans = new ArrayList<>();
//         if (root == null) return ans;

//         Queue<TreeNode> q = new LinkedList<>();
//         q.add(root);

//         while (!q.isEmpty()) {
//             int size = q.size();
//             int lastValue = 0; // Stores the last node's value at this level

//             for (int i = 0; i < size; i++) {
//                 TreeNode curr = q.poll();
//                 lastValue = curr.val; // Update last node value at this level

//                 // Add left child first, then right child (to prioritize left-side nodes)
//                 if (curr.left != null) q.add(curr.left);
//                 if (curr.right != null) q.add(curr.right);
//             }

//             ans.add(lastValue); // Only add the last seen node at this level
//         }
//         return ans;
//     }
// }

class Solution {
  // Helper function to perform a modified pre-order traversal (Root -> Right -> Left)
  public static void solve(TreeNode root, List<Integer> res, int level) {
      // Base case: If the current node is null, return (end of recursion for this branch)
      if (root == null) {
          return;
      }

      // If the current level is equal to the size of the result list,
      // it means we are encountering the rightmost node at this level for the first time.
      // So, we add the current node's value to the result list.
      if (level == res.size()) {
          res.add(root.val);
      }

      // Recur for the right subtree first. This ensures that we prioritize the rightmost node
      // at each level. If a right node exists, it will be processed before the left node.
      solve(root.right, res, level + 1);

      // Recur for the left subtree. This ensures that if no right node exists at a level,
      // the leftmost node will be processed and added to the result list.
      solve(root.left, res, level + 1);
  }

  // Main function to return the right side view of the binary tree
  public List<Integer> rightSideView(TreeNode root) {
      // Initialize the result list to store the right side view
      List<Integer> res = new ArrayList<>();

      // Call the helper function starting from the root, with level = 0
      solve(root, res, 0);

      // Return the final result list
      return res;
  }
}