import java.util.*;
class Solution {
  // Function to detect a cycle in a directed graph
  public boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {
      int v = adj.size(); // Number of vertices in the graph

      // Arrays to keep track of visited nodes and nodes in the current recursion stack
      int[] vis = new int[v];   // vis[i] = 1 means node 'i' has been visited
      int[] stack = new int[v]; // stack[i] = 1 means node 'i' is in the recursion stack
      
      // Iterate over all nodes to ensure we check all disconnected components
      for (int i = 0; i < v; i++) {
          if (vis[i] == 0) { // If the node has not been visited
              // Perform DFS traversal and check for cycles
              if (dfs(adj, vis, stack, i)) {
                  return true; // If a cycle is detected, return true
              }
          }
      }
      
      return false; // If no cycle is found, return false
  }

  // Helper function to perform DFS and detect cycles
  public boolean dfs(ArrayList<ArrayList<Integer>> adj, int[] vis, int[] stack, int curr) {
      vis[curr] = 1;   // Mark the current node as visited
      stack[curr] = 1; // Add the current node to the recursion stack

      // Traverse all the neighbors (adjacent nodes) of the current node
      for (int neigh : adj.get(curr)) {
          if (stack[neigh] == 1) { // If the neighbor is already in the recursion stack, a cycle is found
              return true;
          }
          
          if (vis[neigh] == 0) { // If the neighbor has not been visited
              // Recursively call DFS for the neighbor
              if (dfs(adj, vis, stack, neigh)) {
                  return true; // If a cycle is found in the recursive call, return true
              }
          }
      }
      
      stack[curr] = 0; // Remove the current node from the recursion stack before backtracking
      
      return false; // If no cycle is detected, return false
  }
}


/*

Intuition Behind the Algorithm
We need to detect a cycle in a directed graph.

A cycle exists if we encounter a node that is already in the current DFS path (recursion stack).

We use DFS (Depth-First Search) for traversal.

We maintain two arrays:

vis[]: Marks whether a node has been visited or not.

stack[]: Tracks nodes that are part of the current DFS recursion path.

Key Observations:

If we visit a node that is already in the recursion stack (stack[i] == 1), we found a cycle.

If we visit a node that is not yet visited, we recursively check its neighbors.

After exploring all paths from the current node, we remove it from the recursion stack (stack[curr] = 0) before backtracking.

Why Use Recursion Stack (stack[])?
The stack[] array ensures that we only detect cycles within the same DFS path.

Unlike an undirected graph, where a cycle can be detected by revisiting any visited node, in a directed graph, we must ensure the cycle is within a single DFS traversal path.

Time Complexity Analysis
DFS Traversal takes O(V + E) (visiting all nodes and edges once).

Hence, the overall complexity is O(V + E).



Why Use Recursion Stack (stack[])?

In a directed graph, a cycle exists if a node reaches itself while following the direction of edges.

But how do we detect this using DFS (Depth-First Search)?

Problem Without stack[]:
If we only use the vis[] array (which tracks if a node is visited), we might detect a previously visited node that is NOT part of the current DFS path, leading to false cycle detection.

Solution Using stack[]:
The stack[] array keeps track of nodes that are currently in the DFS call stack.

If we encounter a node that is already in the stack (stack[neigh] == 1), it means we have found a back edge (a node pointing back to one in the current path), which confirms a cycle.

Once DFS finishes checking a node and its neighbors, we remove it from stack[] (stack[curr] = 0) before backtracking.


Example:
Consider the graph:

markdown
Copy
Edit
 0 → 1 → 2
     ↑    ↓
     4 ←  3
If DFS starts at 0:

Visit 0, mark stack[0] = 1

Visit 1, mark stack[1] = 1

Visit 2, mark stack[2] = 1

Visit 3, mark stack[3] = 1

Visit 4, mark stack[4] = 1

4 has an edge back to 1, which is still in stack[] → Cycle Detected!

Key Point:
stack[] helps track nodes in the current path (not just visited ones).

If a node is found again in stack[], it means we are revisiting it in the same DFS path, which confirms a cycle.

If a node is just visited (vis[] is 1) but not in stack[], it's from a different DFS path and doesn't indicate a cycle.

*/