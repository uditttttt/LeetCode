class Solution {

  // Depth First Search (DFS) to mark all reachable land cells
  public static void dfs(int[][] grid, int[][] vis, int r, int c) {
      // Mark the current cell as visited
      vis[r][c] = 1;
      int n = grid.length;  // Number of rows
      int m = grid[0].length;  // Number of columns

      // Define 4 possible directions: Down, Up, Right, Left
      int[] drow = {1, -1, 0, 0};
      int[] dcol = {0, 0, 1, -1};

      // Explore all 4 possible directions
      for (int i = 0; i < 4; i++) {
          int nr = r + drow[i];  // New row index
          int nc = c + dcol[i];  // New column index

          // Check if the new cell is within bounds and is an unvisited land cell
          if (nr >= 0 && nc >= 0 && nr < n && nc < m && vis[nr][nc] == 0 && grid[nr][nc] == 1) { 
              dfs(grid, vis, nr, nc);  // Recursively visit the new cell
          }
      }
  }

  public int numEnclaves(int[][] grid) {
      int n = grid.length;  // Number of rows
      int m = grid[0].length;  // Number of columns
      int[][] vis = new int[n][m];  // Visited array to track visited cells
      int count = 0;  // Count of enclosed land cells

      // Step 1: Run DFS on all boundary land cells to mark them as visited

      // Traverse the first (top) row
      for (int j = 0; j < m; j++) {
          if (grid[0][j] == 1 && vis[0][j] == 0) {  // If land cell and not visited
              dfs(grid, vis, 0, j);  // Run DFS from this boundary land cell
          }
      }

      // Traverse the last (bottom) row
      for (int j = 0; j < m; j++) {
          if (grid[n - 1][j] == 1 && vis[n - 1][j] == 0) {  // If land cell and not visited
              dfs(grid, vis, n - 1, j);  // Run DFS from this boundary land cell
          }
      }

      // Traverse the first (left) column
      for (int i = 0; i < n; i++) {
          if (grid[i][0] == 1 && vis[i][0] == 0) {  // If land cell and not visited
              dfs(grid, vis, i, 0);  // Run DFS from this boundary land cell
          }
      }

      // Traverse the last (right) column
      for (int i = 0; i < n; i++) {
          if (grid[i][m - 1] == 1 && vis[i][m - 1] == 0) {  // If land cell and not visited
              dfs(grid, vis, i, m - 1);  // Run DFS from this boundary land cell
          }
      }

      // Step 2: Count the number of land cells that are NOT visited (enclaved land)
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
              if (grid[i][j] == 1 && vis[i][j] == 0) {  // Land cell not visited by DFS
                  count++;  // This cell is an enclosed land cell
              }
          }
      }

      return count;  // Return the count of enclosed land cells
  }
}

/*

Intuition Behind the Approach
Identifying the Problem:

We have a grid of 1s (land) and 0s (water).
Our goal is to count land cells (1s) that are completely surrounded by water (0s) and not connected to the boundary.
Key Observations:

Any land (1) connected to the grid boundary cannot be enclosed.
We must find and remove such land by marking it as visited.
Approach:

Step 1: Use DFS from boundary land cells to mark all reachable land as visited.
Step 2: Count the remaining unvisited land cells, as they are the enclaves.
Why This Works?
✅ DFS ensures all connected land cells are marked
✅ Only unvisited land cells are counted as enclaves
✅ Efficient O(n × m) complexity (since each cell is visited once)

This method correctly finds and counts enclaved land cells in the grid. 🚀

*/