import java.util.*;

class Solution {

  // Helper function to find all possible subset sums up to 's' using the first 'n' elements of arr
  public ArrayList<Integer> find(int[] arr , int s , int n){
      // Create a DP table where dp[i][j] is true if a subset of first 'i' elements has a sum 'j'
      boolean dp[][] = new boolean[n+1][s+1];

      // If sum is 0, we can always form it by taking no elements (empty subset)
      for(int i = 0; i <= n; i++){
          dp[i][0] = true;
      }

 
   // If there are 0 elements, we can't form any positive sum
      for(int j = 1; j <= s; j++){
          dp[0][j] = false;
      }

      // Fill the DP table
      for(int i = 1; i <= n; i++){
          for(int j = 1; j <= s; j++){
              // If current element arr[i-1] is less than or equal to the current sum 'j'
              if(arr[i-1] <= j){
                  // We can either include it or not include it
                  dp[i][j] = dp[i-1][j-arr[i-1]] || dp[i-1][j];
              } else {
                  // We cannot include current element because it's greater than current target sum
                  dp[i][j] = dp[i-1][j];
              }
          }
      }

      // Prepare a list to store all achievable subset sums up to s/2
      ArrayList<Integer> check = new ArrayList<>();

      // ✅ You’ll still get the correct answer, if u traverse till last but
      // ❌ You’ll waste time and memory, because:
      
      // The values beyond Total / 2 are mirror values (they give the 
      // same difference as their complements).
      
      // You are re-checking the same possibilities in reverse.
  //   for(int i=0; i<=s; i++){
      for(int i = 0; i <= s/2; i++){
          // If a subset sum 'i' is possible using all n elements
          if(dp[n][i]){
              check.add(i); // Add to the list
          }
      }

      // Return all valid subset sums (S1 values)
      return check;
  }

  // Main function to find the minimum difference between two subsets
  public int minDifference(int arr[]) {
      // Step 1: Find total sum of the array
      int range = 0;
      for(int i = 0; i < arr.length; i++){
          range = range + arr[i];
      }

      // Step 2: Get all subset sums (S1) that are possible up to range/2
      ArrayList<Integer> check = find(arr , range , arr.length);

      // Step 3: Initialize min difference to a large value
      int min = Integer.MAX_VALUE;

      // Step 4: Try each possible S1, calculate |S - 2*S1|, and find the minimum
      for(int i = 0; i < check.size(); i++){
          int s1 = check.get(i);
          int diff = Math.abs(range - 2 * s1); // equivalent to |S1 - S2|
          min = Math.min(min, diff); // update minimum difference
      }

      // Step 5: Return the final result
      return min;
  }
}

/*

🧠 Why We Only Go Till s/2
If total sum = S, then we want to minimize |S1 - S2| = |S - 2*S1|.
So the closer S1 is to S/2, the smaller the difference.
Hence, no need to go beyond S/2 — after that, differences start increasing again due to symmetry.

Let me know if you'd like a dry run with an example like [1, 6, 11, 5] — I can show you exactly how the DP table is filled and how the answer is found.

💡 Key Idea:
If total sum = S, and you divide into subsets S1 and S2, you want to minimize:


|S1 - S2| = |(S1 + S2) - 2*S1| = |S - 2*S1|
So: 👉 Your goal is to find the value of S1 such that |S - 2*S1| is minimized
👉 But S1 must be a subset sum that is possible from the array
👉 So we use Subset Sum DP to check which sums are possible

We only check S1 from 0 to S/2, because after that the difference starts increasing again (explained earlier)

🧠 Thought Process to Solve in Exam
Recognize it's a partition/subset problem ➝ keywords: "divide into 2 subsets", "minimum difference"

Think of total sum S ➝ want |S1 - S2| to be minimum ➝ leads to |S - 2*S1|

Want to find all S1 such that S1 <= S/2 (as it gives minimum difference)

Use Subset Sum DP to figure out which S1 are possible

Find min(|S - 2*S1|) from those


*/