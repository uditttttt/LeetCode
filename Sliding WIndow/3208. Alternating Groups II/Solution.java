// // Brute force  give tle

// class Solution {
//     public int numberOfAlternatingGroups(int[] colors, int k) {
//         int n = colors.length;  // Store the length of the array
//         int count = 0;          // To count the number of valid alternating groups

//         // Iterate through each possible starting position in the circular array
//         for (int i = 0; i < n; i++) {
//             boolean isAlternating = true; // Assume the current group is alternating

//             // Check the k-sized window starting from index `i`
//             for (int j = 0; j < k - 1; j++) {
//                 int currIndex = (i + j) % n;       // Get the current index (handle circular array)
//                 int nextIndex = (i + j + 1) % n;   // Get the next index in the group (circular)

//                 // If two consecutive elements are the same, it's not alternating
//                 if (colors[currIndex] == colors[nextIndex]) {
//                     isAlternating = false;  // Mark as invalid
//                     break; // No need to check further in this group
//                 }
//             }

//             // If the group is valid, increment the count
//             if (isAlternating) {
//                 count++;
//             }
//         }

//         return count; // Return the total count of alternating groups
//     }
// }


class Solution {
  public int numberOfAlternatingGroups(int[] colors, int k) {
      int N = colors.length + k - 1;  // Create an extended array of size `n + k - 1`
      int[] color = new int[N];       // New array to handle circular behavior

      // Fill the extended array with original colors, repeating the start for circular handling
      for (int i = 0; i < N; i++) {
          color[i] = colors[i % colors.length]; // Use modulo to wrap around
      }

      int i = 0;  // Start index of the sliding window
      int j = 1;  // Expanding pointer
      int res = 0; // Result counter for valid alternating groups

      // Sliding window technique
      while (j < color.length) {
          // If two adjacent elements are the same, reset the window
          if (color[j] == color[j - 1]) {
              i = j; // Move `i` to break the invalid sequence
              j++;   // Move `j` forward
              continue; // Skip further checking
          }

          // If the window size reaches `k`, it's a valid alternating group
          if (j - i + 1 == k) {
              res++;  // Increment valid alternating group count
              i++;    // Shrink the window from the left
          }
          j++; // Expand the window from the right
      }

      return res; // Return the total count of alternating groups
  }
}

/*
 
Intuition
Circular Handling Trick: Instead of checking wraparounds using modulo (%), we extend the array artificially.
We create a new array of size N = colors.length + k - 1, which duplicates the start of the array.
This helps avoid modulo operations and makes checking contiguous subarrays easier.

Two-Pointer Sliding Window:

We use two pointers (i and j) to find valid alternating groups efficiently.
Instead of checking every possible subarray, we expand and shrink the window dynamically.
If the current segment violates alternation, we reset the window.
If a valid segment of k is found, we count it and move forward.

 */