class Solution {
  // Depth-First Search (DFS) function to mark all connected 'O' cells starting from the given position
  public static void dfs(char[][] board, int[][] vis, int r, int c) {
      int n = board.length;  // Number of rows
      int m = board[0].length; // Number of columns
      vis[r][c] = 1; // Mark the current cell as visited

      // Possible movements in four directions: Down, Up, Right, Left
      int[] drow = {1, -1, 0, 0};
      int[] dcol = {0, 0, 1, -1};

      // Explore the four possible directions
      for (int i = 0; i < 4; i++) {
          int nr = r + drow[i]; // New row index
          int nc = c + dcol[i]; // New column index

          // Check if the new position is within bounds, contains 'O', and is not visited
          if (nr >= 0 && nc >= 0 && nr < n && nc < m && board[nr][nc] == 'O' && vis[nr][nc] == 0) {
              dfs(board, vis, nr, nc); // Recursively call DFS for the new position
          }
      }
  }

  public void solve(char[][] board) {
      int n = board.length;  // Number of rows
      int m = board[0].length; // Number of columns     
      int[][] vis = new int[n][m]; // Visited array to track safe 'O' cells

      // Step 1: Mark all 'O' cells connected to the top border as safe
      for (int j = 0; j < m; j++) {
          if (board[0][j] == 'O') {
              dfs(board, vis, 0, j);
              vis[0][j] = 1; // Mark as visited
          }
      }

      // Step 2: Mark all 'O' cells connected to the bottom border as safe
      for (int j = 0; j < m; j++) {
          if (board[n - 1][j] == 'O') {
              dfs(board, vis, n - 1, j);
              vis[n - 1][j] = 1; // Mark as visited
          }
      }

      // Step 3: Mark all 'O' cells connected to the left border as safe
      for (int i = 0; i < n; i++) {
          if (board[i][0] == 'O') {
              dfs(board, vis, i, 0);
              vis[i][0] = 1; // Mark as visited
          }
      }

      // Step 4: Mark all 'O' cells connected to the right border as safe
      for (int i = 0; i < n; i++) {
          if (board[i][m - 1] == 'O') {
              dfs(board, vis, i, m - 1);
              vis[i][m - 1] = 1; // Mark as visited
          }
      }

      // Step 5: Convert all remaining 'O' cells (not visited) into 'X'
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
              if (board[i][j] == 'O' && vis[i][j] == 0) {
                  board[i][j] = 'X'; // Flip the 'O' to 'X' since it's surrounded
              }
          }
      }
  }
}

/*

Intuition Behind the Code:
Why DFS?

We use DFS to traverse all connected 'O' cells starting from the border cells. This helps mark all safe 'O' cells that should not be flipped.
Why do we process border cells first?

Any 'O' connected to a border cannot be surrounded. If we start from these, we can differentiate safe 'O' cells from the ones that need to be flipped.
Why use a vis[][] array?

This array keeps track of safe 'O' cells so that we don’t flip them later.
Final Step: Flipping 'O' to 'X'

Any 'O' cell that is not marked as safe (i.e., not visited) is completely surrounded, so we flip it to 'X'.
Time Complexity:
O(N * M)
Each cell is processed once during DFS, leading to an efficient solution.
This approach ensures that only the required 'O' cells remain unchanged while all enclosed ones are converted to 'X'. 🚀

*/