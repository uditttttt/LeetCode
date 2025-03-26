class Solution {

  // Function for finding maximum and value pair
  public static int longestSubarray(int[] A, int K) {
      int N = A.length;
      // HashMap to store (cumulative sum, index)
      HashMap<Integer, Integer> map = new HashMap<>();
      int sum = 0;
      int maxLen = 0;

      for (int i = 0; i < N; i++) {
          sum += A[i];

          // Check if the current sum is equal to K
          if (sum == K) {
              maxLen = i + 1;
          }

          // If (sum - K) exists in the map, it means there is a subarray
          // with the sum equal to K
          if (map.containsKey(sum - K)) {
              maxLen = Math.max(maxLen, i - map.get(sum - K));
          }

          // If the sum is not already in the map, add it with its index
          if (!map.containsKey(sum)) {
              map.put(sum, i);
          }
      }

      return maxLen;
  }
}

/*




*/

