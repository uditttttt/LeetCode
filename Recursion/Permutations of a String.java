Simple recursion

class Solution {
    public static void solve(String ip, String op, List<String> ans) {
        // Base case: if input string is empty, add the current permutation to the result list
        if (ip.length() == 0) {
            ans.add(op);
            return;
        }

        // HashSet to track characters that have already been used at the current level
        HashSet<Character> set = new HashSet<>();

        // Loop through each character in the input string
        for (int i = 0; i < ip.length(); i++) {
            char ch = ip.charAt(i); // Current character to process

            // Check if the character has already been used in this recursion level
            if (!set.contains(ch)) {
                set.add(ch); // Mark character as used

                // Generate the remaining input string by removing the current character
                String newip = ip.substring(0, i) + ip.substring(i + 1);

                // Add the current character to the output string
                String newop = op + ch;

                // Recursively solve for the remaining input and updated output
                solve(newip, newop, ans);
            }
        }
    }

    public List<String> findPermutation(String s) {
        List<String> ans = new ArrayList<>(); // List to store the final permutations

        // Convert the input string to a character array and sort it
        // Sorting ensures that the permutations are generated in lexicographical order
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        // Call the recursive solve function with the sorted string, empty output string, and result list
        solve(new String(chars), "", ans);

        return ans; // Return the list of permutations
    }


Backtracking
class Solution {
    public void swap(StringBuilder s, int start, int i) {
        char temp = s.charAt(start);
        s.setCharAt(start, s.charAt(i));
        s.setCharAt(i, temp);
    }

    public void solve(StringBuilder s, int start, List<String> ans) {
        if (start == s.length() - 1) {
            ans.add(s.toString());
            return;
        }

        HashSet<Character> set = new HashSet<>();
        for (int i = start; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) { // for case string = aab
                set.add(s.charAt(i));
                // Swap characters
                swap(s, start, i); // changes
                // Recur for the next character
                solve(s, start + 1, ans); // fn call
                // Backtrack by swapping back
                swap(s, start, i); // reverse changes
            }
        }
    }

    public List<String> findPermutation(String s) {
        // Sort the input string to ensure lexicographical order of permutations
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder(new String(chars));
        solve(sb, 0, ans);

        // Sorting ensures lexicographical order (if not already handled)
        Collections.sort(ans);
        return ans;
    }
}


Initial Input
•	Input string: "ABC"
•	Call: findPermutation("ABC")
•	After sorting: "ABC"
•	Start recursion: solve("ABC", start = 0, ans = []).
________________________________________
Recursive Execution
________________________________________
Step 1: start = 0
•	Current string: "ABC"
•	For loop (i = 0 to 2):
1.	Iteration 1 (i = 0):
o	Swap s[0] with s[0]: "ABC"
o	Recursive call: solve("ABC", start = 1, ans = []).
________________________________________
Step 2: start = 1 (Branch 1: "ABC")
•	Current string: "ABC"
•	For loop (i = 1 to 2):
1.	Iteration 1 (i = 1):
o	Swap s[1] with s[1]: "ABC"
o	Recursive call: solve("ABC", start = 2, ans = []).
________________________________________
Step 3: start = 2 (Branch 1.1: "ABC")
•	Current string: "ABC"
•	Base case reached (start == s.length() - 1).
•	Add "ABC" to ans: ans = ["ABC"].
•	Backtrack: No swap to undo since s[1] was swapped with itself.
________________________________________
Back to Step 2: start = 1 (Branch 1: "ABC")
2.	Iteration 2 (i = 2):
o	Swap s[1] with s[2]: "ACB"
o	Recursive call: solve("ACB", start = 2, ans = ["ABC"]).
________________________________________
Step 3: start = 2 (Branch 1.2: "ACB")
•	Current string: "ACB"
•	Base case reached (start == s.length() - 1).
•	Add "ACB" to ans: ans = ["ABC", "ACB"].
•	Backtrack: Undo swap (s[1] with s[2]), restoring "ABC".
________________________________________
Back to Step 1: start = 0
2.	Iteration 2 (i = 1):
o	Swap s[0] with s[1]: "BAC"
o	Recursive call: solve("BAC", start = 1, ans = ["ABC", "ACB"]).
________________________________________
Step 2: start = 1 (Branch 2: "BAC")
•	Current string: "BAC"
•	For loop (i = 1 to 2):
1.	Iteration 1 (i = 1):
o	Swap s[1] with s[1]: "BAC"
o	Recursive call: solve("BAC", start = 2, ans = ["ABC", "ACB"]).
________________________________________
Step 3: start = 2 (Branch 2.1: "BAC")
•	Current string: "BAC"
•	Base case reached (start == s.length() - 1).
•	Add "BAC" to ans: ans = ["ABC", "ACB", "BAC"].
•	Backtrack: No swap to undo since s[1] was swapped with itself.
________________________________________
Back to Step 2: start = 1 (Branch 2: "BAC")
2.	Iteration 2 (i = 2):
o	Swap s[1] with s[2]: "BCA"
o	Recursive call: solve("BCA", start = 2, ans = ["ABC", "ACB", "BAC"]).
________________________________________
Step 3: start = 2 (Branch 2.2: "BCA")
•	Current string: "BCA"
•	Base case reached (start == s.length() - 1).
•	Add "BCA" to ans: ans = ["ABC", "ACB", "BAC", "BCA"].
•	Backtrack: Undo swap (s[1] with s[2]), restoring "BAC".
________________________________________
Back to Step 1: start = 0
3.	Iteration 3 (i = 2):
o	Swap s[0] with s[2]: "CBA"
o	Recursive call: solve("CBA", start = 1, ans = ["ABC", "ACB", "BAC", "BCA"]).
________________________________________
Step 2: start = 1 (Branch 3: "CBA")
•	Current string: "CBA"
•	For loop (i = 1 to 2):
1.	Iteration 1 (i = 1):
o	Swap s[1] with s[1]: "CBA"
o	Recursive call: solve("CBA", start = 2, ans = ["ABC", "ACB", "BAC", "BCA"]).
________________________________________
Step 3: start = 2 (Branch 3.1: "CBA")
•	Current string: "CBA"
•	Base case reached (start == s.length() - 1).
•	Add "CBA" to ans: ans = ["ABC", "ACB", "BAC", "BCA", "CBA"].
•	Backtrack: No swap to undo since s[1] was swapped with itself.
________________________________________
Back to Step 2: start = 1 (Branch 3: "CBA")
2.	Iteration 2 (i = 2):
o	Swap s[1] with s[2]: "CAB"
o	Recursive call: solve("CAB", start = 2, ans = ["ABC", "ACB", "BAC", "BCA", "CBA"]).
________________________________________
Step 3: start = 2 (Branch 3.2: "CAB")
•	Current string: "CAB"
•	Base case reached (start == s.length() - 1).
•	Add "CAB" to ans: ans = ["ABC", "ACB", "BAC", "BCA", "CBA", "CAB"].
•	Backtrack: Undo swap (s[1] with s[2]), restoring "CBA".
________________________________________
Back to Step 1: start = 0
•	Backtrack: Undo swap (s[0] with s[2]), restoring "ABC".
________________________________________
Final Output
The ans list contains all permutations:
css
Copy code
["ABC", "ACB", "BAC", "BCA", "CBA", "CAB"]
________________________________________
How the For Loop Works
•	At each level (start), the for loop swaps the character at start with all characters from start to the end (i = start to s.length()).
•	Each iteration of the loop explores one branch of the recursive tree.
________________________________________
How Backtracking Works
•	After a recursive call completes, the function undoes the swap made in that iteration.
•	This restores the string to its previous state, allowing the next loop iteration to explore other branches.
