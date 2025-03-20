class Solution {
  public int[] sortArrayByParity(int[] nums) {
      int i =0; 
      int j = 0;
      int n= nums.length;
      while(j<n){
          if(nums[j] %2 == 0){
              int t = nums[i];
              nums[i] = nums[j];
              nums[j] = t;
              i++;
          }
          j++;
      }

      return nums;
  }
}