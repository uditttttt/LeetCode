class Solution {
    // Helper recursive method to generate all valid combinations of parentheses
    public void solve(int open , int close , String op , List<String> ans) {
        // Base case: If there are no more open or close parentheses to add
        if (close == 0 && open == 0) {
            // If both open and close counts are zero, we've built a valid combination
            // Add the current string (op) to the answer list
            ans.add(op);
            return;  // Return as we have finished one valid combination
        }

        // If there are still open parentheses left to add
        if (open != 0) {
            // Create a new string op1 by adding an open parenthesis to the current string
            String op1 = op;
            op1 = op1 + "(";
            // Recursively call the function with one less open parenthesis to add
            solve(open - 1, close, op1, ans);
        }

        // If there are more close parentheses to add than open ones, it's valid to close one
        if (close > open) {
            // Create a new string op2 by adding a close parenthesis to the current string
            String op2 = op;
            op2 = op2 + ")";
            // Recursively call the function with one less close parenthesis to add
            solve(open, close - 1, op2, ans);
        }
    }

    // Main function to generate parentheses combinations for a given number 'n'
    public List<String> generateParenthesis(int n) {
        // Initialize the count of open and close parentheses to 'n'
        int close = n;
        int open = n;
        // Start with an empty string for the current combination
        String op = "";
        // Initialize an empty list to store the valid combinations
        List<String> ans = new ArrayList<>();
        // Call the helper function to start generating the combinations
        solve(open, close, op, ans); 
        // Return the list of valid parentheses combinations
        return ans;
    }
}

