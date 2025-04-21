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
  public boolean isCousins(TreeNode root, int x, int y) {

      // Step 1: Create a queue for BFS traversal
      Queue<TreeNode> q = new LinkedList<>();
      q.add(root); // Start BFS from the root

      // Step 2: Continue until there are no more nodes to process
      while (!q.isEmpty()) {

          // These will track whether x and y are found at the current level
          TreeNode parentX = null, parentY = null;
          boolean foundX = false, foundY = false;

          int size = q.size(); // Number of nodes at this level

          // Process all nodes at the current level
          for (int i = 0; i < size; i++) {
              TreeNode curr = q.poll(); // Get the next node from the queue

              // Check left child
              if (curr.left != null) {
                  q.offer(curr.left); // Add left child to queue for next level

                  // If left child is x or y, record it and its parent
                  if (curr.left.val == x) {
                      foundX = true;
                      parentX = curr;
                  }
                  if (curr.left.val == y) {
                      foundY = true;
                      parentY = curr;
                  }
              }

              // Check right child
              if (curr.right != null) {
                  q.offer(curr.right); // Add right child to queue for next level

                  // If right child is x or y, record it and its parent
                  if (curr.right.val == y) {
                      foundY = true;
                      parentY = curr;
                  }
                  if (curr.right.val == x) {
                      foundX = true;
                      parentX = curr;
                  }
              }
          }

          // After scanning this level:

          // If both x and y are found, check if they have different parents
          if (foundX && foundY) {
              return parentX != parentY; // True if parents are different
          }

          // If only one of x or y was found, they can't be cousins
          if (foundX || foundY)
              return false;
      }

      // If the loop ends, x and y were not found as cousins
      return false;
  }
}

/*

✅ Main Idea / Thought Process
Use level-order traversal (BFS) to visit nodes level by level.

At each level, check:

If both x and y are present

If their parents are different

If yes → return true

If only one is found at a level → return false (they can't be cousins)

*/