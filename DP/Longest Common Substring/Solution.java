
// User function Template for Java


// Recursive code

// class Solution {
//     public int longestCommonSubstr(String s1, String s2) {
//         int maxLen = 0;

//         // Try every pair of starting indexes (i, j)
//         for (int i = 0; i < s1.length(); i++) {
//             for (int j = 0; j < s2.length(); j++) {
//                 // Update max length with the result of matching substrings starting at i and j
//                 maxLen = Math.max(maxLen, countMatch(s1, s2, i, j));
//             }
//         }

//         return maxLen;
//     }

//     // Recursive function to count the length of common substring from position i and j
//     private int countMatch(String s1, String s2, int i, int j) {
//         // Base case: if either index is out of bounds, return 0
//         if (i >= s1.length() || j >= s2.length()) return 0;

//         // If characters match, move diagonally and increase count
//         if (s1.charAt(i) == s2.charAt(j)) {
//             return 1 + countMatch(s1, s2, i + 1, j + 1);
//         } else {
//             // If characters do not match, the common substring ends here
//             return 0;
//         }
//     }
// }


class Solution {
  public int longestCommonSubstr(String s1, String s2) {
      // Get lengths of both strings
      int n1 = s1.length();
      int n2 = s2.length();

      // To store the length of the longest common substring found so far
      int maxLen = 0;

      // Create a 2D dp array of size (n1+1) x (n2+1)
      // dp[i][j] = length of longest common substring ending at s1[i-1] and s2[j-1]
      int[][] dp = new int[n1+1][n2+1]; // Initialized to 0 by default

      // Fill the dp table
      for(int i = 1; i <= n1; i++) { // loop from 1 to n1 (we use i-1 for indexing into s1)
          for(int j = 1; j <= n2; j++) { // loop from 1 to n2 (we use j-1 for indexing into s2)
              
              // If characters match at current positions in s1 and s2
              if(s1.charAt(i-1) == s2.charAt(j-1)) {
                  // Extend the previous matching substring
                  dp[i][j] = 1 + dp[i-1][j-1];

                  // Update maxLen if we found a longer common substring
                  maxLen = Math.max(maxLen, dp[i][j]);
              } else {
                  // If characters don't match, reset the length to 0
                  dp[i][j] = 0;
              }
          }
      }

      // Return the length of the longest common substring found
      return maxLen;
  }
}

/*

✅ So we use maxLen to keep track of:

maxLen = max of all dp[i][j] values

Because we don’t know at which point the longest matching substring will end — it could 
be anywhere in the matrix.

*/