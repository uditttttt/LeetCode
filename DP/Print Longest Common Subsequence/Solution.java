// coding ninja
public class Solution {
    public static String findLCS(int n, int m, String s1, String s2){
        // Create a 2D DP table to store LCS lengths for substrings of s1 and s2
        int dp[][] = new int[n+1][m+1]; // dp[i][j] will store LCS length of s1[0..i-1] and s2[0..j-1]

        // Fill the dp table using bottom-up dynamic programming
        for(int i = 1; i <= n; i++){ // loop over string s1
            for(int j = 1; j <= m; j++){ // loop over string s2

                // If characters match, we extend the LCS by 1
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // take diagonal value and add 1
                } else {
                    // If they don't match, take the maximum from left or top
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Now we backtrack from dp[n][m] to build the actual LCS string
        int i = n;
        int j = m;
        String s = ""; // this will store the LCS in reverse order

        while(i > 0 && j > 0){ // while we are not at the top or left border of the dp table
            if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                // If characters match, it's part of LCS, add to result
                s = s + s1.charAt(i - 1); // add character to result (currently in reverse order)
                i--; // move diagonally up
                j--;
            } else {
                // If not matched, move in direction of greater LCS value
                if(dp[i - 1][j] > dp[i][j - 1]){
                    i--; // move up
                } else {
                    j--; // move left
                }
            }
        }

        // Since we added characters in reverse order, reverse the string before returning
        return new StringBuilder(s).reverse().toString();
    }
}
/*

?? Intuition:
We are given two strings s1 and s2 and we need to find their Longest Common Subsequence (LCS).

LCS means the longest sequence of characters that appear in the same relative order (but not necessarily contiguous) in both strings.

We solve this using Dynamic Programming:

We use a 2D DP table to store lengths of LCS for all substrings of s1 and s2.

Then we backtrack through the DP table to construct the LCS string.

 Summary of Thought Process:
DP Table Idea:

We break the problem into subproblems: LCS of prefixes of s1 and s2.

dp[i][j] = LCS length of s1[0..i-1] and s2[0..j-1].

Filling the Table:

If characters match, add 1 to LCS of previous chars (diagonal).

If not, take max of excluding current char from either string.

Backtracking:

Start from bottom-right of the table.

Move diagonally if matched.

Else move to direction where LCS is greater (either up or left).

Reversal:

Since we built the string from end to start, we reverse it at the end.



*/