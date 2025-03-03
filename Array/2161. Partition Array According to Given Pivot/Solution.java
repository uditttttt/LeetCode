// import java.util.*;

// class Solution {
//     public int[] pivotArray(int[] nums, int pivot) {
//         List<Integer> less = new ArrayList<>();
//         List<Integer> equal = new ArrayList<>();
//         List<Integer> greater = new ArrayList<>();

//         // Step 1: Divide elements into three lists
//         for (int num : nums) {
//             if (num < pivot) {
//                 less.add(num);
//             } else if (num == pivot) {
//                 equal.add(num);
//             } else {
//                 greater.add(num);
//             }
//         }

//         // Step 2: Merge back into nums[]
//         int i = 0;
//         for (int num : less) nums[i++] = num;
//         for (int num : equal) nums[i++] = num;
//         for (int num : greater) nums[i++] = num;

//         return nums;
//     }
// }

class Solution {
  public int[] pivotArray(int[] nums, int pivot) {
     int answer[]=new int[nums.length];
     int l=0;
     for(int i=0;i<nums.length;i++)
     {
      if(nums[i]<pivot)
      {
          answer[l]=nums[i];
          l++;
      }
     }
     for(int i=0;i<nums.length;i++)
     {
      if(nums[i]==pivot)
      {
          answer[l]=nums[i];
          l++;
      }
     }
     for(int i=0;i<nums.length;i++)
     {
      if(nums[i]>pivot)
      {
          answer[l]=nums[i];
          l++;
      }
     }
     return answer;

  }
}
