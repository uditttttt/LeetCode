/**
 * Definition for a binary tree node.
 * This is a basic structure for a node in the binary tree.
 */
public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {} // default constructor

  TreeNode(int val) { 
      this.val = val; 
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
  }
}

class Solution {

  // A helper class to store both the TreeNode and its index
  class Info {
      TreeNode node;
      int index; // virtual index of the node in a full binary tree

      public Info(TreeNode node, int i) {
          this.node = node;
          this.index = i;
      }
  }

  public int widthOfBinaryTree(TreeNode root) {
      // Queue to perform level-order traversal (BFS)
      // It stores Info objects which include the node and its index
      Queue<Info> q = new LinkedList<>();

      // Start with root node at index 0
      q.add(new Info(root, 0));

      int ans = 0; // To store the maximum width

      while (!q.isEmpty()) {
          int size = q.size(); // Number of nodes in current level
          int f = 0, l = 0;     // To store first and last indices in this level

          // Get index of first node in this level (to normalize indices)
          f = q.peek().index;

          for (int i = 0; i < size; i++) {
              Info curr = q.poll(); // Remove node from queue
              int idx = curr.index; // Get current node's index
              TreeNode node = curr.node;
              l = idx; // Update last index (will be the last node in this level)

              // Left child will be at 2*idx + 1 in a full binary tree
              if (node.left != null) {
                  q.offer(new Info(node.left, 2 * idx + 1));
              }

              // Right child will be at 2*idx + 2
              if (node.right != null) {
                  q.offer(new Info(node.right, 2 * idx + 2));
              }
          }

          // Width of this level = last index - first index + 1
          ans = Math.max(ans, l - f + 1);
      }

      return ans;
  }
}

/*

💡 Intuition:
We're trying to find the maximum width of a binary tree.

The width of a level is the distance between the leftmost and rightmost non-null nodes in that level.

To calculate width correctly even when tree is sparse, we assign a virtual index to each node as if it were a full binary tree.

For a node at index i, the left child is at 2*i+1, and the right child is at 2*i+2.

Using BFS, we track these indices level by level, and compute width using the difference between first and last indices in that level.

*/