import java.util.*;  // Importing necessary libraries

class Solution {
    // Function to detect a cycle in an undirected graph.
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();  // Get the number of nodes in the graph
        int[] vis = new int[n];  // Create a visited array to track visited nodes
        
        // Loop through all nodes (handles disconnected components)
        for(int i = 0; i < n; i++) {
            if(vis[i] == 0) {  // If node is not visited
                // Call the recursive DFS function to check for cycles
                if(detectCycleUtil(adj, vis, i, -1)) {
                    return true;  //  // cycle exists in one of the parts
                }
            }
        }
        
        return false;  //// no cycle found in any component of the graph
    }
    
    // Helper function to perform DFS and detect cycles
    public static boolean detectCycleUtil(ArrayList<ArrayList<Integer>> adj, int[] vis, int curr, int parent) {
        vis[curr] = 1;  // Mark the current node as visited
        
        // Iterate through all neighbors of the current node
        for(int neigh : adj.get(curr)) {
            if(vis[neigh] == 0) {  // If neighbor is not visited, perform DFS
                if(detectCycleUtil(adj, vis, neigh, curr)) { 
                    return true;  // If a cycle is found in recursion, return true
                }
            }
            
            // If the neighbor is already visited and is not the parent, then cycle exists
            else if(neigh != parent) {
                return true;  // Cycle detected in curr component
            }
        }
        return false;  // no cycle found in this component of the graph
    }
}

/*

Intuition Behind the Code
Graph Traversal: The algorithm uses Depth-First Search (DFS) to explore all nodes and edges.
Cycle Detection: A cycle in an undirected graph occurs when we encounter a visited node that is not the parent of the current node.
Handling Disconnected Graphs: The isCycle method iterates over all nodes to ensure we check all components.
DFS Recursion: The detectCycleUtil function recursively explores the graph and checks for cycles.
Base Condition for Cycle: If we reach an already visited node that is not our direct parent, a cycle exists.
This method ensures an O(V + E) time complexity, where V is the number of vertices and E is the number of edges, making it efficient for cycle detection in undirected graphs. 🚀

*/
