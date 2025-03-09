import java.util.*;
class Solution{
  static ArrayList<Integer> sieveOfEratosthenes(int N){
      // code here
      int[] isPrime = new int[N+1];
      Arrays.fill(isPrime , 1);
      for(int i=2; i*i <= N; i++){
          if(isPrime[i] == 1){
              for(int j =2; i*j <=N; j++){
                  isPrime[i*j] = 0;
              }
          }
      }
      
      ArrayList<Integer> ans = new ArrayList<>();
      
      // since 0 and 1 are not prime so start i from 2
      for(int i=2; i<=N; i++){
          if(isPrime[i] == 1) ans.add(i);
      }
      
      return ans;
  }
  
}