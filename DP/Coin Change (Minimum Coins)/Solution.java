class Solution {

  public int minCoins(int coins[], int sum) {
      int n = coins.length;

      // dp[i][j] = minimum number of coins required to make sum 'j' using first 'i' coins
      int dp[][] = new int[n + 1][sum + 1];

      // Base Case 1:
      // To make sum = 0, we need 0 coins no matter how many coins we have
      for (int i = 0; i <= n; i++) {
          dp[i][0] = 0;
      }

      // Base Case 2:
      // If we have 0 coins (i=0), we can't make any positive sum
      // So initialize dp[0][j] as "infinity" (we use a very large number)
      // We use Integer.MAX_VALUE - 1 instead of Integer.MAX_VALUE to avoid overflow when we do 1 + dp[..]
      for (int j = 1; j <= sum; j++) {
          dp[0][j] = Integer.MAX_VALUE - 1;
      }

      // Fill the DP table
      for (int i = 1; i <= n; i++) {
          for (int j = 1; j <= sum; j++) {

              if (coins[i - 1] <= j) {
                  // We have two choices:

                  // 1. Take the current coin (coins[i-1]):
                  // If we take it, we reduce the sum by coin value: (j - coins[i-1])
                  // Since we took 1 coin, we add 1 to the result
                  // So this becomes: 1 + dp[i][j - coins[i - 1]]
                  //
                  // 🔴 WHY ADD 1?
                  // → Because we are taking 1 coin (coins[i-1]) right now,
                  // and dp[i][j - coins[i-1]] tells us how many more coins we need
                  // to make the remaining sum (after using this coin)

                  // 2. Don't take the current coin → move to previous row (exclude this coin)
                  dp[i][j] = Math.min(
                      1 + dp[i][j - coins[i - 1]],  // pick the coin
                      dp[i - 1][j]                  // don’t pick the coin
                  );
              } else {
                  // Coin value is greater than current sum, can't pick it
                  dp[i][j] = dp[i - 1][j];
              }
          }
      }

      // If final result is still "infinity", it means no combination was possible
      return dp[n][sum] == Integer.MAX_VALUE - 1 ? -1 : dp[n][sum];
  }
}


/*

✅ Step 1: What type of problem is this?
Ask yourself:

"Do I need the minimum number of something?" → YES.

"Can the same coin be used unlimited times?" → YES (It’s unbounded knapsack).

"Do I have multiple choices for each sum?" → YES.

🔁 That’s a strong indicator of DP + unbounded knapsack type.

✅ The Key Idea:
When i == 0 → we have no coins.
If we try to make any sum j > 0 without coins, it is impossible.

So in DP, we represent "impossible" by using a very large number, such as Integer.MAX_VALUE - 1.

*/