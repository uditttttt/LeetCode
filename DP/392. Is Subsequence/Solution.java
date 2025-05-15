// Greedy Approach

// class Solution {
//     public boolean isSubsequence(String s, String t) {
//         int i = 0;
//         int j = 0;
//         int check = 0;
//         while( j < t.length() && i < s.length()){
//             if(s.charAt(i) == t.charAt(j)){
//                 i++;
//             }  
//             j++;     
//         }

//         return i == s.length();
        
//     }
// }

// class Solution {
//     public boolean isSubsequence(String s, String t) {
//         if(s.length() == 0) {
//             return true;
//         } else if(s.length() > t.length()) {
//             return false;
//         } else {
//             int i=0;
//             int j=0;
//             char[] sArr = s.toCharArray();
//             char[] tArr = t.toCharArray();
//             while(i<sArr.length && j<tArr.length) {
//                 if(sArr[i] == tArr[j]) {
//                     i++;
//                     j++;
//                 } else {
//                     j++;
//                 }
//             }
//             if(i == sArr.length) {
//                 return true;
//             } else {
//                 return false;
//             }
//         }
//     }
// }


// Dp solution

class Solution {
    public static int lcs(String s , String t){
        int n1 = s.length();
        int n2 = t.length();

        int[][] dp = new int[n1+1][n2+1];

        for(int i=1; i<=n1; i++){
            for(int j=1; j<=n2; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }

        return dp[n1][n2];

    }
    public boolean isSubsequence(String s, String t) {
        int len = lcs(s ,  t);

        return len == s.length()? true : false;
    }
}

/*

🔍 Here's why Calculating length is sufficient:
A string s is a subsequence of string t if all characters of s appear in t in the same order, but not necessarily contiguously.

The length of the LCS between s and t tells us how many characters from s can be matched in order in t.

✅ If the LCS length == s.length():
That means all characters of s appear in order in t, so s is a subsequence of t.

❌ If the LCS length < s.length():
That means some characters of s do not appear in t in order, so s is not a subsequence of t.

🚫 No need to reconstruct the actual LCS string
Because we only care about whether all characters of s can be matched in order, not what the actual subsequence is.



*/
