/*

🧠 Memory Trick
If problem says:
"Find a subarray (contiguous) such that sum is exactly k, and negatives exist"
then immediately think:
🚀 Prefix Sum + HashMap

*/


class Solution {
    public int longestSubarray(int[] arr, int k) {
        int n = arr.length;

        int sum = 0;             // running prefix sum
        int maxLen = 0;          // result: max length of subarray with sum == k

        // Map to store first occurrence of each prefix sum
        // Key = prefix sum, Value = index where it first occurred
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int j = 0; j < n; j++) {

            sum += arr[j];  // Update prefix sum up to index j

            // Case 1: If sum from 0 to j itself is equal to k
            if (sum == k) {
                maxLen = j + 1;  // Whole subarray from 0 to j has sum == k
            }

            // Case 2: Check if there's a previous prefix sum that gives us sum == k
            // If sum[i] = sum - k, then subarray (i+1 to j) has sum == k
            // since sum - (sum-k) = k
            if (map.containsKey(sum - k)) {
                int i = map.get(sum - k);     // Get index where (sum - k) occurred
                int len = j - i;              // Length of subarray from (i+1) to j
                maxLen = Math.max(maxLen, len);  // Update max length if longer subarray found
            }

            // Case 3: Store current prefix sum if not already present
            // Only store the first occurrence to ensure longest possible subarray
            if (!map.containsKey(sum)) {
                map.put(sum, j);
            }
        }

        return maxLen;  // Return the length of the longest subarray with sum == k
    }
}
