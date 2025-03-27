class Solution {
  public int minimumIndex(List<Integer> nums) {
      // Step 1: Count the frequency of each number in the list
      HashMap<Integer, Integer> map = new HashMap<>();
      int n = nums.size();

      // Iterate through the list and populate the frequency map
      for (int i = 0; i < n; i++) {
          int num = nums.get(i);
          map.put(num, map.getOrDefault(num, 0) + 1);
      }

      // Step 2: Identify the dominant element (an element appearing more than n/2 times)
      int dominant = 0; // Stores the dominant element
      int totalCount = 0; // Total count of the dominant element

      // Iterate through the frequency map to find the dominant element
      for (int key : map.keySet()) {
          int count = map.get(key);
          if (count > n / 2) { // A dominant element must appear more than n/2 times
              dominant = key;
              totalCount = count;
          }
      }

      // Step 3: Find the minimum index where the left subarray is valid
      int currCount = 0; // Count of the dominant element in the left partition

      for (int i = 0; i < n; i++) {
          if (nums.get(i) == dominant) {
              currCount++; // Increase count of dominant element in left partition

              // Check if the left partition is valid
              if (currCount > (i + 1) / 2) { 
                  // Remaining dominant count in right partition
                  int remainingCount = totalCount - currCount; 

                  // If no remaining dominant elements, we stop checking further
                  if (remainingCount == 0) break;

                  // Check if the dominant element can still be a majority in the right partition
                  if (remainingCount > (n - i - 1) / 2) {
                      return i; // This is the minimum valid index
                  }
              }
          }
      }
      return -1; // If no valid index found, return -1
  }
}
