class Solution {
    public char solve(StringBuilder s, int k) {
        // Base case: If the length of the string matches k, return the k-th character
        if (s.length() >= k) {
            return s.charAt(k - 1);
        }
        // Generate the next sequence by incrementing each character
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            ch++; // Increment character
            s1.append(ch);
        }
        // Append the new string to the existing string
        s.append(s1);
        // Recursive call
        return solve(s, k);
    }

    public char kthCharacter(int k) {
        // Start with the initial string "a"
        StringBuilder s = new StringBuilder("a");
        return solve(s, k);
    }
}


