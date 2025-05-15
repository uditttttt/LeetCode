class Solution {
    // Function to compute the Longest Common Subsequence (LCS) between original and reversed strings
    public int lcs(String s ,  String rev){
        int n1 = s.length();  // Length of original string
        int n2 = rev.length();  // Length of reversed string (same as original)

        // DP table to store LCS values between prefixes of s and rev
        int[][] dp = new int[n1+1][n2+1];  // +1 to handle base case (empty string)

        // Build the LCS table bottom-up
        for(int i=1; i<=n1; i++){  // Loop through original string
            for(int j=1; j<=n2; j++){  // Loop through reversed string
                if(s.charAt(i-1) == rev.charAt(j-1)){  
                    // If characters match, include this character in LCS
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    // If they don't match, take max from excluding current char from s or rev
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }

        // dp[n1][n2] contains the length of LCS, which is the LPS of original string
        return dp[n1][n2];
    }

    // Function to return minimum insertions to make string palindrome
    int findMinInsertions(String s) {
        // Reverse the original string to compare for palindromic subsequence
        String rev = new StringBuilder(s).reverse().toString();  // Reverse using StringBuilder

        // Compute the length of Longest Palindromic Subsequence (LPS)
        int lps = lcs(s , rev);  // LPS = LCS between s and reverse(s)

        // Number of characters to insert = characters not part of LPS
        int del = s.length() - lps;  // del holds count of non-palindromic characters

        int ins = del;  // Since we can only insert, insertions = deletions (logically)

        // Return the number of insertions needed
        return ins;
    }
}

/*

🧠 High-Level Intuition:
💬 Problem:
We are given a string s. We need to find the minimum number of insertions required to make the string a palindrome.

🔍 Key Insight:
We don’t need to directly figure out what to insert. Instead, we realize that:

The longest palindromic subsequence (LPS) in the string can be kept as-is.

For the remaining characters (not part of the LPS), we can insert matching characters to make the whole string a palindrome.

So the number of insertions needed is:

text
Copy
Edit
length of string - length of longest palindromic subsequence
🔄 Trick:
To find the LPS of a string s, we can find the LCS (Longest Common Subsequence) of s and its reverse, because a palindrome reads the same forwards and backwards.


*/