class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        // Step 1: Get lengths of both strings
        int n1 = str1.length();
        int n2 = str2.length();

        // Step 2: Initialize DP table to store LCS lengths
        // dp[i][j] = LCS length of str1[0..i-1] and str2[0..j-1]
        int[][] dp = new int[n1+1][n2+1];

        // Step 3: Fill the DP table for LCS
        for(int i = 1; i <= n1; i++) {
            for(int j = 1; j <= n2; j++) {
                // If characters match, include in LCS and move diagonally
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    // Else take the max of ignoring current char from either string
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // Step 4: Backtrack to build the shortest common supersequence
        int i = n1;
        int j = n2;
        String ans = ""; // We'll build the answer in reverse

        while(i > 0 && j > 0) {
            // Case 1: If characters match, take it (part of LCS)
            if(str1.charAt(i-1) == str2.charAt(j-1)) {
                ans = ans + str1.charAt(i-1); // Add to result
                i--; // Move diagonally up-left
                j--;
            } 
            // Case 2: LCS came from top → include str1[i-1]
            else if(dp[i-1][j] > dp[i][j-1]) {
                ans = ans + str1.charAt(i-1); // Add str1 character (non-matching)
                i--; // Move up
            } 
            // Case 3: LCS came from left → include str2[j-1]
            else {
                ans = ans + str2.charAt(j-1); // Add str2 character (non-matching)
                j--; // Move left
            }
        }

        // Step 5: Add remaining characters (if any) from str1
        while(i > 0) {
            ans = ans + str1.charAt(i-1);
            i--;
        }

        // Step 6: Add remaining characters (if any) from str2
        while(j > 0) {
            ans = ans + str2.charAt(j-1);
            j--;
        }

        // Step 7: The string is built in reverse, so reverse it to get final answer
        return new StringBuilder(ans).reverse().toString();
    }
}
/*

🔍 Why use LCS to find SCS?
🔴 Problem:
We want to find the shortest string that contains both str1 and str2 as subsequences.

A subsequence means you can delete characters (but can't change the order).

🔑 Key Idea:
If we just concatenate str1 + str2, it will always contain both — but it may repeat common characters.

To make it shorter, we should avoid duplicating the common characters.

That’s where LCS helps.

💡 Think of it like this:
Let's say:
str1 = "abac"

str2 = "cab"

Their LCS is "ab" → appears in both.

Now, to build the shortest common supersequence, we can:

Include all characters from both strings

But do NOT repeat the characters that are in LCS

✅ Formula:
Length of SCS = str1.length() + str2.length() - LCS.length()

Why?

You start with all characters from both strings.

LCS characters are common, so we count them once, not twice.

📌 Why we trace back like that?
In the DP backtracking part:

If characters match, they are part of LCS → take once and move diagonally.

If they don’t match, we check:

dp[i-1][j] > dp[i][j-1] → LCS came from top → take from str1

dp[i][j-1] > dp[i-1][j] → LCS came from left → take from str2

This ensures that we:

Build only one copy of common characters

And include all non-common characters from both strings



*/