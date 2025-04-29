class Solution {

  // Function to count number of subsets with sum equal to 's'
  public static int solve(int arr[], int s){
      int n = arr.length;

      // dp[i][j] = number of subsets using first 'i' elements that sum to 'j'
      int[][] dp = new int[n + 1][s + 1];

      // Base Case:
      // There's 1 way to make sum = 0 (by taking nothing), for any i
      dp[0][0] = 1;

      // Fill the DP table
      for(int i = 1; i <= n; i++){
          for(int j = 0; j <= s; j++){
              // If current element can be included in the sum
              if(arr[i - 1] <= j){
                  // 1. Include current element → dp[i-1][j - arr[i-1]]
                  // 2. Exclude current element → dp[i-1][j]
                  dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
              } else {
                  // If current element > sum j, we can only exclude it
                  dp[i][j] = dp[i - 1][j];
              }
          }
      }

      // Return the number of ways to reach sum 's' using all 'n' elements
      return dp[n][s];
  }

  // Main function to find number of ways to assign + and - signs
  static int findTargetSumWays(int N, int[] A, int target) {

      // Step 1: Calculate the total sum of all elements in array
      int totalSum = 0;
      for(int i = 0; i < A.length; i++){
          totalSum += A[i];  // We need this to apply the transformation logic
      }

      // Step 2: Apply the formula derived from equations:
      // S1 = (target + totalSum) / 2
      int targetSum = (totalSum + target) / 2;

      // Step 3: Check for edge cases
      // 1. If (totalSum + target) is odd → can't divide evenly → no valid subset
      // 2. If (totalSum + target) is negative → impossible to reach a negative sum using only positives
      if((totalSum + target) % 2 != 0 || (totalSum + target) < 0){
          return 0;
      } else {
          // Step 4: Solve subset sum count problem with sum = targetSum
          return solve(A , targetSum);
      }
  }
}

/*

🔍 Problem Recap (Intuition Behind Logic)
You're given an array of positive integers A, and you have to assign '+' or '-' signs to each element so that the final expression equals the given target.

This is a classic transformation problem.

✨ Key Insight:
Split the array into two subsets:

Subset 1 → elements with '+' sign → sum = S1

Subset 2 → elements with '-' sign → sum = S2

From the problem:

nginx
Copy
Edit
S1 - S2 = target    ...(1)
S1 + S2 = totalSum  ...(2)
Add both equations:

ruby
Copy
Edit
2 * S1 = target + totalSum
=> S1 = (target + totalSum) / 2
So now, the problem reduces to:

"Count the number of subsets whose sum = (target + totalSum)/2"

This is a Subset Sum Count DP problem.

*/
