class Solution {
    // Recursive helper function to generate all combinations
    public static void solve(List<String> ans, String digits, StringBuilder temp, HashMap<Integer, String> map, int idx) {
        // Base case: If the current index reaches the length of the digits string,
        // it means we have completed one combination
        if (idx == digits.length()) {
            ans.add(temp.toString()); // Add the current combination to the answer list
            return; // Exit the recursion
        }

        // Get the current digit as a character and convert it to an integer
        char c = digits.charAt(idx);
        int n = c - '0'; // Convert char '0'-'9' to corresponding integer value

        // Get the corresponding characters for the digit from the map
        StringBuilder s = new StringBuilder(map.get(n));

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            temp.append(s.charAt(i)); // Add the current character to the temporary combination
            solve(ans, digits, temp, map, idx + 1); // Recurse for the next digit
            temp.deleteCharAt(temp.length() - 1); // Backtrack by removing the last character
        }
    }

    // Main function to generate letter combinations
    public List<String> letterCombinations(String digits) {
        // If the input string is empty, return an empty list
        if (digits.length() == 0) return new ArrayList<>();

        List<String> ans = new ArrayList<>(); // List to store all combinations
        StringBuilder temp = new StringBuilder(); // Temporary string builder for the current combination

        // Mapping digits to their corresponding letters on a phone keypad
        HashMap<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        // Start the recursive process with index 0
        solve(ans, digits, temp, map, 0);

        // Return the list of all generated combinations
        return ans;
    }
}




Let’s visualize the recursion tree for the input digits = "23". Each node in the tree represents a recursive call, with the temp string being built at that point.

Input Details:
Digits: "23"
Map:
arduino
Copy code
2 → "abc"
3 → "def"
Recursion Tree Visualization:
sql
Copy code
Level 0: Start
  temp = ""

Level 1: Processing '2' → "abc"
  temp = "a"
     ↓
Level 2: Processing '3' → "def"
  temp = "ad" (Complete, Add to ans)
  temp = "ae" (Complete, Add to ans)
  temp = "af" (Complete, Add to ans)

  temp = "b"
     ↓
Level 2: Processing '3' → "def"
  temp = "bd" (Complete, Add to ans)
  temp = "be" (Complete, Add to ans)
  temp = "bf" (Complete, Add to ans)

  temp = "c"
     ↓
Level 2: Processing '3' → "def"
  temp = "cd" (Complete, Add to ans)
  temp = "ce" (Complete, Add to ans)
  temp = "cf" (Complete, Add to ans)
Detailed Visualization:
arduino
Copy code
                     ""
                /      |      \
              "a"     "b"     "c"
            / | \    / | \    / | \
         "ad""ae""af""bd""be""bf""cd""ce""cf"
How the Tree Expands:
Root Node (""):

Start with an empty temp string.
At index 0, digit 2 maps to "abc".
First Level:

Add each letter of "abc" (a, b, c) to temp.
Recurse to process the next digit (3).
Second Level:

For each letter from the first level (a, b, c):
Add each letter of "def" (d, e, f) to temp.
Recurse or finish if the combination length equals the input digits' length.
Leaf Nodes:

Complete combinations like "ad", "ae", "af", ..., "cf" are added to the result list.
Execution Path:
Start with temp = "".
Add a → Recurse → Add d → Combination "ad", Backtrack.
Add e → Combination "ae", Backtrack.
Add f → Combination "af", Backtrack.
Backtrack to temp = "", add b, repeat.
Final Output:
The traversal yields the combinations in the order:

plaintext
Copy code
[ad, ae, af, bd, be, bf, cd, ce, cf]
This tree structure demonstrates the recursive and backtracking process used to generate all combinations.






