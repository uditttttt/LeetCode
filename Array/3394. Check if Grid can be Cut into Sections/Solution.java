class Solution {

  // Function to merge overlapping intervals (Leetcode 56)
  public int[][] merge(int[][] intervals) {
      // Step 1: Sort intervals based on the start value
      // This ensures that overlapping intervals appear next to each other
      Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

      // Step 2: Create a list to store the merged intervals
      List<int[]> merged = new ArrayList<>();

      // Step 3: Initialize 'prev' with the first interval
      // This keeps track of the last merged interval
      int[] prev = intervals[0];

      // Step 4: Iterate over the remaining intervals
      for (int i = 1; i < intervals.length; i++) {
          int[] interval = intervals[i]; // Get the current interval

          // Step 5: Check if 'prev' and 'interval' overlap
          if (interval[0] < prev[1]) {
              // Overlapping case: Merge by updating the end of 'prev'
              prev[1] = Math.max(prev[1], interval[1]);
          } else {
              // Non-overlapping case: Add 'prev' to the list and update 'prev'
              merged.add(prev);
              prev = interval;
          }
      }

      // Step 6: Add the last merged interval to the list
      merged.add(prev);

      // Step 7: Convert the list to a 2D array and return
      return merged.toArray(new int[merged.size()][]);         
  }

  // Function to check if we can make at least 3 valid cuts in rectangles
  public boolean checkValidCuts(int n, int[][] rectangles) {
      int n1 = rectangles.length; // Number of rectangles

      // Step 1: Create two arrays to store x and y intervals separately
      int[][] x = new int[n1][2]; // Stores x-ranges of rectangles
      int[][] y = new int[n1][2]; // Stores y-ranges of rectangles

      // Step 2: Extract x and y intervals from each rectangle
      for (int i = 0; i < n1; i++) {
          x[i][0] = rectangles[i][0]; // x1 (left boundary)
          x[i][1] = rectangles[i][2]; // x2 (right boundary)

          y[i][0] = rectangles[i][1]; // y1 (bottom boundary)
          y[i][1] = rectangles[i][3]; // y2 (top boundary)
      }

      // Step 3: Merge x intervals and check if at least 3 separate intervals exist
      int[][] r1 = merge(x);
      if (r1.length >= 3) return true; // If there are 3+ disjoint x-intervals, return true

      // Step 4: Merge y intervals and check the same condition
      int[][] r2 = merge(y);
      return r2.length >= 3; // Return true if there are 3+ disjoint y-intervals
  }
}
