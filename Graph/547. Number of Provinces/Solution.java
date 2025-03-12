class Solution {
  public static void dfs(int[][] isConnected, int[] vis, int j) {
      vis[j] = 1;

      for (int i = 0; i < isConnected[j].length; i++) {
          // dfs, you must check if isConnected[j][i] == 1 before visiting i.
          // Without this check, DFS will visit all nodes, even if they are not connected.
          if (isConnected[j][i] == 1 && vis[i] != 1) {
              dfs(isConnected, vis, i);
          }
      }
  }

  public int findCircleNum(int[][] isConnected) {
      int n = isConnected.length;
      int cnt = 0;
      int[] vis = new int[n];
      for (int i = 0; i < n; i++) {
          if (vis[i] != 1) {
              cnt++;
              dfs(isConnected, vis, i);
          }
      }

      return cnt;
  }
}

// // Using  dfs
// import java.util.*;

// class Solution {
//     public int findCircleNum(int[][] isConnected) {
//         int n = isConnected.length; // Number of cities (or nodes)
      
//         // Create an adjacency list representation of the graph
//         ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//         for (int i = 0; i < n; i++) {
//             adj.add(new ArrayList<>()); // Initialize an empty list for each node
//         }

//         // Convert adjacency matrix to adjacency list
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (isConnected[i][j] != 0) { // If a connection exists (including self-loops)
//                     adj.get(i).add(j); // Add j as a neighbor of i
//                     adj.get(j).add(i); // Add i as a neighbor of j (undirected graph)
//                 }
//             }
//         }

//         int[] vis = new int[n]; // Array to track visited nodes
//         int cnt = 0; // Count of connected components (provinces)

//         // Traverse all nodes, and if a node is unvisited, start a DFS
//         for (int i = 0; i < n; i++) {
//             if (vis[i] == 0) { // If the node is unvisited, it's a new province
//                 cnt++; // Increment province count
//                 dfs(adj, vis, i); // Perform DFS traversal
//             }
//         }

//         return cnt; // Return total number of provinces
//     }

//     // DFS function to explore all connected nodes
//     public static void dfs(ArrayList<ArrayList<Integer>> adj, int[] vis, int curr) {
//         vis[curr] = 1; // Mark the current node as visited

//         for (int neigh : adj.get(curr)) { // Iterate through all neighbors of the current node
//             if (vis[neigh] == 0) { // If the neighbor is not visited
//                 dfs(adj, vis, neigh); // Recursively visit it
//             }
//         }
//     }
// }



// // Using bfs

// import java.util.*;

// class Solution {
//     public int findCircleNum(int[][] isConnected) {
//         int n = isConnected.length; // Number of cities (or nodes)
      
//         // Create an adjacency list representation of the graph
//         ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//         for (int i = 0; i < n; i++) {
//             adj.add(new ArrayList<>()); // Initialize an empty list for each node
//         }

//         // Convert adjacency matrix to adjacency list
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (isConnected[i][j] == 1 && i != j) { // If a connection exists (excluding self-loops)
//                     adj.get(i).add(j); // Add j as a neighbor of i
//                     adj.get(j).add(i); // Add i as a neighbor of j (undirected graph)
//                 }
//             }
//         }

//         int[] vis = new int[n]; // Array to track visited nodes
//         int cnt = 0; // Count of connected components (provinces)

//         // Traverse all nodes, and if a node is unvisited, start a BFS
//         for (int i = 0; i < n; i++) {
//             if (vis[i] == 0) { // If the node is unvisited, it's a new province
//                 cnt++; // Increment province count
//                 bfs(adj, vis, i); // Perform BFS traversal
//             }
//         }

//         return cnt; // Return total number of provinces
//     }

//     // BFS function to explore all connected nodes
//     public static void bfs(ArrayList<ArrayList<Integer>> adj, int[] vis, int start) {
//         Queue<Integer> q = new LinkedList<>(); // Queue for BFS traversal
//         q.add(start); // Add the starting node
//         vis[start] = 1; // Mark the start node as visited

//         while (!q.isEmpty()) { // Continue until queue is empty
//             int curr = q.poll(); // Remove and process the front node
          
//             for (int neigh : adj.get(curr)) { // Check all neighbors of the current node
//                 if (vis[neigh] == 0) { // If the neighbor is not visited
//                     vis[neigh] = 1; // Mark it as visited
//                     q.add(neigh); // Add it to the queue for further exploration
//                 }
//             }
//         }
//     }
// }
