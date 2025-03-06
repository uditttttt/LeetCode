// class Solution {
//     public long coloredCells(int n) {
//         if(n==1) return 1;
//         long n1 = 1;
//         for(int i=1; i<n; i++){
//             n1 = n1+ 4*i;
//         }

//         return n1;
//     }
// }

class Solution {
  public long coloredCells(int n) {
      return 1 +2L*(n-1)*n; // from sum of n natural nos
  }
}