class Solution {
  public int minimumPairRemoval(int[] nums) {
      int n = nums.length;
      List<Integer> ans = new ArrayList<>();
      for(int i: nums){
          ans.add(i);
      }
      int count = 0;

      while(!isSorted(ans)){
          int minsum = Integer.MAX_VALUE;
          int idx = -1;

          for(int i=0; i<ans.size()-1; i++){
              int sum =ans.get(i) + ans.get(i+1);
              if(minsum > sum){
                  minsum = sum;
                  idx = i;
              }
          }

          int pairSum = ans.get(idx) + ans.get(idx+1);
          ans.set(idx , minsum);
          ans.remove(idx+1);
          count++;
      }

      return count;
      
  }

  public static boolean isSorted( List<Integer> ans){
      for(int i=0; i<ans.size()-1; i++){
          if(ans.get(i) > ans.get(i+1)) return false;
      }

      return true;
  }
}