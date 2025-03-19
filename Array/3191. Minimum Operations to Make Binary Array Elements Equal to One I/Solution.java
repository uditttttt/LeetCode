class Solution {
  public int minOperations(int[] nums) {
      int c = 0;
      int n = nums.length;
      for(int i=0; i<n-2; i++){
          if(nums[i] == 0){
              c++;
              nums[i] = 1;
              if(nums[i+1] == 0) nums[i+1] = 1;
              else nums[i+1] = 0;

              if(nums[i+2] == 0) nums[i+2] = 1;
              else nums[i+2] = 0;
          }
      }
          if(nums[n-1] == 0 || nums[n-2] == 0 || nums[n-3] == 0){
              return -1;
          }else{
              return c;
          }
  }
}