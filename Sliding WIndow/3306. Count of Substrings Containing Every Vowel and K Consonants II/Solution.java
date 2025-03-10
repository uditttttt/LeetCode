class Solution {
  boolean isVowel(char ch){
      return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
  }
  public long countOfSubstrings(String word, int k) {
      int n = word.length();
      // to keep counts of vowels in current window
      // Sc = O(constant) = O(5) as max size of map can be 5 only
      HashMap<Character ,Integer> map = new HashMap<>();
      
      // Preprocessing to be able to find index of just next consonant
      // Sc : O(n)
      int[] nextCons = new int[n];
      int lastConsIdx = n;
      // Tc : O(n)
      for(int i = n-1; i>=0; i--){
          nextCons[i] = lastConsIdx;
          if(!isVowel(word.charAt(i))){ // consonant found
              lastConsIdx = i;
          }
      }

      int i = 0;
      int j = 0;
      long count = 0;
      int cons = 0;
      // Tc = O(2*n) == O(n) as every char is visited 2 times only one by i another by j
      while(j<n){
          char ch = word.charAt(j);
          if(isVowel(ch)){
              map.put(ch , map.getOrDefault(ch , 0)+1);
          }else{
              cons++;
          }

          // cons must be always == k
          while(cons > k){
              ch = word.charAt(i);
              if(isVowel(ch)){
                  map.put(ch , map.get(ch)-1);
                  if(map.get(ch) == 0){
                      map.remove(ch);
                  }
              }else{
                  cons--;
              }
              i++;
          }

          while(i<n && map.size() == 5 && cons == k){ // valid window // i<n safe check we can also remove it
              // it will tell me the next consonant after jth index
              int idx = nextCons[j];
              count += idx - j;// most important part
              ch = word.charAt(i);
              if(isVowel(ch)){
                  map.put(ch , map.get(ch)-1);
                  if(map.get(ch) == 0){
                      map.remove(ch);
                  }
              }else{
                  cons--;
              }
              i++;
          }

          j++;

      }

      return count;

  }
}