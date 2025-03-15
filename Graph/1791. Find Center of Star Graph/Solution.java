// class Solution {
//     public int findCenter(int[][] edges) {
//         ArrayList<Integer> temp = new ArrayList<>();
//         int n = edges.length;
//         int m = edges[0].length;
//         for(int i=0; i<n; i++){
//             for(int j=0; j<m; j++){
//                 if(!temp.contains(edges[i][j])){
//                     temp.add(edges[i][j]);
//                 }else{
//                     return edges[i][j];
//                 }
//             }
//         }

//         return -1;
//     }
// }

class Solution {
  public int findCenter(int[][] edges) {
      // The center node is the common node in the first two edges
      if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
          return edges[0][0];
      } else {
          return edges[0][1];
      }
  }
}
