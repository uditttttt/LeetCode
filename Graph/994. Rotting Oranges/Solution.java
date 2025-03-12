import java.util.*;

class Solution {
    // Define a class to store the row index, column index, and time
    class Pair {
        int r; // Row index
        int c; // Column index
        int tm; // Time taken to rot

        public Pair(int r, int c, int tm) {
            this.r = r;
            this.c = c;
            this.tm = tm;
        }
    }

    public int orangesRotting(int[][] grid) {
        int n = grid.length; // Number of rows in the grid
        int m = grid[0].length; // Number of columns in the grid

        int[][] vis = new int[n][m]; // Visited array to track rotten oranges
        int cnt = 0; // Count of fresh oranges that became rotten
        int cntFresh = 0; // Count of all initial fresh oranges

        Queue<Pair> q = new LinkedList<>(); // BFS queue to process rotten oranges

        // Step 1: Traverse the grid to find initial rotten oranges and count fresh ones
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    // Found a rotten orange, add to queue with time 0
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = 2; // Mark as visited (already rotten)
                } else if (grid[i][j] == 1) {
                    // Found a fresh orange, count it
                    cntFresh++;
                }
            }
        }

        // Step 2: Define movement directions (up, down, left, right)
        int[] drow = { 1, -1, 0, 0 }; // Row movement
        int[] dcol = { 0, 0, 1, -1 }; // Column movement

        // not necessary as last removed (processed) rotten orange will have
        // the maximum time since BFS spreads level by level.
        // totalTime = Math.max(totalTime, t);
        int totalTime = 0; // Track time taken for all oranges to rot

        // Step 3: BFS traversal to rot all fresh oranges level by level
        while (!q.isEmpty()) {
            Pair curr = q.poll(); // Get the first element in the queue
            int r = curr.r; // Current row
            int c = curr.c; // Current column
            int t = curr.tm; // Time taken to reach this orange

            totalTime = t; // Store last removed time (BFS ensures this is max time)

            // Step 4: Check all 4 possible directions for fresh neighbors
            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i]; // New row position
                int ncol = c + dcol[i]; // New column position

                // Step 5: Check if the neighbor is within bounds and fresh and has not visted
                if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m
                        && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {

                    vis[nrow][ncol] = 2; // Mark the fresh orange as rotten
                    q.offer(new Pair(nrow, ncol, t + 1)); // Add to queue with time incremented
                    cnt++; // Increase count of fresh oranges that became rotten
                }
            }
        }

        // Step 6: If all fresh oranges have rotted, return total time, else return -1
        return cnt == cntFresh ? totalTime : -1;
    }
}

/*

Intuition
The problem is a multi-source BFS problem because multiple rotten oranges (2s) can start rotting fresh ones (1s) simultaneously.
We need to track the time taken for all fresh oranges to rot.
We use a queue (BFS) to spread the rot level by level, ensuring we process all oranges at the earliest possible time.
If there are still fresh oranges left after BFS, return -1.

Key Observations
BFS is used instead of DFS because BFS ensures that we rot the oranges level by level, guaranteeing the correct minimum time.
We don't use Math.max() for totalTime because the last removed element from the queue naturally has the maximum time.
The visited array (vis[][]) ensures we don't process the same cell twice, preventing infinite loops and incorrect updates.
If all fresh oranges (cntFresh) rot (cnt == cntFresh), return totalTime, else return -1 (some fresh oranges are unreachable).

*/