class Solution {
  public List<Integer> eventualSafeNodes(int[][] graph) {
      int v = graph.length; // Get the number of nodes in the graph
      
      // Create a reversed adjacency list (to store edges in reverse direction)
      List<List<Integer>> adjrev = new ArrayList<>();
      for (int i = 0; i < v; i++) {
          adjrev.add(new ArrayList<>()); // Initialize an empty list for each node
      }

      int[] indeg = new int[v]; // Array to store in-degree of each node

      // Reverse the edges in the graph
      for (int s = 0; s < v; s++) { 
          // Iterate through the adjacency list of node `s`
          for (int d : graph[s]) {
              adjrev.get(d).add(s); // Reverse the edge (store `s` as neighbor of `d`)
              indeg[s]++; // Increase the in-degree of `s` (since it originally pointed to `d`)
          }
      }

      List<Integer> ans = new ArrayList<>(); // List to store eventual safe nodes
      Queue<Integer> q  = new LinkedList<>(); // Queue for BFS traversal

      // Identify all terminal nodes (nodes with 0 out-degree in the original graph)
      for (int i = 0; i < v; i++) {
          if (indeg[i] == 0) { // If a node has 0 in-degree after reversing edges
              q.add(i); // It is a terminal node, so add to the queue
          }
      }

      // Perform BFS (Kahn's Algorithm)
      while (!q.isEmpty()) {
          int curr = q.poll(); // Remove a node with 0 in-degree
          ans.add(curr); // It is a safe node, so add to the result list

          // Reduce in-degree of its neighbors (original predecessors)
          for (int neigh : adjrev.get(curr)) {
              indeg[neigh]--; // Remove the connection
              if (indeg[neigh] == 0) { // If this node now has 0 in-degree
                  q.add(neigh); // Add it to the queue, as it is now a safe node
              }
          }
      }

      Collections.sort(ans); // Sort the safe nodes in increasing order
      return ans; // Return the list of eventual safe nodes
  }
}

/*


Intuition & Thought Process Behind the Code:
Understanding Safe Nodes:

A safe node is one that does not lead to a cycle. This means that any path from this node eventually reaches a terminal node.

Terminal nodes are nodes that have no outgoing edges.

If a node is part of a cycle or leads to a cycle, it is not a safe node.

Reversing the Graph:

Instead of checking which nodes lead to cycles, we reverse all edges in the graph.

This allows us to process nodes from terminal nodes backward and find safe nodes efficiently.

Using Kahn’s Algorithm (Topological Sorting for Directed Graphs):

We process nodes with 0 in-degree first (these are safe).

We remove these nodes and update the in-degree of their neighbors.

If a neighbor’s in-degree becomes 0, it means all paths from that node lead to a safe node.

Sorting the Result:

Since the problem requires returning nodes in ascending order, we sort the list before returning.



*/