// Brute force

// import java.util.HashMap;

// class Solution {
//     public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
//         int n1 = nums1.length; // Length of the first input array (nums1)
//         int n2 = nums2.length; // Length of the second input array (nums2)

//         // Determine the maximum index that can be present in the result array
//         // We find the highest key (index) in nums1 and nums2 to set the size of the result array
//         int n3 = Math.max(nums1[n1 - 1][0], nums2[n2 - 1][0]); // Max index from both arrays
        
//         // Create the result array with size n3 rows (max index) and 2 columns (for index and sum)
//         int[][] ans = new int[n3][2];

//         // Create two HashMaps to store the index and value pairs from nums1 and nums2
//         HashMap<Integer, Integer> map1 = new HashMap<>();
//         HashMap<Integer, Integer> map2 = new HashMap<>();

//         // Fill map1 with key-value pairs from nums1
//         // Key is the index (nums1[i][0]), value is the value at that index (nums1[i][1])
//         for (int i = 0; i < n1; i++) {
//             map1.put(nums1[i][0], nums1[i][1]);
//         }

//         // Fill map2 with key-value pairs from nums2
//         // Key is the index (nums2[i][0]), value is the value at that index (nums2[i][1])
//         for (int i = 0; i < n2; i++) {
//             map2.put(nums2[i][0], nums2[i][1]);
//         }

//         int j = 0; // A variable to track the current index in the result array (ans)

//         // Loop through all possible indices from 1 to n3 (the maximum index)
//         for (int i = 1; i <= n3; i++) {
//             // Retrieve the values from both maps for the current index i
//             // If the key doesn't exist in the map, return 0 as the default value
//             int value1 = map1.getOrDefault(i, 0); // Get value from map1 or default to 0
//             int value2 = map2.getOrDefault(i, 0); // Get value from map2 or default to 0

//             // If the sum of value1 and value2 is non-zero (i.e., at least one array has this index),
//             // then we add this index and the sum to the result array
//             if (value1 != 0 || value2 != 0) {
//                 ans[j][0] = i; // Store the index in the first column of the result array
//                 ans[j][1] = value1 + value2; // Store the sum of values from nums1 and nums2 in the second column
//                 j++; // Move to the next position in the result array
//             }
//         }

//         // Create a new result array of exact size to hold the merged values
//         // 'j' is the number of merged elements, so we create a new array with 'j' rows
//         int[][] result = new int[j][2];

//         // Copy the valid entries from the 'ans' array (which has extra rows) into the 'result' array
//         // We use System.arraycopy to efficiently copy the first 'j' rows from ans to result
//         System.arraycopy(ans, 0, result, 0, j);

//         // Return the result array, which contains the merged values
//         return result;
//     }
// }

// optimal code - Two Pointer approach

import java.util.ArrayList;

class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int n1 = nums1.length, n2 = nums2.length;  // Get the sizes of both arrays
        int i = 0, j = 0;  // Two pointers for nums1 and nums2
        
        ArrayList<int[]> ans = new ArrayList<>();  // List to store merged results
        
        // Traverse both arrays simultaneously
        while (i < n1 && j < n2) {
            if (nums1[i][0] == nums2[j][0]) {  // If both have the same first value (key)
                ans.add(new int[]{nums1[i][0], nums1[i][1] + nums2[j][1]});  // Merge by summing second values
                i++;  // Move both pointers forward
                j++;
            } else if (nums1[i][0] < nums2[j][0]) {  // If nums1[i] has a smaller key
                ans.add(nums1[i]);  // Add nums1[i] directly whole pair
                i++;  // Move pointer i forward
            } else {  // If nums2[j] has a smaller key
                ans.add(nums2[j]);  // Add nums2[j] directly
                j++;  // Move pointer j forward
            }
        }
        
        // If any elements remain in nums1, add them to ans
        while (i < n1) {
            ans.add(nums1[i++]);
        }
        
        // If any elements remain in nums2, add them to ans
        while (j < n2) {
            ans.add(nums2[j++]);
        }
        
        // Convert ArrayList to a 2D array and return
        return ans.toArray(new int[ans.size()][]);
    }
}

