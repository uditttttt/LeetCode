class Solution {
  public String removeOccurrences(String s, String part) {
      int p = part.length();
      StringBuilder ans = new StringBuilder();
      for(int i=0; i<s.length(); i++){
          ans.append(s.charAt(i));
          int n = ans.length();
          if(n>=p){
              String sub = ans.substring(n-p);
              if(sub.equals(part)){
                  ans.delete(n-p , n);
              }
          }
      }

      return ans.toString();
  }
}