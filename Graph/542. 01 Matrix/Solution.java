import java.util.*;

class Solution {
    // Custom class to store information about a cell in the matrix
    public static class Info {
        int r; // Row index
        int c; // Column index
        int s; // Distance from the nearest 0

        // Constructor to initialize row, column, and distance
        public Info(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length; // Number of rows
        int m = mat[0].length; // Number of columns
        
        int[][] vis = new int[n][m]; // Visited array to keep track of processed cells
        int[][] dis = new int[n][m]; // Distance matrix to store the shortest distance from 0
        
        Queue<Info> q = new LinkedList<>(); // Queue for BFS traversal

        // Step 1: Initialize the queue with all 0s in the matrix and mark them as visited
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) { 
                    q.offer(new Info(i, j, 0)); // Push 0s with distance 0 into the queue
                    vis[i][j] = 1; // Mark these cells as visited
                }
            }
        }

        // Step 2: Perform BFS to compute the shortest distance to the nearest 0
        while (!q.isEmpty()) {
            Info curr = q.poll(); // Get the front element from the queue
            int r = curr.r; // Current row
            int c = curr.c; // Current column
            int s = curr.s; // Current distance
            dis[r][c] = s; // Update the distance matrix

            // Arrays to explore 4 possible directions (up, down, left, right)
            int[] drow = {1, -1, 0, 0}; 
            int[] dcol = {0, 0, 1, -1};

            // Step 3: Explore all 4 adjacent cells
            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i]; // New row index
                int ncol = c + dcol[i]; // New column index

                // Check if the new cell is within bounds and is unvisited and has value 1
                if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m 
                    && mat[nrow][ncol] == 1 && vis[nrow][ncol] == 0) {
                    
                    q.add(new Info(nrow, ncol, s + 1)); // Add it to the queue with updated distance
                    vis[nrow][ncol] = 1; // Mark it as visited
                }
            }
        }

        // Step 4: Return the distance matrix
        return dis;
    }
}
/*

### **Intuition & Reasoning**
1. **Use of BFS (Breadth-First Search):**  
   - Since we need the shortest distance to the nearest `0`, BFS is the best choice because it explores all neighboring nodes level by level.
   - Unlike DFS (which can go deeper before exploring other nodes), BFS ensures the shortest path is found first.

2. **Initialization:**
   - We first find all `0`s in the matrix and push them into a queue with a distance of `0` because they are already at the nearest `0`.
   - We also mark these cells as visited so they are not processed again.

3. **Processing the Queue (BFS Traversal):**
   - We keep processing the queue, popping an element, and checking its 4 adjacent cells (up, down, left, right).
   - If an adjacent cell is `1` and unvisited, we push it into the queue with a distance `s + 1` and mark it as visited.

4. **Why use a `vis[][]` array?**
   - To prevent reprocessing of cells, which could lead to unnecessary computation.

5. **Why update `dis[][]` inside the loop?**
   - The BFS ensures that the first time a `1` is reached, it is the shortest possible path from a `0`, so we directly store that in `dis[][]`.

### **Complexity Analysis**
- **Time Complexity:** \( O(n \times m) \)  
  - Each cell is processed at most once since it is marked as visited after the first processing.
- **Space Complexity:** \( O(n \times m) \)  
  - The queue can hold at most all elements of the matrix in the worst case.

This approach ensures an efficient and optimal solution using BFS. 🚀
*/ 