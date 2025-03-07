// class Solution {
//     public int[] findMissingAndRepeatedValues(int[][] grid) {
//         // HashMap<Integer , Integer> map = new HashMap<>();
//         ArrayList<Integer> t = new ArrayList<>();
//         int[] ans = new int[2];
//         int n = grid.length;
//         for(int i=0; i<n; i++){
//             for(int j=0; j<n; j++){
//                 // map.put(j , map.getOrDefault(j , 0) + 1);
//                 if(t.contains(grid[i][j])){
//                     ans[0] = grid[i][j];
//                 }
//                 else{
//                     t.add(grid[i][j]);
//                 }
//             }
//         }
//         for(int i=1; i<=n*n; i++){
//             if(!t.contains(i)){
//                 ans[1] = i;
//                 break;
//             }
//         }

//         return ans;

//     }
// }

class Solution {
  public int[] findMissingAndRepeatedValues(int[][] grid) {
      int n = grid.length;
      int m = grid[0].length;
      int total_length = n * m;
      int[] freq_arr = new int[total_length + 1];
      for(int i = 0; i < n; i++){
          for(int j = 0; j < m; j++){
              freq_arr[grid[i][j]]++;
          }
      }
      int a = -1;
      int b = -1;
      for(int i = 1; i <= total_length ; i++){
          if(freq_arr[i] == 0){
              b = i;
          }
          if(freq_arr[i] > 1){
              a = i;
          }
      }
      return new int[]{a,b};
  }
}