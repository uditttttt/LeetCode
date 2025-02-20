// class Solution {
//     public String getHappyString(int n, int k) {
//         List<String> ans = new ArrayList<>();
//         solve(n , ans , new StringBuilder());
//         if(k > ans.size()) return "";
//         return ans.get(k-1);
//     }

//     public void solve(int n , List<String> ans , StringBuilder curr){
//         if(curr.length() == n) {
//             ans.add(curr.toString());
//             return;
//         }

//         for(char ch : new char[]{'a' , 'b' , 'c'}){
//             // to check if previous character in current sequence is not same as current character
//             if(curr.length() > 0 && curr.charAt(curr.length()-1) == ch) continue;
//             // Do
//             curr.append(ch);
//             // Explore
//             solve(n , ans , curr);
//             // Undo : like we reached aba now undo 'a' from aba and add'c' so abc bcm
//             curr.deleteCharAt(curr.length()-1);
//         }
//     }
// }

// optimised
class Solution {
  private int count = 0; // Global count
  private String result = ""; // Global result string

  public String getHappyString(int n, int k) {
      solve(n, k, new StringBuilder());
      return result;
  }

  private void solve(int n, int k, StringBuilder curr) {
      if (curr.length() == n) {
          count++;
          if (count == k) {
              result = curr.toString(); // Correctly update result
          }
          return;
      }

      for (char ch : new char[]{'a', 'b', 'c'}) {
          if (curr.length() > 0 && curr.charAt(curr.length() - 1) == ch) continue;

          curr.append(ch);
          solve(n, k, curr);
          curr.deleteCharAt(curr.length() - 1);

          if (!result.isEmpty()) return; // Stop recursion early when found
      }
  }
}


/*

In Java, strings are immutable. When you pass result into solve(), modifications inside solve() do not affect the original value.
result += curr.toString(); creates a new string but does not update the original variable.
✅ Fix: Use a class-level variable (global variable) instead of passing result.

count is passed by value, meaning changes inside solve() do not reflect in the main function.
count++ only modifies a local copy and does not update the global count.
✅ Fix: Use a global variable for count.

*/