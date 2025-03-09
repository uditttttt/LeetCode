// class Solution {
//   public int minimumRecolors(String blocks, int k) {
//       // Initialize pointers for the sliding window
//       int left = 0;
//       int right = k - 1;
      
//       // Variables to track white and black block counts in the window
//       int whiteCount = 0;
//       int blackCount = 0;
//       int minRecolors = Integer.MAX_VALUE; // To store the minimum recolors needed
      
//       // Iterate through all possible k-length windows
//       while (right < blocks.length()) {
//           // Count the number of white and black blocks in the current window
//           for (int i = left; i <= right; i++) {
//               if (blocks.charAt(i) == 'B') {
//                   blackCount++; // Count black blocks
//               } else {
//                   if (blackCount >= k) {
//                       return 0; // If we already have k black blocks, no recoloring is needed
//                   }
//                   blackCount = 0; // Reset black count since a white block breaks continuity
//                   whiteCount++; // Count white blocks that need to be recolored
//               }
//           }
          
//           // Update the minimum recoloring count for current window
//           minRecolors = Math.min(minRecolors, whiteCount);
          
//           // Reset counts for the next sliding window
//           whiteCount = 0;
//           blackCount = 0;
          
//           // Slide the window one step forward
//           left++;
//           right++;
//       }
      
//       return minRecolors; // Return the minimum recoloring needed
//   }
// }

class Solution {
    public int minimumRecolors(String blocks, int k) {
        int i = 0;  // Left pointer of the sliding window
        int j = 0;  // Right pointer of the sliding window
        int w = 0;  // Count of white blocks ('W') in the current window
        int op = k; // Minimum recolors needed, initialized to k (worst case scenario)
        
        // Expand the right pointer (j) to scan through the string
        while (j < blocks.length()) {
            // Count white blocks in the current window
            if (blocks.charAt(j) == 'W') w++;  
            
            // Check if the current window size reaches k
            if (j - i + 1 == k) {
                op = Math.min(op, w); // Update the minimum recolors needed
                
                // Before sliding the window forward, remove the leftmost element's effect
                if (blocks.charAt(i) == 'W') {
                    w--; // If the removed element is 'W', decrease the count
                }
                i++; // Move the left pointer forward
            }
            
            j++; // Expand the right pointer
        }
        
        return op; // Return the minimum number of recolors needed
    }
}

/*

Intuition Behind the Code:
Sliding Window Concept:

Since we need a fixed-length window of size k, we use a two-pointer (i, j) approach to maintain the window.
i (left pointer) and j (right pointer) define the window.
Counting 'W' (White Blocks) in the Window:

As we move j (expanding the window), we count the number of 'W' blocks.
w keeps track of the number of 'W' in the current window.
Maintaining a Fixed Window of Size k:

When the window reaches size k, we check the minimum number of recolors needed (op).
Before sliding forward, we remove the effect of the leftmost character (i).
If the leftmost character is 'W', we decrement w.
Efficiency:

We slide the window from left to right, updating the count without recalculating from scratch.
Time Complexity: O(n) (we visit each character once).

 */
