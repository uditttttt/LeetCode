// // Recursion only  method give tle

// class Solution {

//     // Helper function that solves the 0/1 knapsack problem using recursion
//     // w = remaining weight capacity of the knapsack
//     // val[] = array of item values
//     // wt[] = array of item weights
//     // n = number of items considered
//     public static int solve(int w , int[] val , int[] wt , int n){
        
//         // Base Case: If either no items are left or weight capacity becomes 0, return 0
//         if(n == 0 || w == 0){
//             return 0;
//         }
        
//         // Choice: check if the weight of the current item (wt[n-1]) is less than or equal to remaining capacity (w)
//         if(wt[n-1] <= w){
//             // Two options:
//             // 1. Include the item: Add its value and solve remaining knapsack for reduced weight and one less item
//             // 2. Exclude the item: Just skip this item and move to the next (n-1)
//             // Take the maximum of both choices (we want maximum value)
//             return Math.max(
//                 val[n-1] + solve(w - wt[n-1], val, wt, n - 1), // include the item
//                 solve(w, val, wt, n - 1)                       // exclude the item
//             );
//         } else {
//             // If current item's weight is more than the capacity, we cannot include it
//             // So, we skip it and move to the next item
//             return solve(w, val, wt, n - 1);
//         }
//     }

//     // Main function to call the recursive solve function
//     static int knapsack(int W, int val[], int wt[]) {
//         // n = total number of items
//         int n = val.length;

//         // Call the recursive function with full capacity and all items
//         return solve(W, val, wt, n);
//     }
// }

/*

Why Backward Traversal?
Base Case Handling:

The base case checks if either n == 0 (no items left) or W == 0 (no capacity left).

This means we start from the full problem (n items and full capacity W) and recursively 
reduce the problem size by considering smaller subproblems (fewer items or reduced capacity).

Intuition:

The backward approach is a natural way to think about the problem recursively: "If I have n items, should I include the n-th item or not?"

This reduces the problem size step by step until we hit the base case.
*/
// Top-Down Approach(Memoization : Recursion + tables)

// class Solution {
//     static int[][] dp = new int[1001][1001];

//     public static int solve(int w , int val[] , int wt[] , int n){
//         if(n <= 0 || w == 0) return 0;
//         if(dp[n][w] != -1) return dp[n][w];
//         if(wt[n-1] <= w){
//              return dp[n][w] = Math.max(val[n-1]+solve(w-wt[n-1] , val , wt , n-1) , solve(w , val , wt , n-1));
//         }else{
//             return dp[n][w] = solve(w , val , wt , n-1);
//         }
//         // return dp[n][w];  // if u dont use return in if and else then return here
//     }
//     static int knapsack(int W, int val[], int wt[]) {
//         // code here
//         int n = val.length;
//         for (int i = 0; i < 1001; i++) {
//             for (int j = 0; j < 1001; j++) {
//                 dp[i][j] = -1;
//             }
//         }
//         return solve(W , val , wt , n);

//     }
// }


// Bottom up Approach (Tabulation method)
class Solution {

  // This function implements the tabulation (bottom-up) approach
  public static int solve(int w, int val[], int wt[], int n, int[][] dp) {
      // i = items from 1 to n
      for (int i = 1; i <= n; i++) {
          // j = weight capacity from 1 to W
          for (int j = 1; j <= w; j++) {
              
              // If the current item can be included (its weight is <= current capacity)
              if (wt[i - 1] <= j) {
                  // Two options:
                  // 1. Include the current item: val[i-1] + value of remaining capacity (j - wt[i-1])
                  // 2. Exclude the item: value without including current item = dp[i-1][j]
                  // Take the maximum of both choices
                  dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
              } else {
                  // If item can't be included, just carry forward the value without it
                  dp[i][j] = dp[i - 1][j];
              }
          }
      }
      // Final answer will be in dp[n][w]: max value using n items and total capacity w
      return dp[n][w];
  }

  static int knapsack(int W, int val[], int wt[]) {
      int n = val.length; // Total number of items

      // Create DP table of size (n+1) x (W+1)
      // dp[i][j] represents the max value using first i items with capacity j
      int[][] dp = new int[n + 1][W + 1];

      // Solve using tabulation
      return solve(W, val, wt, n, dp);
  }
}

/*
✅ Quick Intuition Recap:
You are given:

val[]: values of n items

wt[]: weights of n items

W: total capacity of the knapsack

Goal: Maximize the value you can carry without exceeding weight W

You either pick an item or skip it

DP idea:

Store the result of subproblems so you don’t recompute

Build up the answer using a bottom-up approach in a table dp[i][j]



*/