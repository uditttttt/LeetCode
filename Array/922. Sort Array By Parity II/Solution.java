class Solution {
  public int[] sortArrayByParityII(int[] nums) {
      int oddIdx = 1;
      int evenIdx = 0;
      int[] ans = new int[nums.length];
      for(int n : nums){
          if(n%2 == 0){
              ans[evenIdx] = n;
              evenIdx += 2;
          }else{
              ans[oddIdx] = n;
              oddIdx += 2;
          }
      }
      return ans;
  }
}