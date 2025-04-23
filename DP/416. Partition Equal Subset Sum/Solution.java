class Solution {

  // This method checks if a subset of the array sums up to 's'
  public static boolean solve(int[] arr , int s){
      int n = arr.length;

      // Create a DP table where dp[i][j] means:
      // "Can we achieve sum j using first i elements?"
      boolean[][] dp = new boolean[n + 1][s + 1];

      // Initialization:
      // We can always make sum = 0 by picking no elements.
      for(int i = 0; i <= n; i++){
          dp[i][0] = true;
      }

      // We cannot make any positive sum using 0 elements.
      for(int j = 1; j <= s; j++){
          dp[0][j] = false;
      }

      // Fill the DP table
      for(int i = 1; i <= n; i++){
          for(int j = 1; j <= s; j++){

              // If current element can be included (arr[i-1] <= j)
              if(arr[i - 1] <= j){
                  // We check two possibilities:
                  // 1. Include current element → dp[i-1][j - arr[i-1]]
                  // 2. Exclude current element → dp[i-1][j]
                  dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
              } else {
                  // Current element can't be included, so we carry the result from previous row
                  dp[i][j] = dp[i - 1][j];
              }
          }
      }

      // Final answer: can we achieve sum 's' using all 'n' elements
      return dp[n][s];
  }

  // This method determines if the array can be split into two equal-sum subsets
  public boolean canPartition(int[] nums) {
      int sum = 0;

      // First calculate total sum of the array
      for(int i = 0; i < nums.length; i++){
          sum += nums[i];
      }

      // If the total sum is odd, we can't split it into two equal halves
      if(sum % 2 != 0){
          return false;
      } else {
          // If the sum is even, check if we can find a subset with sum = totalSum / 2
          return solve(nums, sum / 2);
      }
  }
}

/*

🧠 Summary of Intuition
Key Insight:

If total sum is odd, we can’t divide it into two equal subsets.

If total sum is even, check if there exists a subset with sum = total / 2.

DP Table Meaning:
dp[i][j] = true → Yes, you can make sum j using first i elements.

Decision Logic:

If arr[i-1] <= j: You can include or exclude the current element.

Else: You can only exclude it.

Final Goal: Find whether subset with sum/2 is possible.

*/