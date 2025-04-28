import java.util.*;
class Solution {

  // Function to count the number of subsets with a given sum 's'
  public int find(int[] arr, int s, int n) {
      int[][] dp = new int[n + 1][s + 1];  // dp[i][j] = number of subsets from first i elements that have sum j

      // Base case: There is 1 way to get sum 0 → take no elements (empty set)
      dp[0][0] = 1;

      // Initialize first row (0 elements): 
      // For any positive sum j > 0, we can't form it using 0 elements
      for (int j = 1; j <= s; j++) {
          dp[0][j] = 0;
      }

      // Fill the dp table
      for (int i = 1; i <= n; i++) {
          for (int j = 0; j <= s; j++) {

              // If current element can be included (i.e., arr[i-1] <= j)
              if (arr[i - 1] <= j) {
                  // Two choices:
                  // 1. Include current element → check for remaining sum (j - arr[i-1])
                  // 2. Exclude current element → carry over dp[i-1][j]
                  dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
              } else {
                  // Current element too big → only option is to exclude it
                  dp[i][j] = dp[i - 1][j];
              }
          }
      }

      // Final answer: number of ways to form sum 's' using all n elements
      return dp[n][s];
  }

  // Main function to count the number of partitions with difference 'd'
  int countPartitions(int[] arr, int d) {
      int totalSum = 0;
      int n = arr.length;

      // Calculate total sum of array elements
      for (int i = 0; i < n; i++) {
          totalSum += arr[i];
      }

      // Derived from equations:
      // S1 - S2 = d, S1 + S2 = totalSum
      // => S1 = (totalSum + d) / 2
      int targetSum = (totalSum + d) / 2;

      // If (totalSum + d) is odd, it's impossible to partition

      // Because subset sum S1 can't be a decimal.
      if ((totalSum + d) % 2 != 0) {
          return 0;
      }

      // Final step: Count number of subsets with sum = targetSum
      return find(arr, targetSum, n);
  }
}

/*
🔐 Secret Tip for Exam:
Whenever you see:
•	Partition into subsets
•	Given sum or difference
•	Count ways to do it
💡 Immediately think: can I convert this into a subset sum problem using equations?
This trick works for MANY partition-based problems.


*/
