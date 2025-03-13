//  Using bfs

// class Solution {
//     public int[][] floodFill(int[][] image, int sr, int sc, int color) {
//         if (color == image[sr][sc])
//             return image;
//         int prev = image[sr][sc];
//         int n = image.length;
//         int m = image[0].length;
//         int[][] vis = new int[n][m];
//         Queue<int[]> q = new LinkedList<>();
//         image[sr][sc] = color;
//         q.add(new int[] { sr, sc });
//         vis[sr][sc] = 1;

//         int[] drow = { 1, -1, 0, 0 };
//         int[] dcol = { 0, 0, 1, -1 };
//         while (!q.isEmpty()) {
//             int[] arr = q.poll();
//             int r = arr[0];
//             int c = arr[1];
//             for (int i = 0; i < 4; i++) {
//                 int nrow = r + drow[i];
//                 int ncol = c + dcol[i];
//                 if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m && vis[nrow][ncol] == 0
//                         && image[nrow][ncol] == prev) {
//                     image[nrow][ncol] = color;
//                     vis[nrow][ncol] = 1;
//                     q.offer(new int[] { nrow, ncol });
//                 }

//             }
//         }

//         return image;
//     }
// }


// without using vis array - bfs

// import java.util.*;

// class Solution {
//     public int[][] floodFill(int[][] image, int sr, int sc, int color) {
//         int prev = image[sr][sc];
//         if (prev == color) return image; // No change needed

//         int n = image.length;
//         int m = image[0].length;
//         Queue<int[]> q = new LinkedList<>();
//         q.add(new int[]{sr, sc});

//         int[] drow = {1, -1, 0, 0};
//         int[] dcol = {0, 0, 1, -1};

//         while (!q.isEmpty()) {
//             int[] arr = q.poll();
//             int r = arr[0];
//             int c = arr[1];

//             if (image[r][c] == prev) { // Only color when dequeued
//                 image[r][c] = color;

//                 for (int i = 0; i < 4; i++) {
//                     int nrow = r + drow[i];
//                     int ncol = c + dcol[i];

//                     if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m && image[nrow][ncol] == prev) {
//                         q.offer(new int[]{nrow, ncol});
//                     }
//                 }
//             }
//         }

//         return image;
//     }
// }

// using dfs


class Solution {
  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
      // Store the original color of the starting pixel
      int oldColor = image[sr][sc];

      // If the new color is the same as the old color, return the image as it is.
      // This prevents infinite recursion and unnecessary operations.
      if (oldColor == color) return image;

      // Get the dimensions of the image (number of rows and columns)
      int n = image.length;
      int m = image[0].length;

      // Start DFS traversal to update the connected component with the new color
      dfs(image, sr, sc, oldColor, color);
      
      // Return the modified image after filling
      return image;
  }

  // Depth-First Search (DFS) function to fill the connected component
  public void dfs(int[][] image, int row, int col, int oldColor, int newColor) {
      int n = image.length;
      int m = image[0].length;

      // Base case: Check if the current cell is out of bounds or not the target color
      if (row < 0 || col < 0 || row >= n || col >= m || image[row][col] != oldColor) {
          return; // Stop recursion if it's out of bounds or not part of the original region
      }

      // Change the color of the current pixel to the new color
      image[row][col] = newColor;

      // Define possible movements in 4 directions (up, right, down, left)
      int[] delRow = {-1, 0, 1, 0}; // Row movement: Up (-1), No move (0), Down (+1), No move (0)
      int[] delCol = {0, 1, 0, -1}; // Column movement: No move (0), Right (+1), No move (0), Left (-1)

      // Traverse in all 4 directions
      for (int i = 0; i < 4; i++) {
          int nr = row + delRow[i]; // Compute new row index
          int nc = col + delCol[i]; // Compute new column index

          // Recursively apply DFS for the next pixel in the same region
          dfs(image, nr, nc, oldColor, newColor);
      }
  }
}

/*

Intuition Behind the Code
Base Case Handling:

If the new color is the same as the old color, return early to avoid unnecessary operations.
Check for out-of-bounds indices or pixels that do not match the original color to prevent infinite recursion.
Depth-First Search (DFS) Approach:

We use DFS because it efficiently explores all connected pixels of the same color before moving further.
We replace the color of the current pixel and then explore in all four possible directions (up, right, down, left).
Each valid neighboring pixel is recursively colored and explored.
Directional Arrays for Simplicity:

Instead of writing four separate recursive calls, we use two arrays (delRow and delCol) to generalize movement.
This avoids repetitive code and makes it easy to extend to 8-direction movement if needed.
Why DFS?
DFS is suitable when we need to process one connected component fully before moving to another.
Unlike BFS, it uses recursion (function calls) to handle traversal instead of an explicit queue.


*/