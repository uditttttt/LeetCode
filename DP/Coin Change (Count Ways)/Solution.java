import java.util.*;
class Solution {
  public int count(int coins[], int sum) {
      int n = coins.length;

      // dp[i][j] = number of ways to make sum 'j' using first 'i' coins
      int[][] dp = new int[n + 1][sum + 1];

      // Base Case: Sum = 0 can be made in 1 way (select nothing), for any number of coins
      for (int i = 0; i <= n; i++) {
          dp[i][0] = 1;
      }

      // Base Case: No coins => no way to make positive sum
      for (int j = 1; j <= sum; j++) {
          dp[0][j] = 0;
      }

      // Fill the DP table
      for (int i = 1; i <= n; i++) {         // i = current coin index (1-based for dp table)
          for (int j = 1; j <= sum; j++) {   // j = current target sum

              if (coins[i - 1] <= j) {
                  // Include the coin (stay at i because coins can be reused)
                  // +
                  // Exclude the coin (move to previous coin, i-1)
                  dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
              } else {
                  // Can't include the coin (too large), just exclude it
                  dp[i][j] = dp[i - 1][j];
              }
          }
      }

      // Final answer: number of ways to make 'sum' using all 'n' coins
      return dp[n][sum];
  }
}

/*

🧠 Key Notes for Revision:
This is a counting problem → use + in transitions.

Coin can be used unlimited times → stay on same index when including (dp[i][j - coins[i-1]]).

Base case trick: dp[i][0] = 1 (sum = 0 can always be made by picking nothing).

Table dimension is n+1 x sum+1 to handle base cases cleanly.

🎯 Final Checklist in Exam:
Understand: Are you counting, minimizing, or checking?

Is repetition allowed? (yes → unbounded)

Think of recurrence: include or exclude

Can I convert this to a DP table?

Carefully write base cases and transitions

Code it clearly with dp[i][j] meaning defined

*/