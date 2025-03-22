import java.util.*;

class Solution {
  // Function to return a list containing vertices in Topological order.
  static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
      // Get the number of vertices in the graph
      int v = adj.size();

      // Result list to store the topological order
      ArrayList<Integer> ans = new ArrayList<>();

      // Array to store the in-degree (number of incoming edges) of each vertex
      int[] indeg = new int[adj.size()];

      // Step 1: Calculate the in-degree of each node
      for (int i = 0; i < adj.size(); i++) { 
          for (int j = 0; j < adj.get(i).size(); j++) { 
              indeg[adj.get(i).get(j)]++; // Increase the in-degree of the target node
          }
      }

      // Step 2: Add all nodes with in-degree 0 to the queue
      Queue<Integer> q = new LinkedList<>();
      for (int i = 0; i < v; i++) {
          if (indeg[i] == 0) { // Nodes with in-degree 0 have no dependencies
              q.add(i);
          }
      }

      // Step 3: Process nodes using BFS
      while (!q.isEmpty()) {
          int curr = q.poll(); // Remove node from queue
          ans.add(curr); // Add node to topological order

          // Reduce the in-degree of all its adjacent nodes
          for (int i = 0; i < adj.get(curr).size(); i++) {
              int neighbor = adj.get(curr).get(i); // Get the adjacent node
              indeg[neighbor]--; // Decrease its in-degree

              // If in-degree becomes 0, add it to the queue
              if (indeg[neighbor] == 0) {
                  q.add(neighbor);
              }
          }
      }

      // Step 4: Check for a cycle
      if (ans.size() != v) { 
          // If the topological order does not include all nodes, there is a cycle
          return new ArrayList<>(); // Return an empty list indicating failure
      }

      // Return the valid topological order
      return ans;
  }
}
  
  /*
  
  🔍 Intuition Behind the Algorithm:
Topological Sorting is used for DAGs (Directed Acyclic Graphs) where tasks depend on each other.

Kahn’s Algorithm (BFS-based approach) is used here.

Key Idea: Nodes with in-degree 0 have no dependencies, so process them first.

🔢 Step-by-Step Breakdown
Compute in-degree for each node

Count the number of edges pointing to each node.

Nodes with in-degree 0 have no dependencies → They can be processed first.

Push all nodes with in-degree 0 into a queue

These nodes have no dependencies and can be executed immediately.

Perform BFS-like processing

Remove a node from the queue, add it to the result.

Reduce the in-degree of all adjacent nodes.

If any adjacent node’s in-degree becomes 0, push it into the queue.

Check for a cycle

If the result list doesn’t contain all nodes, a cycle exists.

Return an empty list in this case.

  */