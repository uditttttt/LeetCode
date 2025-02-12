import java.util.*;

// Tc = O(n) and Sc = O(n)
class Solution {
    /**
     * Finds the maximum sum of two numbers in the array that have the same digit sum.
     */
    public int maximumSum(int[] nums) {
        int n = nums.length; // Length of the input array
        // HashMap to store lists of numbers grouped by their digit sum
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Step 1: Iterate through the array to calculate digit sums and group numbers
        for (int num : nums) {
            // Calculate the digit sum of the current number
            int sum = digitSum(num);
            
            // If the digit sum is not already a key in the map, create a new list for it
            map.putIfAbsent(sum, new ArrayList<>());
            
            // Add the current number to the list corresponding to its digit sum
            map.get(sum).add(num);
        }
        
        // Step 2: Initialize maxSum to -1 (default value if no valid pair is found)
        int maxSum = -1;
        
        // Step 3: Iterate through the map values to find the maximum sum of two numbers with the same digit sum
        for (List<Integer> list : map.values()) {
            // Only consider lists with at least two numbers (since we need a pair)
            if (list.size() > 1) {
                // Sort the list in ascending order to easily access the two largest numbers
                Collections.sort(list);
                int size = list.size();
                
                // Calculate the sum of the two largest numbers in the list
                int currentSum = list.get(size - 1) + list.get(size - 2);
                
                // Update maxSum if the current sum is greater than the previous maxSum
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        
        // Step 4: Return the maximum sum found (or -1 if no valid pair exists)
        return maxSum;
    }
    
    /**
     * Helper function to calculate the digit sum of a number.
     */
    private int digitSum(int num) {
        int sum = 0; // Initialize sum to 0
        // Loop through each digit of the number
        while (num > 0) {
            sum += num % 10; // Extract the last digit and add it to the sum
            num /= 10; // Remove the last digit from the number
        }
        // Return the total digit sum
        return sum;
    }
}

// 2nd method 
// Tc = O(n) and Sc = O(82)

class Solution {
  public int maximumSum(int[] nums) {
      // Array to store the maximum number for each digit sum
      // Maximum possible digit sum for a 32-bit integer is 9 * 9 = 81
      // max num = 999999999 = 9*9 = 81
      int[] maxNum = new int[82]; 
      // Arrays.fill(maxNum, -1); // Initialize all values to -1
      
      int maxSum = -1; // Initialize the result to -1
      
      // Iterate through the array
      for (int num : nums) {
          int sum = digitSum(num); // Calculate the digit sum
          
          // If a number with this digit sum has been seen before
          // if (maxNum[sum] != -1) {
          if (maxNum[sum] != 0) {
              // Update the maximum sum if the current pair is larger
              maxSum = Math.max(maxSum, maxNum[sum] + num);
          }
          
          // Update the maximum number for this digit sum
          maxNum[sum] = Math.max(maxNum[sum], num);
      }
      
      return maxSum; // Return the result
  }
  
  // Helper function to calculate the digit sum of a number
  private int digitSum(int num) {
      int sum = 0;
      while (num > 0) {
          sum += num % 10; // Add the last digit to the sum
          num /= 10; // Remove the last digit
      }
      return sum;
  }
}