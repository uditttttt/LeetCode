class Solution {
    public int minOperations(String s1, String s2) {
        // Step 1: Get lengths of the strings
        int n1 = s1.length();
        int n2 = s2.length();

        // Step 2: Create a DP table to store LCS values
        // dp[i][j] = LCS of s1[0..i-1] and s2[0..j-1]
        int dp[][] = new int[n1+1][n2+1];

        // Step 3: Fill the DP table using LCS logic
        for(int i=1; i<=n1; i++){
            for(int j=1; j<=n2; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    // If characters match, include in LCS and move diagonally
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    // Else, take the max by ignoring a character from s1 or s2
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // Step 4: The length of LCS is stored at dp[n1][n2]
        int lcs = dp[n1][n2];

        // Step 5: Deletions = characters in s1 not in LCS
        int del = n1 - lcs;

        // Step 6: Insertions = characters in s2 not in LCS
        int ins = n2 - lcs;

        // Step 7: Total operations = deletions + insertions
        return del + ins;
    }
}

/*

 🧠 Problem Understanding
You’re given two strings: s1 and s2.
You want to convert s1 to s2 using only insertions and deletions.

To do that optimally:

Don't change the common parts (LCS)

Remove the rest from s1 → Deletions

Add missing characters from s2 → Insertions

So, the minimum operations =
👉 deletions + insertions = (len(s1) - LCS) + (len(s2) - LCS)


🔥 Intuition Recap
Why LCS?
Because we want to keep as much as possible common between the strings.

Why s1.length() - LCS deletions?
Characters in s1 not in LCS must be deleted.

Why s2.length() - LCS insertions?
Characters in s2 not in LCS must be added.

🧠 Think Like This in Exams
Problem Type? → String transformation using insert/delete.

Observation? → You want to preserve common parts.

Tool? → Use Longest Common Subsequence (LCS).

Rest? → Whatever’s not in LCS = must be inserted/deleted.



*/
