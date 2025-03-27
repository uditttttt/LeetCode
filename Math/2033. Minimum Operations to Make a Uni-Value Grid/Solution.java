class Solution {
  public int minOperations(int[][] grid, int x) {
      // Create a list to store all the elements of the 2D grid
      List<Integer> ans = new ArrayList<>();

      // Flatten the 2D grid into a 1D list
      for(int a[] : grid){ // Iterate through each row in the grid
          for(int i=0; i<a.length; i++){ // Iterate through each element in the row
              ans.add(a[i]); // Add the element to the list
          }
      }

      // Sort the list to find the median efficiently
      Collections.sort(ans);

      // Find the median element, as aligning numbers to the median requires minimum operations
      int m = ans.size()/2; // Get the middle index
      int s = ans.get(m);   // The median value

      int op = 0; // Variable to count the number of operations

      // Iterate through all elements in the sorted list
      for(int i=0; i<ans.size(); i++){
          int d = Math.abs(ans.get(i) - s); // Find the absolute difference between the current element and median
          
          // If the difference is not divisible by x, it's impossible to make all elements equal
          if( d % x != 0) { 
              return -1; // Return -1 as it's not possible to equalize elements
          }
          
          // Count the number of operations needed to reach the median value
          op = op + d / x;
      }

      // Return the total operations required to make all elements equal
      return op;
  }
}
/*

Intuition Behind the Approach:
Flattening the 2D Grid:

Since operations are performed on individual elements, it's easier to work with a 1D list.

Sorting & Choosing the Median:

The best target value to minimize operations is the median, as it minimizes the sum of absolute differences.

Checking Feasibility:

If any number cannot be converted to the median by adding/subtracting x, the transformation is impossible (return -1).

Counting the Steps:

Each operation increases/decreases a number by x.

The number of operations required is (difference / x), summed for all elements.

*/ 