// Optimisied

// class Solution {
//     public int punishmentNumber(int n) {
//         //to store punishment number
//         int punishmentNum = 0;

//         //traverse the array
//         for(int curr = 1;curr <= n ;curr++){

//             //finding square
//             int square = curr*curr;
            
//             if(canPartition(square,curr))
//               punishmentNum += square;
//         }

//         return punishmentNum;

//     }
//     public boolean canPartition(int num, int target){
//         //invalid
//         if(num < target || target < 0)
//           return false;

//         if(num == target)
//           return true;

//         return (canPartition(num/10,target-(num%10)) || canPartition(num/100,target-(num%100)) || canPartition(num/1000,target-(num%1000)));
//     }
// }

// Not optimised
class Solution {
  boolean isPartition(String sq , int target , int si , int currsum){
      int n = sq.length();
      if(si == n){
          return currsum == target;
      }
      for(int index = si; index<n; index++){
          int val = Integer.parseInt(sq.substring(si , index+1));
          if(isPartition(sq , target , index+1, currsum+val)){
              return true;
          }
      }
      return false;
  }
  public int punishmentNumber(int n) {
      int result =0;
      for(int i=1; i<=n; i++){
          int i2 = i*i;
          String sq = Integer.toString(i2);
          if(isPartition(sq, i , 0 , 0)){
              result = result + i2;
          }
      }

      return result;
  }
}