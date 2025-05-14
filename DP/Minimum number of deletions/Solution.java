class Solution {
    
    // This method calculates the Longest Common Subsequence (LCS)
    // between original string 's' and its reverse 'rev'
    public int lcs(String s , String rev){
        int n1 = s.length(); // length of original string
        int n2 = rev.length(); // length of reversed string (same as s)

        // Create a 2D array (dp table) to store LCS values
        // dp[i][j] = length of LCS of s[0..i-1] and rev[0..j-1]
        int[][] dp = new int[n1+1][n2+1];

        // Fill the dp table using bottom-up approach
        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2; j++){

                // If current characters of both strings match,
                // add 1 to the LCS of previous characters
                if(s.charAt(i-1) == rev.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    // Else take the max LCS from either:
                    // 1. skipping current char from 's'
                    // 2. skipping current char from 'rev'
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // The bottom-right cell contains LCS of full strings
        return dp[n1][n2];
    }

    // This method calculates the minimum number of deletions
    // required to make the string a palindrome
    public int minDeletions(String s) {

        // Step 1: Reverse the original string
        String rev = new StringBuilder(s).reverse().toString();

        // Step 2: Find the Longest Palindromic Subsequence (LPS)
        // LPS is same as LCS of s and its reverse
        int lps = lcs(s , rev);

        // Step 3: Subtract LPS from total length
        // The remaining characters must be deleted to make it a palindrome
        // Your goal is to make the given string into a palindrome by deleting 
        // as few characters as possible. so for that we need to find lonngest
        // palindromic subsequence  so that we need to delete min. no of charcters
        // from orginal string s
        int ans = s.length() - lps;

        // Return the result
        return ans;
    }
}

/*

🔁 Quick Recap:
We reverse the string because a palindrome reads the same forward and backward.

Then, we find the LCS of the original and reversed strings — this gives us the longest part already palindromic.

Subtracting this from the original length tells us how many characters need to be deleted.

Would you like a dry run of this code with a sample input like "aebcbda" to see how values are filled in the dp table?


*/