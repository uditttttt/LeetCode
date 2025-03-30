import java.util.*;  // Importing necessary libraries

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length; // Get the number of nodes in the graph
        int[] color = new int[n]; // Array to store colors of nodes (0 or 1)
        Arrays.fill(color, -1); // Initialize all nodes as uncolored (-1)

        // Traverse all nodes in case the graph is disconnected
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) { // If the node is not colored
                if (!check(graph, color, i)) { // Perform BFS to check bipartiteness
                    return false; // If not bipartite, return false
                }
            }
        }

        return true; // If all components are bipartite, return true
    }

    public boolean check(int[][] graph, int[] color, int start) {
        Queue<Integer> q = new LinkedList<>(); // Create a queue for BFS
        q.add(start); // Add the starting node to the queue
        color[start] = 0; // Assign an initial color (0) to the start node

        while (!q.isEmpty()) { // Process all nodes in the queue
            int curr = q.poll(); // Remove the front node from the queue

            for (int neigh : graph[curr]) { // Iterate over all its neighbors
                if (color[neigh] == -1) { // If the neighbor is uncolored
                    color[neigh] = color[curr] == 0 ? 1 : 0; // Assign opposite color
                    q.add(neigh); // Add the neighbor to the queue for further processing
                }

                if (color[neigh] == color[curr]) // If a conflict is found (same color)
                    return false; // The graph is not bipartite
            }
        }

        return true; // If no conflicts were found, return true
    }
}

/*

Intuition Behind the Code
Understanding Bipartiteness:

A graph is bipartite if we can color its nodes using two colors (0 and 1) such that no two adjacent nodes have the same color.

If a cycle of odd length exists in the graph, it cannot be bipartite.

Approach:

We use Breadth-First Search (BFS) to traverse the graph and try to color the nodes.

If at any step, a node has the same color as one of its neighbors, the graph is not bipartite.

We check all components because the graph can be disconnected.

Key Steps:

Initialize all nodes as uncolored (-1).

Try to color each connected component using BFS.

If any conflict arises, return false.

This approach ensures that we efficiently determine if the given graph is bipartite. 🚀

*/


