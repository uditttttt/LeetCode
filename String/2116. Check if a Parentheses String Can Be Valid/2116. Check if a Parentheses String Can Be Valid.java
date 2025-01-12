class Solution {
    public boolean canBeValid(String s, String locked) {
        // If the length of the string is odd, it cannot be a valid parentheses string.
        if (s.length() % 2 != 0) return false;

        // First pass: Check validity from left to right
        int open = 0; // Keeps track of "net opening parentheses" (including unlocked characters)
        for (int i = 0; i < s.length(); i++) {
            // Treat '(' or unlocked characters ('0') as contributing to opening parentheses
            if (s.charAt(i) == '(' || locked.charAt(i) == '0') {
                open++;
            } else { 
                // Treat ')' as contributing to closing parentheses
                open--;
            }

            // If `open` goes negative, it means there are more ')' than '(' up to this point
            if (open < 0) return false;
        }

        // Second pass: Check validity from right to left
        int close = 0; // Keeps track of "net closing parentheses" (including unlocked characters)
        for (int i = s.length() - 1; i >= 0; i--) {
            // Treat ')' or unlocked characters ('0') as contributing to closing parentheses
            if (s.charAt(i) == ')' || locked.charAt(i) == '0') {
                close++;
            } else { 
                // Treat '(' as contributing to opening parentheses
                close--;
            }

            // If `close` goes negative, it means there are more '(' than ')' from this point to the end
            if (close < 0) return false;
        }

        // If both passes succeed, the string can be valid
        return true;
    }
}
