// class Solution {
//     String ans = "";
//     public String findDifferentBinaryString(String[] nums) {
//         int n = nums.length;
//         HashSet<String> set = new HashSet<>(Arrays.asList(nums));
//         solve(set , n , new StringBuilder());
//         return ans;
//     }
//     public void solve(HashSet<String> set , int n , StringBuilder sb){
//         if(sb.length() == n){
//             if(!set.contains(sb.toString())){
//                 ans = sb.toString();
//             }
//             return;
//         }
//         for(int i = 0; i<2; i++){
//             sb.append(i);
//             solve(set , n ,sb);
//             sb.deleteCharAt(sb.length()-1);
//         }
//     }
// }

class Solution {
  public String findDifferentBinaryString(String[] nums) {
      StringBuilder ans = new StringBuilder();
      for (int i=0;i<nums.length;i++){
          ans.append(nums[i].charAt(i) == '0' ? '1' :'0');
      }
      return ans.toString();
  }
}

/*

Why This Works
This method ensures that the generated string differs from each given string at one specific position, making it impossible for the output to be present in nums.

*/