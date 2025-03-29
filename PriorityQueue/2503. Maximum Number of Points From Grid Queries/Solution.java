// // This will give TLE

// import java.util.*;

// class Solution {
    
//     // BFS function to count points (cells) that are strictly less than `val`
//     int bfs(int[][] grid, int val, int n, int m) {
//         Queue<int[]> q = new LinkedList<>(); // Queue for BFS traversal
//         int[][] vis = new int[n][m]; // Visited matrix to track explored cells
        
//         // If the starting cell (0,0) itself is greater than or equal to `val`, return 0
//         if (grid[0][0] >= val) return 0;

//         // Start BFS from the top-left corner (0,0)
//         q.add(new int[] { 0, 0 });
//         vis[0][0] = 1; // Mark (0,0) as visited

//         int points = 0; // Counter to store the number of valid cells
        
//         // BFS traversal
//         while (!q.isEmpty()) {
//             int[] curr = q.poll(); // Extract the front element from the queue
//             int r = curr[0]; // Row index
//             int c = curr[1]; // Column index

//             points++; // Count this cell as valid

//             // Directions for moving in 4 possible ways (Up, Down, Left, Right)
//             int[] dr = { 1, -1, 0, 0 };
//             int[] dc = { 0, 0, 1, -1 };

//             // Explore all 4 possible directions
//             for (int i = 0; i < 4; i++) {
//                 int nr = r + dr[i]; // New row index
//                 int nc = c + dc[i]; // New column index
                
//                 // Check if the new cell is within bounds, not visited, and strictly less than `val`
//                 if (nr >= 0 && nc >= 0 && nr < n && nc < m && vis[nr][nc] == 0 && grid[nr][nc] < val) {
//                     q.add(new int[] { nr, nc }); // Add the new cell to the queue for exploration
//                     vis[nr][nc] = 1; // Mark the new cell as visited
//                 }
//             }
//         }
//         return points; // Return the total count of valid cells
//     }

//     // Main function to process multiple queries
//     public int[] maxPoints(int[][] grid, int[] queries) {
//         int n = queries.length; // Number of queries
//         int r = grid.length;    // Number of rows in the grid
//         int c = grid[0].length; // Number of columns in the grid
        
//         int[] ans = new int[n]; // Array to store the result for each query
        
//         // Process each query independently
//         for (int i = 0; i < n; i++) {
//             ans[i] = bfs(grid, queries[i], r, c); // Run BFS for the given query value
//         }

//         return ans; // Return the array containing results for all queries
//     }
// }



// // Dfs appraoch but this will also give TLE

// import java.util.*;

// class Solution {
//     // DFS function to traverse and count valid points
//     int dfs(int[][] grid, int r, int c, int val, int[][] vis, int n, int m) {
//         // Base case: Check if we are out of bounds, already visited, or grid[r][c] is >= val
//         if (r < 0 || c < 0 || r >= n || c >= m || vis[r][c] == 1 || grid[r][c] >= val) 
//             return 0;

//         vis[r][c] = 1; // Mark cell as visited
//         int points = 1; // Count this cell as valid
        
//         // Move in 4 possible directions (Up, Down, Left, Right)
//         int[] dr = {1, -1, 0, 0};
//         int[] dc = {0, 0, 1, -1};

//         for (int i = 0; i < 4; i++) {
//             int nr = r + dr[i];
//             int nc = c + dc[i];
//             points += dfs(grid, nr, nc, val, vis, n, m); // Recursive DFS call
//         }

//         return points; // Return the total count of valid cells
//     }

//     public int[] maxPoints(int[][] grid, int[] queries) {
//         int n = grid.length, m = grid[0].length;
//         int[] ans = new int[queries.length];

//         // Process each query separately
//         for (int i = 0; i < queries.length; i++) {
//             int[][] vis = new int[n][m]; // Reset visited array for each query
//             if (grid[0][0] < queries[i]) // Only start DFS if (0,0) is valid
//                 ans[i] = dfs(grid, 0, 0, queries[i], vis, n, m);
//         }

//         return ans; // Return the result for all queries
//     }
// }




import java.util.*;

class Solution {
    // Define possible 4 directions: Right, Left, Down, Up
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int[] maxPoints(int[][] grid, int[] queries) {
        int q = queries.length; // Number of queries
        int n = grid.length;    // Number of rows in the grid
        int m = grid[0].length; // Number of columns in the grid
        int[] result = new int[q]; // Array to store results for each query

        // Step 1: Sort queries while keeping track of their original index
        int[][] sortedQ = new int[q][2];

        for (int i = 0; i < q; i++) {
            sortedQ[i][0] = queries[i]; // Store the query value
            sortedQ[i][1] = i;          // Store its original index
        }

        // Sort queries in ascending order of their value
        Arrays.sort(sortedQ, Comparator.comparingInt(a -> a[0]));
        
        // Step 2: Use a Min-Heap (Priority Queue) to process grid cells in increasing order of their values
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Visited matrix to ensure we do not process the same cell multiple times
        boolean[][] vis = new boolean[n][m];

        int points = 0; // Variable to store the number of reachable points

        // Start BFS traversal from the top-left corner (0,0)
        pq.offer(new int[]{grid[0][0], 0, 0}); // Push the first cell {value, row, column} into priority queue
        vis[0][0] = true; // Mark (0,0) as visited

        // Step 3: Process each query one by one
        for (int i = 0; i < q; i++) {
            int queryValue = sortedQ[i][0]; // Get the current query value
            int idx = sortedQ[i][1];        // Get the original index of this query

            // Step 4: Process all grid cells in the priority queue that have a value < queryValue
            while (!pq.isEmpty() && pq.peek()[0] < queryValue) {
                int[] top = pq.poll(); // Remove the smallest valued cell from heap
                int r = top[1]; // Extract row index
                int c = top[2]; // Extract column index
                points++; // Increase count of reachable cells

                // Step 5: Explore all 4 directions
                for (int[] dir : directions) {
                    int nr = r + dir[0]; // Compute new row index
                    int nc = c + dir[1]; // Compute new column index

                    // Check if new cell is within grid bounds and has not been visited
                    if (nr >= 0 && nc >= 0 && nr < n && nc < m && !vis[nr][nc]) {
                        pq.offer(new int[]{grid[nr][nc], nr, nc}); // Add the new cell to priority queue
                        vis[nr][nc] = true; // Mark it as visited
                    }
                }
            }
            // Store the total reachable points for this query at its original index
            result[idx] = points;
        }

        return result; // Return the result array with answers for all queries
    }
}

/*

Detailed Explanation of the Intuition and Reasoning
Sorting Queries:

Since queries are given in random order, we sort them first.

This allows us to process smaller queries first and build upon previous results efficiently.

We store the original index of each query to place the results correctly.

Using a Min-Heap (Priority Queue):

We process grid cells in increasing order of their values.

This ensures that smaller values are expanded first, preventing unnecessary recomputation.

Breadth-First Search (BFS) with Priority Queue:

Instead of visiting all cells for every query (which is slow), we expand the grid only when needed.

When processing a query, we only consider new cells that have values less than the query value.

Visited Array:

Ensures that each cell is processed only once, avoiding redundant computations.

Query Processing:

We process each query in increasing order and update the count of reachable cells accordingly.

This avoids re-exploring the grid for each query, making the approach efficient.

⏱️ Time Complexity Analysis
Sorting Queries: O(Q log Q)

Priority Queue Operations: O(NM log(NM))

Total Complexity: O((N*M + Q) log(N*M))

This is much faster than DFS/BFS solutions that process every query separately.

🚀 Why This Approach Avoids TLE (Time Limit Exceeded)?
We do NOT perform a separate BFS/DFS for each query.

Instead, we incrementally process cells using a priority queue, ensuring efficient expansion.

The sorted query processing ensures we never reprocess previously explored cells.

*/