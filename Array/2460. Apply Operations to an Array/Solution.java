// class Solution {
//     public int[] applyOperations(int[] nums) {
//         int n = nums.length;

//         // Step 1: Apply operations
//         for (int i = 0; i < n - 1; i++) {
//             if (nums[i] == nums[i + 1]) {
//                 nums[i] *= 2;
//                 nums[i + 1] = 0;
//             }
//         }

//         // Step 2: Move non-zero elements to the front
//         int[] result = new int[n];
//         int index = 0;
//         for (int num : nums) {
//             if (num != 0) {
//                 result[index++] = num;
//             }
//         }

//         return result;
//     }
// }


// without extra space
class Solution {
  public int[] applyOperations(int[] nums) {
      int n = nums.length;

      // Step 1: Merge adjacent equal elements
      for (int i = 0; i < n - 1; i++) {
          if (nums[i] == nums[i + 1]) {
              nums[i] = nums[i] * 2;
              nums[i + 1] = 0;
          }
      }

      // Step 2: Move all non-zero elements to the front of the array
      // int i = 0; // Pointer to place the next non-zero element
      // for (int j = 0; j < n; j++) {
      //     if (nums[j] != 0) {
      //         nums[i] = nums[j]; // Place non-zero element at index i
      //         if (i != j) {
      //             nums[j] = 0; // Set the old position of nums[j] to zero
      //         }
      //         i++;
      //     }
      // }
      int index = 0;
      for (int i = 0; i < n; i++) {
          if (nums[i] != 0) {
              int temp = nums[i];
              nums[i] = nums[index];
              nums[index] = temp;
              index++;
          }
      }

      return nums; // Return the modified array
  }
}
