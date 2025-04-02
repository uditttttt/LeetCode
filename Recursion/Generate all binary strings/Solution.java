import java.util.*;

class Solution {
  public static List<String> generateBinaryStrings(int n) {
      // List to store the generated binary strings
      List<String> ans = new ArrayList<>();

      // Edge case: If n is non-positive, return empty list
      if(n <= 0){
          return ans;
      }

      // Character array to store the current binary string being built
      char[] ch = new char[n];

      // Start building the string with '0'
      ch[0] = '0';
      generate(ch, ans, n, 1);

      // Start building the string with '1'
      ch[0] = '1';
      generate(ch, ans, n, 1);

      // Return the list of valid binary strings
      return ans;
  }

  // Recursive function to generate binary strings
  public static void generate(char[] ch, List<String> ans, int n, int i) {
      // Base case: If we have filled all n positions, store the string in the list
      if(i == n){
          ans.add(new String(ch)); // Convert char array to string and add to list
          return;
      }

      // If the previous character was '0', we can add both '0' and '1'
      if(ch[i - 1] == '0'){
          ch[i] = '0';  // Add '0' at current position
          generate(ch, ans, n, i + 1);  // Recursively generate remaining positions

          ch[i] = '1';  // Add '1' at current position
          generate(ch, ans, n, i + 1);  // Recursively generate remaining positions
      }

      // If the previous character was '1', we can only add '0' to avoid consecutive '1's
      if(ch[i - 1] == '1'){
          ch[i] = '0';  // Add '0' at current position
          generate(ch, ans, n, i + 1);  // Recursively generate remaining positions
      }
  }

  // Driver code to test the function
  public static void main(String[] args) {
      int n = 3;  // Set the length of binary strings
      List<String> result = generateBinaryStrings(n);
      System.out.println(result); // Expected Output: [000, 001, 010, 100, 101]
  }
}

/*

Intuition and Thought Process
The goal of this code is to generate all binary strings of length n without consecutive 1s.

We achieve this using recursion by building the binary string character by character while ensuring:

If the last added character is 0, we can add either 0 or 1.

If the last added character is 1, we can add only 0 to avoid consecutive 1s.

Step-by-Step Thought Process
Use a recursive function (generate) to build the binary string.

Use a character array (ch) to store the binary string while constructing it.

Base case: If we reach the required length n, store the string in the list.

Recursive case: Decide whether to append 0 or 1 based on the last character.



Recursion Tree for n = 3

       ""
     /    \
    0      1
   / \      |
 00   01    10
/  \    |     \
000  001  010   100
              |
             101

*/