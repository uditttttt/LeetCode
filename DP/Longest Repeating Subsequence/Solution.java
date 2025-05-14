
class Solution {
    public int LongestRepeatingSubsequence(String s) {
        // Step 1: Copy the string to compare it with itself
        String s1 = s;
        String s2 = s;

        // Step 2: Get lengths of the strings
        int n1 = s1.length();
        int n2 = s2.length();

        // Step 3: Create a DP table where dp[i][j] stores the length of longest
        // repeating subsequence in substrings s1[0..i-1] and s2[0..j-1]
        int[][] dp = new int[n1+1][n2+1];

        // Step 4: Fill the DP table using nested loops
        for(int i=1; i<=n1; i++){
            for(int j=1; j<=n2; j++){

                // Step 5: If characters match and are from different indices,
                // we can include this character in the repeating subsequence
                if(s1.charAt(i-1) == s2.charAt(j-1) && i != j){
                    dp[i][j] = 1 + dp[i-1][j-1]; // Include character and move diagonally back
                } else {
                    // Step 6: If characters don't match or indices are same,
                    // take the maximum of excluding one character from either string
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // Step 7: The value in dp[n1][n2] contains the length of the longest repeating subsequence
        return dp[n1][n2];
    }
}


/*
🔍 High-Level Intuition
The problem is to find the longest subsequence that appears at least twice in the string, but at different positions. A subsequence is a sequence that can be derived from another string by deleting some characters without changing the order.

This is very similar to the Longest Common Subsequence (LCS) — but with a twist:
We’re comparing the string with itself, and we must not allow characters at the same index to match.

So, if characters match and are at different indices, they can be included in the repeating subsequence.


🧠 When to Use i != j in DP Problems?
Use i != j only when:

You are comparing the same string with itself

The problem asks for repeating elements or patterns

But you must not count the same index twice

✅ Final Tip (Exam Shortcut)
If in the exam you read:

"Find a repeating subsequence"

Immediately ask yourself:

“Am I allowed to use the same index twice?”

If not, then you must add i != j in your condition.

🧠 Summary of Thought Process to Reach the Code
Read the problem → "Repeating Subsequence"

Think: What other problems are similar? → LCS!

Twist: We're comparing string with itself → need i != j

Build DP Table → Same logic as LCS with i != j condition

Translate recurrence into code → Implement bottom-up DP




*/