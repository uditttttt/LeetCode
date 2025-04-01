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
  // Helper class to store tree node information along with its row and column index
  class Info {
      int r;  // Row index (depth in the tree)
      int c;  // Column index (horizontal position)
      TreeNode node;  // Reference to the tree node

      public Info(int r, int c, TreeNode node) {
          this.r = r;
          this.c = c;
          this.node = node;
      }
  }

  public List<List<Integer>> verticalTraversal(TreeNode root) {
      List<List<Integer>> ans = new ArrayList<>();  // Final answer list

      // Queue for BFS traversal, storing nodes along with their row & column index
      Queue<Info> q = new LinkedList<>();
      q.add(new Info(0, 0, root));  // Start BFS from the root at (row=0, col=0)

      // TreeMap to store column -> (row -> PriorityQueue of node values)
      TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

      // BFS traversal
      while (!q.isEmpty()) {
          Info curr = q.poll();  // Get the front element of the queue
          int r = curr.r;  // Extract row index
          int c = curr.c;  // Extract column index
          TreeNode node = curr.node;  // Extract tree node

          // If column 'c' is not present, add it to the map
          map.putIfAbsent(c, new TreeMap<>());

          // If row 'r' inside column 'c' is not present, add it to the map
          map.get(c).putIfAbsent(r, new PriorityQueue<>());

          // Add the node value to the corresponding (column, row) PriorityQueue
          map.get(c).get(r).offer(node.val);

          // Add left child with updated row & column indices
          if (node.left != null) q.add(new Info(r + 1, c - 1, node.left));

          // Add right child with updated row & column indices
          if (node.right != null) q.add(new Info(r + 1, c + 1, node.right));
      }

      // Convert map data into the final answer format
      for (TreeMap<Integer, PriorityQueue<Integer>> row : map.values()) {
          List<Integer> columnList = new ArrayList<>();
          for (PriorityQueue<Integer> pq : row.values()) {
              while (!pq.isEmpty()) {
                  columnList.add(pq.poll());  // Extract nodes in sorted order
              }
          }
          ans.add(columnList);  // Add sorted column list to the final answer
      }

      return ans;  // Return the vertical order traversal result
  }
}


/*

The given code performs Vertical Order Traversal of a Binary Tree while maintaining specific sorting rules:

Nodes are grouped by vertical columns (left to right).

Within the same column, nodes are sorted by row index (top to bottom).

If multiple nodes share the same column and row, they are sorted in ascending order.

Thought Process & Intuition
Breadth-First Search (BFS) is used to ensure we traverse each level completely before moving to the next.

A TreeMap is used to maintain a sorted order of columns (horizontal index).

Another TreeMap inside it maintains nodes at different row indices.

A PriorityQueue (Min-Heap) ensures nodes at the same position (row, column) are sorted in ascending order.


Why BFS instead of DFS?
BFS ensures level-order processing, making it easier to assign correct row indices (r values).

DFS would require more complex recursion logic to track and update r and c.

Why Use TreeMap?
Ensures columns are processed in sorted order (c values).

Nested TreeMap ensures rows (r values) are also processed in order.

Why Use PriorityQueue?
Ensures nodes with the same r and c values are sorted in ascending order.

If we used a List, we'd need extra sorting after processing.

Time & Space Complexity
Time Complexity: O(N log N)

Each node is processed once (O(N)).

Insertion in TreeMap and PriorityQueue takes O(log N).

Extracting elements from PriorityQueue also takes O(log N).

Space Complexity: O(N)

TreeMap stores all nodes.

Queue stores at most O(N/2) ≈ O(N) elements (at the last level of the tree).

*/