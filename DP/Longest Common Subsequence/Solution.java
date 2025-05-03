// Recursive method will give TLE

// A subsequence is not necessarily contiguous, but the order must be preserved.

// class Solution {

//     // Recursive function to find the length of LCS
//     public static int solve(String s1, String s2, int n, int m) {

//         // Base Case: if any string is empty, LCS is 0
//         if (n == 0 || m == 0) return 0;

//         // If the last characters match, include this character in LCS
//         if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
//             // Move both strings one step back and add 1 to the result
//             return 1 + solve(s1, s2, n - 1, m - 1);
//         } else {
//             // If characters don’t match, try two possibilities:
//             // 1. Ignore last char of s1
//             // 2. Ignore last char of s2
//             // Take the max of both possibilities
//             return Math.max(
//                 solve(s1, s2, n - 1, m),    // exclude last char from s1
//                 solve(s1, s2, n, m - 1)     // exclude last char from s2
//             );
//         }
//     }

//     // Function called by the main method or test
//     static int lcs(String s1, String s2) {
//         // Get lengths of both strings
//         int n = s1.length();
//         int m = s2.length();

//         // Call recursive function to compute LCS
//         return solve(s1, s2, n, m);
//     }
// }

/*

🧠 Thinking Recap in Comments:
Use recursion when you have to try all possibilities.

Ask yourself at each step:
"Do the current characters match?"

✅ Yes → include and move both pointers

❌ No → try skipping each one and take max

Stop when any string becomes empty → base case.

*/



// //  Memoization method

// class Solution {

//     // Recursive function with memoization to calculate LCS length
//     public static int solve(String s1, String s2, int n, int m, int[][] dp) {

//         // ✅ Base Case:
//         // If length of any string becomes 0, no common subsequence possible
//         if(n == 0 || m == 0) return 0;

//         // ✅ Memoization check:
//         // If the result is already computed, return it
//         if(dp[n][m] != -1) return dp[n][m];

//         // ✅ If characters match at current position:
//         // include this character in LCS and move diagonally (n-1, m-1)
//         if(s1.charAt(n - 1) == s2.charAt(m - 1)) {
//             dp[n][m] = 1 + solve(s1, s2, n - 1, m - 1, dp);
//         } 
//         else {
//             // ✅ If characters don’t match:
//             // take max of two choices:
//             // 1. Exclude character from s1 and move (n-1, m)
//             // 2. Exclude character from s2 and move (n, m-1)
//             dp[n][m] = Math.max(
//                 solve(s1, s2, n - 1, m, dp),
//                 solve(s1, s2, n, m - 1, dp)
//             );
//         }

//         // Return the final answer for current (n, m)
//         return dp[n][m];
//     }

//     // Main function to be called with two strings
//     static int lcs(String s1, String s2) {
//         int n = s1.length();  // length of first string
//         int m = s2.length();  // length of second string

//         // ✅ Create 2D DP array of size (n+1) x (m+1)
//         // dp[i][j] will store LCS of s1[0...i-1] and s2[0...j-1]
//         int[][] dp = new int[n + 1][m + 1];

//         // ✅ Initialize all values with -1 to mark them as uncomputed
//         for(int[] row : dp) {
//             Arrays.fill(row, -1);
//         }

//         // ✅ Call the recursive LCS function with memoization
//         return solve(s1, s2, n, m, dp);
//     }
// }



// Tabulation method

class Solution {

  // Main function to find the length of the Longest Common Subsequence (LCS)
  static int lcs(String s1, String s2) {

      // Step 1: Get the lengths of the two strings
      int n = s1.length(); 
      int m = s2.length();

      // Step 2: Create a 2D DP array to store subproblem results
      // dp[i][j] will hold the LCS length for s1[0...(i-1)] and s2[0...(j-1)]
      int dp[][] = new int[n + 1][m + 1];  // Initialized with 0 by default in Java

      // Thought Process:
      // We are going to fill this table in a bottom-up manner
      // dp[0][*] and dp[*][0] are already 0 (when one string is empty, LCS is 0)

      // Step 3: Loop through each character of both strings
      for (int i = 1; i <= n; i++) {
          for (int j = 1; j <= m; j++) {

              // Step 4: If characters match at current position in both strings
              if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                  // We can include this character in the LCS
                  // So we take 1 + dp[i-1][j-1] (go diagonally back in the table)
                  dp[i][j] = 1 + dp[i - 1][j - 1];

              } else {
                  // If characters do not match, we have two options:
                  // 1. Ignore current character from s1 → dp[i-1][j]
                  // 2. Ignore current character from s2 → dp[i][j-1]
                  // We take the max of both to get the longest subsequence so far
                  dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
              }
          }
      }

      // Step 5: The value at dp[n][m] contains the answer: LCS of s1 and s2
      return dp[n][m];
  }
}

/*

💡 Intuition Behind the Approach
What is LCS?
The longest sequence that appears in the same order (not necessarily continuous) in both strings.

How do we compare characters?
We go from beginning to end of both strings and compare:

If characters match, we include them in the result and move diagonally.

If they don’t match, we have two options — skip one from s1 or one from s2.

Why use a 2D array?
To store the results of smaller subproblems, so we don't recompute them again. This is Dynamic Programming.

Why bottom-up?
It’s efficient and avoids the overhead of recursion. You can build the solution for longer strings by solving smaller strings first.

🚀 Thought Process to Follow in Exam
Read carefully: You’re given two strings → think LCS.

Recognize the pattern: Characters need to match in order → think subsequence.

Ask: Can you make decisions at each index? Yes → Recursion + DP.

Optimize: Use 2D dp table, fill it from smallest case (empty string) upward.

Remember: Matching → diagonal move with +1, Not matching → take max of top or left.


*/