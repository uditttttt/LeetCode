class Solution {
    public int maximumSumSubarray(int[] arr, int k) {
        // Two pointers for window: i -> start, j -> end
        int i = 0, j = 0;

        // To store current window sum
        int sum = 0;

        // To store the maximum sum seen so far
        int maxSum = 0;

        // Loop until end of array
        while (j < arr.length) {
            // Add current element to the window sum
            sum += arr[j];

            // Check if current window size is less than k
            if (j - i + 1 < k) {
                // Expand the window by moving end pointer
                j++;
            }

            // If window size is exactly k
            else if (j - i + 1 == k) {
                // Update the maximum sum if current window sum is greater
                maxSum = Math.max(maxSum, sum);

                // Now slide the window:
                // Remove the element going out of the window (arr[i])
                sum -= arr[i];

                // Move start pointer forward
                i++;

                // Move end pointer forward
                j++;
            }
        }

        // After checking all windows, return the maximum found
        return maxSum;
    }
}

/*

🤔 Intuition / Thought Process
Brute force would calculate the sum of all subarrays of size k — that's O(n * k).

That’s inefficient, especially for large arrays.

But if you look closely — when moving from one window to the next, only one element enters the window and one leaves.

So we can use a sliding window — just add the new element, and subtract the outgoing one, keeping track of the current sum and maximum seen so far.

This reduces time complexity to O(n)!




*/